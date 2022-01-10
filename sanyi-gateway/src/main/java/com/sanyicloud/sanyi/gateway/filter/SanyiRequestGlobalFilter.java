/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sanyicloud.sanyi.gateway.filter;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.fastjson.JSONObject;
import com.sanyicloud.sanyi.common.core.constant.CommonConstants;
import com.sanyicloud.sanyi.common.core.exception.CheckedException;
import com.sanyicloud.sanyi.common.core.util.DateUtils;
import com.sanyicloud.sanyi.common.core.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p>
 * 全局拦截器，作用所有的微服务
 * 针对性 进行请求头效验, 对于需要传递 token 值的接口都需要进行效验
 * 并且 将其 传递到 下一个项目的 body 或者 query 中
 * <p>
 */
@Slf4j
@RequiredArgsConstructor
public class SanyiRequestGlobalFilter implements GlobalFilter, Ordered {

    @Value("${server.servlet.context-path}")
    private String CONTEXT_PATH;

    /**
     * Process the Web request and (optionally) delegate to the next {@code WebFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 清洗请求头中from 参数
        ServerHttpRequest request = exchange.getRequest().mutate().build();

        String rawPath = request.getURI().getRawPath();
        String newPath = "/" + Arrays.stream(StringUtils.tokenizeToStringArray(rawPath, "/"))
                .skip(CONTEXT_PATH.length())
                .collect(Collectors.joining("/"));
        // 是否效验 请求头中的 token 有效性
        if (!IgnoreUri.tokenUrl(newPath)) {
            return checkToken(exchange, chain);
        }
        log.info("白名单:{}", newPath);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1000;
    }

    private Mono<Void> checkToken(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        // 请求投 携带的 token
        String sanyiToken = headers.getFirst(CommonConstants.HEAD_TOKEN_KEY);
        if (org.apache.commons.lang3.StringUtils.isEmpty(sanyiToken)) {
            throw new CheckedException("鉴权失败");
        }
        String account_request = TokenUtils.decryptStr(sanyiToken);
        if (org.apache.commons.lang3.StringUtils.isEmpty(account_request)) {
            throw new CheckedException("鉴权失败");
        }
        String[] split = account_request.split("-");
        if (split.length != 2) {
            throw new CheckedException("鉴权失败");
        }
        String _request = split[1];
        // 正负 300秒内的 请求都算 可靠请求
        // 请求携带时间戳 -- 这个是客户端的，先出来，也就是他应该小
        long request_timestamp = Long.parseLong(_request);
        // UTC 时间的 时间戳 -- 这个是服务端的，后出来，他应该大
        long timestamp = DateUtils.getZoneOfMillis() / 1000;
        long difference = timestamp - request_timestamp;
        if (difference > 300 || difference < -300) {
            throw new CheckedException("请求超时");
        }
        return modifyRequest(exchange, chain, split[0]);
    }

    /**
     * 修改 请求 -- 包括 post 、get、put、del 等
     */
    private Mono<Void> modifyRequest(ServerWebExchange exchange, GatewayFilterChain chain, String accountId)
    {
        ServerHttpRequest request = exchange.getRequest();

        ServerRequest serverRequest = ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders());
        MediaType mediaType = exchange.getRequest().getHeaders().getContentType();
        // 不为 文件上传时 才进行修改 body 等
        if (!MediaType.MULTIPART_FORM_DATA.isCompatibleWith(mediaType)) {
            //重点
            Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
                //因为约定了终端传参的格式，所以只考虑json的情况，如果是表单传参，请自行发挥
                if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType) || MediaType.APPLICATION_JSON_UTF8.isCompatibleWith(mediaType)) {
                    JSONObject jsonObject;
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(body)) {
                        jsonObject = JSONObject.parseObject(body);
                    } else {
                        jsonObject = new JSONObject();
                    }
                    jsonObject.put(CommonConstants.ACCOUNT_ID, accountId);
                    return Mono.just(jsonObject.toJSONString());
                }
                return Mono.empty();
            });
            BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());
            headers.remove(HttpHeaders.CONTENT_LENGTH);
            CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
            return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
                URI newUri = getUri(request, accountId);
                return chain.filter(exchange
                        .mutate()
                        .request(
                                new ServerHttpRequestDecorator(exchange.getRequest()) {
                    public HttpHeaders getHeaders() {
                        long contentLength = headers.getContentLength();
                        HttpHeaders httpHeaders = new HttpHeaders();
                        httpHeaders.putAll(super.getHeaders());
                        if (contentLength > 0L) {
                            httpHeaders.setContentLength(contentLength);
                        } else {
                            httpHeaders.set("Transfer-Encoding", "chunked");
                        }
                        return httpHeaders;
                    }
                    public Flux<DataBuffer> getBody() {
                        return outputMessage.getBody();
                    }
                }
                                        .mutate()
                                        .uri(newUri)
                                        .build())
                        .build());
            }));
        }
        URI newUri = getUri(request, accountId);
        return chain.filter(exchange.mutate().request(request.mutate().uri(newUri).build()).build());
    }

    private URI getUri(ServerHttpRequest request, String accountId)
    {
        URI uri = request.getURI();
        String rawQuery = uri.getRawQuery();
        StringBuilder query = new StringBuilder();
        //无请求信息
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(rawQuery)) {
            query.append(rawQuery);
            query.append("&");
        }
        query.append(CommonConstants.ACCOUNT_ID)
                .append("=")
                .append(accountId);
        return UriComponentsBuilder.fromUri(uri).replaceQuery(query.toString()).build(true).toUri();
    }

    public static void main(String[] args) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, "sane_cloud_token".getBytes(StandardCharsets.UTF_8));
        String accountId = "3453403133521293411";
        long timestamp = DateUtils.getZoneOfMillis() / 1000;
        String s = aes.encryptHex(accountId + "-" + timestamp);
        System.out.println(s);
    }
}

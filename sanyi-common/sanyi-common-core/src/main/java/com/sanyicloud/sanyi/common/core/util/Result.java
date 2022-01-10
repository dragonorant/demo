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

package com.sanyicloud.sanyi.common.core.util;

import com.sanyicloud.sanyi.common.core.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 响应信息主体
 *
 * @author lengleng
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String msg;

	@Getter
	@Setter
	private Object data;

	public static Result ok() {
		return restResult(null, CommonConstants.SUCCESS_CODE, CommonConstants.SUCCESS_MSG);
	}

	public static  Result ok(Object data) {
		return restResult(data, CommonConstants.SUCCESS_CODE, CommonConstants.SUCCESS_MSG);
	}

	public static  Result ok(Object data, String msg) {
		return restResult(data, CommonConstants.SUCCESS_CODE, CommonConstants.SUCCESS_MSG);
	}

	public static  Result failed() {
		return restResult(null, CommonConstants.FAIL_CODE, CommonConstants.FAIL_MSG);
	}

	public static  Result failed(String msg) {
		return restResult(null, CommonConstants.FAIL_CODE, msg);
	}

	public static  Result failed(Object data) {
		return restResult(data, CommonConstants.FAIL_CODE, CommonConstants.FAIL_MSG);
	}

	public static  Result failed(Object data, String msg) {
		return restResult(data, CommonConstants.FAIL_CODE, msg);
	}

	private static  Result restResult(Object data, int code, String msg) {
		Result apiResult = new Result();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}

	public static ResultMap responseData(){
		return new ResultMap();
	}

	public static class ResultMap {
		private final Map<String, Object> resultMap = new HashMap<>();

		public ResultMap addParam(String key, Object value){
			this.resultMap.put(key, value);
			return this;
		}

		public ResultMap addParams(Map<String, Object> params) {
			this.resultMap.putAll(params);
			return this;
		}

		public Result build(){
			return Result.ok(this.resultMap);
		}

	}

}

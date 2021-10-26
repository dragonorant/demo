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

package com.sanyicloud.sanyi.common.core.constant;

/**
 * @author lengleng
 * @date 2020年01月01日
 * <p>
 * 缓存的key 常量
 */
public interface CacheConstants {
	/**
	 * 参数缓存
	 */
	String PARAMS_DETAILS = "params_details";
	/**
	 * 参数加密密钥 密钥:时间戳:随机字符串
	 */
	String PARAMS_CHECK_KEY = "sanyicloud:%s:%s";

}

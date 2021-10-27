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
package com.sanyicloud.sanyi.codegen.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sanyicloud.sanyi.codegen.entity.GenDatasourceConf;
import com.sanyicloud.sanyi.codegen.service.GenDatasourceConfService;
import com.sanyicloud.sanyi.common.core.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 数据源管理
 *
 * @author lengleng
 * @date 2019-03-31 16:00:20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dsconf")
public class GenDsConfController {

	private final GenDatasourceConfService datasourceConfService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param datasourceConf 数据源表
	 * @return
	 */
	@GetMapping("/page")
	public Result getSysDatasourceConfPage(Page page, GenDatasourceConf datasourceConf) {
		return Result.ok(datasourceConfService.page(page, Wrappers.query(datasourceConf)));
	}

	/**
	 * 查询全部数据源
	 * @return
	 */
	@GetMapping("/list")
	public Result list() {
		return Result.ok(datasourceConfService.list());
	}

	/**
	 * 通过id查询数据源表
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public Result getById(@PathVariable("id") Integer id) {
		return Result.ok(datasourceConfService.getById(id));
	}

	/**
	 * 新增数据源表
	 * @param datasourceConf 数据源表
	 * @return R
	 */
	@PostMapping
	public Result save(@RequestBody GenDatasourceConf datasourceConf) {
		return Result.ok(datasourceConfService.saveDsByEnc(datasourceConf));
	}

	/**
	 * 修改数据源表
	 * @param conf 数据源表
	 * @return R
	 */
	@PutMapping
	public Result updateById(@RequestBody GenDatasourceConf conf) {
		return Result.ok(datasourceConfService.updateDsByEnc(conf));
	}

	/**
	 * 通过id删除数据源表
	 * @param id id
	 * @return R
	 */
	@DeleteMapping("/{id}")
	public Result removeById(@PathVariable Integer id) {
		return Result.ok(datasourceConfService.removeByDsId(id));
	}

}

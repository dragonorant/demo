package com.sanyicloud.sanyi.common.mybatis.base;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 抽象实体
 *
 * @author lengleng
 * @date 2021/8/9
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
	/**
	 * 创建时间
	 */
	@TableField(value = "create_time",fill = FieldFill.INSERT)
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
	private LocalDateTime updateTime;
	/**
	 * 删除状态
	 */
	@TableLogic(value = "false",delval = "true")
	private Boolean delState;

	protected static final long serialVersionUID = 1L;

}

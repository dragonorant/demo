package com.sanyicloud.account.entity.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * by zhaowenyuan create 2022/1/25 14:15
 */
@Data
public class DeviceBO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 设备编号
     */
    @NotBlank(message = "设备编号不能为空")
    private String deviceNum;
    /**
     * 设备类型
     */
    @NotNull(message = "设备类型不能为空")
    private Short deviceType;
}

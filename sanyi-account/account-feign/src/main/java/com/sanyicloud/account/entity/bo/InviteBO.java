package com.sanyicloud.account.entity.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * by zhaowenyuan create 2022/1/25 15:55
 * 推广 码邀请
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InviteBO extends DeviceBO {

    /**
     * 账号id -- 当前的账号id -- 可能为空
     * 此账号 id 应当为本机 即 填写人的账号id , 但是填写人 之前可能未进行注册, 所以不会存在 accountId , 则 此时需要进行 通过
     * 设备信息 获取 accountId 进行注册 并 进行邀请信息的绑定
     * 如果 不为空, 则需要验证是否为 设备信息 加密后的 accountId, 如不是, 则 直接拒绝此次请求。因为 其属于 无效请求 403
     *                                                     如 是, 则在数据库中进行 查询, 看是否注册,
     *                                                      如未注册, 进行注册, 如注册,
     *                                                          进行邀请信息的绑定,   200
     */
    private String accountId;
    /**
     * 推广码
     */
    private String promoCode;

}

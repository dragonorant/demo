package com.sanyicloud.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sanyicloud.account.entity.po.SanyiAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * by zhaowenyuan create 2021/12/29 14:31
 */
@Mapper
public interface SanyiAccountMapper extends BaseMapper<SanyiAccount> {
    @Update("UPDATE `sanyi_account` SET `invite_num` = `invite_num` + 1 WHERE `account_id` = #{directInviterId}")
    void updateInviteNum(@Param("directInviterId") String directInviterId);

}

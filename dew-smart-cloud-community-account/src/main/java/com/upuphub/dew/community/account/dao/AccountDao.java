package com.upuphub.dew.community.account.dao;

import com.upuphub.dew.community.account.bean.po.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountDao {
    Integer insertAccountLogin(@Param("login") AccountLoginPO login);

    Integer insertAccountUin(@Param("uin") AccountUinPO uin);

    Integer insertAccountTrack(@Param("track") AccountTrackPO track);

    AccountLoginPO selectAccountLoginRecordByKey(@Param("loginKey") String LoginKey);

    List<AccountLoginPO> selectAccountLoginRecordByUin(@Param("uin") Long uin);

    AccountUinPO selectAccountUinByUin(@Param("uin") Long uin);

    AccountBasicPO selectAccountBasicInfoWithKeyAndPwd(@Param("loginKey") String loginKey,
                                                       @Param("password") String password);

    AccountTrackPO selectAccountLastedLoginInfoByUin(@Param("uin") Long uin);

    Integer updateAccountLoginStatus(@Param("uin") long uin, @Param("idType") String idType, @Param("status") String status);


    Integer updateAccountPassword(@Param("uin") long uin, @Param("email") String email, @Param("password") String password);
}

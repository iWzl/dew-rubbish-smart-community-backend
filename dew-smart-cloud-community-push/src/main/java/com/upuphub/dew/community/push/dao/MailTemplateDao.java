package com.upuphub.dew.community.push.dao;

import com.upuphub.dew.community.push.bean.po.MailTemplatePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MailTemplateDao {
    /**
     * 根据模板ID查询邮件模板信息
     *
     * @param templateCode 邮件模板Code
     * @return 查询到的模板属性信息
     */
    MailTemplatePO fetchMailTemplateByTemplateCode(@Param("templateCode") String templateCode);
}

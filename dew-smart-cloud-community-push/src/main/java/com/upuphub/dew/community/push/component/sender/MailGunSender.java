package com.upuphub.dew.community.push.component.sender;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.upuphub.dew.community.connection.constant.PushConst;
import com.upuphub.dew.community.push.bean.po.MailTemplatePO;
import com.upuphub.dew.community.push.dao.MailTemplateDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Mall Gun 邮件服务的发送支持相关信息
 */
@Component
public class MailGunSender {
    @Resource
    private MailTemplateDao mailTemplateDao;

    private static final String MAIL_GUN_KEY_API = "api";
    private static final String MAIL_GUN_KEY_FROM = "from";
    private static final String MAIL_GUN_KEY_TO = "to";
    private static final String MAIL_GUN_KEY_SUBJECT = "subject";
    private static final String MAIL_GUN_KEY_TAG = "o:tag";
    private static final String MAIL_GUN_KEY_VARIABLES = "recipient-variables";

    private static final String MAIL_FORM_TEMPLATE = "%s<%s@%s>";

    @Value("${push.mailgun.api-key}")
    private static String mailGunApiKey;
    @Value("${push.mailgun.domain}")
    private static String mailDomainName;
    @Value("${push.mailgun.url}")
    private static String mailGunUrl;


    /**
     * 使用邮件ID和单个发送邮件相关信息
     *
     * @param email                邮件地址
     * @param templateCode         邮件需要的发送的模板Code
     * @param replaceParametersMap 邮件模板中的需要替代的模板参数
     */
    public Integer sendEmailWithTemplateCode(String email, String templateCode, Map<String, String> replaceParametersMap) {
        MailTemplatePO mailTemplate = mailTemplateDao.fetchMailTemplateByTemplateCode(templateCode);
        if (null == mailTemplate) {
            return PushConst.ERROR_CODE_TEMPLATE_NOT_EXISTS;
        }
        Future<HttpResponse<JsonNode>> request = Unirest.post(String.format(mailGunUrl, mailDomainName))
                .basicAuth(MAIL_GUN_KEY_API, mailGunApiKey)
                .field(MAIL_GUN_KEY_FROM, String.format(MAIL_FORM_TEMPLATE, mailTemplate.getSender(), mailTemplate.getFrom(), mailDomainName))
                .field(MAIL_GUN_KEY_TO, email)
                .field(MAIL_GUN_KEY_SUBJECT, mailTemplate.getSubject())
                .field(MAIL_GUN_KEY_TAG,mailTemplate.getTag())
                .field(MAIL_GUN_KEY_VARIABLES,replaceParametersMap)
                .field(mailTemplate.getType(), mailTemplate.getTemplate())
                .asJsonAsync();
        return PushConst.ERROR_CODE_SUCCESS;
    }
}

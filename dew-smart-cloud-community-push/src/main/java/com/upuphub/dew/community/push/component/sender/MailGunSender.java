package com.upuphub.dew.community.push.component.sender;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Mall Gun 邮件服务的发送支持相关信息
 */
@Component
public class MailGunSender {

    private static final String MAIL_GUN_KEY_ID      = "id";
    private static final String MAIL_GUN_KEY_URL     = "url";
    private static final String MAIL_GUN_KEY_API     = "api";
    private static final String MAIL_GUN_KEY_FROM    = "from";
    private static final String MAIL_GUN_KEY_TO      = "to";
    private static final String MAIL_GUN_KEY_SUBJECT = "subject";
    private static final String MAIL_GUN_KEY_TAG     = "o:tag";
    private static final String MAIL_GUN_KEY_VARIABLES = "recipient-variables";

    private static final String MAIL_FORM_TEMPLATE = "%s<%s@%s>";

    @Value("${push.mailgun.api-key}")
    private static String mailGunApiKey = "";
    @Value("${push.mailgun.domain}")
    private static String mailDomainName = "";
    @Value("${push.mailgun.url}")
    private static String mailGunUrl = "";

    public static JsonNode sendSimpleMessage() throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post(String.format(mailGunUrl,mailDomainName))
                .basicAuth(MAIL_GUN_KEY_API, mailGunApiKey)
                .field(MAIL_GUN_KEY_FROM, String.format(MAIL_FORM_TEMPLATE,"垃圾智慧处理云社区开发者团队","developers",mailDomainName))
                .field(MAIL_GUN_KEY_TO, "1178696130@qq.com")
                .field(MAIL_GUN_KEY_SUBJECT, "您好,宝贝小可爱!")
/*                .field(MAIL_GUN_KEY_TAG,"")
                .field(MAIL_GUN_KEY_VARIABLES,"")*/
                .field("text", "欲系青春，少住春还去。")
                .asJson();
        return request.getBody();
    }

    public static void main(String[] args) throws UnirestException {
        System.out.println(sendSimpleMessage());
    }

}

package com.upuphub.dew.community.push.component.sender;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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


    private static String mailGunApiKey = "03f60638f75fcac2879af85d43642a43-aa4b0867-4da10346";
    private static String mailDomainName = "support.upuphub.com";
    private static String mailGunUrl = "https://api.mailgun.net/v3/%s/messages";

    public static JsonNode sendSimpleMessage() throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post(String.format(mailGunUrl,mailDomainName))
                .basicAuth(MAIL_GUN_KEY_API, mailGunApiKey)
                .field(MAIL_GUN_KEY_FROM, String.format(MAIL_FORM_TEMPLATE,"垃圾智慧处理云社区开发者团队","developers",mailDomainName))
                .field(MAIL_GUN_KEY_TO, "994318935@qq.com")
                .field(MAIL_GUN_KEY_SUBJECT, "您好,Android开发者!")
/*                .field(MAIL_GUN_KEY_TAG,"")
                .field(MAIL_GUN_KEY_VARIABLES,"")*/
                .field("text", "您好:\n张少.\n\n欢迎加入垃圾智慧处理云社区开发者团队")
                .asJson();
        return request.getBody();
    }

    public static void main(String[] args) throws UnirestException {
        System.out.println(sendSimpleMessage());
    }

}

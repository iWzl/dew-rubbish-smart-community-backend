package com.upuphub.dew.community.push.config;

import com.google.protobuf.InvalidProtocolBufferException;
import com.upuphub.dew.community.connection.constant.MqttConst;
import com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage;
import com.upuphub.dew.community.push.service.MqttHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.Base64;
import java.util.Objects;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/13 18:08
 */

@Slf4j
@Configuration
public class MqttConfiguration {
    private static final byte[] WILL_DATA;

    static {
        WILL_DATA = "offline".getBytes();
    }

    /**
     * 订阅的bean名称
     */
    public static final String CHANNEL_NAME_IN = "mqttInboundChannel";
    /**
     * 发布的bean名称
     */
    public static final String CHANNEL_NAME_OUT = "mqttOutboundChannel";

    @Value("${push.mqtt.username}")
    private String username;

    @Value("${push.mqtt.password}")
    private String password;

    @Value("${push.mqtt.url}")
    private String url;

    @Value("${push.mqtt.producer.clientId}")
    private String producerClientId;

    @Value("${push.mqtt.producer.defaultTopic}")
    private String producerDefaultTopic;

    @Value("${push.mqtt.consumer.clientId}")
    private String consumerClientId;

    @Value("${push.mqtt.consumer.defaultTopic}")
    private String consumerDefaultTopic;

    @Autowired
    MqttHandlerService mqttHandlerService;


    /**
     * MQTT连接器选项
     *
     * @return {@link MqttConnectOptions}
     */
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，
        // 这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置连接的用户名
        options.setUserName(username);
        // 设置连接的密码
        options.setPassword(password.toCharArray());
        options.setServerURIs(StringUtils.split(url, ","));
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);
        // 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息。
        options.setWill("willTopic", WILL_DATA, 2, false);
        return options;
    }


    /**
     * MQTT客户端
     *
     * @return {@link MqttPahoClientFactory}
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    /**
     * MQTT信息通道（生产者）
     *
     * @return {@link MessageChannel}
     */
    @Bean(name = CHANNEL_NAME_OUT)
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /**
     * MQTT消息处理器（生产者）
     *
     * @return {@link MessageHandler}
     */
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_OUT)
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                producerClientId,
                mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(producerDefaultTopic);
        return messageHandler;
    }

    /**
     * MQTT消息订阅绑定（消费者）
     *
     * @return {@link MessageProducer}
     */
    @Bean
    public MessageProducer inbound() {
        // 可以同时消费（订阅）多个Topic
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        consumerClientId, mqttClientFactory(),
                        StringUtils.split(consumerDefaultTopic, ","));
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        // 设置订阅通道
        adapter.setOutputChannel(mqttInboundChannel());
        return adapter;
    }

    /**
     * MQTT信息通道（消费者）
     *
     * @return {@link MessageChannel}
     */
    @Bean(name = CHANNEL_NAME_IN)
    public MessageChannel mqttInboundChannel() {
        return new DirectChannel();
    }

    /**
     * MQTT消息处理器（消费者）
     *
     * @return {@link MessageHandler}
     */
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_IN)
    public MessageHandler handler() {
        return message -> {
            try {
                MqttMessage mqttMessage = MqttMessage.parseFrom(Base64.getDecoder().decode((String)message.getPayload()));
                if (MqttConst
                        .TOPIC_RBC_API_SVT.equals(Objects.requireNonNull(message.getHeaders().get("mqtt_receivedTopic")))) {
                    mqttHandlerService.sendEmailCode(mqttMessage.getPayload());
                }
                log.debug("Send Email {}", message.getPayload());
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        };
    }
}
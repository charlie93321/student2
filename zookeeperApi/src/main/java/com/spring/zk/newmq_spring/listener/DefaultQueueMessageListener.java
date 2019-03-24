package com.spring.zk.newmq_spring.listener;

import com.spring.zk.wechat.WechatUtil;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.Random;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/14  17:38]
 * @DESC:
 */
@Component("defaultListener")
public class DefaultQueueMessageListener implements SessionAwareMessageListener<ActiveMQTextMessage> {

    Random random=new Random(System.currentTimeMillis());

   /* @Override
    public void onMessage(ActiveMQTextMessage message) {


        TextMessage textMessage= (TextMessage) message;
        try {
            boolean isSuccess=WechatUtil.sendMessage("ZengXiaoYu","queue 接收到消息:"+textMessage.getText());
            message.acknowledge();

        } catch (JMSException e) {

        }

    }*/

    @Override
    public void onMessage(ActiveMQTextMessage message, Session session) throws JMSException {
        String info=message.getText();
        String userName="ZengXiaoYu";
        String content=info;
        if(StringUtils.isNotEmpty(info)) {
            String[] ns = info.split(":");
            if(ns!=null && ns.length==2){
                userName=ns[0];
                content=ns[1];
            }
        }

        try {
            boolean isSuccess =
                    WechatUtil.sendMessage(userName, "queue 接收到消息:" + content+"时间:"+System.currentTimeMillis());
            Thread.sleep(500);
            if (isSuccess) {
                message.acknowledge();
                session.commit();
            }
            else throw new Exception("消息接收失败");
        }catch (Exception e){
            WechatUtil.sendMessage("ZengXiaoYu", "消息接收失败,数据回滚");
            session.rollback();
        }
    }
}

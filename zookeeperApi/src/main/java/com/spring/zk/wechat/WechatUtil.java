package com.spring.zk.wechat;

import com.google.gson.Gson;

import java.io.IOException;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/14  19:00]
 * @DESC:
 */
public class WechatUtil {

    public static boolean sendMessage(String user,String message){
        WeChatMsgSend swx = new WeChatMsgSend();
        try {
            String token = swx.getToken(
                    "wwfaf01cf172ffe126",
                    "ufWpzvyiRSucaUtj7MUWHLi4SVt33IcAFXWuCikZYP8");

        String postdata = swx.createpostdata(user, "text", 1000002, "content",message);
        String resp = swx.post("utf-8", WeChatMsgSend.CONTENT_TYPE,(new WeChatUrlData()).getSendMessage_Url(), postdata, token);

            WechatResp wechatResp=new Gson().fromJson(resp,WechatResp.class);
         return wechatResp.isOk();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

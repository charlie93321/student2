package com.spring.zk.wechat;

public class Test {
 
    public static void main(String[] args) {

       boolean isSuccess=WechatUtil.sendMessage("ZengXiaoYu","我是大坏蛋");

       if(isSuccess){
           System.out.println("发送消息成功");
       }else    System.out.println("发送消息失败");

    }
}
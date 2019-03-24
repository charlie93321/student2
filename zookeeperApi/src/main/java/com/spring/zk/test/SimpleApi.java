package com.spring.zk.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.zookeeper.ZooDefs.Ids.ANYONE_ID_UNSAFE;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/12  19:39]
 * @DESC: zookeeper 的简单的API
 */
public class SimpleApi {
    // 会话超时时间，设置为与系统默认时间一致
    private    final Integer SESSION_TIMEOUT=30000;

    private final String CONNECTION_STRING="hadoop01:5888,hadoop02:5888,hadoop03:5888";
    /**
     * 查看某个节点的 子节点名称列表
     *
     *
     *
     *
     */
    @Test
    public void test_getChildren(){
        ZooKeeper zookeeper=null;
        try {
             zookeeper=new ZooKeeper(CONNECTION_STRING,SESSION_TIMEOUT,null);

             List<String> list=zookeeper.getChildren("/",null);

            for (String node : list) {
                 byte[] data=zookeeper.getData("/"+node,null,null);
                 System.out.println(new String(data));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(null!=zookeeper) zookeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test2(){
        ZooKeeper zookeeper=null;
        try {
            zookeeper=new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, event -> {
                    System.out.println("监听开始");
                    System.out.println("监听路径"+event.getPath()+"的"+event.getType()+"事件"
                    +"状态为:"+event.getState()
                    );
            });


            zookeeper.getData("/test1",event -> {
                System.out.println("监听开始");
                System.out.println("监听路径"+event.getPath()+"的"+event.getType()+"事件"
                        +"状态为:"+event.getState());
                },null);


            zookeeper.setData("/test1","我是中国人".getBytes("utf-8"),-1);



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(null!=zookeeper) zookeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private ZooKeeper zk=null;

    void  init() throws IOException {

            zk=new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, event -> {
                System.out.println("监听开始");
                System.out.println("监听路径"+event.getPath()+"的"+event.getType()+"事件"
                        +"状态为:"+event.getState()
                );
            });
    }

    void release(){

        try {
            if(null!=zk)zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void lsdir(){
            try{
                init();

                lsr("/");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                release();
            }
    }

    private void lsr(String path) throws KeeperException, InterruptedException {
         List<String> list=zk.getChildren(path,null);
         if(null==list || list.isEmpty())return;

         StringBuilder sb=new StringBuilder(path+"的子节点:[");
        for (String childNodeName : list) {
             String childUrl="";
              if("/".equals(path))
                        childUrl="/"+childNodeName;
              else childUrl=path+"/"+childNodeName;
             sb.append(childNodeName).append(",");
             lsr(childUrl);
        }
        String result=sb.append("]").toString().replace(",]","]");
        System.out.println(result);

    }

    @Test
    public void createAclNode(){
        try {
            init();


            /**
             *
             *  ip:192.168.8.172:crwda
             *
             *
             *  ZooKeeper提供了如下几种验证模式（scheme）：
             *
             *     digest：Client端由用户名和密码验证，譬如user:password，digest的密码生成方式是Sha1摘要的base64形式
             *     auth：不使用任何id，代表任何已确认用户。
             *     ip：Client端由IP地址验证，譬如172.2.0.0/24
             *     world：固定用户为anyone，为所有Client端开放权限(默认)
             *     super：在这种scheme情况下，对应的id拥有超级权限，可以做任何事情(cdrwa）
             *
             *
             */
            Id digest_id = new Id("digest",
                    DigestAuthenticationProvider.generateDigest("admin:admin"));


            Id ip_id=new Id("ip","192.168.8.172:crwda");


            Id world_id=new Id("world","anyone");


            /**
             * auth 方案
             * addauth digest <user>:<password> #添加认证用户
             *
             * setAcl <path> auth:<user>:<acl>
             */
            zk.addAuthInfo("auth",data(""));
            List<ACL> acls=Arrays.asList(new ACL(ZooDefs.Perms.READ, digest_id));


            zk.create("/test9_1",data("永久授权访问节点"),acls, CreateMode.PERSISTENT);


            /**
             * 设置acl 权限认证
             */
            //zk.addAuthInfo("",data(""));




        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release();
        }
    }

    private byte[] data(String message) throws UnsupportedEncodingException {

        return message.getBytes("utf-8");
    }


}

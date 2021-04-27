package com.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : huang
 * @date :  2021/2/3
 * @time : 14:58
 * To change this template use File | Settings | File and Code Templates.
 */
public class RabbitMQUtil {

    private static Connection connection = null;

    public static void  init(String host, Integer port,String username,String pw,String vHost){
        getConnect(host,port,username,pw,vHost);
    }

    private static Connection getConnect(String host, Integer port,String username,String pw,String vHost) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(pw);
        connectionFactory.setVirtualHost(vHost);
        //1、获取连接
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Channel getChannel(){
        Channel channel=null;

        if(connection!=null){
            try {
                channel=connection.createChannel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return channel;
    }
}

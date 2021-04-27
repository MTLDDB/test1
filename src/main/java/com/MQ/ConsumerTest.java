package com.MQ;


import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;
import com.utils.RabbitMQUtil;
import textACK.DBhelper;
import textACK.Person;

import java.io.IOException;

import static textACK.Consumer1.unzip;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : huang
 * @date :  2021/2/3
 * @time : 16:26
 * To change this template use File | Settings | File and Code Templates.
 */
public class ConsumerTest {

    private static Channel channel=null;
    public static void main(String[] args) {
        String post="localhost";
        int port=5672;
        String username="ameyamq";
        String password="ameyads";
        String vhost="/";
        RabbitMQUtil.init(post,port,username,password,vhost);
        channel=RabbitMQUtil.getChannel();
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println(new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        try {
            channel.basicConsume("queue_name1",false,consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

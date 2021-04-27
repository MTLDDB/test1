package textACK;


import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;


public class Producer {
       // private final static String EXCHANGE_NAME = "direct_exchange";
        //private final static String QUEUE_NAME = "add";

    public static byte[] zip(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
        public static void main(String[] args) throws Exception {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("localhost");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            connectionFactory.setVirtualHost("/");
            //1、获取连接
            Connection connection = connectionFactory.newConnection();
            //2、声明信道
            Channel channel = connection.createChannel();
            //3、声明交换器，类型为direct

            String EXCHANGE_NAME = "exchange.direct";
            String QUEUE_NAME = "queue_name";
            String ROUTING_KEY = "key";
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);



           // channel.exchangeDeclare(EXCHANGE_NAME, "direct");
           // channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //4、创建消
            Map<String,String> map=new HashMap<>();
            String t="µ°±®™";
            map.put("test",t);
            String map_json=JSON.toJSONString( map);
            Person message1=new Person();
            message1.setName(map_json);
            byte[] message = zip(JSON.toJSONString(message1),"UTF-8");//ISO-8859-1
            //5、发布消息
            System.out.println(" 生产者"+"UTF-8");

            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message);
            System.out.println("生产者发送" + message + map.get("test"));
            //6、关闭通道
            channel.close();
            //7、关闭连接
            connection.close();
        }
    }

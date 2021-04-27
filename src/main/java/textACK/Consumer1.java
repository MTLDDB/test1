package textACK;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;


public class Consumer1 {

   // private final static String QUEUE_NAME = "direct_queue_1";

   // private final static String EXCHANGE_NAME = "direct_exchange";
    private Channel channel=null;
    public static void main(String args[]) throws Exception {
        Consumer1 c= new Consumer1();
        c.con();
    }
    public static String unzip(byte[] b, String encoding) {
        if (b == null || b.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(b);

        try {
            GZIPInputStream gunzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(encoding);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  void con() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ameyamq");
        connectionFactory.setPassword("ameyads");
        connectionFactory.setVirtualHost("/");
        //1、获取连接
        Connection connection = connectionFactory.newConnection();
        //2、声明通道
        channel = connection.createChannel();
        //3、声明队列

        String EXCHANGE_NAME = "exchange.direct";
        String QUEUE_NAME = "queue_name";
        String ROUTING_KEY = "key";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //4、绑定队列到交换机，指定路由key为update
       // channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);
        //5、定义队列的消费者
        //QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //6、监听队列,手动返回完成状态
       // channel.basicConsume(QUEUE_NAME,false,queueingConsumer);
        //6、获取消息

         //   QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                   String b=unzip(body,"UTF-8");
                   // System.exit(0);
                    System.out.println(" 消费者1"+"UTF-8");
                    String message =b; //new String(body);
                    System.out.println(" 消费者1：" + message + "'");
                    Person message1= JSON.parseObject(message,Person.class);
                    DBhelper.add(message1.getName());
                    //返回确认状态
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            };
        channel.basicConsume(QUEUE_NAME,false,consumer);
            //消费者1接收一条消息后休眠10毫秒
            Thread.sleep(10);

    }

}
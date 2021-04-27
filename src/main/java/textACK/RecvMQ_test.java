package textACK;

import com.rabbitmq.client.*;
import test.proxyip.Ip;
import test.proxyip.ProxyIp;
import java.io.IOException;
import java.util.List;

public class RecvMQ_test {
    private final static String QUEUE_NAME = "Spider_Task_Queue_Test1";

    public static void main(String[] args) throws IOException, Exception {
        //Thread.currentThread().sleep(60*1000);
        ProxyIp pp=new ProxyIp();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("101.231.86.38");
        factory.setPort(5672);
        factory.setUsername("ameyamq");
        factory.setPassword("ameyads");
        factory.setVirtualHost("Spider");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
       // channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        channel.basicQos(1);//确认每次接受任务数为一个
        List<Ip> lists = pp.getIp();
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = GzipUtils.unzip(body);
                System.out.println(message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}


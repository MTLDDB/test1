package textACK;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class Consumr2  {
@Test
    public void testBasicConsumer1() throws Exception{
        ConnectionFactory  connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("localhost");
    connectionFactory.setPort(5672);
    connectionFactory.setUsername("guest");
    connectionFactory.setPassword("guest");
    connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        String EXCHANGE_NAME = "exchange.direct";
        String QUEUE_NAME = "queue_name";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "key");


//        GetResponse response = channel.basicGet(QUEUE_NAME, false);
//        byte[] body = response.getBody();
//        System.out.println(new String(body).toString());

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException, UnsupportedEncodingException {
                String message = new String(body, "UTF-8");
               // ByteArrayOutputStream out1= new ByteArrayOutputStream();
                ByteArrayInputStream in = new ByteArrayInputStream(body);
                File file = new File("C:\\Users\\PC\\Desktop\\student1.sql");
                if(!file.exists()){
                    file.createNewFile();
                }
                OutputStream out=new FileOutputStream(file,true);//如果文件不存在会自动创建
                try {
                    GZIPInputStream gunzip = new GZIPInputStream(in);
                    byte[] buffer = new byte[256];
                    int n;
                    while ((n = gunzip.read(buffer)) >= 0) {
                        out.write(buffer, 0, n);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(out.toString());
            }
       };
       // channel.basicConsume(QUEUE_NAME, true, consumer);

       // Thread.sleep(100000);
    }
}

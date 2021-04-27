package textACK;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.*;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPOutputStream;

public class Producer2  {
    @Test
    public void testBasicPublish() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory  connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String EXCHANGE_NAME = "exchange.direct";
        String QUEUE_NAME = "queue_name";
        String ROUTING_KEY = "key";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        File my = new File("C:\\Users\\PC\\Desktop\\student.sql");
        BufferedReader reader = null;
        String tempString= new String();
        try {
            reader = new BufferedReader(new FileReader(my));
            // 一次读入一行，直到读入null为文件结束
            while (reader.readLine() != null) {
                tempString +=  reader.readLine() +"\r\n";//加入换行符
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(tempString.getBytes("UTF-8"));
            gzip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( out.toByteArray());
        byte[] b=out.toByteArray();
        //String message = "Hello RabbitMQ:";
        for (int i = 4; i < 5; i++) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, b);//(b + i).getBytes("UTF-8"));
        }

        channel.close();
        connection.close();
    }
}

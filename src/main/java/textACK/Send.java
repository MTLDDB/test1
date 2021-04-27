package textACK;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : huang
 * @date :  2021/2/3
 * @time : 11:52
 * To change this template use File | Settings | File and Code Templates.
 */
import com.rabbitmq.client.*;


import java.io.IOException;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Send {

    static Long id = 0L;

    static TreeSet<Long> tags = new TreeSet<Long>();

    public static Long send(Channel channel,byte[] bytes) throws Exception{
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2).//delivery mode设置为2,持久化的一个条件
                contentEncoding("UTF-8").build();
        channel.basicPublish("exchange.test","tes.key",false,properties,bytes);
        return id++;
    }


    public static void main(String[] args) throws Exception{
        //建立连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ameyamq");
        connectionFactory.setPassword("ameyads");
        connectionFactory.setVirtualHost("/");
        //1、获取连接
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        //是当前的channel处于确认模式
        channel.confirmSelect();
        String EXCHANGE_NAME = "exchange.test";
        String QUEUE_NAME = "queue_name11";
        String ROUTING_KEY = "*.key";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC,true);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueDeclare("queue_name111", true, false, false, null);
        channel.queueDeclare("queue_name211", true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        channel.queueBind("queue_name11", EXCHANGE_NAME, ROUTING_KEY);
        channel.queueBind("queue_name211", EXCHANGE_NAME, ROUTING_KEY);


        //使当前的channel处于事务模式，与上面的使channel处于确认模式使互斥的
        //channel.txSelect();

        /**
         * deliveryTag 消息id
         * multiple 是否批量
         *      如果是true，就意味着，小于等于deliveryTag的消息都处理成功了
         *      如果是false，只是成功了deliveryTag这一条消息
         */
        channel.addConfirmListener(new ConfirmListener() {
            //消息发送成功并且在broker落地，deliveryTag是唯一标志符，在channek上发布的消息的deliveryTag都会比之前加1
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("=========deliveryTag==========");
                System.out.println("deliveryTag: "+deliveryTag);
                System.out.println("multiple: "+multiple);
                //处理成功发送的消息
                if(multiple){
                    //批量操作
                    for(Long _id:new TreeSet<Long>(tags.headSet(deliveryTag+1))){
                        tags.remove(_id);
                    }
                }else{
                    //单个确认
                    tags.remove(deliveryTag);
                }

                System.out.println("未处理的消息: "+tags.toString());
            }

            /**
             * deliveryTag 消息id
             * multiple 是否批量
             *      如果是true，就意味着，小于等于deliveryTag的消息都处理失败了
             *      如果是false，只是失败了deliveryTag这一条消息
             */
            //消息发送失败或者落地失败
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("===========handleNack===========`````");
                System.out.println("deliveryTag: "+deliveryTag);
                System.out.println("multiple: "+multiple);
            }
        });

        /**
         * 当Channel设置成confirm模式时，发布的每一条消息都会获得一个唯一的deliveryTag
         * deliveryTag在basicPublish执行的时候加1
         */

        for (int i = 0; i<100;i++){
            Long id = send(channel,"你的外卖已经送达".getBytes());
            tags.add(id);
        }

    }
}

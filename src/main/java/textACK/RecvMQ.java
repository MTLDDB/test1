//package textACK;
//
//import com.alibaba.fastjson.JSONObject;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.Connection;
//import java.io.IOException;
//import java.util.List;
//import java.util.Random;
//
//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Consumer;
//import com.rabbitmq.client.DefaultConsumer;
//import com.rabbitmq.client.Envelope;
//import SpiderTask;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.config.CookieSpecs;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import Ip;
//import ProxyIp;
//
//public class RecvMQ {
//    private final static String QUEUE_NAME = "Spider_Task_Queue1";
//
//    public static void main(String[] args) throws IOException, Exception {
//        //Thread.currentThread().sleep(60*1000);
//        ProxyIp pp=new ProxyIp();
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("ameyamq");
//        factory.setPassword("ameyads");
//        factory.setVirtualHost("Spider");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//        channel.basicQos(1);//确认每次接受任务数为一个
//        List<Ip> lists = pp.getIp();
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
//                                       byte[] body) throws IOException {
//                String message = GzipUtils.unzip(body);
//                SpiderTask task = JSONObject.parseObject(message,SpiderTask.class);
//                System.out.println(message);
//
//                //设置代理IP、端口、协议（请分别替换）
//
//                Ip ip=lists.get(new Random().nextInt(lists.size()));
//                System.out.println(ip.getIp());
//                System.out.println(ip.getPort());
//                System.out.println(ip.getHttp());
//                HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()), ip.getHttp());
//                //把代理设置到请求配置
//                RequestConfig defaultRequestConfig=null;
//                CloseableHttpClient httpclient=null;
//                try {
//                    defaultRequestConfig = RequestConfig.custom()
//                            .setConnectTimeout(10000)//设置连接超时时间
//                            .setSocketTimeout(5000)//设置读取超时时间
//                            .setCookieSpec(CookieSpecs.STANDARD)
//                            .setProxy(proxy)
//                            .build();
//
//                    //实例化CloseableHttpClient对象
//                    httpclient = HttpClients.custom()
//                            .setDefaultRequestConfig(defaultRequestConfig)
//                            .build();
//                }catch (Exception e){
//
//                }
//                //访问目标地址
//                HttpGet httpGet = new HttpGet(task.getUrl());
//                httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
//                httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
//                //请求返回
//                CloseableHttpResponse httpResp = httpclient.execute(httpGet);
//                try {
//                    //System.out.println(httpResp.getEntity());
//                    //String htmlbody = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
//                    //System.out.println(htmlbody);
//                    int statusCode = httpResp.getStatusLine().getStatusCode();
//                    if (statusCode == HttpStatus.SC_OK) {
//                        System.out.println("成功");
//                    }
//                } catch (Exception e) {
//
//                } finally {
//                    httpResp.close();
//                }
//            }
//
//        };
//        channel.basicConsume(QUEUE_NAME, true, consumer);
//    }
//
//}
//

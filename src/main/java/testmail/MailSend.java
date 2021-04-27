//package testmail;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.text.MessageFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
///**
// * @author: Ant
// * @Date: 2018/11/28 09:29
// * @Description:
// */
//public class MailSend {
//
//    private static String sendMessage = "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><title>这是一条有程序发送的邮件！！</title></head><body><h2>这已经是正文了</h2>这是第一个参数的内容：<br><code style='color: red'><b>{0}</b></code><br><br>这是第二个参数的内容：<br><code>{1}</code></body></html>";
//    private static String to = "404156918@qq.com";
//    private static final Logger logger = LoggerFactory.getLogger(MailSend.class);
//
//    public static InternetAddress[] Address(String to) {
//
//        //多个接收账号
//        String str = to;
//        InternetAddress[] address = null;
//        try {
//            List list = new ArrayList();//不能使用string类型的类型，这样只能发送一个收件人
//            String[] median = str.split(",");//对输入的多个邮件进行逗号分割
//            for (int i = 0; i < median.length; i++) {
//                list.add(new InternetAddress(median[i]));
//            }
//            address = (InternetAddress[]) list.toArray(new InternetAddress[list.size()]);
//
//        } catch (AddressException e) {
//            logger.error("未处理错误！",e);
//        }
//        return address;
//    }
//
//    public static void sendMessage(String reason,String exceptionCode) {
//        // 收件人电子邮箱
//        //String to = "891575283@qq.com,huilinliu@alliedelec.com.cn";
//
//        // 发件人电子邮箱
//        String from = "h183258161@126.com";
//
//        // 指定发送邮件的主机为 localhost
//        String host = "smtp.126.com";
//
//        // 获取系统属性
//        Properties properties = System.getProperties();
//
//        final String smtpPort = "465";
//        properties.setProperty("mail.smtp.port", smtpPort);
//        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
//
//        properties.setProperty("mail.smtp.socketFactory.port", smtpPort);
//
//        // 设置邮件服务器
//        properties.setProperty("mail.smtp.host", host);
//
//        properties.setProperty("mail.smtp.auth", "true");
//
//        //0.2确定权限（账号和密码）
//        Authenticator authenticator = new Authenticator() {
//            @Override
//            public PasswordAuthentication getPasswordAuthentication() {
//                //填写自己的163邮箱的登录帐号和授权密码，授权密码的获取，在后面会进行讲解。
//                return new PasswordAuthentication(from, "HUANGCHANG0724");
//            }
//        };
//
//        // 获取默认session对象
//        Session session = Session.getDefaultInstance(properties, authenticator);
//
//        try {
//            // 创建默认的 MimeMessage 对象
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: 头部头字段
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: 头部头字段
//            for (InternetAddress internetAddress : Address(to)) {
//                message.addRecipient(Message.RecipientType.TO,
//                        internetAddress);
//            }
//
//            // Set Subject: 头部头字段
//           message.setSubject("这是一条有程序发送的邮件！！");
//
//            // 设置消息体
//            message.setText("爬虫[1]意外终止！\n终止原因：代理IP获取失败。");
//
//            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
//            Multipart mainPart = new MimeMultipart();
//
//
//            // 创建一个包含HTML内容的MimeBodyPart
//            BodyPart html = new MimeBodyPart();
//            // 设置HTML内容
//            html.setContent(MessageFormat.format(sendMessage, reason,exceptionCode), "text/html; charset=utf-8");
//            mainPart.addBodyPart(html);
//
//            // 创建图片的一个表示用于显示在邮件中显示
//            MimeBodyPart img= new MimeBodyPart();
//            DataHandler dh = new DataHandler(new FileDataSource("C:\\Users\\PC\\Desktop\\我的\\狗头.jpg"));//图片路径
//            img.setDataHandler(dh);
//            img.setFileName(dh.getName());
//            mainPart.addBodyPart(img);
//
//            // 将MiniMultipart对象设置为邮件内容
//            message.setContent(mainPart);
//
//            // 发送消息
//            Transport.send(message, Address(to));
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        sendMessage("我是第一个参数","我是第二个参数\n");
//    }
//}

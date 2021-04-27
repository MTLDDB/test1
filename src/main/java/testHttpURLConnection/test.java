package testHttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class test {

    private final static Logger logger = LoggerFactory.getLogger(test.class);

    public static void main(String args[]) {

        test.verifyLoginInfo(Status.LIST_SUCCESS);
        System.out.println("hello");
    }


    public static boolean verifyLoginInfo(Status userName) {
//        String  []a={"1","2","3"};
//        try{
//            logger.info("Start to verify User [{}]", userName+a[3]);
//        }catch(Exception e){
//            logger.error(e.toString());
//        }
        //发送一个GET请求
        HttpURLConnection httpConn = null;
        BufferedReader in = null;
        String urlString = "http://wapi.http.cnapi.cc/index/index/get_my_balance?neek=47826&appkey=0abbeaf2cb6ef0a62f97d16005d486bd";//"http://web.http.cnapi.cc/index/index/get_my_balance?neek=47826&appkey=afe1ec91c3de033087fda76bb63684c0";
        URL url = null;
        StringBuilder content = new StringBuilder();//具有更高效率的字符串类
        System.out.println(urlString);
        try {
            url = new URL(urlString);
            httpConn = (HttpURLConnection) url.openConnection();
            // 建立实际的连接
            httpConn.connect();
            //读取响应
            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String tempStr = "";
                in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                while ((tempStr = in.readLine()) != null) {
                    content.append(tempStr);
                    System.out.println(content);
                }
                in.close();
                httpConn.disconnect();
            } else {
                httpConn.disconnect();
                throw new Exception("返回状态码不为200");
            }
        } catch (Exception e) {
            logger.error("记录完成任务数与IP余额失败，请求余额时出错", e);
        }
        return true;
    }

}

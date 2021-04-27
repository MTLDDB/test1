package zaikostoreApi;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CoreStaffHttpClient {
    public static void main(String[] args) {
        try {
            new CoreStaffHttpClient().getZaikoArrow("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getZaikoArrow(String str) throws IOException {
        str="https://api.zaikostore.com/apiservice/v1?key=8cc4c656-5a45-440d-b88f-42127f297746&sw=FDCD-37S(55)&fmt=json&pNo=1&lg=zh-cn";
        HttpGet httpget = new HttpGet(str);
        CloseableHttpResponse response = null;
        String content=null;
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(15000)//设置连接超时时间
                .setSocketTimeout(15000)//设置读取超时时间
                .setConnectionRequestTimeout(15000)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
        try {
            httpget.setConfig(defaultRequestConfig);
            httpget.setHeader("X-Requested-With", "XMLHttpRequest");
            httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
            response = client.execute(httpget);
            HttpEntity entity = response.getEntity(); // 获取返回实体
            content = EntityUtils.toString(entity, "utf-8");
            //System.out.println(content);
            response.close(); // 关闭流和释放系统资源
        } finally {
            if (response != null)
                response.close();
        }
        return content;
    }

}

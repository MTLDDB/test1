package test.arrowApi;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetArrowData {

    public String getApiArrow(String str) throws IOException {
       // String str="http://api.arrow.com/itemservice/v3/en/search/token?login=ameyaholding2&apikey=5f4a1376ff2daa15aebabc37ae073c85e9ca9a2849f9396e293f4f51d789c8e6&search_token=BM1422AGMV-ZE2";
        HttpGet httpget = new HttpGet(str);
        CloseableHttpResponse response = null;
        String content=null;
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)//设置连接超时时间
                .setSocketTimeout(10000)//设置读取超时时间
                .setConnectionRequestTimeout(10000)
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
            response.close(); // 关闭流和释放系统资源
        } finally {
            if (response != null)
                response.close();
        }
        return content;
    }
}

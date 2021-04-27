package textJsoup;


import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class text {
    static int i=1;
    public static void main(String args[]) throws IOException {
        Main m=new Main();

        String url="https://www.verical.com/products/electromechanical-692/audio-700/indicators-and-alerts-755/";
        CloseableHttpClient httpclient1 = HttpClients.createDefault(); // 创建httpclient实例
        HttpGet httpget = new HttpGet(url); // 创建httpget实例
        httpget.setHeader("X-Requested-With", "XMLHttpRequest");
        httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
        httpget.setHeader("cookie","ga_cid=1311839131.1574237234; _abck=07F577A1FB4506C8701259133FA0EC3A~0~YAAQI9o4fe91CH5uAQAAPujZhwKTJtzNHARDWiM2uCNVvP73MzMAP9xcU9r5Rmf0e3RHtNT0RwEXoadTqhC1xdJ+uxm7RIwjcn/Wpm24unJhnMg0WJNNdRc2Ma7HFy3zKAsbcG77qOTnQ6FAlzS8HcUupresnbVwWF6l9DBLf2kcpbu3i9LHhB8Wl9HYUeCn/Ot36uPsSy2ReQVnWh6XMSzqpyLnZFINdhImOBznbDjH3d7veRWgrNqOmLCgb+75oeizs7mlFNF6udEgGu413Bi3aNidP3vberOWtp9jEfYI71hIi/3OxJcqpeBiyiXLmOuP8RefLxA=~-1~-1~-1; _ga=GA1.2.1311839131.1574237234; _gid=GA1.2.1787990463.1574237234; liveagent_oref=https://www.baidu.com/baidu.php?sc.0s0000aPch_Smgbn9wSaPR2GTAng0-nBSpOrpCquXYQqBLXegThYJTr2Kqvb6zjql5EYZxqJmjOdmsC_fPQeppBis4A-z_1FAT069aRai5UFZCLrBDlrqEYBeMQvU64j8xTsTiGiFakR456_NNn75DvLb_y-VqFSIHYS34FNGClq7T22r_r86sQDgc59BX_wtyIBOPv15uu9mkFIlhS7ZSeckbzj.DD_NR2Ar5Od66lT-xH4IAo_eRkc6YG8X1Knh4tLELqrVW_olX_QrMAkke5ZJ1ods88ikvtAHkozUVLgV4gkwFh4TDdZ-H7al4P17xyZDkbtpqOHgEe2SrHo6hCOOSOOOH7Na9WWstxU9zxgjbSEKsTZ-qauMs_YRmTMd9Bs45o93OgKfYt_U_DY2T5Z4g_3_AZFY32AM-YG8x6Y3X5z9ks3TMH6CpXy7MHWuxJBstx-xIMk33TtnyXSuh2ejblT7jHzYD1pT5Z4xfhutQPZj3OjvNqrZkSZF3d4PvejblT7jHzs8BCFBCmX5zEhuB81OfhutQP7mhQn--MZWE_4X1Fzdn84TXGmuCyrM_lThwf.U1Yk0ZDqIhNzpyP9U0KspynqnfKY5gu-Th-Wmys0pyYqnW0Y0ATqUvNsT100Iybqmh7GuZN_UfKspyfqn6KWpyfqPj0d0AdY5HDsnHIxnH0krNtknjfv0AVG5H00TMfqnHDk0ANGujY0mhbqn7tkPH9xnW0Y0AdW5HD3n1c4njTLPW7xnH0snNtknjc0TgKGujYs0Z7Wpyfqn0KzuLw9u1Ys0A7B5HKxn0K-ThTqn0KsTjYknH0LPWDsnjnk0A4vTjYsQW0snj0snj0s0AdYTjYs0AwbUL0qn0KzpWYs0Aw-IWdsmsKhIjYs0ZKC5H00ULnqn0KBI1Ykn0K8IjYs0ZPl5fK9TdqGuAnqTZnVUhC0pywW5R42i-n0IZN15HDLPj6snHn4Pjm4P1TYnHD1n1c40ZF-TgfqnHRLPjc1P1czP1RvP0K1pyfqryF9njbvnH6snj0YuWmdu0KWTvYqrDD3P1fzwjcYPDf1PRcvr0K9m1Yk0ZK85H00TydY5H00Tyd15H00XMfqn0KVmdqhThqV5HKxn7ts0Aw9UMNBuNqsUA78pyw15HKxn7t1PjRkrj6z0ZK9I7qhUA7M5H00uAPGujYs0ANYpyfqQHD0mgPsmvnqn0KdTA-8mvnqn0KkUymqnHm0uhPdIjYs0A-1mvsqn0KlTAkdT1Ys0A7buhk9u1Yk0Akhm1Ys0AwWmvfq0AFY5H00ULfqn0KETMKY5H0WnanWnansc10Wna3snj0snj0WnanWn0KkgLmqna3LPNtsQW0sg108njKxna3snNtsQWcsg108n1c0mLFW5Hc1rjm&word=verical&ck=5257.10.128.409.149.648.169.636&shh=www.baidu.com&sht=02003390_42_hao_pg&us=1.0.1.0.1.301.0&bc=110101; dfp=0e84eefc1f8eef67a1f1f64ce237c795; alohomora=f82f5424-6781-472f-9fbe-a5c899a7aace; fidelius=fb91fbe0-1d43-4715-911c-989df7bc4948; liveagent_ptid=8ffa1699-d8eb-45e2-bc2a-d46129aa2892; experimentCoverageId=12; viewedPrivacyPolicyMessage=true; SERVERID=.31-81; bm_sz=83ECDDD6F353FFF10867A27BB844E76D~YAAQI9o4fUbGRX5uAQAA2Sa5iwWXRgcv1kRyqR0585zj3jPrt+3YoTSder3Tk0pEH5XxnrUu/WLBBO44jsSEgcl0sbqN4FO96Bsy/eg9xLZ5ByT+/5qIfG7zC0Qq+Zrnlw5HE2PvKiI2yOgp8UlVP4OovQwe1E8abQSyeOzIyvgDjaKr43rCsrtnH6JubC4hgg==; JSESSIONID=5008B8D3344B6AB470D06FF3368C2FCC; Hm_lvt_ca6ad841e2bc77818047639352d1f30d=1574237234,1574302196; Hm_lvt_aec5d04257e77a43bdc2b5236188aad0=1574237234,1574302196; liveagent_sid=2d83b075-4c83-4915-bbc1-6581c0fdad9c; liveagent_vc=3; Hm_lpvt_aec5d04257e77a43bdc2b5236188aad0=1574306280; Hm_lpvt_ca6ad841e2bc77818047639352d1f30d=1574306280; liveagent_invite_rejected_57370000000XZCc=true");
        CloseableHttpResponse response = httpclient1.execute(httpget); // 执行get请求

        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        response.close(); // 关闭流和释放系统资源
        //System.out.println(html);
        Document doc = Jsoup.parse(content); // 解析网页 得到文档对象
//
        Elements elements = doc.getElementsByTag("title");// 获取tag是title的所有DOM元素
//
        System.out.println(doc);
        Elements trs = doc.getElementsByClass("odd");
        List<IP> list =new ArrayList<IP>();
        for (Element tr:trs){
            IP i=new IP();
            i.setIp(tr.child(1).text());
            i.setPort(tr.child(2).text());
            i.setHttp(tr.child(5).text());
            System.out.println(i.getIp());
            System.out.println("_____________");
            list.add(i);
        }
    for(int i=0;i<list.size();i++){
        //设置代理IP、端口、协议（请分别替换）
        IP p=list.get(i);
        HttpHost proxy = new HttpHost(p.getIp(), Integer.parseInt(p.getPort()),p.getHttp());
        System.out.println(p.getIp());
        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .build();
        //实例化CloseableHttpClient对象
        CloseableHttpClient httpclient=null;
        CloseableHttpResponse httpResp=null;
        try {
            httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        //访问目标地址
        HttpGet httpGet = new HttpGet("https://www.baidu.com/");
            httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
        //请求返回
             httpResp = httpclient.execute(httpGet);
            int statusCode = httpResp.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                System.out.println("成功"+p.getIp()+" "+p.getPort()+" "+p.getHttp());

                httpResp.close();
            }
        } catch (Exception e) {
            //httpResp.close();
        } finally {
            //httpResp.close();
        }
    }
    }
    static class IP{
        public  String  ip;
        public  String port;
        public  String http;

        public String getHttp() {
            return http;
        }

        public void setHttp(String http) {
            this.http = http;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }
    }
}

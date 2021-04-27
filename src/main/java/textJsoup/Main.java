package textJsoup;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import test.arrowApi.ParserArrowJsonData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

import test.proxyip.Ip;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.*;

import java.net.*;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public void getPicture(String str) throws IOException {
//"https://wx2.sinaimg.cn/mw690/006RYJvjly1fmfk7c049vj30zk0qogq6.jpg
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        HttpGet httpget = new HttpGet(str);

        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)//设置连接超时时间
                .setSocketTimeout(10000)//设置读取超时时间
                .setProxy(proxy)
                .build();
        httpget.setConfig(defaultRequestConfig);
        httpget.setHeader("X-Requested-With", "XMLHttpRequest");
        httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");

        CloseableHttpResponse response = httpclient.execute(httpget, context);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println("Content-Type:" + entity.getContentType().getValue());
                InputStream inputStream = entity.getContent();
                //文件复制
                String mpn = "test";
                FileUtils.copyToFile(inputStream, new File("D:\\data\\susumu\\" + mpn + ".jpg"));
            }
        } finally {
            if (response != null)
                response.close();
        }
    }

    public void testCookie() {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            CookieStore cookieStore = new BasicCookieStore();
            httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            httpPost = new HttpPost("https://www.verical.com/");
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Map<String, String> map = new HashMap<>();
            map.put("JSESSIONID", "pp");
            map.put("cookie_user", "oo");
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
                httpPost.setEntity(entity);
            }
            httpClient.execute(httpPost);
            String JSESSIONID = null;
            String cookie_user = null;
            List<Cookie> cookies = cookieStore.getCookies();
            for (int i = 0; i < cookies.size(); i++) {
                if (cookies.get(i).getName().equals("JSESSIONID")) {
                    JSESSIONID = cookies.get(i).getValue();
                    System.out.println(JSESSIONID);
                }
                if (cookies.get(i).getName().equals("cookie_user")) {
                    cookie_user = cookies.get(i).getValue();
                    System.out.println(cookie_user);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Ip getIP() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        //String url = "http://webapi.http.zhimacangku.com/getip?num=1&type=2&yys=0&port=11&time=1&ts=1&lb=1&sb=0&pb=45&mr=1";
        String url = "http://webapi.http.zhimacangku.com/getip?num=1&type=2&pro=0&city=0&yys=0&port=11&time=1&ts=1&ys=0&cs=0&lb=1&sb=0&pb=4&mr=1&regions=";

        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("X-Requested-With", "XMLHttpRequest");
        httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
        CloseableHttpResponse response = null;
        Ip ip = null;
        try {
            response = httpclient.execute(httpget, context);
            HttpEntity entity = response.getEntity(); // 获取返回实体
            String content = EntityUtils.toString(entity, "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(content);
            if (jsonObject.getBoolean("success")) {
                JSONArray data = jsonObject.getJSONArray("data");
                ip = new Ip();
                ip.setIp(jsonObject.getString(""));
                JSONObject ip1 = data.getJSONObject(0);
                ip.setIp(ip1.getString("ip"));
                ip.setPort(ip1.getString("port"));
                System.out.println(ip.getIp() + "ll" + ip.getPort());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return ip;
    }

    public String test222(String str, Header[] headers) throws IOException {
        HttpGet httpget = new HttpGet(str);
        CloseableHttpResponse response = null;
        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)//设置连接超时时间
                .setSocketTimeout(20000)//设置读取超时时间
                .setConnectionRequestTimeout(20000)
                .setProxy(proxy)
                //.setCookieSpec(CookieSpecs.STANDARD)
                .build();
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();//.createDefault();
        try {

            // response.close(); // 关闭流和释放系统资源
        } finally {
            // if (response != null)
            //    response.close();
        }
        return null;
    }

    public String test(String str) throws IOException {
        str = "https://au.element14.com/c/audio-visual/speaker-components#";
        HttpGet httpget = new HttpGet(str);
        // HttpClientContext context = HttpClientContext.create();

        CloseableHttpResponse response = null;
        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)//设置连接超时时间
                .setSocketTimeout(20000)//设置读取超时时间
                .setConnectionRequestTimeout(20000)
                .setProxy(proxy)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
        CloseableHttpClient client1 = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();//.createDefault();
        try {
            httpget.setConfig(defaultRequestConfig);
            // httpget.setHeader(":authority", "www.avnet.com");
//            httpget.setHeader("Request Method","GET");
//            httpget.setHeader(":path", "/search/resources/store/715839035/productview/byCategory/3074457345616735233?searchType=100&profileName=Avn_findProductsByCategory_Summary_More_Ajax&searchSource=Q&storeId=715839035&catalogId=10001&langId=-1&contractId=4000000000000071008&responseFormat=json&pageSize=20&pageNumber=1&_wcf.search.internal.boostquery=obsoleteFlag:%22NO%22^599999.0+price_USD:{0.00001+TO+*}^499999.0+inStock:%22true%22^9000.0+newProductFlag:%22Yes%22^0.085+topSellerFlag:%22Yes%22^0.080+packageTypeCode:%22BKN%22^0.075&wt=json&userAction=false");
            //  httpget.setHeader(":scheme", "https");
//            httpget.setHeader("accept", "application/json, text/javascript, */*; q=0.01");
//            httpget.setHeader("accept-encoding", "gzip, deflate, br");
//            httpget.setHeader("accept-language", "zh-CN,zh;q=0.9");
//            httpget.setHeader("sec-fetch-dest", "empty");
//            httpget.setHeader("sec-fetch-mode", "cors");
//            httpget.setHeader("sec-fetch-site", "same-origin");
            //httpget.setHeader("content-type", "application/x-www-form-urlencoded");
            httpget.setHeader("x-requested-with", "XMLHttpRequest");
            httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
            // httpget.setHeader(HTTP.CONTENT_TYPE, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            // httpget.setHeader(HTTP.CONTENT_TYPE,"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");

            //  httpget.setHeader("Referer", "https://www.avnet.com/wps/portal/apac/");
            //  httpget.setHeader("Referer", "https://www.avnet.com/shop/us/c/connectors/connector-accessories/backshells/");
            httpget.setHeader("cookie", "dadadad");
            response = client.execute(httpget);
            HttpEntity entity = response.getEntity(); // 获取返回实体
            String content = EntityUtils.toString(entity, "utf-8");
            Document document = Jsoup.parse(content);
            Elements ths = document.select("th[class=header  hide-xsmall]");
            int l = 0;
            for (Element th : ths) {
                ++l;
                System.out.println(th.select("span[class=heading-text]").text() + "kkk" + l);
            }

            Element table = document.getElementById("SearchResultsGrid_grid").select("tbody").first();

            int size = 25;
            for (int i = 1; i <= size; i++) {
                String string = "tr[data-index=" + i + "]";//拼接tr的data-index属性
                Elements trs = table.select(string);
                if (trs.size() == 0) continue;
                Element tr = trs.first();
                Elements tds = tr.select("td[class=column hide-xsmall]");
                if (i != 1) continue;
                for (Element td : tds) {
                    // System.out.println(td.select("span").text()+"index"+i);
                    System.out.println(td);
                }
            }

//            Header[] headers=response.getAllHeaders();
//            System.out.println(JSON.toJSONString(headers));
//            String url="https://www.avnet.com/search/resources/store/715839035/productview/byCategory/3074457345616735233?searchType=100&profileName=Avn_findProductsByCategory_Summary_More_Ajax&searchSource=Q&storeId=715839035&catalogId=10001&langId=-1&contractId=4000000000000071008&responseFormat=json&pageSize=20&pageNumber=1 &_wcf.search.internal.boostquery=obsoleteFlag%3A%22NO%22%5E599999.0+price_USD%3A%7B0.00001+TO+*%7B%5E499999.0+inStock%3A%22true%22%5E9000.0+newProductFlag%3A%22Yes%22%5E0.085+topSellerFlag%3A%22Yes%22%5E0.080+packageTypeCode%3A%22BKN%22%5E0.075 &wt=json&userAction=false".replace(" ","");
//            HttpGet httpget1 = new HttpGet(url);
//            httpget1.setConfig(defaultRequestConfig);
//            //httpget1.setHeaders(headers);
//            httpget1.setHeader("x-requested-with", "XMLHttpRequest");
//           // httpget1.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
//            CloseableHttpResponse response1 = client1.execute(httpget1,context);
//            HttpEntity entity1 = response1.getEntity(); // 获取返回实体
//            String content1 = EntityUtils.toString(entity1, "utf-8");
//            System.out.println(content1);//            Map params=new HashMap();
//            params.put("searchType",100);
//            params.put("profileName","Avn_findProductsByCategory_Summary_More_Ajax");
//            params.put("searchSource","Q");
//            params.put("storeId","715839035");
//            params.put("catalogId","10001");
//            params.put("langId",-1);
//            params.put("contractId","4000000000000071008");
//            params.put("responseFormat","json");
//            params.put("pageSize",20);
//            params.put("pageNumber",1);
//            params.put("_wcf.search.internal.boostquery","obsoleteFlag%3A%22NO%22%5E599999.0+price_USD%3A%7B0.00001+TO+*%7B%5E499999.0+inStock%3A%22true%22%5E9000.0+newProductFlag%3A%22Yes%22%5E0.085+topSellerFlag%3A%22Yes%22%5E0.080+packageTypeCode%3A%22BKN%22%5E0.075");
//            params.put("wt","json");
//            params.put("userAction",false);


//            final AjaxHttpRequest ajax=new AjaxHttpRequest();
//
//            // 设置状态监听器,类似 XHR对象的 onreadystatechange 属性.
//            // 由于js 和java的本质区别 导致这里可能和 xhr有点不一样,但是应该不难理解.
//            ajax.setReadyStateChangeListener(
//                    // 监听器, 根据需求 实现onReadyStateChange方法即可.
//                    new AjaxHttpRequest.ReadyStateChangeListener(){
//                        public void onReadyStateChange() {
//                            int readyState = ajax.getReadyState();
//                            //判断状态 然后做出相应处理.
//                            if (readyState==AjaxHttpRequest.STATE_COMPLETE){
//                                System.out.println( ajax.getResponseText() );
//                                System.out.println(ajax.getRequestHeader("cookie"));
//                            }
//                        }
//                    }
//            );
//            ajax.open("get", url, true);
//            ajax.send(params);
            // response1.close();
            // response.close(); // 关闭流和释放系统资源
        } finally {
            if (response != null)
                response.close();
        }
        //return null;
        return null;
    }

    public String testElement(String str) {
        // str="https://au.element14.com/c/audio-visual/speaker-components#";
        // str="https://www.pasternack.cn/";
        // str="https://www.pasternack.com/90db-continuously-variable-sma-female-sma-female-5-watts-attenuator-pe7422-p.aspx";
        //str = "https://www.avnet.com/shop/FetchProductPrice?storeId=715839038&langId=-1&currency=USD&contractPriceReq=Y&catEntryIds=3074457345630773203,3074457345629901045,3074457345629903817,3074457345629907071,3074457345629907364,&_=1597996160047&userAction=false";
        // str="https://www.ickey.cn/detail/100300319865444/STM32F407VGT6.html";
        // str="https://www.ickey.cn/detail/get-sku-info?sku=100300319865444&currency=1&keyword=STM32F407VGT6&data_type=1&expiration=0&counter_num=1";
//        String tkey = LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        String password= MD5.getmd5(MD5.getmd5("jkwyY4000").toLowerCase()+tkey).toLowerCase();
//        System.out.println(tkey);
        str = "https://www.digikey.com/en/products/detail/united-chemi-con/HHXC500ARA330MF80G/6928175";
        // str="https://api.mix2.zthysms.com/health?username==jkwy";
        HttpGet httpget = new HttpGet(str);

        CloseableHttpResponse response = null;
        Ip ip = null;
        try {
            ip = getIP();
            HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setConnectTimeout(20000)//设置连接超时时间
                    .setSocketTimeout(20000)//设置读取超时时间
                    .setConnectionRequestTimeout(20000)
                    .setProxy(proxy)
                    .setCookieSpec(CookieSpecs.STANDARD)
                    .build();


            CloseableHttpClient client = HttpClients.createDefault();
            httpget.setConfig(defaultRequestConfig);

            //X-Requested-With: XMLHttpRequest
            //User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36
            //Sec-Fetch-Site: same-origin
            //Sec-Fetch-Mode: cors
            //Referer: https://www.avnet.com/shop/apac/c/%E8%BF%9E%E6%8E%A5%E5%99%A8/%E8%83%8C%E6%9D%BF%E8%BF%9E%E6%8E%A5%E5%99%A8/?krypto=zDwL094PMN9NMpNZNsUt1EqodflJ9NPYUpZR3FRYD%2BZUTs15OpUPXnDM6z1hplmU2AHGb58rlEeL5dW9lmy8DA%3D%3D
            //Accept-Encoding: gzip, deflate, br
            //Accept-Language: zh-CN,zh;q=0.9
            //Cookie: User_Country=country_code=CNYAAQ02
            //Host: www.avnet.com
//            httpget.setHeader("X-Requested-With","XMLHttpRequest");
//            httpget.setHeader("Sec-Fetch-Site","same-origin");
//            httpget.setHeader("Sec-Fetch-Mode","cors");
//            httpget.setHeader("Referer","https://www.avnet.com/shop/apac/c/%E8%BF%9E%E6%8E%A5%E5%99%A8/%E8%83%8C%E6%9D%BF%E8%BF%9E%E6%8E%A5%E5%99%A8/?krypto=zDwL094PMN9NMpNZNsUt1EqodflJ9NPYUpZR3FRYD%2BZUTs15OpUPXnDM6z1hplmU2AHGb58rlEeL5dW9lmy8DA%3D%3D");
//            httpget.setHeader("Accept-Encoding","gzip, deflate, br");
//            httpget.setHeader("Accept-Language","zh-CN,zh;q=0.9");
            httpget.setHeader("Cookie", "tk_trace=1; cna=L9nhF7XMQCsCAWXnViM76z+U; xlly_s=1; t=382e900595fe1eb8f04f5a8baf82ba37; _tb_token_=7e77b64688f0e; cookie2=17ce9ad80d5f1955636fd6b4887b410c; _m_h5_tk=db797d950949e6484ad7c407cd0e4437_1600080508640; _m_h5_tk_enc=6d0bae1296e4d85bf9c54a7001914191; dnk=tb9201281718; hng=CN%7Czh-CN%7CCNY%7C156; uc1=cookie16=URm48syIJ1yk0MX2J7mAAEhTuw%3D%3D&pas=0&existShop=false&cookie21=VT5L2FSpdiBh&cookie15=W5iHLLyFOGW7aA%3D%3D&cart_m=0&cookie14=Uoe0bUiHJ%2F4Ybw%3D%3D; uc3=id2=UUphw2QnlRcpC0g2ig%3D%3D&vt3=F8dCufbDBGo6ovArbak%3D&nk2=F5RMGyOh276R%2Bri9&lg2=UtASsssmOIJ0bQ%3D%3D; tracknick=tb9201281718; lid=tb9201281718; uc4=nk4=0%40FY4HXgndVcaURr8K63HJCp82zooj0WA%3D&id4=0%40U2grGNtp5M1B7rpKquLMO3k2AkINQ0xL; _l_g_=Ug%3D%3D; unb=2209165857324; lgc=tb9201281718; cookie1=Vy6xnT4mMYhbyTrbZc0cGE0c%2Fbq2NuTI8by1A%2BeEolg%3D; login=true; cookie17=UUphw2QnlRcpC0g2ig%3D%3D; _nk_=tb9201281718; sgcookie=E100zVEBj6y3cpUMool7%2BpUcp0M8zG1Mlne1lM82rjV3%2BbPouUGVRP2BviTw1sK4KYwpf%2FKCcf6GdPZwZBBO3Nz5WA%3D%3D; sg=84b; csg=d203e111; enc=4MLqB%2B0WzeyF1djD9J45unFgxAHgkJPwm0tiBKqwkNvbUEdNzT91gGVjSL4LM71VIfZS%2BJTBsDxXBo7ARc5i%2BZpVFOnhWcSsJliq99AcsBY%3D; pnm_cku822=; tfstk=c5b1BJZjYAD_2_rq71Ne39OP8q8fwNu6T59h1G_gxnhOCL1cI4J7W1Cmp2KJO; l=eBjw6oiHOz7m7ymNBOfZourza77TSIRAguPzaNbMiOCPOu5p5GPAWZy3xaT9CnGVh6qeR3WXILt3BeYBqnv5eZXEk2lYw5Hmn; isg=BDEx7bEQyp-PemaLra8O9J5mQL3LHqWQo9iLaBNGI_gXOlGMW2ycYEFcXM5c8j3I");
//            httpget.setHeader("Host","www.avnet.com");
            httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
            response = client.execute(httpget);
            HttpEntity entity = response.getEntity(); // 获取返回实体
            String content = EntityUtils.toString(entity, "utf-8");
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void keepAliveDemo3() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient1 = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://wiki.acegear.com/dologin.action");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:60.0) Gecko/20100101 Firefox/60.0");
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("os_username", "username"));
        nvp.add(new BasicNameValuePair("os_password", "password"));
        CloseableHttpResponse response = null;
        String postEntity = null;
        HttpClientContext context = HttpClientContext.create();
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvp));
            response = httpClient.execute(httpPost, context);
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            postEntity = EntityUtils.toString(entity, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(postEntity);
        System.out.println("==================================");
        HttpGet httpGet = new HttpGet("http://wiki.acegear.com/pages/viewpage.action?pageId=11273011");
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpClient1.execute(httpGet, context);
            String getEntity = EntityUtils.toString(response1.getEntity(), "utf-8");
            System.out.println(getEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String testAvnetDetail(String str) throws IOException {
        HttpGet httpget = new HttpGet(str);
        CloseableHttpResponse response = null;
        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(50000)//设置连接超时时间
                .setSocketTimeout(50000)//设置读取超时时间
                .setProxy(proxy)
                //.setCookieSpec(CookieSpecs.STANDARD)
                .build();
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            httpget.setConfig(defaultRequestConfig);
            httpget.setHeader("Host", "www.avnet.com");
            httpget.setHeader("X-Requested-With", "XMLHttpRequest");
            httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
            httpget.setHeader(HTTP.CONTENT_TYPE, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            httpget.setHeader("Referer", "https://www.avnet.com/wps/portal/apac/");
            //httpget.setHeader("Referer", "https://www.avnet.com/shop/us/c/connectors/connector-accessories/backshells/");
            // httpget.setHeader("Cookie","oop=1");
            response = client.execute(httpget);
            HttpEntity entity = response.getEntity(); // 获取返回实体
            String content = EntityUtils.toString(entity, "utf-8");
            System.out.println(content);
            response.close(); // 关闭流和释放系统资源
        } finally {
            if (response != null)
                response.close();
        }
        return null;
    }

    /**
     * 输入一个网址返回这个网址的字符串
     */
    public String getHtml_post(String str) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(str); // 创建httpget实例

        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)//设置连接超时时间
                .setSocketTimeout(10000)//设置读取超时时间
                .setProxy(proxy)
                .build();
        httppost.setConfig(defaultRequestConfig);
        httppost.setHeader("Host", "www.verical.com");
        httppost.setHeader("X-Requested-With", "XMLHttpRequest");
        httppost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
        httppost.setHeader("Origin", "https://www.verical.com");
        // httppost.setHeader("Referer", "https://www.verical.com/products/electromechanical-692/power-supplies-704/ac-to-dc-power-supply-768/");
        //httppost.addHeader("Cookie", "_abck=C142729E2D240A710D8B35E6CE5B686A~0~YAAQI9o4fdoV/rBuAQAATa8HxgIYoheqeNcd1cosvS5ldl5JqVHAWUbHPAej8+EBBJcuFM/jFKUlgeZRMI6NbHBHWWpGjgARLXKzWYx3B/IRQEx8NWMEdgB/BJ7BfvuX0XXrFQI++H1P+u6hfpHKYw2wTqvjWQazHc3obNyBfrzVL2lyPzVAObk6L0dVbvWstYyZ3/elFC8z2giHSlME2SmCUwzIixESLHLT5QHWOoo5QqkANyNPmednYRw+fyZD5f3VOmOWl+Fqnw4hO02JvsQ3oVKK4taXH9Tq1rX0fhjV1SeyvRdvVSXgfoEbHgbemnmW9tUAnLM=~-1~-1~-1; experimentCoverageId=65; _ga=GA1.2.576644263.1575280424; _gid=GA1.2.1029333475.1575280424; dfp=0e84eefc1f8eef67a1f1f64ce237c795; fidelius=3ab607bf-823b-4291-aaca-b1f77b1240d3; alohomora=bcdc4125-7f7a-4732-9e2f-651ba11a7e67; liveagent_oref=; liveagent_ptid=59f38878-9f78-459d-beef-1a3192ddf216; bm_sz=748735DC629FA8A1510F9AC3507EC5D4~YAAQI9o4ffycPLFuAQAA0ABOyQWIWIn0KNK32yE+51YF2w2AVLCHPSfGGENtTZHN0C3nOXjSCk0rAF9Z7xTufOaL1AmO5gVojy8IxAjmz7qCHtsPMrDbsLRRZGxNUGECqfaqbm+78J6a1ohtS1/2m+HoS+pRxckFzrv7CJ1bDX5rsVV8ZpajlXPxmqbPhvurqA==; SERVERID=.30-81; JSESSIONID=63DEF4029E1B3F74BBD5836238D74DC1; ak_bmsc=4CF4F811B9C8EA12C6BFA803FA4A52E47D38DA23FA170000C2B5E55D9DE51434~plKV+VXk9tG3PYKdA/GVFWbY5DdwhttgXs5EFhR0OcfKQDSYRA7g4Pbg6fWQMVYOkwzBEkHFMuVXeDMe7FonmnxnDVZEQjM5LA/+xzxn7ggBEQZAbWgxZ+yzVLOJtsapZsKvWvWBR2rAQcYFBaZPyWcGQ8h+GiUiAT1qwrKKXHqd+eqzyTUsAg5ve/AQPrWTslzyjeF6KkIyCjlTlH6DI165sFBeangRZtqkzLqLfhYlVSueo0L4eNLhtCQxiX74XnQzFr9PwKd0D4P2iGXOnIdnLsmvNMHn+LNmgcLNCffGTvhw3bUVPgIU8iY6kpDOc2sUhd8AnH07Ftr9kTaSRf1g==; ga_cid=576644263.1575280424; _gat_coreTracker=1; Hm_lvt_ca6ad841e2bc77818047639352d1f30d=1575280425,1575335365; Hm_lpvt_ca6ad841e2bc77818047639352d1f30d=1575335365; Hm_lvt_aec5d04257e77a43bdc2b5236188aad0=1575280424,1575335365; Hm_lpvt_aec5d04257e77a43bdc2b5236188aad0=1575335365; bm_sv=8AA317D1DA7882EC4525030568E315F0~5NaHTZT9mTF88GkXxlatZpYznsMhjaAKCl7cTAOPAQR1YodoCZ/jhzUlxgMuCo5drcQPG5UubS0FBQ8CteskeNakjpht1aSplXaOMtwwf/nezwo3+qTNCqvc/9jaVkkATN7oF91Wss7zXRbWYHabApKrdF6cgPYBtUpYx8ZT6Hs=; liveagent_sid=eacd6430-c4f0-47a4-b8fe-52d4a0a3d735; liveagent_vc=3");
        httppost.setHeader(HTTP.CONTENT_TYPE, "application/json");
        // httppost.addHeader("DFP", "0e84eefc1f8eef67a1f1f64ce237c795");
        // httppost.setHeader("Connection", "keep-alive");
        httppost.addHeader("accept", "application/json, text/plain, */*");
        // httppost.setHeader("Cache-Control", "no-cache");
        // httppost.setHeader("Pragma", "no-cache");
        //  httppost.setHeader("If-Modified-Since", "0");
        httppost.setHeader("Accept-Encoding", "gzip, deflate, br");
        String json = "{\"parameters\":[{\"key\":\"search_term\",\"values\":[\"*\"]},{\"key\":\"part_category_id\",\"values\":[691]},{\"key\":\"start_index\",\"values\":[0]},{\"key\":\"quantity_min\",\"values\":[0]},{\"key\":\"facet_field\",\"values\":[\"manufacturer_id\",\"category_unique\"]}]}";
        JSONObject obj = JSONObject.parseObject(json, JSONObject.class);
        StringEntity entity1 = new StringEntity(obj.toString());
        entity1.setContentType("application/json");
        entity1.setContentEncoding("UTF-8");
        httppost.setEntity(entity1);
        CloseableHttpResponse response = httpclient.execute(httppost);
        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        response.close(); // 关闭流和释放系统资源
        return content;
    }


    public String getHtml_vpost(String str) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault(); // 创建httpclient实例
        HttpPost httppost = new HttpPost(str); // 创建httpget实例
        httppost.setHeader("Host", "https://www.verical.com");
        httppost.setHeader("X-Requested-With", "XMLHttpRequest");
        httppost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
        //httppost.setHeader("Origin","https://www.digikey.com");
        httppost.setHeader("Referer", "https://www.verical.com/products/other-components-695/computer-products-716/misc-computer-products-920/");
        httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        //httppost.addHeader("Cookie", "_abck=07F577A1FB4506C8701259133FA0EC3A~0~YAAQI9o4fe91CH5uAQAAPujZhwKTJtzNHARDWiM2uCNVvP73MzMAP9xcU9r5Rmf0e3RHtNT0RwEXoadTqhC1xdJ+uxm7RIwjcn/Wpm24unJhnMg0WJNNdRc2Ma7HFy3zKAsbcG77qOTnQ6FAlzS8HcUupresnbVwWF6l9DBLf2kcpbu3i9LHhB8Wl9HYUeCn/Ot36uPsSy2ReQVnWh6XMSzqpyLnZFINdhImOBznbDjH3d7veRWgrNqOmLCgb+75oeizs7mlFNF6udEgGu413Bi3aNidP3vberOWtp9jEfYI71hIi/3OxJcqpeBiyiXLmOuP8RefLxA=~-1~-1~-1; _ga=GA1.2.1311839131.1574237234; liveagent_oref=https://www.baidu.com/baidu.php?sc.0s0000aPch_Smgbn9wSaPR2GTAng0-nBSpOrpCquXYQqBLXegThYJTr2Kqvb6zjql5EYZxqJmjOdmsC_fPQeppBis4A-z_1FAT069aRai5UFZCLrBDlrqEYBeMQvU64j8xTsTiGiFakR456_NNn75DvLb_y-VqFSIHYS34FNGClq7T22r_r86sQDgc59BX_wtyIBOPv15uu9mkFIlhS7ZSeckbzj.DD_NR2Ar5Od66lT-xH4IAo_eRkc6YG8X1Knh4tLELqrVW_olX_QrMAkke5ZJ1ods88ikvtAHkozUVLgV4gkwFh4TDdZ-H7al4P17xyZDkbtpqOHgEe2SrHo6hCOOSOOOH7Na9WWstxU9zxgjbSEKsTZ-qauMs_YRmTMd9Bs45o93OgKfYt_U_DY2T5Z4g_3_AZFY32AM-YG8x6Y3X5z9ks3TMH6CpXy7MHWuxJBstx-xIMk33TtnyXSuh2ejblT7jHzYD1pT5Z4xfhutQPZj3OjvNqrZkSZF3d4PvejblT7jHzs8BCFBCmX5zEhuB81OfhutQP7mhQn--MZWE_4X1Fzdn84TXGmuCyrM_lThwf.U1Yk0ZDqIhNzpyP9U0KspynqnfKY5gu-Th-Wmys0pyYqnW0Y0ATqUvNsT100Iybqmh7GuZN_UfKspyfqn6KWpyfqPj0d0AdY5HDsnHIxnH0krNtknjfv0AVG5H00TMfqnHDk0ANGujY0mhbqn7tkPH9xnW0Y0AdW5HD3n1c4njTLPW7xnH0snNtknjc0TgKGujYs0Z7Wpyfqn0KzuLw9u1Ys0A7B5HKxn0K-ThTqn0KsTjYknH0LPWDsnjnk0A4vTjYsQW0snj0snj0s0AdYTjYs0AwbUL0qn0KzpWYs0Aw-IWdsmsKhIjYs0ZKC5H00ULnqn0KBI1Ykn0K8IjYs0ZPl5fK9TdqGuAnqTZnVUhC0pywW5R42i-n0IZN15HDLPj6snHn4Pjm4P1TYnHD1n1c40ZF-TgfqnHRLPjc1P1czP1RvP0K1pyfqryF9njbvnH6snj0YuWmdu0KWTvYqrDD3P1fzwjcYPDf1PRcvr0K9m1Yk0ZK85H00TydY5H00Tyd15H00XMfqn0KVmdqhThqV5HKxn7ts0Aw9UMNBuNqsUA78pyw15HKxn7t1PjRkrj6z0ZK9I7qhUA7M5H00uAPGujYs0ANYpyfqQHD0mgPsmvnqn0KdTA-8mvnqn0KkUymqnHm0uhPdIjYs0A-1mvsqn0KlTAkdT1Ys0A7buhk9u1Yk0Akhm1Ys0AwWmvfq0AFY5H00ULfqn0KETMKY5H0WnanWnansc10Wna3snj0snj0WnanWn0KkgLmqna3LPNtsQW0sg108njKxna3snNtsQWcsg108n1c0mLFW5Hc1rjm&word=verical&ck=5257.10.128.409.149.648.169.636&shh=www.baidu.com&sht=02003390_42_hao_pg&us=1.0.1.0.1.301.0&bc=110101; dfp=0e84eefc1f8eef67a1f1f64ce237c795; alohomora=f82f5424-6781-472f-9fbe-a5c899a7aace; fidelius=fb91fbe0-1d43-4715-911c-989df7bc4948; liveagent_ptid=8ffa1699-d8eb-45e2-bc2a-d46129aa2892; experimentCoverageId=12; viewedPrivacyPolicyMessage=true; SERVERID=.31-81; bm_sz=445747B3BA7710A3A2B0696059CD4363~YAAQNNo4fbqsa8JuAQAAJvlbxAV0lJT9FmNIJSUvl7Fi1CyhhqbcWTGX9tYoBzaihrWqHnd8b14cZ1+pKPZT1aDEH5LSAPH8qutzT8AYjHSXKNQigQVNm12ML5GeYQzZGpCrduHPKUBr5xC2r1P1dbfpltHFHQ89t3bSzcjsnADVr/MK/iPQ5Rf/Vr0+lB2M; JSESSIONID=8DEAD737EB43594D4D09845EA372F3CD; ak_bmsc=A474696F8C19D1B02680569B99F60E937D38DA343E0E0000A871E45D23E9345C~pl8bS76PBGGnP3IsnEVXKeCdJE8sHziME1K4RGUbFRRa4UDGXK1/vUWqat0nVKVW0VAKT0hwWl++JoZZhjQchxl5F5QFhq8G2rY1lLEitgTnfPafVZuNIE1Rw8pjRCYNzX0U3gu5FEnQzU/H6y23+A9LwwJzQvH86I2KThbZCKYS5nxvLzqlpczZ+tZwh4DKgeLaqAKWzDfO4dZTEyiR+DzXcQA4CX6gdSq5TJApgHm9ac5b9381NztOz6PPoVaYD1Bdrz+4Ip3NLq9vTZNnJewFmC/5gAaTvaa7GIgzt1hjIhGEog6BowFSaX59+By/hwO2IoHI+pBMGNrzHh5hvhQg==; _gid=GA1.2.1522548130.1575252394; Hm_lvt_aec5d04257e77a43bdc2b5236188aad0=1574392402,1574645553,1575022795,1575252395; Hm_lpvt_aec5d04257e77a43bdc2b5236188aad0=1575252395; Hm_lvt_ca6ad841e2bc77818047639352d1f30d=1574392402,1574645553,1575022795,1575252395; Hm_lpvt_ca6ad841e2bc77818047639352d1f30d=1575252395; ga_cid=1311839131.1574237234; liveagent_sid=06675b61-1486-4dfd-9237-dbbf5c24d4ab; liveagent_vc=7; liveagent_invite_rejected_57370000000XZCc=true; bm_sv=875B78DE6C9759C9BB9376BB51C2B8BE~JrXVAZpkayNfHMYesrTeey/yZpS6CY9cmp5Hy9IQaRl7ieYf9tmpBR7YOS8shhUvkA5qUUKJ/g0avuv2VAp5wtBJpUdzL+0zlmGN48O1XWm1JKK+gpY+f8hDqfmGfc3BXIR2P/gWMndnZ+AiJV40C1ixxdgB69k14aIe5xo+WoY=; _gat_coreTracker=1");
        // HttpEntity entity=new
        // httppost.setEntity();
        // httppost.setHeader("json","{\"parameters\":[{\"key\":\"search_term\",\"values\":[\"*\"]},{\"key\":\"part_category_id\",\"values\":[920]},{\"key\":\"start_index\",\"values\":[0]},{\"key\":\"quantity_min\",\"values\":[0]},{\"key\":\"facet_field\",\"values\":[\"manufacturer_id\",\"category_unique\"]}]}");
        CloseableHttpResponse response = httpclient.execute(httppost); // 执行get请求
        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        response.close(); // 关闭流和释放系统资源
        return content;
    }


    public String getChipPost1(String str) throws IOException {
        // str="https://www.chip1stop.com/CHN/en/view/searchResult/SearchResultTop";
        //?classCd=010100&classLv=2&searchType=2&dispAllFlg=true&searchFlg=false&did=https&isRtn
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Ip ip = getIP();

        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)//设置连接超时时间
                .setSocketTimeout(20000)//设置读取超时时间
                .setProxy(proxy)
                .build();

        HttpPost httppost = new HttpPost(str); // 创建httpget实例
        httppost.setConfig(defaultRequestConfig);
        httppost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
        httppost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        // httppost.setHeader("Content-Type", "text/xml;charset=UTF-8");
        //httppost.setHeader("X-Requested-With", "XMLHttpRequest");
//        httppost.setHeader("Host", "www.chip1stop.com");
//        httppost.setHeader("Connection", " keep-alive");
//       // httppost.setHeader("Content-Length", "8781");
//        httppost.setHeader("X-Requested-With", "XMLHttpRequest");
//        httppost.setHeader("Accept", "application/xml, text/xml, */*; q=0.01");
//        httppost.setHeader("Sec-Fetch-Dest", "empty");
//        httppost.setHeader("Faces-Request", "partial/ajax");
//        httppost.setHeader("Origin", "https://www.chip1stop.com");
//        httppost.setHeader("Sec-Fetch-Site", "same-origin");
//        httppost.setHeader("Sec-Fetch-Mode", "cors");
//        httppost.setHeader("Accept-Encoding", "gzip, deflate, br");
        //  httppost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        // httppost.setHeader("Cookies", "zrgdfdfs");
        //   httppost.setHeader("referer", "https://www.chip1stop.com/CHN/en/view/searchResult/SearchResultWithClassCd?classCd=010101&classLv=3&subWinSearchFlg=false&searchType=2&dispAllFlg=true&searchFlg=false");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("classCd", "010100"));
//        nvps.add(new BasicNameValuePair("classLv", "2"));
//        nvps.add(new BasicNameValuePair("searchType", "2"));
//        nvps.add(new BasicNameValuePair("dispAllFlg", "true"));
//        nvps.add(new BasicNameValuePair("searchFlg", "false"));
//        nvps.add(new BasicNameValuePair("did", "https"));
//        nvps.add(new BasicNameValuePair("disRtn", ""));


        nvps.add(new BasicNameValuePair("javax.faces.partial.ajax", "true"));
        nvps.add(new BasicNameValuePair("javax.faces.source", "searchFormForDisp:searchRepeat:0:loadingButton"));//获取剩余所有
        nvps.add(new BasicNameValuePair("javax.faces.partial.execute", "@all"));
        nvps.add(new BasicNameValuePair("javax.faces.partial.render", "searchFormForDisp:searchRepeat:0:loadingPanel"));
        nvps.add(new BasicNameValuePair("searchFormForDisp:searchRepeat:0:loadingButton", "searchFormForDisp:searchRepeat:0:loadingButton"));
        nvps.add(new BasicNameValuePair("searchFormForDisp", "searchFormForDisp"));
        nvps.add(new BasicNameValuePair("searchFormForDisp:urlTest", "https://www.chip1stop.com/CHN/en/view/searchResult/SearchResultWithClassCd?classCd=010101&classLv=3&subWinSearchFlg=false&searchType=2&dispAllFlg=true&searchFlg=false"));
        nvps.add(new BasicNameValuePair("javax.faces.ViewState", "q5bgkTVqkh4JBASe5ekHVkJyAF6MCnfm5BpHMKi3qBwnSJcUysbCsdO6tSjDKFjS8PQbHcTqvlMFDQSSNyAvA+4A+Dq7is0gQdEYlJ79MhpbLcK52JYcgamGSaWsGyXH7t8FRUEimr3ZYXcb2oLL4iL4878fcvIWycwx3laNpGD6AgpcND/zK0RDjkrw8EeTDXe+b/Zs4/EtjfhfifS3fE3iaaLLxMdj10JvlYljTrUIW3lO/PstSH1GAFcBVh7bz2a4dZh/w751RM6riZBro6ITGvo+q2STNNr5L8jSVEAOT+EM6c75hYYm9AEzIW+o4dSqHgHDJ0FDRyazkE2HQQoCwso6UD3MDfoteS4vbZ4B51MnkHS+0HKZDUJ1RxGa8+4C2c/t/D4saM4nJUJ9ZXqjb2QREnwJol+gpKBhzQ9H2yPnsqapsP/buHW3i/kFeisA3miT2lb0uz/73jVwgMsfJ/AedM4Ia+q5gNYRc6hH96DDw7FfFpBxshtz4qOaZvN01ti4Wxl/Tlzhxr8qiFguwN7mueF0ULhI9lZYtn5J6KjyhCw+MYKrJF1sL0xZADuTRTfGj6Qtsf9/1drELR5XdV+Sirz4WE7KScfabhSdq2ETaJ+ZIOg/e0uSbN6PndoiicgVxdobk3CxhgPxfh5uyMj04QTJmAHQeTdRNiMlKcCL6ivOHOwW67UyrPqBiycsq+KzpOQPgZ7PPAuwbsvr8Nm4AgkBQymDb1gvUa45oCbO0s5By6MyubkXMUPT8R7kxcuV8SxHYUcKMbNF4lv4tZvda1NY93LvHHJ3MXOkiNtWLjhg+RtWTzSeVwZ+jnnMej+TGgVuhbdrN58JwFrgSB60v66MI1NktAV/6u50nOY9MDStgRWih7jro94NVGvP7TQmalpsst/v8h7z9SDwgZ0RfL1nJbCeIkyo5yh+BxpdPo5qBbxAnHK12Ouxmg/YTNzxrWLG2H08sRNwkzx950C3JFLhAiEKof2me6F6XDxFro5bGbLnCwl+jZLQBVjZToXgxMlWFUvb3zRieXWfo9IcsqFNhr44GNzwpLdmfnIVVpKVYt+Xo+NCN7uaRzOuUXFfAkAQpCApBTxmTxZb7PgIRGYYlTRQw38So5u+hxaVAKuP4m/lnmGr0FAWAP2LUE5ybnXk01z02iNDCEBQpEvT9uapLQL8vi7++2/KTVvrldfUVLcyZCS6l5pkwnxS9q7uD5lz+WrFmLO0wycwDKhZ+YS+FQ1o87/t7LOza0afPg5WtxeJucuqK6I0QVTnNXO+Y7LdWCbY8tv09aOm12CHkaMfJIbSM+37AxlEyF0zAPs448CveGH3eIMBlDXxEnksmNIXXVjdx0cck7pOC1gvCB9HekExKmqSyKdbzdOAwE05SbtjN2aRgRX6l+lxM1GPv/zpDahGta7CljvAVjDK2LNPPuwtjLhixUmhksxi9UXk2pjvwQXQFf8Ha8YULNg5wVCjoZoq4anZOf63IfTnr6MZtoUcUeRftMkMdRQSqWmKwqdC8ZFCzxfpQQstxQi85SJXIxI0uS5bZWck92zdjWSIc0GWeOrIWKLA98+hMUyCZF+uzEsNCt1eKLd521l3vDpY5KC14hxcg69s/kSfYwWOr6IaaRd6yQRIvBzSzkxC34KjvPpLjzyUtCNEsSAAekkaEhuHOtd6w5sEBCU57t32BFyldjmcntcw7ezy6JaUt8dFX28GA2JrbvmFbpQUYnYjPIDDRM4EvpEm9fyd7PX5gnl+n6b0EcrDsFHgn8ybFMhcnKdhBbzozXfUAN3rbxLJpdZEAl8Tr6s3qr0AZdq5T/MLhBwqqYoTPCEqJhBTvtTTrrN6TMzZdMcpmBX6r7656GWbgJvh/eDlSs7nPzMpz/mR8I2gCyXbI1ldnGVJKP9emDcnfN9uWY1WFjBhw53I4yzJ/9NoHWox1i46FRy4YM+8YRyO3DBYBpxH9DPfwGLkpLqX+ge0Ds0rdApBRzALbvQuaBnhO5rNUHEvsy7xjSmRcyJabuFkrArwpd4bdRKPKuFGMWEFd8Lkm/GGkWKWH/V0NF5P2kybw1mY4S/GhaRkvIVHpf5dKR1ldE1GjmgsKBCs0t0AThNn1rJ3x0/nW2SWpBsVfR5Rf3nhUt9Jdeh03PBbUT0s8zF7v4mA2Xfq7wLHD8V9vQGdbiR3LtgiEcgMToGNytiaZgfGbQdM8/puLKJe6Z3atYBL5Y2ahFaJOJ6z6cKnG+6WCAa8VmW8F/Dsbi+Xa8Os7cXLJ4Cy5hVpthC4l71mh8rauWosuo6byFox+csvwaseGSXal0S1haXoACMiP6Irt+UR9HL512h9ig4ysIwZK/3qLfIzNfqAH+McVUToYLbbqUJQyH8umwztfpw4qy9Z8UpOhETU8fdQA2BGBNuI908pr6nh14XdJg5U4Z4j8+wTTW4kAwrUuPdBsQyGAnxEv5ea/afysPzb4aiwQuZQWghmJ1QHxStju5sWrlUV1PVkMKM5kJMm1U4PYiFuH/n9wrM4HzFynX934yQZD7F3VUjSWtrCdqKBoSMAErMplNcaAVP/GB9Mg8eLmB4eo2ZFN34LndS2HNmRkzngWU2pEfm+336VuNu+QUsc/8/BBaT1ajDh/ALPNGPz2he6PsJ9vsRx8H+LiqPGPRV+GfqkJTHN3UvObb7nQIfNMfcAYDxUZnZpuhqtDe5Nfw99JBArFvEb55ilmU5s4gPjK9Lm/pBL5HuZavmH8X9LK3ccUPBQ/MSZGD8Xwn/sSR+2tgH3MkvTpxw8LHKWquVm/N8ZQJaFllEELah0DSzEXGOFLcaNKoZydbufvBaREULLjLjr4cs1S9EOMAuhTMA3PtV5xg6oiJa1x4AJKNMUioYazLiQ0NvVlyOF9grAPr+FcQVxIGAHL8IWJQqksmeWJvH87vppsv8qyaYU+6Rr4DiY6bDcI2ycCP8a6mKExlHrFgWf4KRlNVNNOF3+vwg+x3fKYjm85nwVFuI6UgzVwlWfFN1Vnb0JiwO63LnZjvKWABdtkje2HJ0MBIj6DKPYWf/ovt9hTKLt3vN1TFQzzDTWoRE1nE39W6coV3YjG1UrlVJR9hU9VExvcPBtIO3v+lKCifrpv3FJvb0odWFgmwQQXYBjLGCiHIeZtYFEAWnnTnMm8Sywyx0Wr+tGVnQLaJIlEl9lCgwhQgQ+IuEtqhAtrtrcLKAIuC7Qc3hELkJbnMa57R1QyhxtogonqkeJ4Noy62zaUwPL87hsE3caemxr3PPDByy9HJyWP90YLiUkGlRIHmN/O0oBlvcBEi3TNQe5JO7SGEYvvwp3ybQsOfmIYzCFBKKJsSTfRqum/x6g8v7/vkfH7gye9tVu0x2PW9Id79e7i80oe8ZCTfuU1aRy8091n3q6xhDx3WxVfr4pOnSjsQI+aXfcv4S0057f6Q0VK/29/bxaksiL2n+8r7B9pI/IpKkMRzzcTqjhKT8hI1Zp/oPLu1Nt2zjNkPvDHcENSV/2Kfn5qrRwMYkDXwo6SIeIEOQPN36S1ZfiO1VqpF6zg4K5xkgg67BVjWjNBEO+GGJPEBAxjN6mMQrGuVSacgRUWwJCYvab0K/IpNrX9F6mZYkl7fbAcIz36X9ALXXl9OHGnrcw5S+KBLhki5WAxPgnqRsoKPhRSr1hehq2WW+/5HETLcz7bASZXEaFFTKY0WegRJ2JyJU2yP10uz+1syZa0HlzA8jVz0tcWJO25rYwFZ5NGLTyXV2TycQUSyNtFbDeVeJWIOwv02Vq6uq/od3d3WOGcNpWN0mOLMkZRrl3p+K2I4CsE2SMs1NtuwbwHy5HfLKcAtcdRCxi34kCho02qwoh2boOdKzvJRA8yn8/FRdJnUAJjSm753cn2kO11JSG1fB0Ko/P9pv58lDtOBFkwjGnOD9WAOmjG76hIA0VtieAmQ60nvIOraHMR/d0bj2gOZ1iSdjSubexDwo7NUAmMvkxk1j/oSNlBbknRrwvNvZPLKrvLuPbus0aX0KLSlBl4NlL6+W77jrx0PWOmAfhBCG+a7P4p6vUcZxUdCmIMLd5rX0bkmw29ecPyRFpZcYiE/XMaYh1Vjiyx2b/jIX8AOm0xctnCn9Gl7rfGbuyz8leQ9r9zEKxUpKrXVCldzco1Rk/HnUnjjnTirSWzfYQ/Taog/ETNi44bqjgkDl4wGwCbVkDAvfjQ2BsMtyJ0O3qtbreo25i3SCNU4nFsG/xBEbmqCCCILHoU2KrUE10wWCmQBtvlNY9HF5c6uAedxlJ0GvfSJebAzksEW3Nd/KpgkOg+ZGhe4YJEpEDSwcQPKY4eLuBUl9cwADXB/wufqvpKnvbPgds4t+BMHnSTqkx0qv5c9p3UCBlU1heYAbX63PPg3Sz9J2xcYiA4Vvl24FX/yFIhVjtWpIS7OHBft8ggU6docJblXv6TEa3g3DiBi7SdjneVfJuN5yMKuecbm1nD4H+kiSCqS5U1YQf+5WAZ7PW/UgBq48IPYJMBvuegmnYv4lyd+QB/LsmZ8rXH9BUIlKKEeHlFexGAy3PWd5IZFqrtTIFOeRsW13HKnt8E9e80U2AfkpXzDWSgmXSet0nUXs/4BrpWucRjCMVP4eeOaDdFkBjOhKNIX02jHKqrSL8Ix0U1YvjWVno2bkB7TaGMpvDcf+yDkwkYYfHuWwubErg3CV+cpBA/Zdykef2MaLvl9qaRDdgOLR2oSIsU/fzGa5wrzjU15LS8/lASKu6uksp3L9GUzXNFV3SmVYOnVy4SnpXo/usyQKy8cHqr8amKUyJcB+Gkz2+vdIAzxn6BdUvra1Jn+wzo7MQnviGwYgrbqSknt5LAZVcjpA1w7yQntOPyIZdNysatEW5wLPa4NfDnlDiwJjn1Hbhr0w4FepJoyCWGoys68Ko0+NPLBdpscEDGlgkzrqSkm+wycGZyROW86Q0PDaEpnDWjTuMUIpuJwVTxFUi2Nr+vRThgkAkR+7jYchg6qt30Xut9I26mEQ6q/LUX8Hf+cE6NvRChnQ6XdRcWdp4m4KRhsIl+AgbCblZPIa/4MJDaSTmDLHs3WR8B3YUXeubvhtNnsH3JKIjUaNbZjxDroAK2+/uCN685q/lv5EVnXVmHMpG7TbOCgx94tTlWL/ufyxfx85TSxMP2LUF0WLdSzqZQmojEr06jNTMOkbY4dn4CUyu8EPctNI1IgM1Dbz0Zi1gsDeYNaKa+9MQixYrskwOMhwM11GqDtIkxKAf2gjX1aNwqbJqOQsh95NeeHU2ek/wRkwsGi42MlrFzIfHg7bO6YHsRmfE7VKbg9VMZ38NrbKtUG6QzoE0/lhXZmj1G7S4UXmV6s5GNLiXUROJW1DI02pvGtI7HsPhyzT3CART7se3aUWI+Kf/Dn45M9UsQH70mbHZrySVSa0hZH+XfTt0EnCVkZsDqf2Nj1IJG+Dv2LQe0aNhqnmj+PIVun+Rlj9ygBHTGUIERaczer9yphBEBq4msmyncloM0ECsX97DoU8M+WhYiO0vkPJ4T9RYLPiUsVi086cAgd91JjGoMC0PxlrB8Z7vlS+IsWeTx8gH5i2RuAhlwoc1zlnayOiDzOchcjcCl+u/eI9EdBoZWM8OAZvY7DmoVQjP/MMDakqRSLN6xup3/iTc91voPpPZzOSg6KC8pTmtK+f/lF++s40mFemQONxi2idA7tlP/Rzu3I4WVlyeupoxjLy3lAVERPVv66FvIOc6y5AlBANFvC23s2urrNxYi8dYgugLwFnHoOzV1kKa1M07OI4iRiGhxzcQRANJVwuE7wWg/Fa4CGQvd43EOYAXy4Iq1uFTmLGOJba5Q54jQK2DE4E5dV5AYb3HmyOqyuc6890v7p0OSrKtIycIF75K9y668zxbEiot/ARV8xr3n6kHwDUN8Yv9MvSTdRcp21ew+tt4KaMHtBFhnEl7ITcxGMLrD/ChyRyXAg8Pua8vu5Cw+D3Nl3awScoy+f4TVtZIL9L6UdIvqGk/UzyQYYzNQ9E8QLceju0xm4YzLyA/tL1zPrbL6U7kVGDHXQpY+nNqhpHBi5TRAKuO9FArmpcjA2pZW8QyvJTpLAcunw9+zpXThW6VnVQ063GXMS+ohTJKjYDDofnaW80U1uZNMjNwW37TctnRDkwmnX3bJGWHdKO2PHkTYEcmcFyIu/vqjRbvtOwRvmemhj2N5x9N0F1FUoj4iTQ1IXWwJkXi9rXBhCko5RvRgmj4GBPhR8dUOO4NMy97DvXM3/zcS5q1aWq/ocq2N+o/5RITJTDvhWYVhpPiE/AC14565Hmwr860/4Z/1noPBs3fVmRfXnIb+9YwnRl1AmYZitdjbZSRLPDsNaQ2+XuFh44J1nNazT5cana8sYLgDttStHByH4laITEWYUU7ZBC8Wvm9SdRJvntBvPA0Cbda65Zgd/wZrmE/8Gm9zeqk7WW/odN1kFzw6MlMHq2+4KPhLRHhjiQSjMnbrdtTOxy3/Ob6IodQNIGWBpMyAm1KSCDppGPwGE9hpIQPVBEohpywfZhxSCCF5lbPzbXVbLRNUKUK7pKNkZOumJkP5+wui3mbezq+nBrhFOFnXpyS9PnemYTc3gozvE5yhLtkjfpNr9mEnCUsOphIASXUfAKXr8CcGtunyD1mRqLvwrvGEXW3AzONZpIHX0liuV1Ly2ATuBEaWphR5QAhlsv6iFRREQPA5/9zM1eoB6eNtkCwUp/q0cpTcSQbvXQblvtx97CnWyzwSiHKvliCv4XcJdovqbj5eX/2TcDgc60IFnixpV/AWAYoqrtK/82HF2Qds0D2ftUNWtrLj6/R5pjDq9ogAD+Q7wupakCwcktjibE+Tn/WCFmYvw9ofFZWBTWBZIuXqywkJ+SA9cMwwUSyIpdkGUQ/8Yw8+a3fpaSHSRWau2UVf4D5s96L72YhWZ78Ymm/QaS0BHwi31yl457FmLgwsGoYGst7P/EhqpauA25oWW/TwU8upmruzn8ihlIrAxfFRzLMlJRfAnrGMKsH5U8/BrItUIqfBQvtV+3qNQ3I1qVqkZEUNXoACNXyz4mvOSnmdtRd3BFfqpM+2NfEyCdApyixDSaewxiLeCij1GrVqqas0XIfm818d1rRa75h+iSeZPPdYj22tYzJ21AAkwAznJFupOi9FxzgZtDvWVpo3MUiiMe9KOxPfxcU3xufOH+/tanl5nUQLpVSatEKb3wbvtqWTb1sewg6RtWKil/DhuL9flwOP7YfmajuOTaHlFU6Tb3GuvF0QT3UyOBPrDFYw6mPZ9BI4flvixNUpWlvN7yGHbNGUjVVycXWgJ3CT5cT4cdDndChK4hDTve0wyuBWazA5oWUlYaavzQcanOTtraQv7RByW3m7favAdDa3YzGoWd9SMUYTjuMXnsmLF6bz3inLqYtpTkKaIpYeYzCmYwWVdElj+PUdRXSTzMZjZh73DzaWAkgoLDpLm100yfj15uxlfdU9VAvUC3gP/LVOgpixBLmAYwkXdjjtCmpOjT9Ojsd6itKpTnuhtZSUwuT9u0rrxHpoR2obKN02OMn9tYL9DDYHgJ+swmMdo5Z39p4EbLIy5O7jjyrXIBMDHWLAtmMWPHbWWRmvoYrDA00eHSh3iicXGR3ICpVJJudOwH8gylsGvMQvFPxt5ry3qlFXZeRvk9zO2fqLqRfHGuMNvybXESs9FEcicnwq9RlmacmoKxaqtENfWXalxl546MjkIWSRt9zdpyJOXFKf8e84CHcYuM5087wzkbAVqglVjqm3PzJ3muCkOM24qD2vX51bWxXxMsLDA0NEWZhlc8gHkbr4KH2Bv4EWERMiSlQF96g8zV969rq/0tZFooyX2AZEV0BckDAmBXWA9kYGq+5LSTRl67ocK7dXN39UhzxTIy/Ba5nc3j9qsUZm1hh608y/jj+d7khJwF3FYSmwYHRO7VICzJTiEm9BdqEk9qRckJKc7LrRsokM2DeU8EqEw70h8+YKkxzNRe4t9mhAWbf495w+Hl+9M9ro1PvPnCSr3Vski2Gg44zS+McdQlRnoOehK151Qol6YS07GxIPFK0C5CgxmavYzNs8eRyd9c5wY0lJi0GKWPmFnzfwMLlUbJsS1itn9e0igka67fFX0ZBuxslaqs+qytrZ/meP7R/9BS2hwO7oQN225zEymvOr1KeISrM2eZuLRAExjkwVsJCsP5dlMWQ3WI880pztQKqJHFmeq10T7QcrIeVzyBuE4IDwPcSYVu1s/+I+FO2l7iHHeOvbQyyxeZH5TlDPopJMVUBP9bbA25wWEexzEpVTU9d+uoDq9GIxlQhclVbPJ7oRIh+DqCl+fWF+Y+45cVwMBvjLjv5Vc7p3ID5j9lQNug/kJb2PDznGGaFKQBi89MDMh8W4goftDOtcgDDuMKogjIeChttwnmceqwhTt9LhdAYZ39dZ88g/TGwcxhNo5xGHCADCZvvN/eK"));
        //nvps.add(new BasicNameValuePair("javax.faces.ViewState", ""));
        HttpEntity entity1 = new UrlEncodedFormEntity(nvps);
        httppost.setEntity(entity1);
        CloseableHttpResponse response = httpclient.execute(httppost); // 执行get请求
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(JSON.toJSONString(response.getAllHeaders()));
        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        System.out.println(entity.getContentType());
        System.out.println(content);
        //Document document=Jsoup.parse(content);
        // String s=document.getElementsByTag("body");


        org.jdom2.Document document = null;
        ByteArrayInputStream xmlStream = null;

        try {
            xmlStream = new ByteArrayInputStream(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }

        if (xmlStream != null) {
            try {
                SAXBuilder saxBuilder = new SAXBuilder();
                document = saxBuilder.build(xmlStream);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JDOMException ex) {
                ex.printStackTrace();
            }
        }

        org.jdom2.Element root = document.getRootElement();

        // List<Content> other=document.get;
        System.out.println(JSON.toJSONString(root.getChild("changes").getChild("update")));
////        System.out.println(root.getName());
//        Elements elements=document.select("li");
//        System.out.println(elements);

        response.close(); // 关闭流和释放系统资源
        return content;
    }

    public String getChipPost(String str) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)//设置连接超时时间
                .setSocketTimeout(10000)//设置读取超时时间
                .setProxy(proxy)
                .build();

        HttpPost httppost = new HttpPost(str); // 创建httpget实例
        httppost.setConfig(defaultRequestConfig);
        httppost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
        httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("javax.faces.partial.ajax", "true"));
        //nvps.add(new BasicNameValuePair("javax.faces.source","categoryAnchorGet"));//获取剩余所有
        //nvps.add(new BasicNameValuePair("categoryAnchorGet","categoryAnchorGet"));//获取剩余所有
        nvps.add(new BasicNameValuePair("javax.faces.source", "categoryAnchorLeadGet"));//获取Semiconductors下所有使用
        nvps.add(new BasicNameValuePair("categoryAnchorLeadGet", "categoryAnchorLeadGet"));//获取Semiconductors下所有使用
        nvps.add(new BasicNameValuePair("javax.faces.partial.execute", "@all"));
        nvps.add(new BasicNameValuePair("javax.faces.partial.render", "categoryAnchorReady"));
        nvps.add(new BasicNameValuePair("searchForm", "searchForm"));
        nvps.add(new BasicNameValuePair("j_idt153", ""));
        nvps.add(new BasicNameValuePair("headerKeywordSearch", ""));
        nvps.add(new BasicNameValuePair("suggestCategory", ""));
        nvps.add(new BasicNameValuePair("javax.faces.ViewState", "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"));
        httppost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        CloseableHttpResponse response = httpclient.execute(httppost); // 执行get请求
        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        //Document document=Jsoup.parse(content);
        // String s=document.getElementsByTag("body");


        org.jdom2.Document document = null;
        ByteArrayInputStream xmlStream = null;

        try {
            xmlStream = new ByteArrayInputStream(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }

        if (xmlStream != null) {
            try {
                SAXBuilder saxBuilder = new SAXBuilder();
                document = saxBuilder.build(xmlStream);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JDOMException ex) {
                ex.printStackTrace();
            }
        }

        org.jdom2.Element root = document.getRootElement();

        // List<Content> other=document.get;
        System.out.println(JSON.toJSONString(root.getChild("changes").getChild("update")));
//        System.out.println(root.getName());
//        Elements elements=document.select("li");
//        System.out.println(elements);

        response.close(); // 关闭流和释放系统资源
        return content;
    }

    public String getalliPost(String str) throws IOException {
        str = "https://www.alliedelec.com/View/Product/GetMaterialComparisonTable";
        CloseableHttpClient httpclient = null;//HttpClients.createDefault();


//        try {
//            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
//                // 信任所有
//                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                    return true;
//                }
//            }).build();
//            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
//            httpclient=HttpClients.custom().setSSLSocketFactory(sslsf).build();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }
        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                // .setConnectTimeout(20000)//设置连接超时时间
                .setSocketTimeout(20000)//设置读取超时时间
                .setConnectionRequestTimeout(20000)
                .setProxy(proxy)
                .build();

        HttpPost httppost = new HttpPost(str); // 创建httpget实例
        httppost.setConfig(defaultRequestConfig);
        /**
         * POST https://www.alliedelec.com/View/Product/GetMaterialComparisonTable HTTP/1.1
         * Connection: keep-alive
         * Content-Length: 69
         */
        httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        httppost.setHeader("Host", " www.alliedelec.com");
        httppost.setHeader("Connection", "keep-live");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        //materialNumber=70184156&salesOrganization=1000&distributionChannel=10
        nvps.add(new BasicNameValuePair("materialNumber", "70184156"));
        nvps.add(new BasicNameValuePair("alesOrganization", "1000"));
        nvps.add(new BasicNameValuePair("distributionChannel", "10"));

        httppost.setEntity(new UrlEncodedFormEntity(nvps));
        System.out.println(JSON.toJSONString(httppost.getEntity()));

        CloseableHttpResponse response = httpclient.execute(httppost); // 执行get请求
        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        System.out.println(content);
        //Document document=Jsoup.parse(content);
        // String s=document.getElementsByTag("body");

        response.close(); // 关闭流和释放系统资源
        return content;
    }

    public String getmaxiPost(String str) throws IOException {
        str = "https://www.maximintegrated.com/bin/OrderPageSearchServlet";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)//设置连接超时时间
                .setSocketTimeout(10000)//设置读取超时时间
                .setProxy(proxy)
                .build();

        HttpPost httppost = new HttpPost(str); // 创建httpget实例
        httppost.setConfig(defaultRequestConfig);
        httppost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
        httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        httppost.setHeader("Referer", "https://www.maximintegrated.com/cn/products/power/linear-regulators/MAX38902E.html/tb_tab3");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("product_info_root_part", "MAX38902E"));
        nvps.add(new BasicNameValuePair("requestType", "fullData"));//获取Semiconductors下所有使用
        httppost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        CloseableHttpResponse response = httpclient.execute(httppost); // 执行get请求
        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        System.out.println(content);


        response.close(); // 关闭流和释放系统资源
        return content;
    }

    public String getVHtml(String str) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault(); // 创建httpclient实例
        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)//设置连接超时时间
                .setSocketTimeout(10000)//设置读取超时时间
                .setProxy(proxy)
                .build();
        CloseableHttpResponse response = null;
        if (str.contains("/products/")) {
            String uri = "https://www.verical.com/server-webapp/api/rest/search/parametric?format=json";
            HttpPost httppost = new HttpPost(uri); // 创建httpget实例
            httppost.setConfig(defaultRequestConfig);
            httppost.setHeader("Host", "www.verical.com");
            httppost.setHeader("X-Requested-With", "XMLHttpRequest");
            httppost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
            httppost.setHeader("Origin", "https://www.verical.com");
            // httppost.setHeader("Referer", "https://www.verical.com/products/electromechanical-692/power-supplies-704/ac-to-dc-power-supply-768/");
            httppost.setHeader(HTTP.CONTENT_TYPE, "application/json");
            httppost.addHeader("accept", "application/json, text/plain, */*");
            httppost.setHeader("Accept-Encoding", "gzip, deflate, br");

            String pattern = "(\\d+)";
            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);
            // 创建 matcher 对象
            Matcher matcher = r.matcher(str);
            List<String> numStr = new ArrayList<>();
            while (matcher.find()) {
                numStr.add(matcher.group(0));
            }
            String part_category_id = null;
            String start_index = "0";
            if (numStr.size() == 3) {
                part_category_id = numStr.get(2);
            } else {
                if (numStr.size() == 4) {
                    part_category_id = numStr.get(2);
                    start_index = numStr.get(3);
                }
            }
            //拼接参数part_category_id为列表页号，start_index为起始位置，可由列表页url中获取
            String json = "{\"parameters\":[{\"key\":\"search_term\",\"values\":[\"*\"]},{\"key\":\"part_category_id\",\"values\":[" + part_category_id + "]},{\"key\":\"start_index\",\"values\":[" + start_index + "]},{\"key\":\"quantity_min\",\"values\":[0]},{\"key\":\"facet_field\",\"values\":[\"manufacturer_id\",\"category_unique\"]}]}";
            JSONObject obj = JSONObject.parseObject(json, JSONObject.class);
            StringEntity entity1 = new StringEntity(obj.toString());
            entity1.setContentType("application/json");
            entity1.setContentEncoding("UTF-8");
            httppost.setEntity(entity1);
            response = client.execute(httppost);
        } else {
            // if(str.contains("pd")){
            String pattern = "(\\d+)";
            Pattern r = Pattern.compile(pattern);
            Matcher matcher = r.matcher(str);
            List<String> numStr = new ArrayList<>();
            while (matcher.find()) {
                numStr.add(matcher.group(0));
            }
            String partId = numStr.get(numStr.size() - 1).trim();
            System.out.println(partId);
            String uri = "https://www.verical.com/server-webapp/api/getMPNDetailsByID?partID=" + partId + "&format=json";
            HttpGet httpget = new HttpGet(uri);
            httpget.setConfig(defaultRequestConfig);
            httpget.setHeader("X-Requested-With", "XMLHttpRequest");
            httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
            httpget.setHeader(HTTP.CONTENT_TYPE, "application/json");
            response = client.execute(httpget);
            //  }
        }
        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity, "utf-8");
        response.close(); // 关闭流和释放系统资源
        return content;
    }


    public String testApiArrow() throws IOException {
        String str = "http://api.arrow.com/itemservice/v3/en/search/token?login=ameyaholding2&apikey=5f4a1376ff2daa15aebabc37ae073c85e9ca9a2849f9396e293f4f51d789c8e6&search_token=BM1422AGMV-ZE2";
        HttpGet httpget = new HttpGet(str);
        CloseableHttpResponse response = null;
        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)//设置连接超时时间
                .setSocketTimeout(10000)//设置读取超时时间
                .setConnectionRequestTimeout(10000)
                // .setProxy(proxy)
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
            String content = EntityUtils.toString(entity, "utf-8");
            ParserArrowJsonData.parser(content, "mpn");
            response.close(); // 关闭流和释放系统资源
        } finally {
            if (response != null)
                response.close();
        }
        return null;
    }

    /**
     * 带参数的get请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()), "HTTPS");
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)//设置连接超时时间
                .setSocketTimeout(20000)//设置读取超时时间
                .setConnectionRequestTimeout(20000)
                .setProxy(proxy)
                //.setCookieSpec(CookieSpecs.STANDARD)
                .build();
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
        // 声明URIBuilder
//        URIBuilder uriBuilder = new URIBuilder(url);
//        // 判断参数map是否为非空
//        Map<String, Object> map=new HashMap<>();
//        map.put("catalogId",10001);
//        map.put("langId",-7);
//        map.put("storeId","715839038");
//        map.put("catentryId","3074457345630017631");
//        map.put("botFlow","");
//        map.put("dojo.preventCache","1594779147152");
//        map.put("userAction",false);
//        String json=JSON.toJSONString(map);
//        if (map != null) {
//            // 遍历参数
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                // 设置参数
//                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
//            }
//        }

        // 2 创建httpGet对象，相当于设置url请求地址
        HttpGet httpget = new HttpGet(url);
        //  httpget.setHeader(":authority", "www.avnet.com");
//            httpget.setHeader("Request Method","GET");
//            httpget.setHeader(":path", "/search/resources/store/715839035/productview/byCategory/3074457345616735233?searchType=100&profileName=Avn_findProductsByCategory_Summary_More_Ajax&searchSource=Q&storeId=715839035&catalogId=10001&langId=-1&contractId=4000000000000071008&responseFormat=json&pageSize=20&pageNumber=1&_wcf.search.internal.boostquery=obsoleteFlag:%22NO%22^599999.0+price_USD:{0.00001+TO+*}^499999.0+inStock:%22true%22^9000.0+newProductFlag:%22Yes%22^0.085+topSellerFlag:%22Yes%22^0.080+packageTypeCode:%22BKN%22^0.075&wt=json&userAction=false");
        // httpget.setHeader(":scheme", "https");
        /// httpget.setHeader("accept", "*/*");
//        httpget.setHeader("accept-encoding", "gzip, deflate, br");
//        httpget.setHeader("accept-language", "zh-CN,zh;q=0.9");
//        httpget.setHeader("sec-fetch-dest", "empty");
//        httpget.setHeader("sec-fetch-mode", "cors");
//        httpget.setHeader("sec-fetch-site", "same-origin");
        httpget.setHeader("content-type", "application/json");
        //httpget.setHeader("x-requested-with", "XMLHttpRequest");
        httpget.setHeader("X-Requested-With", "XMLHttpRequest");
        //  httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");

        // 3 使用HttpClient执行httpGet，相当于按回车，发起请求
        CloseableHttpResponse response = client.execute(httpget);

        // 4 解析结果，封装返回对象httpResult，相当于显示相应的结果
        // 状态码
        // response.getStatusLine().getStatusCode();
        // 响应体，字符串，如果response.getEntity()为空，下面这个代码会报错,所以解析之前要做非空的判断
        // EntityUtils.toString(response.getEntity(), "UTF-8");
        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        return content;
    }


    public String getcoilcraftpost(String str) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault(); // 创建httpclient实例
        HttpPost httppost = new HttpPost(str); // 创建httpget实例
        httppost.setHeader("X-Requested-With", "XMLHttpRequest");
        httppost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
        httppost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httppost.setHeader("Accept", "application/json, text/plain, */*");
        httppost.setHeader("Connection", "keep-alive");
        httppost.setHeader("Cookie", "__cfduid=d357b69cf87adad91a853eef891336e121596418757; cf_clearance=e09fd72e2a76c5752f2464ad03e2270ae5434e39-1596418762-0-1z34b0f404z2fe1ec99z692003cb-150; ARRAffinity=33f3607a97446f806f98e4431cafc22b9a7249354803fed4d8462ddb15ebe7ea; ai_user=GCaCT|2020-08-03T01:39:25.600Z; ASP.NET_SessionId=oovlmdascgxa4tqbpypxoa2i; Coilcraft_SelectedCurrency=2; Coilcraft_SelectedCompany=1; _ga=GA1.2.395664814.1596418767; _gid=GA1.2.1065569358.1596418767; Coilcraft_ShoppingCartCount=0; CMSCookieLevel=1000; CurrentContact=ca19d6a8-f7f5-415b-9609-7f943c6b4835; _trackatronId=l2nju59gv; _uetsid=d2c4807be50d968829d7653fe5087391; _uetvid=c72eb5ee01b7b4e99a44295bd5a33730; _dc_gtm_UA-1403655-1=1; ai_session=UbMQK|1596424234034.06|1596424234034.06; CMSLandingPageLoaded=true");
        httppost.setHeader("Referer", "https://www.coilcraft.com/en-us/products/power/shielded-inductors/molded-inductor/");
        String json = "{\"searchString\":\"*\",\"facetFilters\":[],\"orderBy\":[{\"columnId\":\"length\",\"isAscending\":true,\"displayName\":\"Length\"},{\"columnId\":\"height\",\"isAscending\":true,\"displayName\":\"Height\"}],\"page\":1,\"path\":\"/Products/Power/Shielded-Inductors/Molded-Inductor\",\"resultsPerPage\":50,\"typeId\":\"power\",\"currencyId\":2}";
        JSONObject jsonObject = JSON.parseObject(json);
        StringEntity entity1 = new StringEntity(jsonObject.toJSONString(), "utf-8");
        httppost.setEntity(entity1);
        CloseableHttpResponse response = httpclient.execute(httppost); // 执行get请求
        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        System.out.println(content);
        response.close(); // 关闭流和释放系统资源
        return content;
    }

    public String getcoilcraftGet(String str) throws IOException {
        Ip ip = getIP();
        // HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()), "HTTPS");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip.getIp(), Integer.parseInt(ip.getPort())));
        URLConnection connection = new URL(
                "http://www.coilcraft.com/")
                .openConnection(proxy);
//        URLConnection connection = new URL(
//                "http://fanyi.youdao.com/openapi.do?keyfrom=N3verL4nd&key=208118276&type=data&doctype=xml&version=1.1&q=investigate")
//                .openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 compatible; MSIE 5.0;Windows NT; DigExt");
        //  connection.setRequestProperty("Cookie", "__cfduid=d357b69cf87adad91a853eef891336e121596418757; cf_clearance=e09fd72e2a76c5752f2464ad03e2270ae5434e39-1596418762-0-1z34b0f404z2fe1ec99z692003cb-150; ARRAffinity=33f3607a97446f806f98e4431cafc22b9a7249354803fed4d8462ddb15ebe7ea; ai_user=GCaCT|2020-08-03T01:39:25.600Z; ASP.NET_SessionId=oovlmdascgxa4tqbpypxoa2i; Coilcraft_SelectedCurrency=2; Coilcraft_SelectedCompany=1; _ga=GA1.2.395664814.1596418767; _gid=GA1.2.1065569358.1596418767; Coilcraft_ShoppingCartCount=0; CMSCookieLevel=1000; CurrentContact=ca19d6a8-f7f5-415b-9609-7f943c6b4835; _trackatronId=l2nju59gv; _uetsid=d2c4807be50d968829d7653fe5087391; _uetvid=c72eb5ee01b7b4e99a44295bd5a33730; _dc_gtm_UA-1403655-1=1; ai_session=UbMQK|1596424234034.06|1596424234034.06; CMSLandingPageLoaded=true");
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        httpURLConnection.setRequestMethod("GET");
        connection.setRequestProperty("Cookie", "__cfduid=d357b69cf87adad91a853eef891336e121596418757; cf_clearance=e09fd72e2a76c5752f2464ad03e2270ae5434e39-1596418762-0-1z34b0f404z2fe1ec99z692003cb-150; ARRAffinity=33f3607a97446f806f98e4431cafc22b9a7249354803fed4d8462ddb15ebe7ea; ai_user=GCaCT|2020-08-03T01:39:25.600Z; ASP.NET_SessionId=oovlmdascgxa4tqbpypxoa2i; Coilcraft_SelectedCurrency=2; Coilcraft_SelectedCompany=1; _ga=GA1.2.395664814.1596418767; _gid=GA1.2.1065569358.1596418767; Coilcraft_ShoppingCartCount=0; CMSCookieLevel=1000; CurrentContact=ca19d6a8-f7f5-415b-9609-7f943c6b4835; _trackatronId=l2nju59gv; _uetsid=d2c4807be50d968829d7653fe5087391; _uetvid=c72eb5ee01b7b4e99a44295bd5a33730; _dc_gtm_UA-1403655-1=1; ai_session=UbMQK|1596424234034.06|1596424234034.06; CMSLandingPageLoaded=true");
        int httpResult = httpURLConnection.getResponseCode();
        if (httpResult != HttpURLConnection.HTTP_OK) {

            System.out.println(httpResult + "没有连接成功");

        } else {

            System.out.println("连接成功了");

        }
        InputStream response = connection.getInputStream();
        Scanner scanner = new Scanner(response);


        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }


//        str="https://www.coilcraft.com/en-us/products/power/shielded-inductors/molded-inductor/";
//        URL url=new URL("https://www.coilcraft.com/");
//        URLConnection conn =url.openConnection(); // POST要求URL中不包含请求参数
//        conn.setDoOutput(true); // 必须设置这两个请求属性为true，就表示默认使用POST发送
//        conn.setDoInput(true);
//        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
//        conn.setRequestProperty("charsert", "utf-8");
//        conn.setRequestProperty("Cookie","test");
//        // 请求参数必须使用conn获取的OutputStream输出到请求体参数
//        // 用PrintWriter进行包装
//		/*PrintWriter out = new PrintWriter(conn.getOutputStream());
//		out.println(param.getBytes("UTF-8"));*/
//        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
//        //out.write(param);
//        out.flush(); // 立即充刷至请求体）PrintWriter默认先写在内存缓存中
//        String res=null;
//        try// 发送正常的请求（获取资源）
//        {
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//            String line;
//            while ((line = in.readLine()) != null) {
//                res += line + "\n";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(res);


//        URL url=new URL("https://www.coilcraft.com/");
//        System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
//        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
//
//        String str1;
//        while((str1 = bf.readLine()) != null){
//            System.out.println(str1);
//        }

//        HtmlUnitDriver driver = new HtmlUnitDriver();
//        driver.get("https://www.baidu.com/");
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(driver.getPageSource());
//        driver.quit();


        //        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        conn.setConnectTimeout(5*1000);
//        conn.setRequestMethod("GET");
//        InputStream inStream = conn.getInputStream();
//        final StringBuilder builder = new StringBuilder(255);
//        int byteRead;
//        while ((byteRead = inStream.read()) != -1) {
//            builder.append((char) byteRead);
//        }
//        String response = builder.toString();
//        System.out.print("response:"+response);
        //   CloseableHttpClient httpclient = HttpClients.createDefault(); // 创建httpclient实例
//        HttpGet httppost = new HttpGet(str); // 创建httpget实例
//       // httppost.setHeader("X-Requested-With", "XMLHttpRequest");
//        httppost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
////        httppost.setHeader("Content-Type", "application/json;charset=UTF-8");
////        httppost.setHeader("Accept", "application/json, text/plain, */*");
////        httppost.setHeader("Connection", "keep-alive");
////      //  httppost.setHeader("Cookie", "__cfduid=d357b69cf87adad91a853eef891336e121596418757; cf_clearance=e09fd72e2a76c5752f2464ad03e2270ae5434e39-1596418762-0-1z34b0f404z2fe1ec99z692003cb-150; ARRAffinity=33f3607a97446f806f98e4431cafc22b9a7249354803fed4d8462ddb15ebe7ea; ai_user=GCaCT|2020-08-03T01:39:25.600Z; ASP.NET_SessionId=oovlmdascgxa4tqbpypxoa2i; Coilcraft_SelectedCurrency=2; Coilcraft_SelectedCompany=1; _ga=GA1.2.395664814.1596418767; _gid=GA1.2.1065569358.1596418767; Coilcraft_ShoppingCartCount=0; CMSCookieLevel=1000; CurrentContact=ca19d6a8-f7f5-415b-9609-7f943c6b4835; _trackatronId=l2nju59gv; _uetsid=d2c4807be50d968829d7653fe5087391; _uetvid=c72eb5ee01b7b4e99a44295bd5a33730; _dc_gtm_UA-1403655-1=1; ai_session=UbMQK|1596424234034.06|1596424234034.06; CMSLandingPageLoaded=true");
////        httppost.setHeader("Referer","https://www.coilcraft.com/en-us/products/power/shielded-inductors/molded-inductor/");
//        CloseableHttpResponse response = httpclient.execute(httppost); // 执行get请求
//        System.out.println(response.getStatusLine().getStatusCode());
//        HttpEntity entity = response.getEntity(); // 获取返回实体
//        String content = EntityUtils.toString(entity, "utf-8");
//        System.out.println(content);
//        response.close(); // 关闭流和释放系统资源
        return null;
    }

    public String testTtiasiaget() throws IOException {
        // String str = "https://www.ttiasia.com/content/ttiasia/en/apps/part-builder.html";
        //String str = "https://www.ttiasia.com/content/ttiasia/en.html";
        String str = "https://www.rocelec.com/buy/manufacturers";


        HttpGet httpget = new HttpGet(str);
        CloseableHttpResponse response = null;
        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)//设置连接超时时间
                .setSocketTimeout(20000)//设置读取超时时间
                .setConnectionRequestTimeout(20000)
                .setProxy(proxy)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
        try {
            httpget.setConfig(defaultRequestConfig);
            httpget.setHeader("X-Requested-With", "XMLHttpRequest");
            // httpget.setHeader("Cookie", "upMr1YCYuNE3BJ18AAAAAQUIPAAAAAAAeNZeF5KgS2hhnMju/WXzT; incap_ses_1046_731134=B3EcHwtEaE8rSDPzgCOEDk7BJ18AAAAAJeRWmibzNdF1Y0RYHm418A==; _ga=GA1.2.1544031617.1596440914; _gid=GA1.2.1161018729.1596440914; _gat_0b61d0b580ec37f5e241f777c0e1ead3=1; s_fid=7A0ED18C964F59BB-1A2407483BC2CC15; s_cc=true; _pxvid=bd0f8669-d55d-11ea-a67d-0242ac120004; _px3=f701872ae09f40b256bdb4d9232a26cc10e95e9af0008fd0824ee7bbf8518abf:Usi52SwAYf34o3t0tD2hWcGBjBQg/dLvW1jEBYnYmiVNK9d6p2WSZjTPkFCm78KhgxQPw6y7jWMjo9ULiGErSg==:1000:wklxPtt7UjcKj0cWbYvgvrMBBxZUT0ZU5it5MfeMTwEEPPLN48vqBYaJJble+z1yJjT5QIrgjep9X7yiFI3rHECXLHM5oGFig3CCZX607ZXO6QuLHMi+iDwLnxStj4dzxKpL+EvcxZoVL1qa0iLVqcwN7dod/dBHrpEeP0Q3bzI=\n");
            httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");

            response = client.execute(httpget);
            HttpEntity entity = response.getEntity(); // 获取返回实体
            //  setCookieStore(response);
            System.out.println(JSON.toJSONString(response));
            String content = EntityUtils.toString(entity, "utf-8");
            System.out.println(content);
            //ParserArrowJsonData.parser(content);
            response.close(); // 关闭流和释放系统资源
        } finally {
            if (response != null)
                response.close();
        }
        return null;
    }

    public static void setCookieStore(CloseableHttpResponse httpResponse) {
        System.out.println("----setCookieStore");
        CookieStore cookieStore = new BasicCookieStore();
        // JSESSIONID
        String setCookie = httpResponse.getFirstHeader("Set-Cookie")
                .getValue();
        String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
                setCookie.indexOf(";"));
        System.out.println("JSESSIONID:" + JSESSIONID);
        // 新建一个Cookie
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
                JSESSIONID);
        cookie.setVersion(0);
        cookie.setDomain("127.0.0.1");
        cookie.setPath("/CwlProClient");
        // cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
        // cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
        // cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
        // cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
        cookieStore.addCookie(cookie);
    }


    public CloseableHttpResponse getResponseVerical() {
        CloseableHttpResponse response = null;
        Ip ip = null;
        String url = "https://www.digikey.com/en/products/result?keywords=CE3512K2-C1";
        //String url="https://www.digikey.com/en/products/detail/3m-tc/1X6-FMV02/2649845";
        try {
            ip = getIP();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)//设置连接超时时间
                .setSocketTimeout(20000)//设置读取超时时间
                .setConnectionRequestTimeout(20000)
                .setProxy(proxy)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        HttpGet httpget = new HttpGet(url);
        httpget.setConfig(defaultRequestConfig);
        httpget.setHeader("x-currency", "USD");
        httpget.setHeader("accept-language", "en-us");
        //httpget.setHeader("Connection","keep-alive");
        //httpget.setHeader("Sec-Fetch-Dest","empty");
        //httpget.setHeader("Host", "www.digikey.com");
        //httpget.setHeader("Sec-Fetch-Site", "same-origin");
        //httpget.setHeader("x-request-id", "0ae15fa2-53c5-47d0-aec8-44cfc65cdaf6");
        httpget.setHeader("Referer", "https://www.digikey.com/en/products/detail/seeed-technology-co-ltd/402040000/5487753");
        httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build();
        try {
            response = httpClient.execute(httpget);
        } catch (IOException e) {
            return null;
        }
        HttpEntity entity = response.getEntity(); // 获取返回实体
        //  setCookieStore(response);
        //System.out.println(JSON.toJSONString(response));
        String content = null;
        try {
            content = EntityUtils.toString(entity, "utf-8");
            Document document = Jsoup.parse(content);
            System.out.println(document);
//
//            String productJson= String.valueOf(document.select("script[id=__NEXT_DATA__]").first().data());
//            Root root=Root.fill(JSONObject.parseObject(productJson));
//            //System.out.println(JSON.toJSONString(root));
//            Props propsValue=root.getProps();
//            String pagePropsStr=propsValue.getPageProps();
//            JSONObject jsonObjectPageProos=JSONObject.parseObject(pagePropsStr);
//            String envelopeString = jsonObjectPageProos.getString("envelope");
//            String dataString=JSONObject.parseObject(envelopeString).getString("data");
//            System.out.println(dataString);
//            Data data=Data.fill(JSONObject.parseObject(dataString));
            // System.out.println(JSONObject.toJSONString(data.getPriceQuantity().getPricing().get(0).getDigikeyProductNumber()));
            // System.out.println(propsValue.getCorrelationID());
            // System.out.println(document);
            // System.out.println(document.select("script[id=__NEXT_DATA__]").first().childNode(0));
//            System.out.println(document.select("script").first().dataNodes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }


    public static String getMaster() throws IOException, URISyntaxException, KeyManagementException, NoSuchAlgorithmException {
//        String str="https://www.masterelectronics.com/wst/MAM004ws.asmx/GeneralSearch";
        String str = "https://www.masterelectronics.com/wst/MAM004ws.asmx/GeneralSearch?Query=25LC1024-I/SM&InStock=1&ExactMatch=0&Limit=20&username=ME-MAM004&password=MAM004@M1E";

        Ip ip = getIP();
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(15000)//设置连接超时时间
                .setSocketTimeout(15000)//设置读取超时时间
                // .setProxy(proxy)
                .build();
        // "?Query=25LC1024-I/SM
        // &InStock=1
        // &ExactMatch=0
        // &Limit=20
        // &username=ME-MAM004
        // &password=MAM004@M1E";

        SSLContext sslContext = SSLContexts.custom().build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"TLSv1.2"},//解决java1.7不支持TLSv1.2
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        URI uri = new URIBuilder(str)
                .setParameter("Query", "25LC1024-I/SM")
                //.setParameter("Query","100351SCX")
                .setParameter("InStock", "1")
                .setParameter("ExactMatch", "1")
                .setParameter("Limit", "20")
                .setParameter("username", "ME-MAM004")
                .setParameter("password", "MAM004@M1E")
                .build();


        HttpGet httpget = new HttpGet(uri); // 创建httpget实例
        httpget.setConfig(defaultRequestConfig);
        httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36");
        httpget.setHeader(HTTP.CONTENT_TYPE, "text/xml; charset=utf-8");
        CloseableHttpResponse response = httpclient.execute(httpget); // 执行get请求
        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity(); // 获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        //Document document=Jsoup.parse(content);
        // String s=document.getElementsByTag("body");
        System.out.println(content);

        org.jdom2.Document document = null;
        ByteArrayInputStream xmlStream = null;

        try {
            xmlStream = new ByteArrayInputStream(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }

        if (xmlStream != null) {
            try {
                SAXBuilder saxBuilder = new SAXBuilder();
                document = saxBuilder.build(xmlStream);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JDOMException ex) {
                ex.printStackTrace();
            }
        }

        org.jdom2.Element root = document.getRootElement();
        Namespace namespace = Namespace.getNamespace("http://tempuri.org/");
        org.jdom2.Element resultInfo = root.getChild("ResultInfo", namespace);
        if (resultInfo != null) {
            String mpn = resultInfo.getChild("part_number", namespace).getValue();
            System.out.println(mpn);
            String stock = resultInfo.getChild("qoh", namespace).getValue();
            System.out.println(stock);

            String break1 = resultInfo.getChild("break1", namespace).getValue();
            System.out.println(break1);
            String price1 = resultInfo.getChild("price1", namespace).getValue();
            System.out.println(price1);
            for (int i = 1; i <= 8; i++) {
                String break11 = resultInfo.getChild("break" + i, namespace).getValue();
                if (break11.isEmpty()) break;
                String price = resultInfo.getChild("price" + i, namespace).getValue();

            }
            String mult = resultInfo.getChild("OrderIncrSize", namespace).getValue();
            System.out.println(mult);
            String moq = resultInfo.getChild("MOQ", namespace).getValue();
            System.out.println(mult);

        } else {
            System.out.println("没有结果");
        }

        response.close(); // 关闭流和释放系统资源
        return content;
    }

    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {

        //String str="<span style=\"font-family:宋体, Arial, Helvetica, sans-serif;font-size: 15px; line-height: 22px;\">1999年硕士毕业于上海第二医科大学，2009年获神经外科学博士学位。2003-2004年在巴黎第七大学进行为期一年的研修工作，多次参加国际及国内耳鼻咽喉头颈外科学术交流。</span>";
//        String str="<p>&nbsp; &nbsp; &nbsp; &nbsp;上海长海医院位于东海之滨、黄浦江畔，创建于1949年7月，始称华东军区人民医学院附属医院，1951年7月改称第二军医大学附属医院，1958年9月建制为第二军医大学第一附属医院，经过65年的建设发展，已经成为一所学科门类齐全医疗特色鲜明、综合实力强劲的现代化大型综合性医院。</p><p>医院占地23.4万平方米，建筑面积68万平方米，医院下辖一个中医系、57个科室，展开床位2100张，1993年医院首批评为三级甲等医院，1999年荣膺“全国百佳医院”称号，先后被评为全国“支援西部地区医院工作先进单位”，全军“思想政治建设先进单位”、“医院文化建设先进单位”、“为部队服务先进医院”、“创先争优全军试点单位”，连续第16届被评为“上海市文明单位”，在上海市医疗行业“万人问卷调查”中连续6年位居上海市第一。</p><p>医院医疗服务特色鲜明，拥有亚洲一流水平的门急诊大楼，全球最先进的达芬奇手术机器人系统，综合技术最先进的消化内镜诊疗中心，沪上首台640层动态容积CT以及新型PET/CT分子影像技术设备，全球最先进的立体定向放射治疗设备。自主研发具有知识产权的国产室间隔缺损封堵器、世界上首创的“孙氏输尿管镜”、全球首台可定位遥控的胶囊内镜机器人，赢得世界喝彩，服务千百万患者。2013年医院门急诊总量首次突破300万人次，医疗发生额突破30亿，手术4.69万例次，出院人数8.86万人次，平均住院日7.8天。</p><p>医院学科人才积淀丰厚，现有中国工程院院士1名，专家教授253名，其中一级教授4名，二级教授2名，三级教授7名，博士生导师67名，硕士生导师102名，拥有硕士学位授权点46个、博士学位授权点42个，博士后流动站3个，是国家执业医师资格考试临床实践技能类考试基地、上海市住院医师规范化培训全科临床基地，全国、全军护理优质服务示范医院、临床药理基地。现有国家临床医学研究中心1个,国家级重点学科15个，国家卫生部临床重点专科5个，全军医学专科研究所（中心）18个、重点实验室2个，全军或上海市护理示范基地10个，上海市重点学科1个、医学重点学科2个、临床医学中心2个、专业质控中心7个、示范学科1个，胰腺疾病和前列腺疾病诊疗团队入选“国家教育部创新团队”。</p><p>医院科技创新成就斐然，“十一五”以来，医院共获得“973计划”、“863计划”、全军科研重大项目、国家自然科学基金等各类科研项目1156项，经费超过5.99亿元；获省部级以上科技成果170项，其中国家科技进步一等奖1项、二等奖以上成果11项、何梁何利科技与进步奖3项；发表SCIE收录论文1809篇，总影响因子5194.795分。创办了《中华胰腺病杂志》、《中西医结合学报》、《药学服务与研究》、《Journal of Interventional Gastroenterology》四本学术期刊。</p><p>21世纪的长海，秉承接轨国际、同步世界的理念，加速国际化建设进程。医院每年组织的国际学术会议多大30余次，承办第15届世界胃肠病大会、国际泌尿外科学术大会、胰腺大会、腔内血管学大会、东方脑血管大会、东方呼吸病论坛、国际世界华人消化内科大会、国际肛肠周等都成为本领域最高水平的国际学术会议。同时，与美国威克森林大学、斯坦福大学、克利夫兰医学中心、英国剑桥大学、加拿大麦吉尔大学、新加坡中央医院等20多个国家的知名医疗机构建立高水平科技合作关系，极大提升了医院国际交流平台和学术品牌影响力，彰显了长海“大院、名院、强院”的良好形象。</p><p>岁月芳华，沧海长歌，长海医院将始终恪守“救死扶伤、服务军民”的使命宗旨，秉承“锲而不舍、一往无前、直取巅峰”的大师精神和“各类人员齐聚长海，平等共创美好明天”办院理念，励精图治，奋勇登峰、追求卓越，朝着建设“军事特色鲜明国际化创新型医院”的目标而昂扬奋进。</p>";
//        Document document=Jsoup.parse(str);
//        Elements ps=document.getElementsByTag("p");
//        for (Element p : ps) {
//            System.out.println(p.text());
//        }

        getResponseTI();


    }

    public static CloseableHttpResponse getXinHua() {
        CloseableHttpResponse response = null;
        Ip ip = null;
        //String url = "http://www.xinhuanet.com/";
       // String url = "http://cn.chinadaily.com.cn/";
       // String url = "https://www.cctv.com/";
        //String url = "https://www.qq.com/";
        String url = "https://www.zaikostore.com/zaikostore/en/cStockSpecList?cid1=ct100037&cid2=ct201011&cid3=ct300743&pv1=ROHM";
        url = "https://www.zaikostore.com/zaikostore/en/special/parts/listOfModelNumbers?makerName=ROHM";
        url = "https://www.zaikostore.com/zaikostore/en/cStockSpecList?cid1=ct100037&cid2=ct201011&cid3=ct300753&pv1=ROHM&__cf_chl_captcha_tk__=14271ccf1debd98bc0d5244070b5d044eb21cad1-1616556851-0-AduJ__eaVx3W1vDq2x69fXvDlRRbrXe-ttbdNXu_PIYW8e5yKveIs4zszlucCYVfREUdPWUWUlnEfPTBVgJQe-fyGNa-vdflQPLhYL7_3oHCMCVUd1lBNTJAyO3Vrm6AkunF6qtvyE1VJy71ysmqW8vHq4tdihdyN-IWthyEFEuux7nNDChKp90VfQr_dnAENTGNkTI7XBaHNTWHfMG6JOokeRVVXbsWTuoYMUtvgRz1lnJY7xZgl5x5yg19zSLX2wr8ZqdVEWX6-9oRd5J9FQJ9cIwO-QSsymfU25LxiR2Pd1lPNa5rALv48n6NNjSbMaUZBjUu3JiotHCSq3EPRPsTSYJg_4fztU6YoNsb3HUYf4ps7YcDJYGD-FQCgOrs4231DFVQr42VG_6lmGxUrWgpLp_38ZGMlgg__oUjcxZCLRHnahNk3dJsiV2Q6sWy63IXQJ1oH3TK3qobqAi1TdNxx3NqOVvUhGHV3cyJ7CODXsyQYchEryorj8ydvbGpcuSBn0mOkE-sY_TePR6o07o3tESQe7HmNFyPFQc5NQMEISIcqvcGJUrw7nSVb-gNrLYjB18-0YnlzHYzli8PgDtUwNTRAFKlrHABy6pNXaNnLwf6fQ5mRY5S_iM_ywn_uuoLb_IGBrhzOJlATJVIyCoF79_GuwbI0XgX47fJ-FvZOHDGaVjp_D8s919OlSpC997wpUT8Ym0moXaa-upx5fU";
        url = "https://www.zaikostore.com/zaikostore/en/ROHM";
        //String url = "http://tmisc.home.news.cn/da/js/config/global-cfg.js";
        //String url = "http://www.xinhuanet.com/politics/2020-12/22/c_1126894051.htm";
        //String url="https://www.digikey.com/en/products/detail/3m-tc/1X6-FMV02/2649845";
        try {
            ip = getIP();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)//设置连接超时时间
                .setSocketTimeout(20000)//设置读取超时时间
                .setConnectionRequestTimeout(20000)
                .setProxy(proxy)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        HttpGet httpget = new HttpGet(url);
        httpget.setConfig(defaultRequestConfig);

        httpget.setHeader("Host", "www.zaikostore.com");
        httpget.setHeader("Connection","keep-alive");
        httpget.setHeader("Upgrade-Insecure-Requests","1");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36");
        httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        httpget.setHeader("Sec-Fetch-Site","none");
        httpget.setHeader("Sec-Fetch-Mode","navigate");
        httpget.setHeader("Sec-Fetch-Dest","document");
        httpget.setHeader("Referer", "https://www.zaikostore.com/zaikostore/en/ROHM");
        //httpget.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpget.setHeader("Accept-Languag", "zh-CN,zh;q=0.9");
        httpget.setHeader("Cookie", "JSESSIONID=8F26A5286160017C716F0286E3C77D36.jvm2; __cfduid=dc754aec6b3ac1680b4a6e6891f40b2631616569040; cf_chl_2=0c66078a6150b75; cf_chl_prog=b; __cfruid=9cf73f40422dfe8a849ddb1802fc27cafb2b7805-1616571235; _gcl_au=1.1.436606636.1616571234; _rslgvry=7dc22354-4503-4d0c-9769-2dae151e2cbc; _ga=GA1.2.871436982.1616571235; _gid=GA1.2.555773770.1616571235; _gat=1; __na_s_i=d3bad534dd5e4003b5af4d2498c75816; __na_s_t=1616571234412; __na_s_n=1; __na_s_c=https%3A%2F%2Fwww.zaikostore.com%2Fzaikostore%2Fen%2FcStockSpecList%3Fcid1%3Dct100037%26cid2%3Dct201010%26cid3%3Dct300738%26pv1%3DROHM%26pageNumber%3D4; __na_p_n=1; __na_p_t=1616571234412; __na_u_i=97cb975ed3c0469d9c6e7a1cc9d5edcb; __na_c_s=www.zaikostore.com; __na_c_m=referral; __na_c_c=; __na_c_k=; __cf_bm=bab15c316715a52491846a9e66cc081f861e50e4-1616571239-1800-AUzjAuHqGo1nhXcoD34uydIYEcqLCnf/YkCp3Hjj3+5fW9hyUz6yjlTXdoUwFFKGQcXQQ0gVszZf+xYswgQT11sJjqAzRGBX1Hjc0CKHC4QYerL8mnhdZa8zqEg/6uJhLoMOZIsIVgkfSJ5XpgX2nvb1162DkLa0ZhJ64BbY4V7b0e3sYSj7KnjoA3JAJyJ5Hw==; _ts_yjad=1616571236996; __ulfpc=202103241533576540");
           CloseableHttpClient httpClient = HttpClients.custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build();
        try {
            response = httpClient.execute(httpget);
        } catch (IOException e) {
            return null;
        }
        HttpEntity entity = response.getEntity(); // 获取返回实体
        //  setCookieStore(response);
        //System.out.println(JSON.toJSONString(response));
        String content = null;
        try {
            content = EntityUtils.toString(entity, "utf-8");
            Document document = Jsoup.parse(content);
            System.out.println(document);
//
//            String productJson= String.valueOf(document.select("script[id=__NEXT_DATA__]").first().data());
//            Root root=Root.fill(JSONObject.parseObject(productJson));
//            //System.out.println(JSON.toJSONString(root));
//            Props propsValue=root.getProps();
//            String pagePropsStr=propsValue.getPageProps();
//            JSONObject jsonObjectPageProos=JSONObject.parseObject(pagePropsStr);
//            String envelopeString = jsonObjectPageProos.getString("envelope");
//            String dataString=JSONObject.parseObject(envelopeString).getString("data");
//            System.out.println(dataString);
//            Data data=Data.fill(JSONObject.parseObject(dataString));
            // System.out.println(JSONObject.toJSONString(data.getPriceQuantity().getPricing().get(0).getDigikeyProductNumber()));
            // System.out.println(propsValue.getCorrelationID());
            // System.out.println(document);
            // System.out.println(document.select("script[id=__NEXT_DATA__]").first().childNode(0));
//            System.out.println(document.select("script").first().dataNodes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }



    public static String getaisawPost(String str) {
        str = "https://www.taisaw.com/en/product_s.php?p=1";
        Ip ip = null;
        CloseableHttpResponse response = null; // 执行get请求
        String content = null;
        try {
            ip = getIP();
            HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setConnectTimeout(20000)//设置连接超时时间
                    .setSocketTimeout(20000)//设置读取超时时间
                    .setConnectionRequestTimeout(20000)
                    .setProxy(proxy)
                    .build();

            HttpPost httppost = new HttpPost(str); // 创建httpget实例
            httppost.setConfig(defaultRequestConfig);
            httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
            httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("k", "TA1804A"));
            //使用 loadTrustMaterial() 方法实现一个信任策略，信任所有证书
            SSLContext sslContext = null;
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            //NoopHostnameVerifier类:  作为主机名验证工具，实质上关闭了主机名验证，它接受任何
            //有效的SSL会话并匹配到目标主机。
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            httppost.setEntity(new UrlEncodedFormEntity(nvps));
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity(); // 获取返回实体
            content = EntityUtils.toString(entity, "utf-8");
            Document document = Jsoup.parse(content);
            String s = document.getElementById("apDiv2").select("tr").first().select("td").first().select("a").attr("href");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }finally {
            if(response!=null){
                try {
                    response.close(); // 关闭流和释放系统资源
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return content;
    }


    public static CloseableHttpResponse getResponseTI() throws KeyManagementException, NoSuchAlgorithmException {
        CloseableHttpResponse response = null;
        Ip ip = null;
        String url = "https://www.ti.com/store/ti/zh/p/product/?p=XI1443QGABL&keyMatch=XI1443QGABL&tisearch=search-everything&usecase=OPN";
        //String url="https://www.digikey.com/en/products/detail/3m-tc/1X6-FMV02/2649845";
        try {
            ip = getIP();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)//设置连接超时时间
                .setSocketTimeout(20000)//设置读取超时时间
                .setConnectionRequestTimeout(20000)
                .setProxy(proxy)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        HttpGet httpget = new HttpGet(url);
        httpget.setConfig(defaultRequestConfig);
        httpget.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) C hrome/66.0.3359.139 Safari/537.36");
        SSLContext sslContext = org.apache.http.conn.ssl.SSLContexts.custom().build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"TLSv1.1", "TLSv1.2"},//解决java1.7不支持TLSv1.2
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e) {
            return null;
        }
        HttpEntity entity = response.getEntity(); // 获取返回实体
        System.out.println(response.getStatusLine().getStatusCode());
        String content = null;
        try {
            content = EntityUtils.toString(entity, "utf-8");
            Document document = Jsoup.parse(content);
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static CloseableHttpResponse getResponseYunhan() throws KeyManagementException, NoSuchAlgorithmException {
        CloseableHttpResponse response = null;
        Ip ip = null;
        String url = "https://www.mouser.com/Optoelectronics/Cameras-Accessories/Camera-Accessories/_/N-fb8vc";
        //String url="https://www.baidu.com/";
        try {
            ip = getIP();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHost proxy = new HttpHost(ip.getIp(), Integer.parseInt(ip.getPort()));
        //HttpHost proxy = new HttpHost("14.134.188.250", 4272);
        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(20000)//设置连接超时时间
                .setSocketTimeout(20000)//设置读取超时时间
                .setConnectionRequestTimeout(20000)
                .setProxy(proxy)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        HttpGet httpget = new HttpGet(url);
        httpget.setConfig(defaultRequestConfig);


        httpget.setHeader("Cookie", "https://www.mouser.com/Electronic-Components/");
        httpget.setHeader("Connection", "keep-alive");
        httpget.setHeader("Upgrade-Insecure-Requests", "1");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36");
        httpget.setHeader("Sec-Fetch-Site", "cross-site");
        httpget.setHeader("Sec-Fetch-Mode", "navigate");
        httpget.setHeader("Sec-Fetch-User", "?1");
        httpget.setHeader("Sec-Fetch-Dest", "document");
        httpget.setHeader("Accept", " text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.9");


//        SSLContext sslContext = org.apache.http.conn.ssl.SSLContexts.custom().build();
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                sslContext,
//                new String[]{"TLSv1.1"},//解决java1.7不支持TLSv1.2
//                null,
//                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().build();
                //custom().setSSLSocketFactory(sslsf).build();
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        HttpEntity entity = response.getEntity(); // 获取返回实体
        System.out.println(response.getStatusLine().getStatusCode());
        String content = null;
        try {
            content = EntityUtils.toString(entity, "utf-8");
            Document document = Jsoup.parse(content);
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}

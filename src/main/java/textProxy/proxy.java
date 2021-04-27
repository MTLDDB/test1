package textProxy;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class proxy {
    public static void main(String args[]) throws IOException, URISyntaxException {
/**
 * HttpClient期望传输请求到目标服务器并返回对应的响应对象
 * 很简单的请求执行过程的示例
 */
//        HttpClient httpclient = HttpClientBuilder.create().build();
//        HttpGet httpget1 = new HttpGet("http://www.baidu.com");
//        HttpResponse response1 = httpclient.execute(httpget1);
//        HttpEntity entity = response1.getEntity();
//        if (entity != null) {
//            InputStream instream = entity.getContent();
//
//            int l;
//            byte[] tmp = new byte[2048];
//            while ((l = instream.read(tmp)) != -1) {
//                System.out.println(instream.read());
//            }
//        }

/**
 * 所有HTTP请求有一个组合了方法名，请求URI和HTTP协议版本的请求行。
 * HttpClient支持所有定义在HTTP/1.1版本中的HTTP方法：GET，HEAD，POST，PUT，DELETE，TRACE和OPTIONS。
 * 对于每个方法类型都有一个特殊的类：HttpGet，HttpHead，HttpPost，HttpPut，HttpDelete，HttpTrace和HttpOptions。
 * 请求的URI是统一资源定位符，它标识了应用于哪个请求之上的资源。
 * HTTP请求URI包含一个协议模式，主机名称，可选的端口，资源路径，可选的查询和可选的片段。
 */
//        URI uri = new URI("http", null,"www.google.com", -1, "/search","q=httpclient&btnG=Google+Search&aq=f&oq=", null);
//        HttpGet httpget = new HttpGet(uri);
//        System.out.println(httpget.getURI());
/**
 * 查询字符串也可以从独立的参数中来生成
 */
//        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
//        qparams.add(new BasicNameValuePair("q", "httpclient"));
//        qparams.add(new BasicNameValuePair("btnG", "Google Search"));
//        qparams.add(new BasicNameValuePair("aq", "f"));
//        qparams.add(new BasicNameValuePair("oq", null));
//        URI uri = new URI("http",null , "www.google.com", -1, "/search",
//                URLEncodedUtils.format(qparams, "UTF-8"), null);
//        HttpGet httpget = new HttpGet(uri);
//        System.out.println(httpget.getURI());



/**
 * HTTP响应是由服务器在接收和解释请求报文之后返回发送给客户端的报文。
 * 响应报文的第一行包含了协议版本，之后是数字状态码和相关联的文本段。
 */
//        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
//                HttpStatus.SC_OK, "OK");
//        System.out.println(response.getProtocolVersion());
//        System.out.println(response.getStatusLine().getStatusCode());
//        System.out.println(response.getStatusLine().getReasonPhrase());
//        System.out.println(response.getStatusLine().toString());
//
/**
 * 处理报文头部
 * 一个HTTP报文可以包含很多描述如内容长度，内容类型等信息属性的头部信息。
 * HttpClient提供获取，添加，移除和枚举头部信息的方法。
 */
//        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK, "OK");
//        response.addHeader("Set-Cookie",
//                "c1=a; path=/; domain=localhost");
//        response.addHeader("Set-Cookie",
//                "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
//        Header h1 = response.getFirstHeader("Set-Cookie");
//        System.out.println(h1);
//        Header h2 = response.getLastHeader("Set-Cookie");
//        System.out.println(h2);
//        Header[] hs = response.getHeaders("Set-Cookie");
//        System.out.println(hs.length);

/**
 * 使用HttpClient发送请求、接收响应
 *
 * 1.一般需要如下几步:
 * (1) 创建HttpClient对象。
 * (2)创建请求方法的实例，并指定请求URL。如果需要发送GET请求，创建HttpGet对象；如果需要发送POST请求，创建HttpPost对象。
 * (3) 如果需要发送请求参数，可调用HttpGet、HttpPost共同的setParams(HetpParams params)方法来添加请求参数；
 * 对于HttpPost对象而言，也可调用setEntity(HttpEntity entity)方法来设置请求参数。
 * (4) 调用HttpClient对象的execute(HttpUriRequest request)发送请求，该方法返回一个HttpResponse。
 * (5) 调用HttpResponse的getAllHeaders()、getHeaders(String name)等方法可获取服务器的响应头；
 * 调用HttpResponse的getEntity()方法可获取HttpEntity对象，该对象包装了服务器的响应内容。
 * 程序可通过该对象获取服务器的响应内容。
 * (6) 释放连接。无论执行方法是否成功，都必须释放连接
 */
//        List<NameValuePair> formparams=new ArrayList<NameValuePair>();
//        formparams.add(new BasicNameValuePair("account",""));//传递参数
//        formparams.add(new BasicNameValuePair("password",""));
//        HttpEntity reqEntity=new UrlEncodedFormEntity(formparams,"utf-8");//对参数进行编码
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setSocketTimeout(5000)
//                .setConnectTimeout(5000)
//                .setConnectionRequestTimeout(5000)
//                .build();
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpPost post = new HttpPost("http://baidu.com");
//        post.setEntity(reqEntity);
//        post.setConfig(requestConfig);
//        HttpResponse response = client.execute(post);
//        System.out.println(response.getEntity());
//
//        if (response.getStatusLine().getStatusCode() == 200) {
//            HttpEntity resEntity = response.getEntity();
//            String message = EntityUtils.toString(resEntity, "utf-8");
//            System.out.println(message);
//        } else {
//            System.out.println("请求失败");
//        }
        /**
         * GET---无参测试
         *
         *
         */

//            // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//            // 创建Get请求
//            HttpGet httpGet = new HttpGet("http://www.baidu.com");
//
//            // 响应模型
//            CloseableHttpResponse response = null;
//            try {
//                // 由客户端执行(发送)Get请求
//                response = httpClient.execute(httpGet);
//                // 从响应模型中获取响应实体
//                HttpEntity responseEntity = response.getEntity();
//                System.out.println("响应状态为:" + response.getStatusLine());
//                if (responseEntity != null) {
//                    System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                    System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//                }
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    // 释放资源
//                    if (httpClient != null) {
//                        httpClient.close();
//                    }
//                    if (response != null) {
//                        response.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

        /**
         * GET---有参测试 (方式二:将参数放入键值对类中,再放入URI中,从而通过URI得到HttpGet实例)
         *
         * @date 2018年7月13日 下午4:19:23
         */
//
//            // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//            // 参数
//            URI uri = null;
//            try {
//                // 将参数放入键值对类NameValuePair中,再放入集合中
//                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("name", "&"));
//                params.add(new BasicNameValuePair("age", "18"));
//                // 设置uri信息,并将参数集合放入uri;
//                // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
//                uri = new URIBuilder().setScheme("http").setHost("localhost")
//                        .setPort(12345).setPath("/doGetControllerTwo")
//                        .setParameters(params).build();
//            } catch (URISyntaxException e1) {
//                e1.printStackTrace();
//            }
//            // 创建Get请求
//            HttpGet httpGet = new HttpGet(uri);
//
//            // 响应模型
//            CloseableHttpResponse response = null;
//            try {
//                // 配置信息
//                RequestConfig requestConfig = RequestConfig.custom()
//                        // 设置连接超时时间(单位毫秒)
//                        .setConnectTimeout(5000)
//                        // 设置请求超时时间(单位毫秒)
//                        .setConnectionRequestTimeout(5000)
//                        // socket读写超时时间(单位毫秒)
//                        .setSocketTimeout(5000)
//                        // 设置是否允许重定向(默认为true)
//                        .setRedirectsEnabled(true).build();
//
//                // 将上面的配置信息 运用到这个Get请求里
//                httpGet.setConfig(requestConfig);
//
//                // 由客户端执行(发送)Get请求
//                response = httpClient.execute(httpGet);
//
//                // 从响应模型中获取响应实体
//                HttpEntity responseEntity = response.getEntity();
//                System.out.println("响应状态为:" + response.getStatusLine());
//                if (responseEntity != null) {
//                    System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                    System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//                }
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    // 释放资源
//                    if (httpClient != null) {
//                        httpClient.close();
//                    }
//                    if (response != null) {
//                        response.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }





//        Proxy proxy =new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1",9876));
//        URL obj = null;
//        obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
//        System.out.println(con);

//        HttpGet httpget = new HttpGet("http://www.baidu.com");
//        URI url=httpget.getURI();
//        HttpHost proxy =new HttpHost("45.32.21.237",8888,"HTTP");
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpGet request =new HttpGet(url);
//        System.out.println("rrr"+request);
//        try {
//            CloseableHttpResponse response = httpclient.execute(proxy,request);
//            System.out.println(response+"ll");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        HttpGet request =new HttpGet(url);
//
//        request.setConfig(RequestConfig.custom().setProxy(new HttpHost("127.0.0.1",8888,"HTTP")).build()
//);
//
//        try {
//            CloseableHttpResponse response = httpclient.execute(request);
//            System.out.println(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//
//        HttpHost proxy = new HttpHost("127.0.0.1", 9876, "HTTPs");
//        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
//        CloseableHttpClient httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
//        HttpGet request = new HttpGet(url);
//        try {
//            CloseableHttpResponse response = httpclient.execute(request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //设置代理IP、端口、协议（请分别替换）
        HttpHost proxy = new HttpHost("111.231.94.44",8888,"HTTP");

        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .build();

        //实例化CloseableHttpClient对象
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

        //访问目标地址
        HttpGet httpGet = new HttpGet("https://www.xicidaili.com/");

        //请求返回
        CloseableHttpResponse httpResp = httpclient.execute(httpGet);
        try {
            System.out.println(httpResp.getEntity());
            String htmlbody = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
            System.out.println(htmlbody);
            int statusCode = httpResp.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                System.out.println("成功");
            }
        } catch (Exception e) {

        } finally {
            httpResp.close();
        }



    }
}

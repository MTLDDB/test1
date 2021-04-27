package textJsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import test.proxyip.Ip;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class Spider {
    public static void main(String[] args) throws IOException {
        String url = "https://www.mouser.com/Electromechanical/Industrial-Automation/Controllers/_/N-5g1z";
        Ip ip = Main.getIP();
        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ip.getIp(), Integer.parseInt(ip.getPort())));
        Document document = Jsoup.connect(url).proxy(proxy).timeout(20000).get();


//通过Document的select方法获取属性结点集合
        Elements elements = document.select("a");
//得到节点的第一个对象
        Element element = elements.get(0);
        System.out.println(element);

    }
}

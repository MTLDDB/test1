package textACK;

import test.proxyip.Ip;
import test.proxyip.ProxyIp;

import java.util.List;
import java.util.Random;

public class test11 {
    public static void main(String args[]){
        ProxyIp pp=new ProxyIp();
        List<Ip> lists = pp.getIp();
        Ip ip=lists.get(new Random().nextInt(lists.size()));
        System.out.println(ip.getIp());
    }
}

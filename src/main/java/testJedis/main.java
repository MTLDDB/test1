package testJedis;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

import java.util.*;

public class main {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        jedis.publish("chat","hhhh");
        jedis.setex("life", 60*60*24, "享受美好");
        while(jedis.exists("life")){
            System.out.println(jedis.get("life"));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //String
//        jedis.set("runoobkey", "www.runoob.com");
//        // 获取存储的数据并输出
//        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
//
        //List
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
//
//        //hash
//        Map<String,String> map=new HashMap<>();
//        map.put("test1","jjjjj");
//        jedis.hmset("hash",map);
//        Map<String,String> map2=jedis.hgetAll("hash");
//        Iterator<String> iterator=map2.keySet().iterator();
//        while(iterator.hasNext()){
//            String key=iterator.next();
//            System.out.println("map2-key="+key);
//            System.out.println("map2-value="+map2.get(key));
//        }
//        String  map1=jedis.hget("hash","test1");
//        System.out.println("hash-value:"+map1);
//
//        //set
//        jedis.sadd("set","testset1");
//        jedis.sadd("set","testset2");
//        jedis.sadd("set","testset3");
//        Set<String> stringSet=jedis.smembers("set");
//        Iterator<String> iterator1=stringSet.iterator();
//        while(iterator1.hasNext()){
//            System.out.println("set-value="+iterator1.next());
//        }
//
//        //zset
        jedis.zadd("zset",0,"testset1");
        jedis.zadd("zset",1,"testset2");
        jedis.zadd("zset",2,"testset3");
        Set<String> stringZSet=jedis.zrangeByScore("zset",0,1);
        Iterator<String> iteratorz=stringZSet.iterator();
        while(iteratorz.hasNext()){
            System.out.println("zset-value="+iteratorz.next());
        }
        jedis.publish("chat", JSON.toJSONString(stringZSet));
//        //
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
           // jedis.publish("chat",key);
        }
    }
}

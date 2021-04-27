package textHashMap;

import java.util.concurrent.ConcurrentHashMap;

public class test {
    private static ConcurrentHashMap<String, test> ctrls = new ConcurrentHashMap();
    private String key;
    private static String value="0";
    public static String getValue() {
        return value;
    }

    public static test get(String key){

        if(ctrls.containsKey(key)){
            System.out.println(ctrls.get(key).getValue()+"LLLL");
            return ctrls.get(key);
        }
        test t=new test();
        ctrls.put(key,t);
        return t;
    }
    test(){
        value=value+1;
    }
}

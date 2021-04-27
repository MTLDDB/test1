package testJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Test {

    public static void main(String[] args) {
        String[] strings={"test","6LF22/9V/MN1604(K1)C&B"};
        X x=new X();
        x.setName("6LF22/9V/MN1604(K1)C&B");
        String jsonString= JSON.toJSONString(x);
        System.out.println(jsonString);

        JSONObject json = JSONObject.parseObject(jsonString);
        System.out.println(json.getString("name"));
    }

    static class X {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

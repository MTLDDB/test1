package textJsoup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class testX {

    public static void  main(String args[]) {
//        String str = "0123456789ABCDEF";
////        String hexStr = "\\x57\\x69\\x64\\x65\\x76\\x69\\x6e\\x65\\x20\\x43\\x6f\\x6e\\x74\\x65\\x6e\\x74\\x20\\x44\\x65\\x63\\x72\\x79\\x70\\x74\\x69\\x6f\\x6e\\x20\\x4d\\x6f\\x64\\x75\\x6c\\x65".replace("\\x","");
////        char[] hexs = hexStr.toCharArray();
////        byte[] bytes = new byte[hexStr.length() / 2];
////        int n;
////        for (int i = 0; i < bytes.length; i++) {
////            n = str.indexOf(hexs[2 * i]) * 16;
////            n += str.indexOf(hexs[2 * i + 1]);
////            bytes[i] = (byte) (n & 0xff);
////        }
////        System.out.println(new String(bytes));
        String s="{\"parameters\":[{\"key\":\"search_term\",\"values\":[\"*\"]},{\"key\":\"part_category_id\",\"values\":[768]},{\"key\":\"start_index\",\"values\":[0]},{\"key\":\"quantity_min\",\"values\":[0]},{\"key\":\"facet_field\",\"values\":[\"manufacturer_id\",\"category_unique\"]}]}";
        JSONObject jsonObject=JSONObject.parseObject(s,JSONObject.class);
        System.out.println(jsonObject.getString("parameters"));
        String p=jsonObject.getString("parameters");
        JSONArray jsonArray=JSONArray.parseArray(p);

        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonTemp=JSONObject.parseObject(JSON.toJSONString(jsonArray.get(i)),JSONObject.class);
            System.out.println(jsonTemp.get("key"));
            System.out.println(jsonTemp.get("values"));
            System.out.println("+++++++++");
        }


    }
}
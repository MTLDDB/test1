package test.JSONModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Suppliers
{
    private String name;

    private int id;

    private String url;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public static Suppliers fill(JSONObject jsonobj){
        Suppliers entity = new Suppliers();
        if (jsonobj.containsKey("name")) {
            entity.setName(jsonobj.getString("name"));
        }
        if (jsonobj.containsKey("id")) {
            entity.setId(jsonobj.getInteger("id"));
        }
        if (jsonobj.containsKey("url")) {
            entity.setUrl(jsonobj.getString("url"));
        }
        return entity;
    }
    public static List<Suppliers> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<Suppliers> olist = new ArrayList<Suppliers>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}

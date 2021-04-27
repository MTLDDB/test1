package test.JSONModel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class Categories
{
    private String id;

    private String label;

    private String url;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getLabel(){
        return this.label;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public static Categories fill(JSONObject jsonobj){
        Categories entity = new Categories();
        if (jsonobj.containsKey("id")) {
            entity.setId(jsonobj.getString("id"));
        }
        if (jsonobj.containsKey("label")) {
            entity.setLabel(jsonobj.getString("label"));
        }
        if (jsonobj.containsKey("url")) {
            entity.setUrl(jsonobj.getString("url"));
        }
        return entity;
    }
    public static List<Categories> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<Categories> olist = new ArrayList<Categories>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}

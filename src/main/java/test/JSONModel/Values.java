package test.JSONModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Values
{
    private String id;

    private String value;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    public static Values fill(JSONObject jsonobj){
        Values entity = new Values();
        if (jsonobj.containsKey("id")) {
            entity.setId(jsonobj.getString("id"));
        }
        if (jsonobj.containsKey("value")) {
            entity.setValue(jsonobj.getString("value"));
        }
        return entity;
    }
    public static List<Values> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<Values> olist = new ArrayList<Values>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}

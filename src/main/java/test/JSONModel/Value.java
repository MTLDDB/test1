package test.JSONModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Value
{
    private String label;

    private String value;

    public void setLabel(String label){
        this.label = label;
    }
    public String getLabel(){
        return this.label;
    }
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    public static Value fill(JSONObject jsonobj){
        Value entity = new Value();
        if (jsonobj.containsKey("label")) {
            entity.setLabel(jsonobj.getString("label"));
        }
        if (jsonobj.containsKey("value")) {
            entity.setValue(jsonobj.getString("value"));
        }
        return entity;
    }
    public static List<Value> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<Value> olist = new ArrayList<Value>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}

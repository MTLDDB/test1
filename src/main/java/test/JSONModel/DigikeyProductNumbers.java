package test.JSONModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DigikeyProductNumbers
{
    private String type;

    private List<Value> value;

    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setValue(List<Value> value){
        this.value = value;
    }
    public List<Value> getValue(){
        return this.value;
    }
    public static DigikeyProductNumbers fill(JSONObject jsonobj){
        DigikeyProductNumbers entity = new DigikeyProductNumbers();
        if (jsonobj.containsKey("type")) {
            entity.setType(jsonobj.getString("type"));
        }
        if (jsonobj.containsKey("value")) {
            entity.setValue(Value.fillList(jsonobj.getJSONArray("value")));
        }
        return entity;
    }
    public static List<DigikeyProductNumbers> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<DigikeyProductNumbers> olist = new ArrayList<DigikeyProductNumbers>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}

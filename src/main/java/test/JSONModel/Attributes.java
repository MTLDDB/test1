package test.JSONModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Attributes
{
    private boolean isFilterable;

    private List<Values> values;

    private String id;

    private String label;

    public void setIsFilterable(boolean isFilterable){
        this.isFilterable = isFilterable;
    }
    public boolean getIsFilterable(){
        return this.isFilterable;
    }
    public void setValues(List<Values> values){
        this.values = values;
    }
    public List<Values> getValues(){
        return this.values;
    }
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
    public static Attributes fill(JSONObject jsonobj){
        Attributes entity = new Attributes();
        if (jsonobj.containsKey("isFilterable")) {
            entity.setIsFilterable(jsonobj.getBoolean("isFilterable"));
        }
        if (jsonobj.containsKey("values")) {
            entity.setValues(Values.fillList(jsonobj.getJSONArray("values")));
        }
        if (jsonobj.containsKey("id")) {
            entity.setId(jsonobj.getString("id"));
        }
        if (jsonobj.containsKey("label")) {
            entity.setLabel(jsonobj.getString("label"));
        }
        return entity;
    }
    public static List<Attributes> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<Attributes> olist = new ArrayList<Attributes>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}

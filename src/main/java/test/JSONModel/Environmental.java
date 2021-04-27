package test.JSONModel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Environmental
{
    private List<DataRows> dataRows;

    private List<String> dataHeaders;

    private String title;

    public void setDataRows(List<DataRows> dataRows){
        this.dataRows = dataRows;
    }
    public List<DataRows> getDataRows(){
        return this.dataRows;
    }
    public void setDataHeaders(List<String> dataHeaders){
        this.dataHeaders = dataHeaders;
    }
    public List<String> getDataHeaders(){
        return this.dataHeaders;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public static Environmental fill(JSONObject jsonobj){
        Environmental entity = new Environmental();
//        if (jsonobj.containsKey("dataRows")) {
//            entity.setDataRows(jsonobj.getJSONArray("dataRows"));
//        }
//        if (jsonobj.containsKey("dataHeaders")) {
//            entity.setDataHeaders(jsonobj.getJSONArray("dataHeaders"));
//        }
        if (jsonobj.containsKey("title")) {
            entity.setTitle(jsonobj.getString("title"));
        }
        return entity;
    }
    public static List<Environmental> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<Environmental> olist = new ArrayList<Environmental>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}

package test.JSONModel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Breadcrumb {
    private String label;

    private String url;

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public static Breadcrumb fill(JSONObject jsonobj) {
        Breadcrumb entity = new Breadcrumb();
        if (jsonobj.containsKey("label")) {
            entity.setLabel(jsonobj.getString("label"));
        }
        if (jsonobj.containsKey("url")) {
            entity.setUrl(jsonobj.getString("url"));
        }
        return entity;
    }

    public static List<Breadcrumb> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<Breadcrumb> olist = new ArrayList<Breadcrumb>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}
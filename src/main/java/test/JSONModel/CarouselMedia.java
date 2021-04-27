package test.JSONModel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CarouselMedia {
    private String displayUrl;

    private String analyticsTag;

    private String hrefUrl;

    private String title;

    private String type;

    public void setDisplayUrl(String displayUrl){
        this.displayUrl = displayUrl;
    }
    public String getDisplayUrl(){
        return this.displayUrl;
    }
    public void setAnalyticsTag(String analyticsTag){
        this.analyticsTag = analyticsTag;
    }
    public String getAnalyticsTag(){
        return this.analyticsTag;
    }
    public void setHrefUrl(String hrefUrl){
        this.hrefUrl = hrefUrl;
    }
    public String getHrefUrl(){
        return this.hrefUrl;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public static CarouselMedia fill(JSONObject jsonobj){
        CarouselMedia entity = new CarouselMedia();
        if (jsonobj.containsKey("displayUrl")) {
            entity.setDisplayUrl(jsonobj.getString("displayUrl"));
        }
        if (jsonobj.containsKey("analyticsTag")) {
            entity.setAnalyticsTag(jsonobj.getString("analyticsTag"));
        }
        if (jsonobj.containsKey("hrefUrl")) {
            entity.setHrefUrl(jsonobj.getString("hrefUrl"));
        }
        if (jsonobj.containsKey("title")) {
            entity.setTitle(jsonobj.getString("title"));
        }
        if (jsonobj.containsKey("type")) {
            entity.setType(jsonobj.getString("type"));
        }
        return entity;
    }
    public static List<CarouselMedia> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<CarouselMedia> olist = new ArrayList<CarouselMedia>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}



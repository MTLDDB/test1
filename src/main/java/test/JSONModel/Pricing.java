package test.JSONModel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pricing
{
    private String digikeyProductNumber;

    private List<PricingTiers> pricingTiers;

    private String packaging;

    private String id;

    public void setDigikeyProductNumber(String digikeyProductNumber){
        this.digikeyProductNumber = digikeyProductNumber;
    }
    public String getDigikeyProductNumber(){
        return this.digikeyProductNumber;
    }
    public void setPricingTiers(List<PricingTiers> pricingTiers){
        this.pricingTiers = pricingTiers;
    }
    public List<PricingTiers> getPricingTiers(){
        return this.pricingTiers;
    }
    public void setPackaging(String packaging){
        this.packaging = packaging;
    }
    public String getPackaging(){
        return this.packaging;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public static Pricing fill(JSONObject jsonobj){
        Pricing entity = new Pricing();
        if (jsonobj.containsKey("digikeyProductNumber")) {
            entity.setDigikeyProductNumber(jsonobj.getString("digikeyProductNumber"));
        }
        if (jsonobj.containsKey("pricingTiers")) {
            entity.setPricingTiers(PricingTiers.fillList(jsonobj.getJSONArray("pricingTiers")));
        }
        if (jsonobj.containsKey("packaging")) {
            entity.setPackaging(jsonobj.getString("packaging"));
        }
        if (jsonobj.containsKey("id")) {
            entity.setId(jsonobj.getString("id"));
        }
        return entity;
    }
    public static List<Pricing> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<Pricing> olist = new ArrayList<Pricing>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}


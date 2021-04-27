package test.JSONModel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PricingTiers
{
    private String unitPrice;

    private String extendedPrice;

    private String breakQty;

    public void setUnitPrice(String unitPrice){
        this.unitPrice = unitPrice;
    }
    public String getUnitPrice(){
        return this.unitPrice;
    }
    public void setExtendedPrice(String extendedPrice){
        this.extendedPrice = extendedPrice;
    }
    public String getExtendedPrice(){
        return this.extendedPrice;
    }
    public void setBreakQty(String breakQty){
        this.breakQty = breakQty;
    }
    public String getBreakQty(){
        return this.breakQty;
    }
    public static PricingTiers fill(JSONObject jsonobj){
        PricingTiers entity = new PricingTiers();
        if (jsonobj.containsKey("unitPrice")) {
            entity.setUnitPrice(jsonobj.getString("unitPrice"));
        }
        if (jsonobj.containsKey("extendedPrice")) {
            entity.setExtendedPrice(jsonobj.getString("extendedPrice"));
        }
        if (jsonobj.containsKey("breakQty")) {
            entity.setBreakQty(jsonobj.getString("breakQty"));
        }
        return entity;
    }
    public static List<PricingTiers> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<PricingTiers> olist = new ArrayList<PricingTiers>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}


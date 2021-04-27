package test.JSONModel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuantityTable
{
    private double unitPrice;

    private String digikeyProductNumber;

    private int fee;

    private int minMultiplier;

    private String packaging;

    private String id;

    private int breakQty;

    public void setUnitPrice(double unitPrice){
        this.unitPrice = unitPrice;
    }
    public double getUnitPrice(){
        return this.unitPrice;
    }
    public void setDigikeyProductNumber(String digikeyProductNumber){
        this.digikeyProductNumber = digikeyProductNumber;
    }
    public String getDigikeyProductNumber(){
        return this.digikeyProductNumber;
    }
    public void setFee(int fee){
        this.fee = fee;
    }
    public int getFee(){
        return this.fee;
    }
    public void setMinMultiplier(int minMultiplier){
        this.minMultiplier = minMultiplier;
    }
    public int getMinMultiplier(){
        return this.minMultiplier;
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
    public void setBreakQty(int breakQty){
        this.breakQty = breakQty;
    }
    public int getBreakQty(){
        return this.breakQty;
    }
    public static QuantityTable fill(JSONObject jsonobj){
        QuantityTable entity = new QuantityTable();
        if (jsonobj.containsKey("unitPrice")) {
            entity.setUnitPrice(jsonobj.getDouble("unitPrice"));
        }
        if (jsonobj.containsKey("digikeyProductNumber")) {
            entity.setDigikeyProductNumber(jsonobj.getString("digikeyProductNumber"));
        }
        if (jsonobj.containsKey("fee")) {
            entity.setFee(jsonobj.getInteger("fee"));
        }
        if (jsonobj.containsKey("minMultiplier")) {
            entity.setMinMultiplier(jsonobj.getInteger("minMultiplier"));
        }
        if (jsonobj.containsKey("packaging")) {
            entity.setPackaging(jsonobj.getString("packaging"));
        }
        if (jsonobj.containsKey("id")) {
            entity.setId(jsonobj.getString("id"));
        }
        if (jsonobj.containsKey("breakQty")) {
            entity.setBreakQty(jsonobj.getInteger("breakQty"));
        }
        return entity;
    }
    public static List<QuantityTable> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<QuantityTable> olist = new ArrayList<QuantityTable>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}

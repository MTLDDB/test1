package test.JSONModel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductOverview
{
    private String manufacturerProductNumber;

    private String detailedDescription;

    private boolean isNormallyStocking;

    private List<Suppliers> suppliers;

    private String standardLeadTime;

    private String datasheetUrl;

    private String manufacturerUrl;

    private DigikeyProductNumbers digikeyProductNumbers;

    private String description;

    private String rolledUpProductNumber;

    private String rolledUpProductId;

    private String manufacturer;

    public void setManufacturerProductNumber(String manufacturerProductNumber){
        this.manufacturerProductNumber = manufacturerProductNumber;
    }
    public String getManufacturerProductNumber(){
        return this.manufacturerProductNumber;
    }
    public void setDetailedDescription(String detailedDescription){
        this.detailedDescription = detailedDescription;
    }
    public String getDetailedDescription(){
        return this.detailedDescription;
    }
    public void setIsNormallyStocking(boolean isNormallyStocking){
        this.isNormallyStocking = isNormallyStocking;
    }
    public boolean getIsNormallyStocking(){
        return this.isNormallyStocking;
    }
    public void setSuppliers(List<Suppliers> suppliers){
        this.suppliers = suppliers;
    }
    public List<Suppliers> getSuppliers(){
        return this.suppliers;
    }
    public void setStandardLeadTime(String standardLeadTime){
        this.standardLeadTime = standardLeadTime;
    }
    public String getStandardLeadTime(){
        return this.standardLeadTime;
    }
    public void setDatasheetUrl(String datasheetUrl){
        this.datasheetUrl = datasheetUrl;
    }
    public String getDatasheetUrl(){
        return this.datasheetUrl;
    }
    public void setManufacturerUrl(String manufacturerUrl){
        this.manufacturerUrl = manufacturerUrl;
    }
    public String getManufacturerUrl(){
        return this.manufacturerUrl;
    }
    public void setDigikeyProductNumbers(DigikeyProductNumbers digikeyProductNumbers){
        this.digikeyProductNumbers = digikeyProductNumbers;
    }
    public DigikeyProductNumbers getDigikeyProductNumbers(){
        return this.digikeyProductNumbers;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setRolledUpProductNumber(String rolledUpProductNumber){
        this.rolledUpProductNumber = rolledUpProductNumber;
    }
    public String getRolledUpProductNumber(){
        return this.rolledUpProductNumber;
    }
    public void setRolledUpProductId(String rolledUpProductId){
        this.rolledUpProductId = rolledUpProductId;
    }
    public String getRolledUpProductId(){
        return this.rolledUpProductId;
    }
    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }
    public String getManufacturer(){
        return this.manufacturer;
    }
    public static ProductOverview fill(JSONObject jsonobj){
        ProductOverview entity = new ProductOverview();
        if (jsonobj.containsKey("manufacturerProductNumber")) {
            entity.setManufacturerProductNumber(jsonobj.getString("manufacturerProductNumber"));
        }
        if (jsonobj.containsKey("detailedDescription")) {
            entity.setDetailedDescription(jsonobj.getString("detailedDescription"));
        }
        if (jsonobj.containsKey("isNormallyStocking")) {
            entity.setIsNormallyStocking(jsonobj.getBoolean("isNormallyStocking"));
        }
        if (jsonobj.containsKey("suppliers")) {
            entity.setSuppliers(Suppliers.fillList(jsonobj.getJSONArray("suppliers")));
        }
        if (jsonobj.containsKey("standardLeadTime")) {
            entity.setStandardLeadTime(jsonobj.getString("standardLeadTime"));
        }
        if (jsonobj.containsKey("datasheetUrl")) {
            entity.setDatasheetUrl(jsonobj.getString("datasheetUrl"));
        }
        if (jsonobj.containsKey("manufacturerUrl")) {
            entity.setManufacturerUrl(jsonobj.getString("manufacturerUrl"));
        }
        if (jsonobj.containsKey("digikeyProductNumbers")) {
            entity.setDigikeyProductNumbers(JSONObject.parseObject(jsonobj.getString("digikeyProductNumbers"),DigikeyProductNumbers.class));
        }
        if (jsonobj.containsKey("description")) {
            entity.setDescription(jsonobj.getString("description"));
        }
        if (jsonobj.containsKey("rolledUpProductNumber")) {
            entity.setRolledUpProductNumber(jsonobj.getString("rolledUpProductNumber"));
        }
        if (jsonobj.containsKey("rolledUpProductId")) {
            entity.setRolledUpProductId(jsonobj.getString("rolledUpProductId"));
        }
        if (jsonobj.containsKey("manufacturer")) {
            entity.setManufacturer(jsonobj.getString("manufacturer"));
        }
        return entity;
    }
    public static List<ProductOverview> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<ProductOverview> olist = new ArrayList<ProductOverview>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}

package zaikostoreApi.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StockList
{
    private String stockId;

    private String makerName;

    private String productName;

    private String rohs;

    private String rohs_label;

    private String qty;

    private String moq;

    private String packageType;

    private String packageType_label;

    private String reeling;

    private String reeling_label;

    private String location;

    private String location_label;

    private String typeStock;

    private String typeStock_label;

    private String dateCode;

    private String img1;

    private String img2;

    private String img3;

    private String dataSheet;

    private String range1;

    private String range2;

    private String range3;

    private String range4;

    private String range5;

    private String range6;

    private String range7;

    private String range8;

    private String price1;

    private String price2;

    private String price3;

    private String price4;

    private String price5;

    private String price6;

    private String price7;

    private String price8;

    private String warrantyType;

    private String warrantyType_label;

    private String explt;

    private String orderlimithh;

    private String orderlimitmm;

    private String explt_label;

    private String detailUrl;

    private String msl;

    private String msl_label;

    private String coo;

    private String coo_label;

    private String pCategory;

    private String pCategory_label;

    private String category1;

    private String category1_label;

    private String category2;

    private String category2_label;

    private String category3;

    private String category3_label;

    public void setStockId(String stockId){
        this.stockId = stockId;
    }
    public String getStockId(){
        return this.stockId;
    }
    public void setMakerName(String makerName){
        this.makerName = makerName;
    }
    public String getMakerName(){
        return this.makerName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public String getProductName(){
        return this.productName;
    }
    public void setRohs(String rohs){
        this.rohs = rohs;
    }
    public String getRohs(){
        return this.rohs;
    }
    public void setRohs_label(String rohs_label){
        this.rohs_label = rohs_label;
    }
    public String getRohs_label(){
        return this.rohs_label;
    }
    public void setQty(String qty){
        this.qty = qty;
    }
    public String getQty(){
        return this.qty;
    }
    public void setMoq(String moq){
        this.moq = moq;
    }
    public String getMoq(){
        return this.moq;
    }
    public void setPackageType(String packageType){
        this.packageType = packageType;
    }
    public String getPackageType(){
        return this.packageType;
    }
    public void setPackageType_label(String packageType_label){
        this.packageType_label = packageType_label;
    }
    public String getPackageType_label(){
        return this.packageType_label;
    }
    public void setReeling(String reeling){
        this.reeling = reeling;
    }
    public String getReeling(){
        return this.reeling;
    }
    public void setReeling_label(String reeling_label){
        this.reeling_label = reeling_label;
    }
    public String getReeling_label(){
        return this.reeling_label;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
    public void setLocation_label(String location_label){
        this.location_label = location_label;
    }
    public String getLocation_label(){
        return this.location_label;
    }
    public void setTypeStock(String typeStock){
        this.typeStock = typeStock;
    }
    public String getTypeStock(){
        return this.typeStock;
    }
    public void setTypeStock_label(String typeStock_label){
        this.typeStock_label = typeStock_label;
    }
    public String getTypeStock_label(){
        return this.typeStock_label;
    }
    public void setDateCode(String dateCode){
        this.dateCode = dateCode;
    }
    public String getDateCode(){
        return this.dateCode;
    }
    public void setImg1(String img1){
        this.img1 = img1;
    }
    public String getImg1(){
        return this.img1;
    }
    public void setImg2(String img2){
        this.img2 = img2;
    }
    public String getImg2(){
        return this.img2;
    }
    public void setImg3(String img3){
        this.img3 = img3;
    }
    public String getImg3(){
        return this.img3;
    }
    public void setDataSheet(String dataSheet){
        this.dataSheet = dataSheet;
    }
    public String getDataSheet(){
        return this.dataSheet;
    }
    public void setRange1(String range1){
        this.range1 = range1;
    }
    public String getRange1(){
        return this.range1;
    }
    public void setRange2(String range2){
        this.range2 = range2;
    }
    public String getRange2(){
        return this.range2;
    }
    public void setRange3(String range3){
        this.range3 = range3;
    }
    public String getRange3(){
        return this.range3;
    }
    public void setRange4(String range4){
        this.range4 = range4;
    }
    public String getRange4(){
        return this.range4;
    }
    public void setRange5(String range5){
        this.range5 = range5;
    }
    public String getRange5(){
        return this.range5;
    }
    public void setRange6(String range6){
        this.range6 = range6;
    }
    public String getRange6(){
        return this.range6;
    }
    public void setRange7(String range7){
        this.range7 = range7;
    }
    public String getRange7(){
        return this.range7;
    }
    public void setRange8(String range8){
        this.range8 = range8;
    }
    public String getRange8(){
        return this.range8;
    }
    public void setPrice1(String price1){
        this.price1 = price1;
    }
    public String getPrice1(){
        return this.price1;
    }
    public void setPrice2(String price2){
        this.price2 = price2;
    }
    public String getPrice2(){
        return this.price2;
    }
    public void setPrice3(String price3){
        this.price3 = price3;
    }
    public String getPrice3(){
        return this.price3;
    }
    public void setPrice4(String price4){
        this.price4 = price4;
    }
    public String getPrice4(){
        return this.price4;
    }
    public void setPrice5(String price5){
        this.price5 = price5;
    }
    public String getPrice5(){
        return this.price5;
    }
    public void setPrice6(String price6){
        this.price6 = price6;
    }
    public String getPrice6(){
        return this.price6;
    }
    public void setPrice7(String price7){
        this.price7 = price7;
    }
    public String getPrice7(){
        return this.price7;
    }
    public void setPrice8(String price8){
        this.price8 = price8;
    }
    public String getPrice8(){
        return this.price8;
    }
    public void setWarrantyType(String warrantyType){
        this.warrantyType = warrantyType;
    }
    public String getWarrantyType(){
        return this.warrantyType;
    }
    public void setWarrantyType_label(String warrantyType_label){
        this.warrantyType_label = warrantyType_label;
    }
    public String getWarrantyType_label(){
        return this.warrantyType_label;
    }
    public void setExplt(String explt){
        this.explt = explt;
    }
    public String getExplt(){
        return this.explt;
    }
    public void setOrderlimithh(String orderlimithh){
        this.orderlimithh = orderlimithh;
    }
    public String getOrderlimithh(){
        return this.orderlimithh;
    }
    public void setOrderlimitmm(String orderlimitmm){
        this.orderlimitmm = orderlimitmm;
    }
    public String getOrderlimitmm(){
        return this.orderlimitmm;
    }
    public void setExplt_label(String explt_label){
        this.explt_label = explt_label;
    }
    public String getExplt_label(){
        return this.explt_label;
    }
    public void setDetailUrl(String detailUrl){
        this.detailUrl = detailUrl;
    }
    public String getDetailUrl(){
        return this.detailUrl;
    }
    public void setMsl(String msl){
        this.msl = msl;
    }
    public String getMsl(){
        return this.msl;
    }
    public void setMsl_label(String msl_label){
        this.msl_label = msl_label;
    }
    public String getMsl_label(){
        return this.msl_label;
    }
    public void setCoo(String coo){
        this.coo = coo;
    }
    public String getCoo(){
        return this.coo;
    }
    public void setCoo_label(String coo_label){
        this.coo_label = coo_label;
    }
    public String getCoo_label(){
        return this.coo_label;
    }
    public void setPCategory(String pCategory){
        this.pCategory = pCategory;
    }
    public String getPCategory(){
        return this.pCategory;
    }
    public void setPCategory_label(String pCategory_label){
        this.pCategory_label = pCategory_label;
    }
    public String getPCategory_label(){
        return this.pCategory_label;
    }
    public void setCategory1(String category1){
        this.category1 = category1;
    }
    public String getCategory1(){
        return this.category1;
    }
    public void setCategory1_label(String category1_label){
        this.category1_label = category1_label;
    }
    public String getCategory1_label(){
        return this.category1_label;
    }
    public void setCategory2(String category2){
        this.category2 = category2;
    }
    public String getCategory2(){
        return this.category2;
    }
    public void setCategory2_label(String category2_label){
        this.category2_label = category2_label;
    }
    public String getCategory2_label(){
        return this.category2_label;
    }
    public void setCategory3(String category3){
        this.category3 = category3;
    }
    public String getCategory3(){
        return this.category3;
    }
    public void setCategory3_label(String category3_label){
        this.category3_label = category3_label;
    }
    public String getCategory3_label(){
        return this.category3_label;
    }
    public static StockList fill(JSONObject jsonobj){
        StockList entity = new StockList();
        if (jsonobj.containsKey("stockId")) {
            entity.setStockId(jsonobj.getString("stockId"));
        }
        if (jsonobj.containsKey("makerName")) {
            entity.setMakerName(jsonobj.getString("makerName"));
        }
        if (jsonobj.containsKey("productName")) {
            entity.setProductName(jsonobj.getString("productName"));
        }
        if (jsonobj.containsKey("rohs")) {
            entity.setRohs(jsonobj.getString("rohs"));
        }
        if (jsonobj.containsKey("rohs_label")) {
            entity.setRohs_label(jsonobj.getString("rohs_label"));
        }
        if (jsonobj.containsKey("qty")) {
            entity.setQty(jsonobj.getString("qty"));
        }
        if (jsonobj.containsKey("moq")) {
            entity.setMoq(jsonobj.getString("moq"));
        }
        if (jsonobj.containsKey("packageType")) {
            entity.setPackageType(jsonobj.getString("packageType"));
        }
        if (jsonobj.containsKey("packageType_label")) {
            entity.setPackageType_label(jsonobj.getString("packageType_label"));
        }
        if (jsonobj.containsKey("reeling")) {
            entity.setReeling(jsonobj.getString("reeling"));
        }
        if (jsonobj.containsKey("reeling_label")) {
            entity.setReeling_label(jsonobj.getString("reeling_label"));
        }
        if (jsonobj.containsKey("location")) {
            entity.setLocation(jsonobj.getString("location"));
        }
        if (jsonobj.containsKey("location_label")) {
            entity.setLocation_label(jsonobj.getString("location_label"));
        }
        if (jsonobj.containsKey("typeStock")) {
            entity.setTypeStock(jsonobj.getString("typeStock"));
        }
        if (jsonobj.containsKey("typeStock_label")) {
            entity.setTypeStock_label(jsonobj.getString("typeStock_label"));
        }
        if (jsonobj.containsKey("dateCode")) {
            entity.setDateCode(jsonobj.getString("dateCode"));
        }
        if (jsonobj.containsKey("img1")) {
            entity.setImg1(jsonobj.getString("img1"));
        }
        if (jsonobj.containsKey("img2")) {
            entity.setImg2(jsonobj.getString("img2"));
        }
        if (jsonobj.containsKey("img3")) {
            entity.setImg3(jsonobj.getString("img3"));
        }
        if (jsonobj.containsKey("dataSheet")) {
            entity.setDataSheet(jsonobj.getString("dataSheet"));
        }
        if (jsonobj.containsKey("range1")) {
            entity.setRange1(jsonobj.getString("range1"));
        }
        if (jsonobj.containsKey("range2")) {
            entity.setRange2(jsonobj.getString("range2"));
        }
        if (jsonobj.containsKey("range3")) {
            entity.setRange3(jsonobj.getString("range3"));
        }
        if (jsonobj.containsKey("range4")) {
            entity.setRange4(jsonobj.getString("range4"));
        }
        if (jsonobj.containsKey("range5")) {
            entity.setRange5(jsonobj.getString("range5"));
        }
        if (jsonobj.containsKey("range6")) {
            entity.setRange6(jsonobj.getString("range6"));
        }
        if (jsonobj.containsKey("range7")) {
            entity.setRange7(jsonobj.getString("range7"));
        }
        if (jsonobj.containsKey("range8")) {
            entity.setRange8(jsonobj.getString("range8"));
        }
        if (jsonobj.containsKey("price1")) {
            entity.setPrice1(jsonobj.getString("price1"));
        }
        if (jsonobj.containsKey("price2")) {
            entity.setPrice2(jsonobj.getString("price2"));
        }
        if (jsonobj.containsKey("price3")) {
            entity.setPrice3(jsonobj.getString("price3"));
        }
        if (jsonobj.containsKey("price4")) {
            entity.setPrice4(jsonobj.getString("price4"));
        }
        if (jsonobj.containsKey("price5")) {
            entity.setPrice5(jsonobj.getString("price5"));
        }
        if (jsonobj.containsKey("price6")) {
            entity.setPrice6(jsonobj.getString("price6"));
        }
        if (jsonobj.containsKey("price7")) {
            entity.setPrice7(jsonobj.getString("price7"));
        }
        if (jsonobj.containsKey("price8")) {
            entity.setPrice8(jsonobj.getString("price8"));
        }
        if (jsonobj.containsKey("warrantyType")) {
            entity.setWarrantyType(jsonobj.getString("warrantyType"));
        }
        if (jsonobj.containsKey("warrantyType_label")) {
            entity.setWarrantyType_label(jsonobj.getString("warrantyType_label"));
        }
        if (jsonobj.containsKey("explt")) {
            entity.setExplt(jsonobj.getString("explt"));
        }
        if (jsonobj.containsKey("orderlimithh")) {
            entity.setOrderlimithh(jsonobj.getString("orderlimithh"));
        }
        if (jsonobj.containsKey("orderlimitmm")) {
            entity.setOrderlimitmm(jsonobj.getString("orderlimitmm"));
        }
        if (jsonobj.containsKey("explt_label")) {
            entity.setExplt_label(jsonobj.getString("explt_label"));
        }
        if (jsonobj.containsKey("detailUrl")) {
            entity.setDetailUrl(jsonobj.getString("detailUrl"));
        }
        if (jsonobj.containsKey("msl")) {
            entity.setMsl(jsonobj.getString("msl"));
        }
        if (jsonobj.containsKey("msl_label")) {
            entity.setMsl_label(jsonobj.getString("msl_label"));
        }
        if (jsonobj.containsKey("coo")) {
            entity.setCoo(jsonobj.getString("coo"));
        }
        if (jsonobj.containsKey("coo_label")) {
            entity.setCoo_label(jsonobj.getString("coo_label"));
        }
        if (jsonobj.containsKey("pCategory")) {
            entity.setPCategory(jsonobj.getString("pCategory"));
        }
        if (jsonobj.containsKey("pCategory_label")) {
            entity.setPCategory_label(jsonobj.getString("pCategory_label"));
        }
        if (jsonobj.containsKey("category1")) {
            entity.setCategory1(jsonobj.getString("category1"));
        }
        if (jsonobj.containsKey("category1_label")) {
            entity.setCategory1_label(jsonobj.getString("category1_label"));
        }
        if (jsonobj.containsKey("category2")) {
            entity.setCategory2(jsonobj.getString("category2"));
        }
        if (jsonobj.containsKey("category2_label")) {
            entity.setCategory2_label(jsonobj.getString("category2_label"));
        }
        if (jsonobj.containsKey("category3")) {
            entity.setCategory3(jsonobj.getString("category3"));
        }
        if (jsonobj.containsKey("category3_label")) {
            entity.setCategory3_label(jsonobj.getString("category3_label"));
        }
        return entity;
    }
    public static List<StockList> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<StockList> olist = new ArrayList<StockList>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}
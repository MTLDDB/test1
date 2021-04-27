package test.JSONModel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Data
{
    private List<CarouselMedia> carouselMedia;

    private Environmental environmental;

    private ProductAttributes productAttributes;

    private boolean isMultiVendor;

    private boolean isMarketplaceOnly;

    private ProductOverview productOverview;

    private PriceQuantity priceQuantity;

    private List<Breadcrumb> breadcrumb;

    private int minimumMultiplier;

        private List<QuantityTable> quantityTable;


    private boolean isBackOrderAllowed;

    private AdditionalResources additionalResources;

    private OtherDocsAndMedia otherDocsAndMedia;

    public List<CarouselMedia> getCarouselMedia() {
        return carouselMedia;
    }

    public void setCarouselMedia(List<CarouselMedia> carouselMedia) {
        this.carouselMedia = carouselMedia;
    }

    public void setEnvironmental(Environmental environmental){
        this.environmental = environmental;
    }
    public Environmental getEnvironmental(){
        return this.environmental;
    }
    public void setProductAttributes(ProductAttributes productAttributes){
        this.productAttributes = productAttributes;
    }
    public ProductAttributes getProductAttributes(){
        return this.productAttributes;
    }
    public void setIsMultiVendor(boolean isMultiVendor){
        this.isMultiVendor = isMultiVendor;
    }
    public boolean getIsMultiVendor(){
        return this.isMultiVendor;
    }
    public void setIsMarketplaceOnly(boolean isMarketplaceOnly){
        this.isMarketplaceOnly = isMarketplaceOnly;
    }
    public boolean getIsMarketplaceOnly(){
        return this.isMarketplaceOnly;
    }
    public void setProductOverview(ProductOverview productOverview){
        this.productOverview = productOverview;
    }
    public ProductOverview getProductOverview(){
        return this.productOverview;
    }
    public void setPriceQuantity(PriceQuantity priceQuantity){
        this.priceQuantity = priceQuantity;
    }
    public PriceQuantity getPriceQuantity(){
        return this.priceQuantity;
    }

    public List<Breadcrumb> getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(List<Breadcrumb> breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public void setMinimumMultiplier(int minimumMultiplier){
        this.minimumMultiplier = minimumMultiplier;
    }
    public int getMinimumMultiplier(){
        return this.minimumMultiplier;
    }

    public List<QuantityTable> getQuantityTable() {
        return quantityTable;
    }

    public void setQuantityTable(List<QuantityTable> quantityTable) {
        this.quantityTable = quantityTable;
    }

    public void setIsBackOrderAllowed(boolean isBackOrderAllowed){
        this.isBackOrderAllowed = isBackOrderAllowed;
    }
    public boolean getIsBackOrderAllowed(){
        return this.isBackOrderAllowed;
    }
    public void setAdditionalResources(AdditionalResources additionalResources){
        this.additionalResources = additionalResources;
    }
    public AdditionalResources getAdditionalResources(){
        return this.additionalResources;
    }
    public void setOtherDocsAndMedia(OtherDocsAndMedia otherDocsAndMedia){
        this.otherDocsAndMedia = otherDocsAndMedia;
    }
    public OtherDocsAndMedia getOtherDocsAndMedia(){
        return this.otherDocsAndMedia;
    }

    public static Data fill(JSONObject jsonobj){
        Data entity = new Data();
        if (jsonobj.containsKey("carouselMedia")) {
            entity.setCarouselMedia(JSON.parseArray(jsonobj.getString("carouselMedia"),CarouselMedia.class));
        }
        if (jsonobj.containsKey("environmental")) {
            entity.setEnvironmental(JSONObject.parseObject(jsonobj.getString("environmental"),Environmental.class));
        }
        if (jsonobj.containsKey("productAttributes")) {
            entity.setProductAttributes(JSONObject.parseObject(jsonobj.getString("productAttributes"),ProductAttributes.class));
        }

        if (jsonobj.containsKey("isMarketplaceOnly")) {
            entity.setIsMarketplaceOnly(jsonobj.getBoolean("isMarketplaceOnly"));
        }
        if (jsonobj.containsKey("productOverview")) {
            entity.setProductOverview(JSONObject.parseObject(jsonobj.getString("productOverview"),ProductOverview.class));
        }
        if (jsonobj.containsKey("priceQuantity")) {
            entity.setPriceQuantity(JSONObject.parseObject(jsonobj.getString("priceQuantity"),PriceQuantity.class));
        }
        if (jsonobj.containsKey("breadcrumb")) {
            entity.setBreadcrumb(Breadcrumb.fillList(jsonobj.getJSONArray("breadcrumb")));
        }
        if (jsonobj.containsKey("minimumMultiplier")) {
            entity.setMinimumMultiplier(jsonobj.getInteger("minimumMultiplier"));
        }
        if (jsonobj.containsKey("quantityTable")) {
            entity.setQuantityTable(QuantityTable.fillList(jsonobj.getJSONArray("quantityTable")));
        }

        if (jsonobj.containsKey("isBackOrderAllowed")) {
            entity.setIsBackOrderAllowed(jsonobj.getBoolean("isBackOrderAllowed"));
        }
        if (jsonobj.containsKey("additionalResources")) {
            entity.setAdditionalResources(JSONObject.parseObject(jsonobj.getString("additionalResources"),AdditionalResources.class));
        }
        if (jsonobj.containsKey("otherDocsAndMedia")) {
            entity.setOtherDocsAndMedia(JSONObject.parseObject(jsonobj.getString("otherDocsAndMedia"),OtherDocsAndMedia.class));
        }
        return entity;
    }
}
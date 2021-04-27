package test.arrowApi.jsonModel;

public class SourcePart {
    //每个包装/卷的零件数
    private String packSize;
    //可订购的最小零件数。这将是packSize的倍数。
    private String minimumOrderQuantity;

    private String sourcePartNumber;
    //此源零件的唯一标识符
    private String sourcePartId;
    //定价数据的容器
    private String Prices;
    //包含库存数据的元素列表
    private String Availability;
    //具有与API凭证关联的帐号特定的价格和库存数据的元素列表。
    private String customerSpecificPricing;
    //保留以备将来使用
    private String customerSpecificInventory;
    //表示零件的制造日期
    private String dateCode;
    //资源元素列表，每个元素都在{}中定义
    private String resources;
    //如果有可用库存，则为true，否则为false
    private String inStock;
    //
    private String mfrLeadTime;
    private String isNcnr;
    private String isNpi;
    private String isASA;
    private String requestQuantity;
    private String productCode;
    private String iccLevels;
    private String cloudMfrCode;
    private String eccnCode;
    private String htsCode;
    private String locationId;
    private String containerType;

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public String getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    public void setMinimumOrderQuantity(String minimumOrderQuantity) {
        this.minimumOrderQuantity = minimumOrderQuantity;
    }

    public String getSourcePartNumber() {
        return sourcePartNumber;
    }

    public void setSourcePartNumber(String sourcePartNumber) {
        this.sourcePartNumber = sourcePartNumber;
    }

    public String getSourcePartId() {
        return sourcePartId;
    }

    public void setSourcePartId(String sourcePartId) {
        this.sourcePartId = sourcePartId;
    }

    public String getPrices() {
        return Prices;
    }

    public void setPrices(String prices) {
        Prices = prices;
    }

    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }

    public String getCustomerSpecificPricing() {
        return customerSpecificPricing;
    }

    public void setCustomerSpecificPricing(String customerSpecificPricing) {
        this.customerSpecificPricing = customerSpecificPricing;
    }

    public String getCustomerSpecificInventory() {
        return customerSpecificInventory;
    }

    public void setCustomerSpecificInventory(String customerSpecificInventory) {
        this.customerSpecificInventory = customerSpecificInventory;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }

    public String getMfrLeadTime() {
        return mfrLeadTime;
    }

    public void setMfrLeadTime(String mfrLeadTime) {
        this.mfrLeadTime = mfrLeadTime;
    }

    public String getIsNcnr() {
        return isNcnr;
    }

    public void setIsNcnr(String isNcnr) {
        this.isNcnr = isNcnr;
    }

    public String getIsNpi() {
        return isNpi;
    }

    public void setIsNpi(String isNpi) {
        this.isNpi = isNpi;
    }

    public String getIsASA() {
        return isASA;
    }

    public void setIsASA(String isASA) {
        this.isASA = isASA;
    }

    public String getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(String requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getIccLevels() {
        return iccLevels;
    }

    public void setIccLevels(String iccLevels) {
        this.iccLevels = iccLevels;
    }

    public String getCloudMfrCode() {
        return cloudMfrCode;
    }

    public void setCloudMfrCode(String cloudMfrCode) {
        this.cloudMfrCode = cloudMfrCode;
    }

    public String getEccnCode() {
        return eccnCode;
    }

    public void setEccnCode(String eccnCode) {
        this.eccnCode = eccnCode;
    }

    public String getHtsCode() {
        return htsCode;
    }

    public void setHtsCode(String htsCode) {
        this.htsCode = htsCode;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }
}

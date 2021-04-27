package test.arrowApi.jsonModel;

public class PartList {
    private String itemId;//Arrow part identifier
    private String partNum;//mpn
    private String manufacturer;//mfr
    private String desc;//description
    private String packageType;
    private String resources;//资源元素列表，每个元素都在{}中定义
    private String EnvData;//环境数据容器
    private String InvOrg;//库存数据的容器
    private String hasDatasheet;//如果提供了包含技术信息的数据表，则为“ true”，否则为“ false”


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPartNum() {
        return partNum;
    }

    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getEnvData() {
        return EnvData;
    }

    public void setEnvData(String envData) {
        EnvData = envData;
    }

    public String getInvOrg() {
        return InvOrg;
    }

    public void setInvOrg(String invOrg) {
        InvOrg = invOrg;
    }

    public String getHasDatasheet() {
        return hasDatasheet;
    }

    public void setHasDatasheet(String hasDatasheet) {
        this.hasDatasheet = hasDatasheet;
    }
}

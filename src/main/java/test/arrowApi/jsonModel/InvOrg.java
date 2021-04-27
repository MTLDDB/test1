package test.arrowApi.jsonModel;

public class InvOrg {//资源元素列表，每个元素都在{}中定义
    /**
     * The currency used to express prices.(不同地区价格的表示)
     * USD: US Dollar
     * AUD: Australian Dollar
     * EUR: European Union Euro
     * KRW: South Korean Won
     * ILS: Israeli Shekel
     */
    private String currency;
    /**
     * A short identifier for the source/region of the inventory. The most common are:(库存来源/区域的简短标识符)
     * NAC: Arrow North America
     * EUROPE: Arrow Europe
     * ASIA: Arrow Asia
     * VERICAL: Verical Marketplace
     */
    private String sourceCd;
    //显示名称
    private String displayName;
    //每个元素是零件编号，包装和库存来源的特定组合。
    private String sourceParts;


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSourceCd() {
        return sourceCd;
    }

    public void setSourceCd(String sourceCd) {
        this.sourceCd = sourceCd;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSourceParts() {
        return sourceParts;
    }

    public void setSourceParts(String sourceParts) {
        this.sourceParts = sourceParts;
    }
}

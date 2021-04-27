package test.arrowApi.jsonModel;

public class Prices {

    private String displayPrice;
    private String price;
    private int minQty;//必须购买的最低数量才能收到此价格
    private int maxQty;//转移到下一个价格层之前可以购买的最大数量

    public String getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(int maxQty) {
        this.maxQty = maxQty;
    }

    public int getMinQty() {
        return minQty;
    }

    public void setMinQty(int minQty) {
        this.minQty = minQty;
    }
}

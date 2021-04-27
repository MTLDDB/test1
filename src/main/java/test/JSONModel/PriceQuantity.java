package test.JSONModel;

import java.util.List;

public class PriceQuantity {
    private String qtyAvailable;
    private String hasLeadTime;
    private List<Pricing> pricing;

    public String getQtyAvailable() {
        return qtyAvailable;
    }

    public void setQtyAvailable(String qtyAvailable) {
        this.qtyAvailable = qtyAvailable;
    }

    public String getHasLeadTime() {
        return hasLeadTime;
    }

    public void setHasLeadTime(String hasLeadTime) {
        this.hasLeadTime = hasLeadTime;
    }

    public List<Pricing> getPricing() {
        return pricing;
    }

    public void setPricing(List<Pricing> pricing) {
        this.pricing = pricing;
    }
}

package test.arrowApi.jsonModel;

public class Availability {//库存信息
    //可用现货数量，即可用库存。
    private String fohQty;
    //指示零件可用性的短代码
    private String availabilityCd;
    //较长的描述性字符串，指示零件的可用性。
    private String availabilityMessage;
    //
    private String pipeline;

    public String getFohQty() {
        return fohQty;
    }

    public void setFohQty(String fohQty) {
        this.fohQty = fohQty;
    }

    public String getAvailabilityCd() {
        return availabilityCd;
    }

    public void setAvailabilityCd(String availabilityCd) {
        this.availabilityCd = availabilityCd;
    }

    public String getAvailabilityMessage() {
        return availabilityMessage;
    }

    public void setAvailabilityMessage(String availabilityMessage) {
        this.availabilityMessage = availabilityMessage;
    }

    public String getPipeline() {
        return pipeline;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }
}

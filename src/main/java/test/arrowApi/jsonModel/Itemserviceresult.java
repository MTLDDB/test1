package test.arrowApi.jsonModel;

public class Itemserviceresult {//获取的所有结果
    private String transactionArea;//返回的结果信息
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTransactionArea() {
        return transactionArea;
    }

    public void setTransactionArea(String transactionArea) {
        this.transactionArea = transactionArea;
    }
}

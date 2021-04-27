package com.entity;

public class Stock {
    private String objectid;
    private int stock;
    private String create_time;
    private String detail_id;
    private String batchno;
    private String otherStock;
    private String stockToPrice;//库存与价格的对应关系
    private Integer state;//发送任务状态（0未读取，1已读取，2已发送）默认为0


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStockToPrice() {
        return stockToPrice;
    }

    public void setStockToPrice(String stockToPrice) {
        this.stockToPrice = stockToPrice;
    }

    public String getOtherStock() {
        return otherStock;
    }

    public void setOtherStock(String otherStock) {
        this.otherStock = otherStock;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}

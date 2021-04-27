package zaikostoreApi.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Root
{
    private String status;

    private int resultCnt;

    private String pageNo;

    private List<StockList> stockList;

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }

    public int getResultCnt() {
        return resultCnt;
    }

    public void setResultCnt(int resultCnt) {
        this.resultCnt = resultCnt;
    }

    public void setPageNo(String pageNo){
        this.pageNo = pageNo;
    }
    public String getPageNo(){
        return this.pageNo;
    }
    public void setStockList(List<StockList> stockList){
        this.stockList = stockList;
    }
    public List<StockList> getStockList(){
        return this.stockList;
    }
    public static Root fill(JSONObject jsonobj){
        Root entity = new Root();
        if (jsonobj.containsKey("status")) {
            entity.setStatus(jsonobj.getString("status"));
        }
        if (jsonobj.containsKey("resultCnt")) {
            entity.setResultCnt(jsonobj.getInteger("resultCnt"));
        }
        if (jsonobj.containsKey("pageNo")) {
            entity.setPageNo(jsonobj.getString("pageNo"));
        }
        if (jsonobj.containsKey("stockList")) {
            entity.setStockList(StockList.fillList(jsonobj.getJSONArray("stockList")));
        }
        return entity;
    }
    public static List<Root> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<Root> olist = new ArrayList<Root>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}
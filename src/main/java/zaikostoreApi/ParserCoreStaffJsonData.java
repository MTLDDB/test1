package zaikostoreApi;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import test.arrowApi.jsonModel.Prices;
import test.arrowApi.jsonModel.Result;
import zaikostoreApi.model.Root;
import zaikostoreApi.model.StockList;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class ParserCoreStaffJsonData {

    public static Result parser(String json) {
        Result endResult = null;
        Root root = Root.fill(JSONObject.parseObject(json));
        System.out.println(root.getStatus());
        if (root != null && "200".equals(root.getStatus()) && root.getResultCnt() > 0) {
            endResult = new Result();
            List<StockList> stockLists = root.getStockList();
            StockList stockListTemp = null;
            for (StockList stockList : stockLists) {
                if ("CoreStaff库存".equals(stockList.getLocation_label()) && stockList.getTypeStock_label().contains("本公司库存")) {
                    stockListTemp = stockList;
                    break;
                }
//                if ("https://www.zaikostore.com/zaikostore/stockDetail?stockID=st43925692&productName_forFind=RC0402JR%2d07100RL&typeStock_forFind=all&productId_fromList=pr2180210"
//                        .equals(stockList.getDetailUrl()) ) {
//                    stockListTemp = stockList;
//                    break;
//                }
            }

            if(stockListTemp==null){

            }else {
                String mpn = stockListTemp.getProductName();
                endResult.setMpn(mpn);
                String dateCode = stockListTemp.getDateCode();
                if (dateCode != null) {
                    endResult.setDateCode(dateCode);
                }
                String moq = stockListTemp.getMoq();
                if ("0".equals(moq)) {
                    moq = "1";
                    endResult.setMinimumOrderQuantity(1);
                } else if (moq != null) {
                    endResult.setMinimumOrderQuantity(Integer.parseInt(moq));
                }
                String stock = stockListTemp.getQty();
                if(stock!=null){
                    endResult.setAvailability(Integer.parseInt(stock));
                }
                List<Prices> pricesList = new ArrayList<>();
                int tempMinQty = Integer.parseInt(moq);
                for (int i = 1; i <= 8; i++) {
                    String priceName = "price" + i;
                    Object priceValue = getFieldValueByName(priceName, stockListTemp);
                    if (priceValue != null) {
                        Prices prices = new Prices();
                        prices.setPrice(String.valueOf(priceValue));
                        prices.setMinQty(tempMinQty);
                        String rangeName = "range" + i;
                        Object rangeValue = getFieldValueByName(rangeName, stockListTemp);
                        if(!"9999999999".equals(rangeValue)){
                            tempMinQty = Integer.parseInt(String.valueOf(rangeValue))+1;
                        }
                        pricesList.add(prices);
                    }else {
                        break;
                    }
                }
                endResult.setPrices(JSON.toJSONString(pricesList));
            }

            System.out.println(JSON.toJSONString(endResult));
        }
        return endResult;
    }

    private static Object getFieldValueByName(String fieldName, Object o) {//用于通过属性名获取属性的值
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            System.out.println("属性不存在");
            return null;
        }
    }
}

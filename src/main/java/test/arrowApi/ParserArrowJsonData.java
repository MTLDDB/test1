package test.arrowApi;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import test.arrowApi.jsonModel.*;


import java.util.ArrayList;
import java.util.List;


public class ParserArrowJsonData {

    public static Result parser(String json, String mpn) {
        Result EndResult = null;
       // try {
            List<Result> resultList = new ArrayList<>();
            JSONObject jsonObject = JSONObject.parseObject(json);
            String itemserviceresultStr = jsonObject.getString("itemserviceresult");//第一层数据
            Itemserviceresult itemserviceresult = JSONObject.parseObject(itemserviceresultStr, Itemserviceresult.class);
            JSONArray jsonArrayTransactionArea = JSONArray.parseArray(itemserviceresult.getTransactionArea());//存放了返回的结果状态
            String string = jsonArrayTransactionArea.getString(0);
            TransactionArea transactionArea = JSONObject.parseObject(string, TransactionArea.class);
            String responseStr = transactionArea.getResponse();
            Response response = JSONObject.parseObject(responseStr, Response.class);//存放了返回的结果状态
            if (response.isSuccess()) {
                JSONArray jsonArrayData = JSONArray.parseArray(itemserviceresult.getData());//存放了产品的具体信息
                Data date = JSONObject.parseObject(jsonArrayData.getString(0), Data.class);
                JSONArray partListArr = JSONArray.parseArray(date.getPartList());
                String partListStr = partListArr.getString(0);
                PartList partList = JSONObject.parseObject(partListStr, PartList.class);
                String InvOrgStr = partList.getInvOrg();//包含库存信息
                String sourcesStr = JSONObject.parseObject(InvOrgStr, JSONObject.class).getString("sources");
                JSONArray sourcesArr = JSONArray.parseArray(sourcesStr);
                for (Object sources : sourcesArr) {//不同地区对应着不同的价格，遍历所有的地区
                    InvOrg invOrg = JSONObject.parseObject(sources.toString(), InvOrg.class);
                    String currency = invOrg.getCurrency();//货币类型
                    if (!"USD".equals(currency)) continue;//只取美元价格
                    String sourceCd = invOrg.getSourceCd();
                    JSONArray sourcePartArr = JSONArray.parseArray(invOrg.getSourceParts());
                    for (Object sourcePartObj : sourcePartArr) {//不同的分包，对应不同的价格与库存
                        SourcePart sourcePart = JSONObject.parseObject(sourcePartObj.toString(), SourcePart.class);
                        String dateCode = "AW" + sourcePart.getDateCode();//生产批次号，加上AW来标志来自arrow
                        JSONArray availabilityArr = JSONArray.parseArray(sourcePart.getAvailability());//获取库存信息
                        Availability availability = JSONObject.parseObject(availabilityArr.getString(0), Availability.class);
                        String stock = availability.getFohQty();//库存
                        String pricesJson = sourcePart.getPrices();
                        String resaleList = null;
                        if (pricesJson != null) {
                            resaleList = JSONObject.parseObject(pricesJson, JSONObject.class).getString("resaleList");
                        }
                        String leadTime=sourcePart.getMfrLeadTime();
                        Result result = new Result();
                        result.setCurrency(currency);
                        result.setSourceCd(sourceCd);
                        result.setDateCode(dateCode);
                        result.setLeadTime(leadTime);
                        result.setAvailability(Integer.parseInt(stock));
                        if (resaleList != null)
                            result.setPrices(resaleList);
                        resultList.add(result);
                    }
                }
                Result resultTemp = null;
                List<Result> tempList = new ArrayList<>();
                for (Result result : resultList) {//获取库存最大的数据
                    if (resultTemp == null) {
                        resultTemp = result;
                    } else {
                        if (resultTemp.getAvailability() < result.getAvailability()) {
                            tempList.add(resultTemp);
                            resultTemp = result;
                        } else {
                            tempList.add(result);
                        }
                    }
                }
                resultList.removeAll(tempList);
                Result resultTemp1 = null;
                if (resultList.size() > 1) {
                    for (Result result : resultList) {//若存在多条库存相等，则按SourceCd排序判断,取最靠前的
                        if (resultTemp1 == null) {
                            resultTemp1 = result;
                        } else {
                            if (SourceCd.valueOf(resultTemp1.getSourceCd()).ordinal() > SourceCd.valueOf(result.getSourceCd()).ordinal()) {
                                resultTemp1 = result;
                            }
                        }
                    }
                } else if (resultList.size() == 1) {
                    resultTemp1 = resultList.get(0);
                }
                EndResult = resultTemp1;
                if(Integer.valueOf(EndResult.getAvailability())==0){
                    return null;
                }
                int moq = 1;//取最小阶梯价格的作为moq
                if (resultTemp1.getPrices() != null) {
                    JSONArray pricesArr = JSONArray.parseArray(resultTemp1.getPrices());//获取阶梯价格
                    for (Object priceObj : pricesArr) {
                        Prices prices = JSONObject.parseObject(priceObj.toString(), Prices.class);
                        if (moq == 1) {
                            moq = prices.getMinQty();
                        } else if (moq > prices.getMinQty()) {
                            moq = prices.getMinQty();
                        }
                    }
                }
                EndResult.setMinimumOrderQuantity(moq);
            } else {
                System.out.println(response.getReturnMsg() + "： test");
            }
       // } catch (Exception e) {
            //System.out.println("arrow数据解析出错mpn是："+mpn+e);
       // }
        return EndResult;
    }
}

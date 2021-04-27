package test.arrowApi;

import test.arrowApi.jsonModel.Result;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String mpn="ISL83072EIBZA-T";
        String str="http://api.arrow.com/itemservice/v3/en/search/token?login=ameyaholding2&apikey=5f4a1376ff2daa15aebabc37ae073c85e9ca9a2849f9396e293f4f51d789c8e6&search_token="+mpn;
        GetArrowData getArrowData=new GetArrowData();

        String jsonString = null;
        try {
            jsonString = getArrowData.getApiArrow(str);//获取json数据
            Result result=ParserArrowJsonData.parser(jsonString,"mpn");//解析数据
            String path="C:\\Users\\PC\\Desktop\\arrow\\";
            String filename=mpn+"_arrow";
            if(result!=null){
                ExcelUtil.getExcelOne(result,path,filename);
            }

//            if(resultList.size()>0){
//                String[] title={"地区/库存来源","标准包装","库存","最小起订量","价格"};
//                String path="C:\\Users\\PC\\Desktop\\arrow\\";
//                String filename=mpn+"_arrowPrice";
//                ExcelUtil.getExcel(resultList,title,path,filename);//通过excel输出所有的价格
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

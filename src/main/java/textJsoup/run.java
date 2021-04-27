package textJsoup;

public class run {
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.getResponseVerical();
           // main.getalliPost("");
           // main.testTtiasiaget();
            //main.getcoilcraftpost("https://www.coilcraft.com/en-us/api/productsearch/parametric");
           // main.getcoilcraftGet("https://www.coilcraft.com/en-us/api/productsearch/parametric");
          //  main.getChipPost1("https://www.chip1stop.com/CHN/en/view/searchResult/SearchResultWithClassCd?classCd=010101&classLv=3&subWinSearchFlg=false&searchType=2&dispAllFlg=true&searchFlg=false");
          //  main.testElement("https://www.mouser.com/Semiconductors/Integrated-Circuits-ICs/Data-Converter-ICs/_/N-6j74r");
           // main.test("https://www.mouser.com/Semiconductors/Integrated-Circuits-ICs/Amplifier-ICs/_/N-6j73l");
           // System.out.println(main.test("https://www.mouser.com/Semiconductors/Discrete-Semiconductors/Diodes-Rectifiers/_/N-ax1ma"));
          //  System.out.println( main.getHtml_post("https://www.verical.com/server-webapp/api/rest/search/parametric?format=json"));//获取列表页的product——partId
         //  main.getPicture("https://media.digikey.com/photos/Murata%20Photos/CSTCC-G%20SERIES.jpg");
           // main.testCookie();
          //  System.out.println(main.getVHtml("https://www.verical.com/products/embedded-solutions-693/computer-products-708/box-pcs-811/2"));

          //  System.out.println(main.getVHtml("https://www.verical.com/pd/omron-limit-d4c1602-1844319"));
           //System.out.println(main.test("https://www.verical.com/server-webapp/api/getCatalogItems?includeAlternates=false&mpnIDs=1844319&format=json&vipCacheBust="));

           // System.out.println(main.test("https://www.mouser.com/Power/Power-Management-ICs/_/N-6g7lv"));
            //详情页图片，pdf，mfr，mpn,attr
            // &&详情页url由“https://www.verical.com/pd/”+mfr(小写)+lastLevel（小写）+mpn+partId(id可由列表页json数据获取)
         //   System.out.println(main.test("https://www.verical.com/static/generated/master.json?format=json"));
            //获取详情页价格，与库存
           //System.out.println(main.getHtml("https://www.verical.com/static/generated/master.json?format=json"));
           // System.out.println(main.getHtml_post("https://www.verical.com/server-webapp/api/rest/search/parametric?format=json"));
            //列表页数据接口
          // System.out.println(main.getHtml("https://www.verical.com/server-webapp/api/rest/buckets/types/cart?forceReload=true&format=json"));
           //System.out.println(main.getHtml("https://www.verical.com/server-webapp/api/rest/cms/categories?forceReload=false&per_page=100&format=json&wpc=1562607770288"));

          //  System.out.println(main.getHtml_post("https://www.verical.com/server-webapp/api/rest/search/parametric?search_term=&part_category_id=768&start_index=0&quantity_min=0&facet_field=%255%2522Bmanufacturer_id%2522%252C%2522category_unique%2522%255D"));





          //  System.out.println(main.test("https://www.avnet.com/search/resources/store/715839038/productview/byCategory/3074457345616735233?searchType=100&profileName=Avn_findProductsByCategory_Summary&searchSource=Q&storeId=715839038&catalogId=10001&langId=-1&contractId=4000000000000071017&responseFormat=json&pageSize=20&pageNumber=2&_wcf.search.internal.boostquery=obsoleteFlag%3A%22NO%22%5E599999.0+price_USD%3A%7B0.00001+TO+*%7D%5E499999.0+inStock%3A%22true%22%5E9000.0+newProductFlag%3A%22Yes%22%5E0.085+topSellerFlag%3A%22Yes%22%5E0.080+packageTypeCode%3A%22BKN%22%5E0.075&intentSearchTerm=*&searchTerm=*&wt=json"));
            //main.test("https://www.avnet.com/search/resources/store/715839035/productview/byCategory/3074457345616735233?searchType=100&profileName=Avn_findProductsByCategory_Summary_More_Ajax&searchSource=Q&storeId=715839035&catalogId=10001&langId=-1&contractId=4000000000000071008&responseFormat=json&pageSize=20&pageNumber=1&_wcf.search.internal.boostquery=obsoleteFlag%3A%22NO%22%5E599999.0+price_USD%3A%7B0.00001+TO+*%7B%5E499999.0+inStock%3A%22true%22%5E9000.0+newProductFlag%3A%22Yes%22%5E0.085+topSellerFlag%3A%22Yes%22%5E0.080+packageTypeCode%3A%22BKN%22%5E0.075&wt=json&userAction=false");

            // System.out.println(main.test("https%3A//www.avnet.com/search/resources/store/715839035/productview/byCategory/3074457345616735233?searchType=100&profileName=Avn_findProductsByCategory_Summary_Filter_Ajax&searchSource=Q&storeId=715839035&catalogId=10001&langId=-1&contractId=4000000000000071008&responseFormat=json&pageSize=20&pageNumber=1&_wcf.search.internal.boostquery=obsoleteFlag%3A%22NO%22%5E599999.0+price_USD%3A%7B0.00001+TO+*%7D%5E499999.0+inStock%3A%22true%22%5E9000.0+newProductFlag%3A%22Yes%22%5E0.085+topSellerFlag%3A%22Yes%22%5E0.080+packageTypeCode%3A%22BKN%22%5E0.075&intentSearchTerm=*&searchTerm=*&wt=json"));

          //  System.out.println(main.test("https%3A//www.avnet.com/search/resources/store/715839035/productview/byCategory/3074457345616735233?searchType=100&profileName=Avn_findProductsByCategory_Summary_Filter_Ajax&searchSource=Q&storeId=715839035&catalogId=10001&langId=-1&contractId=4000000000000071008&responseFormat=json&pageSize=20&pageNumber=1&_wcf.search.internal.boostquery=obsoleteFlag:%22NO%22%5E599999.0+price_USD:{0.00001+TO+*}%5E499999.0+inStock:%22true%22%5E9000.0+newProductFlag:%22Yes%22%5E0.085+topSellerFlag:%22Yes%22%5E0.080+packageTypeCode:%22BKN%22%5E0.075&wt=json"));
           // System.out.println(main.test("https://www.avnet.com/search/resources/store/715839038/productview/byCategory/3074457345616735233?searchType=100&profileName=Avn_findProductsByCategory_Summary_More_Ajax&searchSource=Q&storeId=715839038&catalogId=10001&langId=-1&contractId=4000000000000071017&responseFormat=json&pageSize=20&pageNumber=1&_wcf.search.internal.boostquery=obsoleteFlag%3A%22NO%22%5E599999.0+price_USD%3A%7B0.00001+TO+*%7D%5E499999.0+inStock%3A%22true%22%5E9000.0+newProductFlag%3A%22Yes%22%5E0.085+topSellerFlag%3A%22Yes%22%5E0.080+packageTypeCode%3A%22BKN%22%5E0.075&intentSearchTerm=*&searchTerm=*&wt=json"));
            //可获取改列表下的产品数量，以及分页下的产品详情，可获取id用来获取价格
         //    System.out.println(main.test("https://www.avnet.com/shop/apac/c/connectors/connector-accessories/backshells/"));

            //3074457345635557733,3074457345639522268,3074457345642379204,3074457345637730784,3074457345639650941,
            //3074457345634917855,3074457345636688295,3074457345635699733,3074457345641295712,3074457345635142830,

           // String doc=main.getVHtml("https://www.avnet.com/shop/apac/c/data-conversion/adcs/");
//            Document document= Jsoup.parse(doc);
//            System.out.println();//get请求，可获取category
//            Elements e = document.getElementsByTag("script");
//            Element var=e.get(1);
//            String varStr=var.toString().split("var")[1].replace("WCParamJS = ","").replace(";","").replace("'","\"").trim();
//            System.out.println(varStr);
//            JSONObject str=JSONObject.parseObject(varStr,JSONObject.class);
           // System.out.println(doc);
           //System.out.println(main.getVHtml("https://www.avnet.com/shop/FetchProductPrice?storeId=715839038&langId=-1&currency=USD&contractPriceReq=Y&catEntryIds=3074457345635557733,3074457345639522268,3074457345642379204,3074457345637730784,3074457345639650941,3074457345634917855,3074457345636688295,3074457345635699733,3074457345641295712,3074457345635142830,&_=1574672486671"));
           //获取价格，需要拼接产品id，原始请求为5个一组，可自己添加产品id

         //  System.out.println(main.test("https://www.avnet.com/shop/AllProducts?catalogId=10001&langId=-1&storeId=715839038&krypto=SXnC930b63zqebcvknl0gA5AJy3vqfbBc3yTODdBAlZ2%2FU%2FraDkqIAdEhpwTwIOT8m%2FEKqaLqObQrQ8QbTzFBQ%3D%3D"));//所有列表
     //  System.out.println(main.test("https://www.digikey.com/products/en"));
            //System.out.println(main.test("https://www.avnet.com/shop/AvnPriceInventoryAvailabilityView?catalogId=10001&langId=-7&storeId=715839038&catentryId=3074457345630017631&botFlow=&dojo.preventCache=1594779147152&userAction=false"));//库存与价格
         //   System.out.println(main.test("https://www.avnet.com/shop/apac/zh-CN/products/maxim-integrated/max14789egsat-3074457345630017631/"));//详情页数据，存在属性，图片，pdf等（没有stock和价格）
           //System.out.println(main.test("https://www.avnet.com/shop/apac/c/interface/serial-communications/receivers/"));//列表页
           // System.out.println(main.getVHtml("https://www.avnet.com/shop/AvnPriceInventoryAvailabilityView?catalogId=10001&langId=-1&storeId=715839038&catentryId=3074457345636687238&botFlow="));
         //   System.out.println(main.testAvnetDetail("https://www.avnet.com/shop/us/products/glenair/d38999-32w13n-3074457345625325393/"));

           // System.out.println(main.doGet("https://www.avnet.com/search/resources/store/_storeId_/search/count/?storeId=_storeId_&catalogId=10001&langId=_langId_&currency=_selectedCurrency_&orgEntityId=_orgEntityId_&contractId=_contractId_&searchTerm=_searchTerm_&_wcf.search.expr=_searchTerm_&avnRedirect=true&wt=json"));
//              System.out.println(main.doGet("https://www.avnet.com/search/resources/store/715839038/productview/byCategory/3074457345616735461?searchType=100&profileName=Avn_findProductsByCategory_Summary_Filter_Ajax&searchSource=Q&storeId=715839038&catalogId=10001&langId=-1&contractId=4000000000000071017&responseFormat=json&pageSize=20&pageNumber=1&_wcf.search.internal.boostquery=obsoleteFlag:%22NO%22^599999.0+price_USD:{0.00001+TO+*}^499999.0+inStock:%22true%22^9000.0+newProductFlag:%22Yes%22^0.085+topSellerFlag:%22Yes%22^0.080+packageTypeCode%3A%22BKN%22^0.075&wt=json&userAction=false".replace("[","%5B")
//                      .replace("]","%5D")
//                       .replace("?","%3F")
//                      .replace("=","%3D")
//                      .replace("!","%21")
//                      .replace("*","%2A")
//                     //   .replace("\"","%22")
//                       // .replace("\'","%27")
//                      .replace("(","%28")
//                      .replace(")","%29")
//                      .replace(";","%3B")
//                      .replace("^","%5E")
//                      //.replace(":","%3A")
//                      .replace("{","%7B")
//                      .replace("}","%7D")
//                .replace("@","%40")
//                .replace("+","%2B")
//                .replace("$","%24")
//                .replace(",","%2C")
//                .replace("#","%23")
                   //   .trim()));

//            String string="https://www.avnet.com/shop/AvnPriceInventoryAvailabilityView";
//            System.out.println(main.doGet(string));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

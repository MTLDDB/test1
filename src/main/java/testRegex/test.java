package testRegex;

import com.alibaba.fastjson.JSON;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {

        DecimalFormat format   =   new   DecimalFormat("0.000000");
        double   d   =   12.1234567891;
        System.out.println(format.format(d));
//        long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
//        System.out.println(timeNew);
        //String url="https://www.digikey.com/product-detail/en/samtec-inc/DW-50-11-L-T-400/DW-50-11-L-T-400-ND/6776277";
        //List<String> stringList= Arrays.asList(url.toString().trim().split("/"));
        //System.out.println(stringList.get(stringList.size()-2));
        String line = "https://www.verical.com/products/others-1598/unsorted-1599/2";
        String pattern = "-\\d+/";//"(-(\\d+)/)";
//
//        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
//
//        // 现在创建 matcher 对象
       // Matcher m = r.matcher(line);
//      //  Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = r.matcher(line);
//        String i="0";
        while (matcher.find()) {

            System.out.println(matcher.group(0));
//            System.out.println(matcher.group());
//            System.out.println(matcher.group(2));
//            i=matcher.group(4);
//            System.out.println(i);
//            if(i.length()>0)
//                System.out.println(i.length()+"ppp");
        }
////        String i="245";
////        Double pageNum= Double.parseDouble(i)/ 100;
////        int page = (int)Math.ceil(pageNum);
////        System.out.println(page);
//
//
//        Pattern p = Pattern.compile("(\\d+)");
//
//        StringBuilder string = new StringBuilder("num=10&page=60;");
//        Matcher m1 = p.matcher(string.toString());//Matcher类的matcher方法用判断字符串和正则表达式是否匹配
//        int[] num = new int[4];
//        int index = 0;
//        while (m1.find()) {
//            num[index++] = Integer.parseInt(m1.group(0));
//        }
//        int pageNumber = num[1];
//        System.out.println(pageNumber+"++++++++++++++++++++++++"+ JSON.toJSONString(num));

    }
}

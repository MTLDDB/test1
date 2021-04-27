import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class testTime {
    public static void main(String[] args) {
//        String s="PiezoListen™";
//        String json=JSON.toJSONString(s);
//        System.out.println(json);

        String  x=null;
        System.out.println(A.getA());
        System.out.println(Integer.parseInt(""));
        System.out.println(StringUtils.isNotBlank(""));

        String str="STF26NM60N ";
        System.out.println(stringToAscii(str));
        System.out.println(stringToAscii(str.replace("\\u00A0+","")));
        System.out.println(stringToAscii(
                str.replaceAll("\\u00A0+", "")));
//        int n=9;
//        for(int i=1;i<=n;i++){
//            for(int j=n-1;j>=i;j--){
//                System.out.print(" ");
//            }
//            for(int j=1;j<=2*i-1;j++){
//                System.out.print("*");
//            }
//            System.out.println();
//        }
    }

    static class  A{
       static int a;
        public static int getA(){
            return a;
        }
    }
    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]).append(",");
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();
    }
    private static  boolean judgeDate(String str) {
        boolean  judge = true;
        String sfstr ="";

        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 =new SimpleDateFormat("yyyy-MM-dd");
        try {
            sfstr = sf2.format(sf1.parse(str));
            System.out.println(sfstr);
        } catch (ParseException e) {
            // e.printStackTrace();
            judge=false;
        }

        return  judge;
    }


}

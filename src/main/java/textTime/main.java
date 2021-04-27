package textTime;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class main {
    public static void main(String[] args) throws ParseException {

        List<String> list=new ArrayList<>();
        List<String> listTemp=new ArrayList<>();
        List<String> listTemp1=new ArrayList<>();
        list.add("1");
        list.add("4");
        list.add("1");
        list.add("5");
        list.add("2");
        list.add("1");
        list.add("5");
        list.add("4");
        list.add("5");
        String temp=null;
        list.clear();
        for (String integer : list) {
            if(temp==null){
                temp=integer;
            }else {
                if (Integer.valueOf(temp) < Integer.valueOf(integer)) {
                    listTemp1.clear();
                    listTemp.add(temp);
                    temp = integer;
                } else if(Integer.valueOf(temp) == Integer.valueOf(integer)){
                    listTemp1.add(integer);
                }else {
                    listTemp.add(integer);
                }
            }
        }
        listTemp1.add(temp);
        System.out.println(JSON.toJSONString(listTemp));
        System.out.println(JSON.toJSONString(listTemp1.size()));



//        String dateStr="2020/7/1";
//          SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        Date tmpDate = null;
//        if (StringUtils.isNotBlank(dateStr)) {
//            tmpDate = sdf.parse(dateStr);
//        }
//        System.out.println(tmpDate);
        //long date=System.currentTimeMillis()/1000-24*60*60;
       // System.out.println(date);
//        Date newTime=new Date();
//        String time="20201119155755";
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//
//       // System.out.println(format.format(time));
//       // String date=format.format(newTime);
//        String date="isi_20201027100234";
//        System.out.println(date);
//
//        String updateTime="";
//        if (StringUtils.isNotBlank(date)) {
//            StringBuffer strbuff = new StringBuffer(date);
//            strbuff.insert(8, "_");
//            updateTime = strbuff.toString();
//            System.out.println(updateTime);
//        }
    }
    private boolean judgeDate(String str) {
        boolean  judge = true;
        String sfstr ="";

        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 =new SimpleDateFormat("yyyy-MM-dd");
        try {
            sfstr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            // e.printStackTrace();
            judge=false;
        }
        return  judge;
    }

    void  dealTZ(){
        String dateTime = "2020-04-27T21:17:56.237Z";
        dateTime = dateTime.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date time = format.parse(dateTime);
            String result = defaultFormat.format(time);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

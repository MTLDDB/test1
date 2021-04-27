package test.arrowApi;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

    private static String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            System.out.println("属性不存在");
            return null;
        }
    }
    public static <T> void getExcel(List<T> list,String[] title,String path,String fileName) {
        //创建工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();//这里也可以设置sheet的Name
        //创建工作表对象
        HSSFSheet sheet = workbook.createSheet();
        //创建工作表的行
        HSSFRow row = sheet.createRow(0);//设置第一行，从零开始
        //设置列名
        for (int i=0;i<title.length;i++){
            row.createCell(i).setCellValue(title[i]);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i+1);
            T t=list.get(i);
            String[] fieldNames=getFiledName(t);
            for (int i1 = 0; i1 < fieldNames.length; i1++) {
                String value=getFieldValueByName(fieldNames[i1],t).toString();
                row.createCell(i1).setCellValue(value);
            }
        }
        row=sheet.createRow(list.size()+2);
        row.createCell(0).setCellValue("备注1");
        row.createCell(1).setCellValue("不同地区价格的表示 " +
                "USD: US Dollar; " +
                "AUD: Australian Dollar; " +
                "EUR: European Union Euro; " +
                "KRW: South Korean Won ; " +
                "ILS: Israeli Shekel");
        row=sheet.createRow(list.size()+3);
        row.createCell(0).setCellValue("备注2");
        row.createCell(1).setCellValue("库存来源/区域的简短标识符 " +
                "NAC:Arrow North America;" +
                "EUROPE: Arrow Europe;" +
                "ASIA: Arrow Asia;" +
                "VERICAL: Verical Marketplace;");
        workbook.setSheetName(0,"sheet1");//设置sheet的Name

        //文档输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream( path+ fileName +".xls");//new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString()
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static <T> void getExcelOne(T t,String path,String fileName) {
        //创建工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();//这里也可以设置sheet的Name
        //创建工作表对象
        HSSFSheet sheet = workbook.createSheet();
        //创建工作表的行
        HSSFRow row = sheet.createRow(0);//设置第一行，从零开始
        HSSFRow row1= sheet.createRow(1);
        //设置列名

            String[] fieldNames=getFiledName(t);
            for (int i = 0; i < fieldNames.length; i++) {
                row.createCell(i).setCellValue(fieldNames[i]);
                String value=getFieldValueByName(fieldNames[i],t).toString();
                row1.createCell(i).setCellValue(value);
            }
        workbook.setSheetName(0,"sheet1");//设置sheet的Name

        //文档输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream( path+ fileName +".xls");//new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString()
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

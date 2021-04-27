package testFile;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Read {
    public static void main(String[] args) throws IOException {
        File file=new File("C:\\Users\\PC\\Desktop\\my_log.log");
        BufferedReader bufferedReader =new BufferedReader(new FileReader(file));
        String linetxt = null;
        //result用来存储文件内容
        StringBuilder result = new StringBuilder();
        //按使用readLine方法，一次读一行
        Set<String> set= new HashSet();
        while((linetxt =bufferedReader.readLine())!=null)

        {
            String re = linetxt;//用split()函数直接分割
            if (re.contains("stockdetail表更新失败,MPN=")) {
                String[] ree = linetxt.split("MPN=");
                set.add(ree[1].trim());
                //System.out.println("'"+ree[1].trim()+"',");
            }
        }
        Workbook workbook=new XSSFWorkbook();
        Sheet sheet=workbook.createSheet();
        int i=0;
        for (String s : set) {
            System.out.println(i);
            Row row = sheet.createRow(i++);
            row.createCell(0).setCellValue(s);
        }
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\PC\\Desktop\\MFR_SPIDER\\MPN.xls");
            workbook.write(fout);
            fout.close();
            set.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
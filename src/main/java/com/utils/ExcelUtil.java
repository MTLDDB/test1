package com.utils;

import com.entity.StockDifference;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : huang
 * @date :  2021/3/4
 * @time : 15:49
 * To change this template use File | Settings | File and Code Templates.
 */
public class ExcelUtil {

    public static void getExcel(String excelName, List<StockDifference> stockDifferences, String[] title) {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setDefaultColumnWidth(20);// 默认列宽
        HSSFRow rowTitle = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 添加excel title
        HSSFCell cell = null;
        for (int i = 0; i < title.length; i++) {
            cell = rowTitle.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        for (int k = 0; k < stockDifferences.size(); k++) {
            HSSFRow row = sheet.createRow(k + 1);
            StockDifference ss=stockDifferences.get(k);
            List<String> list = new ArrayList();
            list.add(ss.getObjectid());
            list.add(ss.getMpn());
            list.add(ss.getDomain());
            list.add(ss.getDifference());
            list.add(ss.getOldStock());
            list.add(ss.getNewStock());
            list.add(ss.getOldBacthno());
            list.add(ss.getNewBacthno());
            list.add(ss.getOldStoragetime());
            list.add(ss.getNewStoragetime());
            list.add(ss.getCreatetime());
            for (int j = 0; j < title.length; j++) {
                row.createCell(j).setCellValue(list.get(j));
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\PC\\Desktop\\Test\\" + excelName + ".xls");
            wb.write(fout);
            fout.close();
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

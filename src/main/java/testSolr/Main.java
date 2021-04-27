package testSolr;

import testSolr.Dao.DBhelper;
import testSolr.model.Product;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        long i = 0;
        boolean flag = true;
        long start = System.currentTimeMillis();
        while (flag) {
            try {
                System.out.println("insert程序运行时间：" + new Date());
                long startTime = System.currentTimeMillis();
                new SolrJAmallClient().UpdateProductDocument("5ad9e0bd7308271601732284ccb73_" + i, "BR4H28FJ-dACE" + i);
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;

                System.out.println("insert程序运行时间：" + time + "ms");

            } catch (SQLException e) {
                e.printStackTrace();
            }
            i++;
            if (i > 100000) {
                flag = false;
            }
            //  }
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - start;
        System.out.println("运行总时间：" + time + "ms");
//        List<Product> productList= DBhelper.getProduct();
//        for(Product p:productList){
//            try {
//                long startTime = System.currentTimeMillis();
//                new SolrJAmallClient().UpdateProductDocument(p.getObjectid(),p.getMpn());
//                long endTime=System.currentTimeMillis();
//                long time= endTime-startTime;
//                System.out.println("insert程序运行时间："+time+"ms");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
    }
}

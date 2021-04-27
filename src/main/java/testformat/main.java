package testformat;

import sun.awt.geom.AreaOp;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.ParseException;

public class main {
    public static void main(String[] args) throws ParseException {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();//数字格式化显示
        //dfs.setDecimalSeparator('.');//允许设置小数点分隔符

        dfs.setGroupingSeparator(','); //设置分组分隔符
       // dfs.setMonetaryDecimalSeparator('.');//设置货币小数点分隔符
        DecimalFormat df = new DecimalFormat("###,###,###", dfs);
        int s=df.parse(String.valueOf(91313212.121)).intValue();
        System.out.println(s);
        String aa = "1313212.121";
        String num = df.format(aa);

       // Double amountReceivable = num.doubleValue();
        System.out.println(num);
    }
}

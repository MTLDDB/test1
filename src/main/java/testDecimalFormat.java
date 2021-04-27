import java.text.DecimalFormat;

public class testDecimalFormat {
    public static void main(String[] args) {
        DecimalFormat ddf = new DecimalFormat("###,##0.####");
        System.out.println(ddf.format(2888887663456.08));
    }
}

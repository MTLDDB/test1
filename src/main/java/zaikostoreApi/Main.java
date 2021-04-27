package zaikostoreApi;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String json = new CoreStaffHttpClient().getZaikoArrow("");
            ParserCoreStaffJsonData.parser(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

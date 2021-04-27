package tesyEnum;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : huang
 * @date :  2021/4/22
 * @time : 14:27
 * To change this template use File | Settings | File and Code Templates.
 */
public class main {
    public static void main(String[] args) {
        System.out.println(ResponseCode.ERROR);
    }
}
enum ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(-1,"ERROR");

    private final int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }
}

package testAssert;

public class Mian {
//-ea java 断点生效需要使用-ea命令开启

    public static void main(String[] args) {
        System.out.println("start");
        assert true;
        System.out.println("go on");
        assert false:"stop";
        System.out.println("end");
    }
}

package test.DynamicProxy;

public class main {
    public static void main(String[] args) {
        Flyable fly=new Bird();
        MyInvocationHandler handler = new MyInvocationHandler(fly);
        Flyable flyable=handler.getProxy();
        flyable.fly("huang");
    }
}

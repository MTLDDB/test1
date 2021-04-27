//package testDynamicProxy;
//
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//public class main {
//    public static void main(String[] args) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InterruptedException {
//        MyInvocationHandler handler = new MyInvocationHandler(new Bird());
//        Flyable proxy =(Flyable) Proxy.newProxyInstance(Flyable.class, handler);
//        proxy.fly();
////        Class clazz= Bird.class;
////        Method fly= clazz.getMethod("fly");
////        Bird bird=(Bird)clazz.newInstance();
////        fly.invoke(bird,name);
//
//    }
//}


package test.DynamicProxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {
    private Flyable bird;

    public MyInvocationHandler(Flyable bird) {
        this.bird = bird;
    }

    public  Flyable getProxy(){
        Flyable flyable=(Flyable) Proxy.newProxyInstance(bird.getClass().getClassLoader(),bird.getClass().getInterfaces(),this);
        return flyable;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Start Fly");
        try {
            method.invoke(bird, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
        return null;
    }
}

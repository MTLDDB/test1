package testDynamicProxy.proxy;

import java.lang.Override;
import java.lang.reflect.Method;
import testDynamicProxy.Flyable;
import testDynamicProxy.InvocationHandler;

public class TimeProxy implements Flyable {
  private InvocationHandler handler;

  public TimeProxy(InvocationHandler handler) {
    this.handler = handler;
  }

  @Override
  public void fly() {
    try {
    	Method method = testDynamicProxy.Flyable.class.getMethod("fly");
    	this.handler.invoke(this, method, null);
    } catch(Exception e) {
    	e.printStackTrace();
    }
  }
}

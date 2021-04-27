package test.DynamicProxy;

import testDynamicProxy.Flyable;
import testDynamicProxy.InvocationHandler;

import java.lang.reflect.Method;

public class TimeProxy implements Flyable {
  private InvocationHandler handler;

  public TimeProxy(InvocationHandler handler) {
    this.handler = handler;
  }

  @Override
  public void fly() {
    try {
    	Method method = Flyable.class.getMethod("fly");
    	this.handler.invoke(this, method, null);
    } catch(Exception e) {
    	e.printStackTrace();
    }
  }
}

package testDynamicProxy;

import java.lang.reflect.Method;

public interface InvocationHandler {
    void invoke(Object proxy, Method method, Object[] args);
}

package club.codeqi.factory;


import club.codeqi.Annotation.Autowire;
import club.codeqi.Annotation.Component;
import club.codeqi.utils.TransationManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/2/2 0002 22:01
 */
@Component
public class ProxyFactory{

    @Autowire
    private TransationManager transationManager;

    public Object getProxy(Object target){
        Class<?>[] interfaces = target.getClass().getInterfaces();
        if(interfaces.length==0){
            return this.CGlibProxy(target);
        }
        else{
            return this.JdkProxy(target);
        }

    }
    private Object JdkProxy(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result =null;
                transationManager.begin();
                try{
                    result = method.invoke(target, args);
                    transationManager.commit();
                }
                catch (Exception e){
                    transationManager.rollback();
                }
                return result;
            }
        });
    }

    private Object CGlibProxy(Object target){
        return Enhancer.create(target.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                transationManager.begin();
                try{
                    result = method.invoke(target, objects);
                    transationManager.commit();
                }
                catch (Exception e){
                    transationManager.rollback();
                }
                return result;
            }
        });
    }

}

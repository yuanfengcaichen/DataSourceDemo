package com.xuelangyun.datasource.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/2/2 0002 22:01
 */
@Slf4j
@Component
public class ProxyFactory {

  public Object getProxy(Object target) {
    Class<?>[] interfaces = target.getClass().getInterfaces();
//    if (interfaces.length == 0) {
//      return this.CGlibProxy(target);
//    } else {
//      return this.JdkProxy(target);
//    }
    return this.JdkProxy(target);
  }

  private Object JdkProxy(Object target) {
    return Proxy.newProxyInstance(
        target.getClass().getClassLoader(),
        target.getClass().getInterfaces(),
        new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = null;
            log.info("JDK代理开始，代理类：" + target.getClass() + "代理方法：" + method.getName());
            try {
              result = method.invoke(target, args);
              log.info("JDK代理完成，代理类：" + target.getClass() + "代理方法：" + method.getName());
            } catch (Exception e) {
              log.error(proxy.getClass() + " - " + method.getName() + "方法执行异常");
            }
            return result;
          }
        });
  }

  //  private Object CGlibProxy(Object target) {
  //    return Enhancer.create(
  //        target.getClass(),
  //        new MethodInterceptor() {
  //          @Override
  //          public Object intercept(
  //              Object o, Method method, Object[] objects, MethodProxy methodProxy) throws
  // Throwable {
  //            Object result = null;
  //            try{
  //              log.info("CGlib代理开始，代理类：" + target.getClass() + "代理方法：" + method.getName());
  //              try {
  //                result = method.invoke(target, objects);
  //                log.info("CGlib代理完成，代理类：" + target.getClass() + "代理方法：" + method.getName());
  //              } catch (Exception e) {
  //                log.error(o.getClass() + " - " + method.getName() + "方法执行异常");
  //              }
  //            }catch (Exception e){
  //              log.error("错误信息：",e);
  //            }
  //            return result;
  //          }
  //        });
  //  }
}

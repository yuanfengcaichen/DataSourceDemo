package com.xuelangyun.datasource.proxy;

import com.xuelangyun.datasource.annotation.XOTransactional;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/** @Author mochen.qy @Date 2022/2/15 18:25 @Description: 仅识别类上的事务注解，未识别方法上的事务注解，不能单独为方法生成代理对象 */
@Component
public class TransactionalProxyBeanProcessor implements BeanPostProcessor {
  @Autowired ProxyFactory proxyFactory;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    if (checkBean(bean)) {
      return proxyFactory.getProxy(bean);
    }
    return bean;
  }

  Boolean checkBean(Object bean) {
    Boolean flag = false;
    XOTransactional annotation =
        AnnotationUtils.findAnnotation(bean.getClass(), XOTransactional.class);
    if (null != annotation) {
      return true;
    }
    return flag;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }
}

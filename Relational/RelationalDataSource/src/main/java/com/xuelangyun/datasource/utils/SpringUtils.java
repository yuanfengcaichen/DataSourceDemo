package com.xuelangyun.datasource.utils;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/** @Author mochen.qy @Date 2022/2/15 10:50 @Description: */
@Component
public final class SpringUtils implements ApplicationContextAware {

  @Getter private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (SpringUtils.applicationContext == null) {
      SpringUtils.applicationContext = applicationContext;
    }
  }

  public static <T> String[] getBeanNames(Class<T> clazz) {
    return SpringUtils.applicationContext.getBeanNamesForType(clazz);
  }

  public static <T> T getBean(Class<T> clazz) {
    return SpringUtils.applicationContext.getBean(clazz);
  }

  public static Object getBean(String name) {
    return SpringUtils.applicationContext.getBean(name);
  }

  public static String getProperty(String key) {
    return SpringUtils.applicationContext.getEnvironment().getProperty(key);
  }

  // 运行时创建并注册dataSource的bean对象
  public static void addDataSource(
      String dataSourceName, DataSourceProperties dataSourceProperties) {
    ConfigurableApplicationContext configurableApplicationContext =
        (ConfigurableApplicationContext) SpringUtils.getApplicationContext();

    // 获取bean工厂并转换为DefaultListableBeanFactory
    DefaultListableBeanFactory defaultListableBeanFactory =
        (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();

    // 通过BeanDefinitionBuilder创建bean定义
    BeanDefinitionBuilder beanDefinitionBuilder =
        BeanDefinitionBuilder.genericBeanDefinition(DruidDataSource.class);

    // 添加数据库配置信息
    beanDefinitionBuilder.addPropertyValue("url", dataSourceProperties.getUrl());
    beanDefinitionBuilder.addPropertyValue("username", dataSourceProperties.getUsername());
    beanDefinitionBuilder.addPropertyValue("password", dataSourceProperties.getPassword());

    // 注册bean
    defaultListableBeanFactory.registerBeanDefinition(
        dataSourceName, beanDefinitionBuilder.getRawBeanDefinition());
  }
}

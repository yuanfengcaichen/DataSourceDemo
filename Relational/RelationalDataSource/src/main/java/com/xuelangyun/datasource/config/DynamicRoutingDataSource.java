package com.xuelangyun.datasource.config;

import com.xuelangyun.datasource.tx.ConnectUtils;
import com.xuelangyun.datasource.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author mochen.qy @Date 2022/2/14 17:46 @Description: 运行时动态决定使用哪个数据源
 * 1.加载配置的数据源到AbstractRoutingDataSource的targetDataSources中 2. 调用父类的afterPropertiesSet方法重新设置数据源3.
 * 实现动态加载数据源 4. 支持运行时自动设置数据源 5. 使用时手动设置determineCurrentLookupKey的值
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
  private static final ThreadLocal<String> dataSourceKey =
      ThreadLocal.withInitial(() -> "defaultDataSource");
  public static Map<Object, Object> dataSourcesMap = new ConcurrentHashMap<>(10);

  static {
    String[] beanNames = SpringUtils.getBeanNames(DataSource.class);
    Arrays.asList(beanNames)
        .forEach(beanName -> dataSourcesMap.put(beanName, SpringUtils.getBean(beanName)));
  }

  @Override
  public Connection getConnection() throws SQLException {
    Connection currentConnection = ConnectUtils.getCurrentConnection();
    return currentConnection;
  }

  @Override
  protected Object determineCurrentLookupKey() {
    return DynamicRoutingDataSource.dataSourceKey.get();
  }

  public static void setDataSource(String dataSource) {
    DynamicRoutingDataSource.dataSourceKey.set(dataSource);
    log.info("当前数据源切换为：" + dataSource);
    DynamicRoutingDataSource dynamicDataSource =
        (DynamicRoutingDataSource) SpringUtils.getBean("dynamicRoutingDataSource");
    dynamicDataSource.afterPropertiesSet();
  }

  public static String getDataSource() {
    return DynamicRoutingDataSource.dataSourceKey.get();
  }

  public static void clear() {
    DynamicRoutingDataSource.dataSourceKey.remove();
  }
}

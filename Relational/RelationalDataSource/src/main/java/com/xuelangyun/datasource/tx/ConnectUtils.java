package com.xuelangyun.datasource.tx;

import com.xuelangyun.datasource.config.DynamicRoutingDataSource;
import com.xuelangyun.datasource.utils.SpringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/** @Author mochen.qy @Date 2022/2/15 14:39 @Description: */
public class ConnectUtils {
  private static final ThreadLocal<Map<String, ConnectionProxy>> connectMap =
      ThreadLocal.withInitial(() -> new HashMap<>());
  private static final ThreadLocal<Boolean> autoCommit = ThreadLocal.withInitial(() -> true);

  public static ConnectionProxy getCurrentConnection() throws SQLException {
    String dataSource = DynamicRoutingDataSource.getDataSource();
    Map<String, ConnectionProxy> threadConnectionMap = connectMap.get();
    if (!threadConnectionMap.containsKey(dataSource)) {
      DataSource targetDataSource = (DataSource) SpringUtils.getBean(dataSource);
      Connection connection = targetDataSource.getConnection();
      connection.setAutoCommit(autoCommit.get());
      threadConnectionMap.put(dataSource, new ConnectionProxy(connection, dataSource));
    }
    return threadConnectionMap.get(dataSource);
  }

  public static void closeAutoCommit() {
    autoCommit.set(false);
  }

  public static Map<String, ConnectionProxy> getConnectMap() throws SQLException {
    return connectMap.get();
  }
}

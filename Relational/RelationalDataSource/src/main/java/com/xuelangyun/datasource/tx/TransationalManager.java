package com.xuelangyun.datasource.tx;

import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Map;

/** @Author mochen.qy @Date 2022/2/15 14:39 @Description: */
@Component
public class TransationalManager {

  public void begin() throws SQLException {
    ConnectUtils.closeAutoCommit();
  }

  public void submit(Boolean submit) throws SQLException {
    Map<String, ConnectionProxy> stringConnectionMap = ConnectUtils.getConnectMap();
    stringConnectionMap
        .values()
        .forEach(
            conn -> {
              conn.notify(submit);
            });
  }
}

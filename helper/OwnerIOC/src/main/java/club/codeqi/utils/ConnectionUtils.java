package club.codeqi.utils;

import club.codeqi.Annotation.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/2/2 0002 21:30
 */
@Component
public class ConnectionUtils {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    public Connection getCurrentConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection==null){
            connection = DruidUtils.getInstance().getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }
}

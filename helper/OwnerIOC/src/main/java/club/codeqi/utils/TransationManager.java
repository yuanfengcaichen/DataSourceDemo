package club.codeqi.utils;

import club.codeqi.Annotation.Autowire;
import club.codeqi.Annotation.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/2/2 0002 21:33
 */
@Component
public class TransationManager {
    @Autowire
    private ConnectionUtils connectionUtils;

    public void begin() throws SQLException {
        connectionUtils.getCurrentConnection().setAutoCommit(false);
    }
    public void commit() throws SQLException {
        connectionUtils.getCurrentConnection().commit();
    }

    public void rollback() throws SQLException {
        connectionUtils.getCurrentConnection().rollback();
    }
}

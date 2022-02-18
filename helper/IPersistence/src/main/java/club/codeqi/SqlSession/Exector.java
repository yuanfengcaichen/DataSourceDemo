package club.codeqi.SqlSession;

import club.codeqi.io.Configuration;

import java.sql.SQLException;
import java.util.List;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 20:42
 */
public interface Exector {
    public <T> T selectOne(Configuration configuration,String statementId, Object... objects) throws Exception;
    public <E> List<E> selectList(Configuration configuration,String statementId,Object... objects) throws Exception;
    public Integer insert(Configuration configuration,String statementId,Object... objects) throws Exception;
    public Integer update(Configuration configuration,String statementId,Object... objects) throws Exception;
    public Integer delete(Configuration configuration,String statementId,Object... objects) throws Exception;
}

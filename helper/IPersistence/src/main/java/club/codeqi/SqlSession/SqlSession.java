package club.codeqi.SqlSession;

import java.util.List;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 19:28
 */
public interface SqlSession {
    public <E> List<E> selectList(String statementId,Object... objects) throws Exception;
    public <T> T selectOne(String statementId,Object... objects) throws Exception;
    public Integer insert(String statementId,Object... objects) throws Exception;
    public Integer update(String statementId,Object... objects) throws Exception;
    public Integer delete(String statementId,Object... objects) throws Exception;

    public <T> T getMapper(Class<?> mapperClass);
}

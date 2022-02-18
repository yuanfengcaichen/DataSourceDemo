package club.codeqi.SqlSession;

import club.codeqi.SqlSession.Impl.DefaultSqlSessionFactory;
import club.codeqi.io.Configuration;
import club.codeqi.io.Resource;

import java.io.InputStream;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 19:06
 */
public class SqlSessionFactoryBuild {
    public SqlSessionFactory build(String path) throws Exception {
        Configuration configuration = XMLConfiguration.resolve(Resource.ReadAsInputStream(path));

        return new DefaultSqlSessionFactory(configuration);
    }
}

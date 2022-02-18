package club.codeqi.SqlSession.Impl;

import club.codeqi.SqlSession.SqlSession;
import club.codeqi.SqlSession.SqlSessionFactory;
import club.codeqi.io.Configuration;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 19:29
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;
    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }



    @Override
    public String toString() {
        return "DefaultSqlSessionFactory{" +
                "configuration=" + configuration +
                '}';
    }
}

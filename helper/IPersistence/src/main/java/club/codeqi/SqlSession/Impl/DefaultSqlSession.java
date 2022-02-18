package club.codeqi.SqlSession.Impl;

import club.codeqi.SqlSession.SqlSession;
import club.codeqi.io.Configuration;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 19:29
 */
public class DefaultSqlSession implements SqlSession, InvocationHandler {
    private Configuration configuration;
    public DefaultSqlSession (Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... objects) throws Exception {
        SimpleExector simpleExector = new SimpleExector();
        List<E> list = simpleExector.selectList(configuration, statementId, objects);
        return list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... objects) throws Exception {
        SimpleExector simpleExector = new SimpleExector();
        List<Object> list = simpleExector.selectList(configuration, statementId, objects);
        if(list.size()==1){
            return (T) list.get(0);
        }
        return null;
    }

    @Override
    public Integer insert(String statementId, Object... objects) throws Exception {
        SimpleExector simpleExector = new SimpleExector();
        return simpleExector.insert(configuration, statementId, objects);
    }

    @Override
    public Integer update(String statementId, Object... objects) throws Exception {
        SimpleExector simpleExector = new SimpleExector();
        return simpleExector.update(configuration, statementId, objects);
    }

    @Override
    public Integer delete(String statementId, Object... objects) throws Exception {
        SimpleExector simpleExector = new SimpleExector();
        return simpleExector.delete(configuration, statementId, objects);
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        return (T) Proxy.newProxyInstance(mapperClass.getClassLoader(),new Class[]{mapperClass},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String namespace = method.getDeclaringClass().getName();
        String id = method.getName();
        String statementId = namespace+"."+id;
        String type = configuration.getMentMap().get(statementId).getSqlType();
        switch (type){
            case "insert":
                return insert(statementId,args);
            case "update":
                return update(statementId,args);
            case "delete":
                return delete(statementId,args);
            case "select":
                Type genericReturnType = method.getGenericReturnType();
                if(genericReturnType instanceof ParameterizedType){
                    return selectList(statementId,args);
                }
                else{
                    return selectOne(statementId,args);
                }
        }
        return null;
    }
}

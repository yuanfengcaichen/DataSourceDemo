package club.codeqi.SqlSession.Impl;

import club.codeqi.SqlSession.Exector;
import club.codeqi.io.Configuration;
import club.codeqi.io.StateMent;
import club.codeqi.utils.GenericTokenParser;
import club.codeqi.utils.ParameterMapping;
import club.codeqi.utils.ParameterMappingTokenHandler;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 20:42
 */
public class SimpleExector implements Exector {
    private DataSource dataSource;
    @Override
    public <T> T selectOne(Configuration configuration, String statementId, Object... objects) throws Exception {

        PreparedStatement preparedStatement = exector(configuration, statementId, objects);
        ResultSet resultSet = preparedStatement.executeQuery();

        List list = new ArrayList();
        while(resultSet.next()){
            Class<?> resultType = configuration.getMentMap().get(statementId).getResultType();
            Object o = resultType.getDeclaredConstructor().newInstance();
            Field[] declaredFields = resultType.getDeclaredFields();
            for(Field field : declaredFields){
                field.setAccessible(true);
                String name = field.getName();
                Object value = resultSet.getObject(name);
                field.set(o,value);
            }
            list.add(o);
        }
        if(list.size()==1){
            return (T) list.get(0);
        }
        return null;
    }

    @Override
    public <E> List<E> selectList(Configuration configuration, String statementId, Object... objects) throws Exception {
        PreparedStatement preparedStatement = exector(configuration, statementId, objects);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<E> list = new ArrayList();
        while(resultSet.next()){
            Class<?> resultType = configuration.getMentMap().get(statementId).getResultType();
            E o = (E) resultType.getDeclaredConstructor().newInstance();
            Field[] declaredFields = resultType.getDeclaredFields();
            for(Field field : declaredFields){
                field.setAccessible(true);
                String name = field.getName();
                Object value = resultSet.getObject(name);
                field.set(o,value);
            }
            list.add(o);
        }
        return list;
    }

    @Override
    public Integer insert(Configuration configuration, String statementId, Object... objects) throws Exception {
        try{
            PreparedStatement preparedStatement = exector(configuration, statementId, objects);
            preparedStatement.execute();
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public Integer update(Configuration configuration, String statementId, Object... objects) throws Exception {
        try{
            PreparedStatement preparedStatement = exector(configuration, statementId, objects);
            preparedStatement.execute();
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public Integer delete(Configuration configuration, String statementId, Object... objects) throws Exception {
        try{
            PreparedStatement preparedStatement = exector(configuration, statementId, objects);
            preparedStatement.execute();
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    private Boundsql setBounsql(String sql) {
        Boundsql boundsql = new Boundsql();
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String parse = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        boundsql.setSqlText(parse);
        boundsql.setParameterMappings(parameterMappings);
        return boundsql;
    }

    private PreparedStatement exector(Configuration configuration, String statementId, Object... objects) throws Exception{
        dataSource = configuration.getDataSource();
        Connection connection = dataSource.getConnection();
        StateMent stateMent = configuration.getMentMap().get(statementId);
        Class<?> parameType = stateMent.getParameType();
        String sql = stateMent.getSql();
        Boundsql boundsql = setBounsql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(boundsql.getSqlText());
        for(int i=0;i<boundsql.getParameterMappings().size();i++){
            Field declaredField = parameType.getDeclaredField(boundsql.getParameterMappings().get(i).getContent());
            declaredField.setAccessible(true);
            Object o = declaredField.get(objects[0]);
            preparedStatement.setObject(i+1,o);
        }
        return preparedStatement;
    }
}

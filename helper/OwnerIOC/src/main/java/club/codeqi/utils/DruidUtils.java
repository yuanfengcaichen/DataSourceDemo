package club.codeqi.utils;

import com.alibaba.druid.pool.DruidDataSource;


public class DruidUtils {
    private static volatile DruidDataSource druidDataSource;
    private DruidUtils(){

    }

    public static DruidDataSource getInstance() {
        if(druidDataSource==null){
            synchronized (DruidUtils.class){
                if(druidDataSource==null){
                    druidDataSource = new DruidDataSource();
                    druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
                    druidDataSource.setUrl("jdbc:mysql://localhost:3306/jdbc_test");
                    druidDataSource.setUsername("root");
                    druidDataSource.setPassword("1qaz");
                }
            }
        }
        return druidDataSource;
    }
}

package club.codeqi.io;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 19:04
 */
public class StateMent {
    private String StateMentId;
    private Class<?> parameType;
    private Class<?> resultType;
    private String sql;
    private String sqlType;

    public String getStateMentId() {
        return StateMentId;
    }

    public void setStateMentId(String stateMentId) {
        StateMentId = stateMentId;
    }

    public Class<?> getParameType() {
        return parameType;
    }

    public void setParameType(Class<?> parameType) {
        this.parameType = parameType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    @Override
    public String toString() {
        return "StateMent{" +
                "StateMentId='" + StateMentId + '\'' +
                ", parameType=" + parameType +
                ", resultType=" + resultType +
                ", sql='" + sql + '\'' +
                ", sqlType='" + sqlType + '\'' +
                '}';
    }
}

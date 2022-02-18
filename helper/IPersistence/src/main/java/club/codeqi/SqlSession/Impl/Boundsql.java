package club.codeqi.SqlSession.Impl;

import club.codeqi.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 20:59
 */
public class Boundsql {
    private String sqlText;
    private List<ParameterMapping> parameterMappings= new ArrayList<ParameterMapping>();

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

    @Override
    public String toString() {
        return "Boundsql{" +
                "sqlText='" + sqlText + '\'' +
                ", parameterMappings=" + parameterMappings +
                '}';
    }
}

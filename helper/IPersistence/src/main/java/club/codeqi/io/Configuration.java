package club.codeqi.io;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 19:02
 */
public class Configuration {
    private DataSource dataSource;
    private Map<String,StateMent> mentMap = new HashMap<String, StateMent>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, StateMent> getMentMap() {
        return mentMap;
    }

    public void setMentMap(Map<String, StateMent> mentMap) {
        this.mentMap = mentMap;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "dataSource=" + dataSource +
                ", mentMap=" + mentMap +
                '}';
    }
}

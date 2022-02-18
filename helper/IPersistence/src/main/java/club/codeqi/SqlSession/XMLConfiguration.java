package club.codeqi.SqlSession;

import club.codeqi.io.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 19:33
 */
public class XMLConfiguration {
    public static Configuration resolve(InputStream inputStream) throws Exception {
        Configuration configuration = new Configuration();
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> Elements = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for(Element element:Elements){
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.put(name,value);
        }
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driver"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("url"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(comboPooledDataSource);

        Node node = rootElement.selectSingleNode("//mappers");
        List<Element> mapperlist = node.selectNodes("//mapper");
        for(Element element :mapperlist){
            String url = element.attributeValue("url");
            XMLMapper.resolve(configuration,url);
        }

        return configuration;
    }
}

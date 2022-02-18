package club.codeqi.SqlSession;

import club.codeqi.io.Configuration;
import club.codeqi.io.Resource;
import club.codeqi.io.StateMent;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 19:33
 */
public class XMLMapper {
    public static void resolve(Configuration configuration,String maperurl) throws Exception {
        Document document = new SAXReader().read(Resource.ReadAsInputStream(maperurl));
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<List> noodList = new ArrayList();
        List<Element> selectlist = rootElement.selectNodes("//select");
        List<Element> insertlist = rootElement.selectNodes("//insert");
        List<Element> updatelist = rootElement.selectNodes("//update");
        List<Element> deletelist = rootElement.selectNodes("//delete");
        noodList.add(selectlist);
        noodList.add(insertlist);
        noodList.add(updatelist);
        noodList.add(deletelist);
        for(List<Element> list : noodList){
            for(Element element : list){
                StateMent stateMent = new StateMent();
                String id = element.attributeValue("id");
                String statementid = namespace+"."+id;
                Class<?> parameType = Class.forName(element.attributeValue("parameType"));
                Class<?> resultType = Class.forName(element.attributeValue("resultType"));
                String sql = element.getTextTrim();
                String type = element.getName();
                stateMent.setStateMentId(statementid);
                stateMent.setParameType(parameType);
                stateMent.setResultType(resultType);
                stateMent.setSql(sql);
                stateMent.setSqlType(type);
                configuration.getMentMap().put(statementid,stateMent);
            }
        }
    }
}

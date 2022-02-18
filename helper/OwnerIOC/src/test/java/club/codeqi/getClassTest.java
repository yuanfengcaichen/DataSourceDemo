package club.codeqi;

import club.codeqi.Annotation.Component;
import club.codeqi.factory.beanFactorybyAnno;
import org.junit.Test;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/2/7 0007 14:13
 */

public class getClassTest {
    @Test
    public void getAllClass() throws Exception {
        List<String> clazzs = new ArrayList();
        String packageName = "club.codeqi";
        // 包名对应的路径名称
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> resources = getClassTest.class.getClassLoader().getResources(packageDirName);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
            findFullClassName(packageName,filePath, true,clazzs);
            for(String className : clazzs){
                System.out.println(className);
                Class<?> aClass = Class.forName(className);
                if(!aClass.isInterface()){
                    try{
                        Class<?>[] interfaces = aClass.getInterfaces();
                        for(Class c : interfaces){
                            System.out.println("--------"+c+"---------");
                        }
                    }
                    catch (Exception e){

                    }
                }
                if(aClass.isAnnotationPresent(Component.class)){
                    Annotation[] annotations = aClass.getAnnotations();
                    for(Annotation annotation : annotations){
                        System.out.println(annotation.toString());
                    }
                }
            }
        }
    }

    public static void findFullClassName(String packageName,String filePath, boolean recursive,List clazzs){
        //遍历过滤好的文件
        for (File file : findFiles(filePath,recursive)) {
            //如果文件是目录
            if (file.isDirectory()) {
                //调用当前方法
                findFullClassName(packageName+"."+file.getName(),file.getPath(),true,clazzs);  //路径为文件路径
            }else{
                //如果是class文件 则添加包前缀
                String className = packageName+"."+file.getName();
                className = className.substring(0, className.length() - 6);
                //添加类的全路径名到list
                clazzs.add(className);
            }
        }
    }
    public static File[] findFiles(String filePath, boolean recursive) {
        //过滤出 class文件 和 目录
        return new File(filePath).listFiles(file ->
                (file.isFile() && file.getName().endsWith(".class")) || (file.isDirectory() && recursive));
    }

    @Test
    public void getpackgename(){
        String path = getClassTest.class.getPackage().getName();
        System.out.println(path);
    }
}

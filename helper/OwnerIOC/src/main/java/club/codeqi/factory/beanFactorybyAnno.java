package club.codeqi.factory;

import club.codeqi.Annotation.*;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/2/7 0007 14:37
 */
@ComponentScan(path = "club.codeqi")
public class beanFactorybyAnno {
    private static Map<String,Object> map= new HashMap<>();

    static{
        String path = "";
        if(beanFactorybyAnno.class.isAnnotationPresent(ComponentScan.class)){//如果当前的类使用了ComponentScan注解
            ComponentScan annotation = beanFactorybyAnno.class.getAnnotation(ComponentScan.class);
            path = annotation.path();
        }
        else{
            path = beanFactorybyAnno.class.getPackage().getName();
        }
        try {
            initBeans(path);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void initBeans(String path) throws Exception {
        List<String> clazzs = new ArrayList();
        String packageName = path;
        // 包名对应的路径名称
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> resources = beanFactorybyAnno.class.getClassLoader().getResources(packageDirName);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
            findFullClassName(packageName,filePath, true,clazzs);
            List<String> autowireList = new ArrayList();//存在@Autowire注解的类
            List<String> TransList = new ArrayList<>();//存放需要代理的类
            //遍历全部的class，将有@Component注解的类实例化并放入map中
            for(String className : clazzs){
                Class<?> aClass = Class.forName(className);
                if(!aClass.isInterface()){
                    String[] classNames = className.split("\\.");
                    String beanName = lowerFirst(classNames[classNames.length-1]);//默认bean名称为类名的首字母小写
                    Class<?>[] interfaces = aClass.getInterfaces();
                    if(interfaces.length!=0){
                        String[] interfaceNames = interfaces[0].getName().split("\\.");
                        beanName = lowerFirst(interfaceNames[interfaceNames.length-1]);//实现接口的bean名称为接口名的首字母小写
                    }
                    if(aClass.isAnnotationPresent(Component.class)){
                        Component annotation = aClass.getAnnotation(Component.class);
                        if(null!=annotation.value()&&!annotation.value().equals("")){
                            beanName = annotation.value();
                        }
                        putObject(map,aClass,beanName,autowireList);
                    }
                    else if(aClass.isAnnotationPresent(Service.class)){
                        Service annotation = aClass.getAnnotation(Service.class);
                        if(null!=annotation.value()&&!annotation.value().equals("")){
                            beanName = annotation.value();
                        }
                        putObject(map,aClass,beanName,autowireList);
                        if(aClass.isAnnotationPresent(Transactional.class)){
                            TransList.add(beanName);
                        }
                    }
                    else if(aClass.isAnnotationPresent(Respository.class)){
                        Respository annotation = aClass.getAnnotation(Respository.class);
                        if(null!=annotation.value()&&!annotation.value().equals("")){
                            beanName = annotation.value();
                        }
                        putObject(map,aClass,beanName,autowireList);
                    }

                }
            }


            //遍历需要注入的bean
            for(String beanName : autowireList){
                Object o = map.get(beanName);
                Field[] declaredFields = o.getClass().getDeclaredFields();
                for(Field field : declaredFields){
                    field.setAccessible(true);
                    if(field.isAnnotationPresent(Autowire.class)){
                        Set<Map.Entry<String, Object>> entries = map.entrySet();
                        for(Map.Entry<String, Object> entry :entries){
                            Class fieldType = field.getType();
                            if(fieldType.isInstance(entry.getValue())){
                                field.set(o,entry.getValue());
                            }
                        }
                    }
                }
                map.put(beanName,o);
            }

            //遍历需要代理的类
            for(String beanName : TransList){
                Object o = map.get(beanName);
                ProxyFactory proxyFactory = (ProxyFactory) map.get("proxyFactory");
                Object proxy = proxyFactory.getProxy(o);
                map.put(beanName,proxy);
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

    private static void putObject(Map map,Class aClass,String beanName,List autowireList) {
        Object o =null;
        try {
            o = aClass.getDeclaredConstructor().newInstance();
            Field[] declaredFields = aClass.getDeclaredFields();
            for(Field field : declaredFields){
                field.setAccessible(true);
                if(field.isAnnotationPresent(Autowire.class)){
                    autowireList.add(beanName);
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        map.put(beanName,o);
    }

    public static Object getObject(String name){
        return map.get(name);
    }

    public static Map<String,Object> getMap(){
        return map;
    }

    public static String lowerFirst(String oldStr){//首字母转为小写
        char[]chars = oldStr.toCharArray();
        if(chars[0]<=90&&chars[0]>=65){
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }
}

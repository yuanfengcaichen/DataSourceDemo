package com.xuelangyun;

import com.xuelangyun.datasource.RelationalApplication;
import com.xuelangyun.datasource.dao.DynamicDaoImpl;
import com.xuelangyun.datasource.dao.DynamicServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** @Author mochen.qy @Date 2022/2/14 10:57 @Description: */
@SpringBootTest(classes = RelationalApplication.class)
public class DynamicDataSourceTest {

  @Autowired DynamicDaoImpl dynamicDao;
  @Autowired DynamicServiceImpl dynamicService;

  @Test
  public void findAll() {
    dynamicDao.selectAll().forEach(o -> System.out.println(o));
    System.out.println("----------------------------------");
    dynamicDao.selectByCondition().forEach(o -> System.out.println(o));
  }

  @Test
  public void addTwoDB() {
    dynamicDao.addToDB();
  }

  @Test
  public void add2DB() {
    dynamicDao.addMysql1();
    dynamicDao.addMysql2();
  }

  @Test
  public void serviceAdd() {
    dynamicService.addToTwoDB();
  }
}

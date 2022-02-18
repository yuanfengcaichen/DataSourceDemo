package com.xuelangyun.datasource.dao;

// import com.baomidou.dynamic.datasource.annotation.DSTransactional;

import org.springframework.beans.factory.annotation.Autowired;

/** @Author mochen.qy @Date 2022/2/14 12:52 @Description: */
// @Service
public class DynamicServiceImpl {
  @Autowired DynamicDaoImpl dynamicDao;

  // @DSTransactional
  public void addToTwoDB() {
    dynamicDao.addMysql1();
    // int i=1/0;
    dynamicDao.addMysql2();
    // System.out.println("----------");
  }
}

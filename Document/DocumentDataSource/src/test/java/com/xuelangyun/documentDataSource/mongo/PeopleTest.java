package com.xuelangyun.documentDataSource.mongo;

import com.xuelangyun.documentDataSource.mongo.dao.PeopleDao;
import com.xuelangyun.documentDataSource.mongo.entity.People;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/** @Author mochen.qy @Date 2021/10/8 18:31 @Description: */
@SpringBootTest(classes = MongoDemoApplication.class)
public class PeopleTest {
  @Autowired private PeopleDao peopleDao;

  @Test
  public void add() {
    People people = new People();
    people.setName("小张");
    people.setJob("文职");
    people.setAge(22);
    peopleDao.savePeople(people);
  }

  @Test
  public void update() {
    People people = new People();
    people.setName("小张");
    people.setJob("运动员");
    peopleDao.updatePeople(people);
  }

  @Test
  public void delete() {
    peopleDao.deleteById("6160f255ba268a017448e84a");
  }

  @Test
  public void selectByName() {
    People people = peopleDao.findOneByName("小张");
    System.out.println("findOne : " + people);
    List<People> peoples = peopleDao.findByName("小张");
    System.out.println("findList : " + peoples);
  }
}

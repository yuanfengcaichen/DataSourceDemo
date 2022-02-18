package com.xuelangyun.documentDataSource.mongo;

import com.xuelangyun.documentDataSource.mongo.dao.StudentDao;
import com.xuelangyun.documentDataSource.mongo.entity.Family;
import com.xuelangyun.documentDataSource.mongo.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** @Author mochen.qy @Date 2021/10/9 10:22 @Description: */
@SpringBootTest(classes = MongoDemoApplication.class)
class StudentDaoTest {
  @Autowired StudentDao studentDao;

  @Test
  void saveStudent() {
    Family family1 = new Family();
    family1.setAge(39);
    family1.setName("母亲");
    Family family2 = new Family();
    family2.setAge(42);
    family2.setName("父亲");
    List<Family> familyList = new ArrayList<>();
    familyList.add(family1);
    familyList.add(family2);

    Student student = new Student();
    student.setAge(20);
    student.setName("老李");
    student.setCollege("清华大学");
    student.setHobbies(Stream.of("唱歌", "游泳").collect(Collectors.toList()));
    student.setImages(new String[] {"img1.jpg", "img2.jpg", "img3.jpg"});

    HashMap<String, Integer> scores = new HashMap<>();
    scores.put("C语言程序设计", 88);
    scores.put("数据结果与算法", 82);
    scores.put("数据库概论", 90);
    student.setScore(scores);

    student.setFamilies(familyList);

    Date date = new Date();
    student.setCreateTime(date);
    student.setUpdateTime(date);
    studentDao.saveStudent(student);
  }

  @Test
  void saveStudent2() {
    Family family1 = new Family();
    family1.setAge(37);
    family1.setName("mother");
    Family family2 = new Family();
    family2.setAge(40);
    family2.setName("father");
    List<Family> familyList = new ArrayList<>();
    familyList.add(family1);
    familyList.add(family2);

    Student student = new Student();
    student.setAge(20);
    student.setName("老李");
    student.setCollege("北京大学");
    student.setHobbies(Stream.of("读书", "旅游").collect(Collectors.toList()));
    student.setImages(new String[] {"img4.jpg", "img5.jpg", "img6.jpg"});

    HashMap<String, Integer> scores = new HashMap<>();
    scores.put("C语言程序设计", 84);
    scores.put("数据结果与算法", 83);
    scores.put("数据库概论", 65);
    student.setScore(scores);

    student.setFamilies(familyList);

    Date date = new Date();
    student.setCreateTime(date);
    student.setUpdateTime(date);
    studentDao.saveStudent(student);
  }

  @Test
  void updateStudent() {}

  @Test
  void findOne() {
    Query query = new Query(Criteria.where("name").is("老李"));
    System.out.println(studentDao.findOne(query));
  }

  @Test
  void findAll() {
    Query query = new Query(Criteria.where("name").is("老李"));
    List<Student> students = studentDao.find(query);
    students.forEach(student -> System.out.println(student));
  }

  @Test
  void deleteStudent() {}
}

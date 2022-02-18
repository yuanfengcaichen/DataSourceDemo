package com.xuelangyun.documentDataSource.mongo.dao;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.xuelangyun.documentDataSource.mongo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/** @Author mochen.qy @Date 2021/10/9 10:04 @Description: */
@Component
public class StudentDao {

  @Autowired MongoTemplate mongoTemplate;

  public void saveStudent(Student student) {
    mongoTemplate.save(student);
  }

  public UpdateResult updateStudent(Query query, Update update, Boolean multi) {
    UpdateResult updateResult = null;
    if (multi) {
      updateResult = mongoTemplate.updateMulti(query, update, Student.class);
    } else {
      updateResult = mongoTemplate.updateFirst(query, update, Student.class);
    }
    return updateResult;
  }

  public Student findOne(Query query) {
    return mongoTemplate.findOne(query, Student.class);
  }

  public List<Student> find(Query query) {
    return mongoTemplate.find(query, Student.class);
  }

  public DeleteResult deleteStudent(Query query) {
    return mongoTemplate.remove(query, Student.class);
  }
}

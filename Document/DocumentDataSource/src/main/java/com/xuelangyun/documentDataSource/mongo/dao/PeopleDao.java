package com.xuelangyun.documentDataSource.mongo.dao;

import com.xuelangyun.documentDataSource.mongo.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/** @Author mochen.qy @Date 2021/10/8 18:21 @Description: */
@Component
public class PeopleDao {
  @Autowired private MongoTemplate mongoTemplate;

  public void savePeople(People people) {
    mongoTemplate.save(people);
  }

  public People findOneByName(String name) {
    return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), People.class);
  }

  public List<People> findByName(String name) {
    return mongoTemplate.find(new Query(Criteria.where("name").is(name)), People.class);
  }

  public void updatePeople(People people) {
    Update update = new Update().set("job", people.getJob());
    mongoTemplate.updateMulti(
        new Query(Criteria.where("name").is(people.getName())), update, People.class);
  }

  public void deleteById(String id) {
    mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), People.class);
  }
}

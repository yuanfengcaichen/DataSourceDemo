package com.xuelangyun.documentDataSource.mongo.entity;

import com.xuelangyun.documentDataSource.mongo.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/** @Author mochen.qy @Date 2021/10/9 9:57 @Description: */
@Data
public class Student extends BaseEntity {
  private String name;
  private Integer age;
  private String college;
  private List<String> hobbies;
  private List<Family> families;
  private Map<String, Integer> score;
  private String[] images;
  private Date createTime;
  private Date updateTime;
}

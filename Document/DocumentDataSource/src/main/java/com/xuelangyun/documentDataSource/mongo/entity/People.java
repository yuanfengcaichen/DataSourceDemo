package com.xuelangyun.documentDataSource.mongo.entity;

import com.xuelangyun.documentDataSource.mongo.entity.base.BaseEntity;
import lombok.Data;

/** @Author mochen.qy @Date 2021/10/8 18:20 @Description: */
@Data
public class People extends BaseEntity {
  private String name;
  private String job;
  private Integer age;
}

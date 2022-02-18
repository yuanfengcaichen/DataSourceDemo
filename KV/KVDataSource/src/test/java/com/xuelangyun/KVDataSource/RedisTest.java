package com.xuelangyun.KVDataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/** @Author mochen.qy @Date 2022/2/16 17:50 @Description: */
@SpringBootTest(classes = KVApplication.class)
public class RedisTest {
  @Autowired RedisTemplate redisTemplate;

  @Test
  public void test() {
    //    Long add = redisTemplate.opsForSet().add("test", "test");
    //    System.out.println(add);
    Object test = redisTemplate.opsForSet().pop("test");
    System.out.println(test);
  }
}

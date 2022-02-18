package com.xuelangyun.KVDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/** @Author mochen.qy @Date 2022/2/17 13:29 @Description: */
@SpringBootTest(classes = KVApplication.class)
public class LuttuceTest {
  @Autowired LettuceConnectionFactory redisConnectionFactory;

  public void connectTest() {
    JedisConnection connection = (JedisConnection) redisConnectionFactory.getConnection();
  }
}

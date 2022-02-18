package com.xuelangyun;

import com.xuelangyun.datasource.RelationalApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/** @Author mochen.qy @Date 2022/2/11 15:47 @Description: */
@SpringBootTest(classes = RelationalApplication.class)
public class PostGresDataSourceTest {
  @Autowired JdbcTemplate jdbcTemplatePostgres;

  @Test
  public void selectByCondition() {
    List<Map<String, Object>> maps =
        jdbcTemplatePostgres.queryForList("select * from user_info where id = ? ", 1);
    maps.forEach(l -> System.out.println(l));
  }
}

import club.codeqi.Mapper.UserMapper;
import club.codeqi.SqlSession.SqlSession;
import club.codeqi.SqlSession.SqlSessionFactory;
import club.codeqi.SqlSession.SqlSessionFactoryBuild;
import club.codeqi.pojo.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/26 0026 19:54
 */
public class UserMapperTest {
    private UserMapper userMapper;
    /**
     * 初始化userMapper
     * @throws Exception
     */
    @Before
    public void initDataSource() throws Exception {
        SqlSessionFactory build = new SqlSessionFactoryBuild().build("sqlConfiguration.xml");
        SqlSession sqlSession = build.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    /**
     * 代理类添加用户
     * @throws Exception
     */
    @Test
    public void proxyinsert() throws Exception {
        User user = new User();
        user.setUsername("proxy username");
        user.setPassword("proxy password");
        user.setBirthday("2020-11-11");
        Integer integer = userMapper.insertUser(user);
        System.out.println(integer);
    }

    /**
     * 代理类修改用户
     * @throws Exception
     */
    @Test
    public void proxyupdate() throws Exception {
        User user = new User();
        user.setId(15);
        user.setUsername("changeed username");
        Integer integer = userMapper.updateUser(user);
        System.out.println(integer);
    }

    /**
     * 代理类删除用户
     * @throws Exception
     */
    @Test
    public void proxydelete() throws Exception {
        User user = new User();
        user.setId(15);
        Integer integer = userMapper.deleteUser(user);
        System.out.println(integer);
    }

    /**
     * 代理类查询单个用户
     * @throws Exception
     */
    @Test
    public void proxyselectOne() throws Exception {
        User user = new User();
        user.setId(1);
        User resuser1 = userMapper.selectOne(user);
        System.out.println(resuser1);
    }

    /**
     * 代理类查询用户列表
     * @throws Exception
     */
    @Test
    public void proxyselectList() throws Exception {
        List<User> users = userMapper.selectList();
        for(User resuser:users){
            System.out.println(resuser);
        }
    }
}

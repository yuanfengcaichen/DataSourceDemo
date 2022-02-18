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
public class UserStatementTest {
    private SqlSession sqlSession;

    /**
     * 初始化sqlsession
     * @throws Exception
     */
    @Before
    public void initDataSource() throws Exception {
        SqlSessionFactory build = new SqlSessionFactoryBuild().build("sqlConfiguration.xml");
        sqlSession = build.openSession();
    }

    /**
     * 添加用户
     * @throws Exception
     */
    @Test
    public void insertuser() throws Exception {
        User user = new User();
        user.setUsername("test username");
        user.setPassword("test password");
        user.setBirthday("2020-11-11");
        Integer result = sqlSession.insert("club.codeqi.Mapper.UserMapper.insertUser",user);
        System.out.println(result);
    }

    /**
     * 修改用户
     * @throws Exception
     */
    @Test
    public void updateuser() throws Exception {
        User user = new User();
        user.setId(14);
        user.setUsername("change username");
        Integer result = sqlSession.update("club.codeqi.Mapper.UserMapper.updateUser",user);
        System.out.println(result);
    }

    /**
     * 删除用户
     * @throws Exception
     */
    @Test
    public void deletuser() throws Exception {
        User user = new User();
        user.setId(14);
        Integer result = sqlSession.delete("club.codeqi.Mapper.UserMapper.deleteUser",user);
        System.out.println(result);
    }

    /**
     * 查询单个用户
     * @throws Exception
     */
    @Test
    public void selectOne() throws Exception {
        User user = new User();
        user.setId(1);
        User resuser1 = sqlSession.selectOne("club.codeqi.Mapper.UserMapper.selectOne",user);
        System.out.println(resuser1);
    }

    /**
     * 查询全部用户
     * @throws Exception
     */
    @Test
    public void selectList() throws Exception {
        List<User> users = sqlSession.selectList("club.codeqi.Mapper.UserMapper.selectList");
        for(User resuser:users){
            System.out.println(resuser);
        }
    }
}

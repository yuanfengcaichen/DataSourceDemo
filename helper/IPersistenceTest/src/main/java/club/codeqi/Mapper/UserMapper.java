package club.codeqi.Mapper;

import club.codeqi.pojo.User;

import java.util.List;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/1/25 0025 20:21
 */
public interface UserMapper {
    public User selectOne(User user);
    public List<User> selectList();
    public Integer insertUser(User user);
    public Integer updateUser(User user);
    public Integer deleteUser(User user);
}

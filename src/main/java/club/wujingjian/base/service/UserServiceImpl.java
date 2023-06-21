package club.wujingjian.base.service;


import club.wujingjian.base.mapper.UserMapper;
import club.wujingjian.base.po.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    public List<User> selectAll(){
        List<User> userList = userMapper.selectList(null);
        return userList;
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int deleteByMap(Map<String, Object> deleteMap) {
        return userMapper.deleteByMap(deleteMap);
    }

    @Override
    public int deleteBatchIds(List<Long> ids) {
        return userMapper.deleteBatchIds(ids);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }
}

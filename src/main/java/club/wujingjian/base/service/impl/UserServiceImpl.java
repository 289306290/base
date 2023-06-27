package club.wujingjian.base.service.impl;


import club.wujingjian.base.enums.EnumSex;
import club.wujingjian.base.mapper.UserMapper;
import club.wujingjian.base.mapper.VideoMapper;
import club.wujingjian.base.po.User;
import club.wujingjian.base.po.Video;
import club.wujingjian.base.service.UserService;
import club.wujingjian.base.service.VideoService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoService videoService;
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

    @Override
    @DSTransactional
//    @Transactional
    public void testTransaction() {
        User user = new User();
        user.setName("老大");
        user.setSex(EnumSex.MALE);
        user.setEmail("admin@126.com");
        userMapper.insert(user);

        Video video = new Video();
        video.setName("快来智影");
        video.setAuthor("作者本人");
        video.setTitle("标题党");
        video.setLikeCount(10);
//        videoService.save(video);
        videoMapper.insert(video);
        int a = 10/0;
    }
}

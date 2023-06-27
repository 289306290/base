package club.wujingjian.base.service;

import club.wujingjian.base.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    public List<User> selectAll();

    public int insert(User user);

    public int deleteByMap(Map<String, Object> deleteMap);

    public int deleteById(Long id);

    public int deleteBatchIds(List<Long> ids);

    public void testTransaction();
}

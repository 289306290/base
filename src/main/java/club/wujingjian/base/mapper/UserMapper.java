package club.wujingjian.base.mapper;

import club.wujingjian.base.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public interface UserMapper extends BaseMapper<User> {

    Map<String, Object> selectMapById(Long id);

    User selectIgnoreLogicById(Long id);

    IPage<User> selectPageVo(@Param("page") Page page, Integer age);
}

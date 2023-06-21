package club.wujingjian.base;

import club.wujingjian.base.mapper.UserMapper;
import club.wujingjian.base.po.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusPluginTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testPage() {
        Page<User> page = new Page<>( 1,3);
        //SELECT id,name,age,email,deleted FROM t_user WHERE deleted=0 LIMIT 3
        userMapper.selectPage(page, null);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasPrevious());
        System.out.println(page.hasNext());
    }

    @Test
    public void testCustomerPage() {
        //select id,name,age,email from t_user where age > 20 LIMIT 3
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPageVo(page, 20);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasPrevious());
        System.out.println(page.hasNext());
    }

}

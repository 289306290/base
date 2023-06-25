package club.wujingjian.base;

import club.wujingjian.base.enums.EnumSex;
import club.wujingjian.base.mapper.UserMapper;
import club.wujingjian.base.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 在枚举类中添加 @EnumValue
     * 在mybatis配置文件中配置扫描枚举类包 type-enums-package: club.wujingjian.base.enums
     */
    @Test
    public void test() {
        User user = new User();
        user.setName("admin");
        user.setSex(EnumSex.MALE);
        user.setEmail("admin@126.com");
        userMapper.insert(user);
    }
}

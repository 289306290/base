package club.wujingjian.base;

import club.wujingjian.base.po.User;
import club.wujingjian.base.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyBatisPlusServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCount() {
//        System.out.println(userService.count());
        System.out.println(userService.selectAll().size());
    }

    @Test
    public void testSaveBatch() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("abc"+i);
            user.setAge(20+i);
            userList.add(user);
        }
        userService.saveBatch(userList);
    }

    @Test
    public void testDelete() {
        System.out.println(userService.deleteById(27l));
    }

    @Test
    public void testgetOnew() {
        System.out.println(userService.getById(27l));
    }

}

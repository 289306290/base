package club.wujingjian.base;

import club.wujingjian.base.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DynamicDatasourceTransactionalTest {
    @Autowired
    private UserService userService;


    @Test
    public void test01(){
        userService.testTransaction();
    }
}

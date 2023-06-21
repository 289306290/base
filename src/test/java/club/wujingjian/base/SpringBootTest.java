package club.wujingjian.base;
import club.wujingjian.base.mapper.UserMapper;
import club.wujingjian.base.po.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelect(){
//        userMapper.selectList(null);
        val map = userMapper.selectMapById(5l);
        System.out.println(map);
    }

    @Test
    public void testSelectById(){
        val user = userMapper.selectById(14);
        System.out.println(user.getName());
    }

    @Test
    public void testUpdateById(){
        User user = new User();
        user.setName("王五");
        user.setId(14l);
        userMapper.updateById(user);
        System.out.println(user);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("王五");
        user.setAge(18);
        user.setEmail("nice@gmail.com");
        userMapper.insert(user);
        System.out.println(user);
    }

    @Test
    public void selectIgnoreLogic(){
        System.out.println(userMapper.selectIgnoreLogicById(27l));
    }
}

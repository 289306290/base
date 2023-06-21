package club.wujingjian.base.web;

import club.wujingjian.base.po.User;
import club.wujingjian.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public List<User> list(){
        return userService.selectAll();
    }

    @GetMapping("/save")
    public User save(){
        User user = new User();
        user.setAge(10);
        user.setName("张三");
        user.setEmail("zhangsan@126.com");
        userService.insert(user);
        return user;
    }

    @GetMapping("/deleteById")
    public int deleteById(Long id){
        return userService.deleteById(id);
    }

    /**
     * ?ids=15&ids=16&ids=17
     * @param ids
     * @return
     */
    @GetMapping("/deleteBatchIds")
    public int deleteBatchIds(Long[] ids){
        return userService.deleteBatchIds(Arrays.stream(ids).toList());
    }

    @GetMapping("/deleteByMap")
    public int deleteByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age",10);
        return userService.deleteByMap(map);
    }
}

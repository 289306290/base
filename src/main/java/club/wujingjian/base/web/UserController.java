package club.wujingjian.base.web;

import club.wujingjian.base.enums.EnumSex;
import club.wujingjian.base.po.User;
import club.wujingjian.base.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "用户模块",description = "用户模块相关的接口描述")
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
        user.setSex(EnumSex.MALE);
        userService.insert(user);
        return user;
    }

    @GetMapping("/deleteById")
    @Operation(summary="summary根据ID删除用户",description = "描述",method = "method",tags = {"tag1","tag2","tag3"})
    @ApiResponse(ref = "ApiResponse-ref",description="ApiResponse-description")
    @Parameter(name = "id",description = "这个是要删除的用户id(描述)",in = ParameterIn.QUERY,example = "1")
    public int deleteById( Long id){
        return userService.deleteById(id);
    }

    /**
     * ?ids=15&ids=16&ids=17
     * @param ids
     * @return
     */
    @GetMapping("/deleteBatchIds")
    @Operation(tags = {"删除用户"})
    public int deleteBatchIds(Long[] ids){
        return userService.deleteBatchIds(Arrays.stream(ids).toList());
    }

    @Operation(summary = "根据map删除用户", tags = {"tag3"})
    @GetMapping("/deleteByMap")
    public int deleteByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age",10);
        return userService.deleteByMap(map);
    }
}

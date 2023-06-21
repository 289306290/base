package club.wujingjian.base;

import club.wujingjian.base.mapper.UserMapper;
import club.wujingjian.base.po.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","a")
                .between("age",10,30)
                .isNotNull("email");
        //SELECT id,name,age,email,deleted FROM t_user WHERE deleted=0 AND (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test02(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        //SELECT id,name,age,email,deleted FROM t_user WHERE deleted=0 ORDER BY age DESC,id ASC
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test03(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        //UPDATE t_user SET deleted=1 WHERE deleted=0 AND (email IS NULL)
        int result = userMapper.delete(queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test04(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20)
                .like("name", "a")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("小明");
        user.setEmail("test@guigu.com");
        //UPDATE t_user SET name=?, email=? WHERE deleted=0 AND (age > ? AND name LIKE ? OR email IS NULL)
        int result = userMapper.update(user,queryWrapper);
        System.out.println(result);
    }

    /**
     * 如果要执行优先级比较高的(也就是加小括号的sql条件),可以使用lambda表达式.and(i->{xxx})   .or(i->{xxx})
     */
    @Test
    public void test05(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20)
                .like("name", "a")
                .or()
                .and(i->{
                    i.isNull("email")
                            .like("name","b");
                })
                ;
        User user = new User();
        user.setName("小明");
        user.setEmail("test@guigu.com");
        // UPDATE t_user SET name=?, email=? WHERE deleted=0 AND (age > ? AND name LIKE ? AND (email IS NULL AND name LIKE ?))
        int result = userMapper.update(user,queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test06(){
        //查询部分字段 name,age,email
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age","email");
        //SELECT name,age,email FROM t_user WHERE deleted=0
        List<Map<String,Object>> userMap = userMapper.selectMaps(queryWrapper);
        userMap.forEach(System.out::println);
    }

    @Test
    public void test07(){
        //子查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from t_user where id <20");
        //SELECT id,name,age,email,deleted FROM t_user WHERE deleted=0 AND (id IN (select id from t_user where id <20))
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void test08(){
        //查询出姓名lisi开头并且(年龄大于20或者email是null)的用户信息,然后将name改为赵六,email改为nice@126.com
        /**
         * ==>  Preparing: UPDATE t_user SET name=?,email=? WHERE deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
         * ==> Parameters: 赵六(String), nice@126.com(String), lisi%(String), 20(Integer)
         */
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.likeRight("name","lisi")
                .and(i->i.gt("age",20).or().isNull("email"));
        updateWrapper.set("name","赵六").set("email","nice@126.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }

    @Test
    public void test09(){
        //需要根据条件拼接SQL查询条件
        String username = "a";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("name", username);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        //SELECT id,name,age,email,deleted FROM t_user WHERE deleted=0 AND (name LIKE ? AND age >= ? AND age <= ?)
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

    }

    @Test
    public void test10(){
        //根据条件组装sql优化
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"name", username);
        queryWrapper.ge(ageBegin != null,"age", ageBegin);
        queryWrapper.le(ageEnd != null,"age", ageEnd);
        //SELECT id,name,age,email,deleted FROM t_user WHERE deleted=0 AND (name LIKE ? AND age <= ?)
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testlambdaQuery(){
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        //SELECT id,name,age,email,deleted FROM t_user WHERE deleted=0 AND (name LIKE ? AND age <= ?)
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void test11(){
        //查询出姓名lisi开头并且(年龄大于20或者email是null)的用户信息,然后将name改为赵六,email改为nice@126.com
        /**
         * ==>  Preparing: UPDATE t_user SET name=?,email=? WHERE deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
         * ==> Parameters: 赵六(String), nice@126.com(String), lisi%(String), 20(Integer)
         */
        LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.likeRight(User::getName,"lisi")
                .and(i->i.gt(User::getAge,20).or().isNull(User::getEmail));
        updateWrapper.set(User::getName,"赵六").set(User::getEmail,"nice@126.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }


}

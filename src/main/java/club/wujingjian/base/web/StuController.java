package club.wujingjian.base.web;

import club.wujingjian.base.config.Student;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.UUID;

@RestController
@Slf4j
public class StuController {

    @Autowired
    private Student student;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/getOne")
    public String stu(){
        log.info("stu"+student);
        redisTemplate.opsForValue().set("stu1",student);
        Student stuGet = (Student) redisTemplate.opsForValue().get("stu1");

        redisTemplate.opsForHash().put("stu",student.getId().toString(),student);
        Student stu = (Student) redisTemplate.opsForHash().get("stu", student.getId().toString());
        System.out.println(stu.getName());
        MDC.put("traceId", UUID.randomUUID().toString());
        log.trace("trace访问了HelloController的stu方法,打印name:{}", this.student.getId());
        log.info("info访问了HelloController的stu方法,打印name:{}", this.student.getName());
        Logger performanceLogger = LoggerFactory.getLogger("performanceLogger");
        performanceLogger.info("这里是performanceAppender的信息,{}", MethodHandles.lookup().lookupClass());
        return this.student.getName();
    }
}

package club.wujingjian.base.web;

import club.wujingjian.base.config.Person;
import club.wujingjian.base.config.Student;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.UUID;

@RestController
@Slf4j
@EnableConfigurationProperties(Student.class)
public class HelloController {

    @Autowired
    private Person person;

    @Autowired
    private Student student;



    @GetMapping("/hello")
    public String hello(){
        return "Hello,Spring Boot 3!";
    }

    @GetMapping("/person")
    public String person(){
        MDC.put("traceId", UUID.randomUUID().toString());
        log.trace("trace访问了HelloController的person方法,打印name:{}",person.getName());
        log.info("info访问了HelloController的person方法,打印name:{}",person.getName());
        Logger performanceLogger = LoggerFactory.getLogger("performanceLogger");
        performanceLogger.info("这里是performanceAppender的信息,{}", MethodHandles.lookup().lookupClass());
        return person.getName();
    }
    @GetMapping("/stu")
    public String stu(){
        MDC.put("traceId", UUID.randomUUID().toString());
        log.trace("trace访问了HelloController的stu方法,打印name:{}",student.getId());
        log.info("info访问了HelloController的stu方法,打印name:{}",student.getName());
        Logger performanceLogger = LoggerFactory.getLogger("performanceLogger");
        performanceLogger.info("这里是performanceAppender的信息,{}", MethodHandles.lookup().lookupClass());
        return student.getName();
    }
}

package club.wujingjian.base;

import club.wujingjian.base.config.Teacher;
import lombok.val;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        var ioc = SpringApplication.run(Application.class);
        /*String[] names = ioc.getBeanDefinitionNames();
        for (String name: names) {
            System.out.println(name);
        }*/
        val teacher = ioc.getBean(Teacher.class);
        System.out.println(teacher);
    }
}

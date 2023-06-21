package club.wujingjian.base.web;

import club.wujingjian.base.config.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @GetMapping("/save")
    public Student saveStu() {
        return new Student();
    }
}

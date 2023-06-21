package club.wujingjian.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "teacher")
@Component
public class Teacher implements Serializable {
    private Long id;
    private String name;
    private List<Student> studentList;
}

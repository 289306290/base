package club.wujingjian.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@ConfigurationProperties(prefix = "stu")
public class Student implements Serializable{

    private Long id;
    private String name;
    private int age;
}

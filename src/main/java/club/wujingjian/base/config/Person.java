package club.wujingjian.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@ConfigurationProperties(prefix = "person")
@Data
@Component
public class Person implements Serializable {

    private String name;
    private int age;

}

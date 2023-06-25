package club.wujingjian.base.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum EnumSex {
    MALE(1, "男"),
    FEMALE(2, "女");

    @EnumValue //将注解锁标识的属性的值存储到数据库中
    private Integer sex;
    private String sexName;

    EnumSex(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }


}

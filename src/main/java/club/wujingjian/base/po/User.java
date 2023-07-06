package club.wujingjian.base.po;

import club.wujingjian.base.enums.EnumSex;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("t_user")
@Schema(name = "User",title = "schema-title",description = "schema-description")
public class User {
//    @TableId(type = IdType.AUTO,value = "id")
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String pwd;
    @TableLogic//逻辑删除,这样删除语句会变为update语句
    private Integer deleted;

    private EnumSex sex;


}

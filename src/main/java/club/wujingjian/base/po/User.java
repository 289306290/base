package club.wujingjian.base.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {
//    @TableId(type = IdType.AUTO,value = "id")
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableLogic//逻辑删除,这样删除语句会变为update语句
    private Integer deleted;
}

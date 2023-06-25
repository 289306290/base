package club.wujingjian.base.po;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;

@Data
public class Product  implements Serializable {
    private Long id;
    private String name;
    private Integer price;

    @Version
    private Integer version;


}

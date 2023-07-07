package club.wujingjian.base.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "BaseResp",description = "通用返回对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResp<T> {

    private T data;

    private String code;

    private String msg;

}

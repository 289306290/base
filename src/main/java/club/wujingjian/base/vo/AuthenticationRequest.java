package club.wujingjian.base.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "认证实体请求VO")
public class AuthenticationRequest {

    @Schema(description = "用户名title属性",example = "张三")
    private String username;
    private String pwd;
}

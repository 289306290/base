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
@Schema(description = "注册实体")
public class RegisterRequest {

    @Schema(description = "用户名")
    private String name;
    @Schema(description = "邮件地址")
    private String email;
    private String pwd;

}

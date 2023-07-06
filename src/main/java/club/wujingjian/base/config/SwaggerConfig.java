package club.wujingjian.base.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("用户信息相关分组")
                .pathsToMatch("/user/**")
                .build();
    }
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder().addOperationCustomizer(swaggerHeaders())
                .group("api分组")
                .pathsToMatch("/api/**","/**")
                .build();
    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer",new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                // scheme和bearerFormat请固定设置为"bearer"和"JWT",否则可能会报错
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .name("bearer")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearer"))
                .info(new Info().title("base项目title")
                        .description("base项目description")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"))
                .externalDocs(new ExternalDocumentation().description("相关认证的配置请参考")
                        .url("https://blog.csdn.net/weixin_44741471/article/details/130973637"));
    }

    @Bean
    public OperationCustomizer swaggerHeaders(){
        // 返回一个全局方法handler
        return (operation, handlerMethod) -> {
            // 自定义一个要在全局使用的参数
            Parameter globalHeader = new Parameter()
                    // in方法表示该参数入参方式,此处为Header代表请求头
                    .in(ParameterIn.HEADER.toString())
                    .schema(new StringSchema())
                    // name方法设置参数名称,此处为请求头名称
                    .name("global-header")
                    .example("示例")
                    // description方法设置参数描述,此处为请求头描述
                    .description("全局请求头")
                    // required方法设置参数是否为必须
                    .required(Boolean.FALSE);
            // 将参数添加到全局方法中
            operation.addParametersItem(globalHeader);
            return operation;
        };
    }
}

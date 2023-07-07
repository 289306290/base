package club.wujingjian.base.config;

import club.wujingjian.base.filter.JwtAuthenticationFilter;
import club.wujingjian.base.util.RespUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import java.io.PrintWriter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig  {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests(registry->{
            registry.requestMatchers("/api/v1/auth/**").permitAll() //1/hello所有人都可以访问
                    .requestMatchers("/swagger**/**","/webjars/**","/api-docs/**").permitAll()
                    .anyRequest().authenticated();
        }).exceptionHandling().accessDeniedHandler(((request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();

            ObjectMapper objectMapper = new ObjectMapper();
            String errorMsg = objectMapper.writeValueAsString(RespUtil.fail("对不起,您没权限"));
            out.write(errorMsg);
            out.flush();
            out.close();
        })).authenticationEntryPoint(((request, response, authException) -> {
            //用来解决匿名用户访问无权限资源时的异常
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            String errorMsg = objectMapper.writeValueAsString(RespUtil.fail("请先登录!"));
            out.write(errorMsg);
            out.flush();
            out.close();
        }));   //2. 其他请求都需要认证;
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    public static void main(String[] args) {
        System.out.println((new AntPathMatcher().match("/swagger**/**","/swagger-ui.index.html")));
    }


}

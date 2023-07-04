package club.wujingjian.base.service;

import club.wujingjian.base.enums.EnumSex;
import club.wujingjian.base.mapper.UserMapper;
import club.wujingjian.base.po.User;
import club.wujingjian.base.service.impl.JwtService;
import club.wujingjian.base.vo.AuthenticationRequest;
import club.wujingjian.base.vo.AuthenticationResponse;
import club.wujingjian.base.vo.BaseUserDetail;
import club.wujingjian.base.vo.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPwd(passwordEncoder.encode(request.getPwd()));
        user.setSex(EnumSex.MALE);
        userMapper.insert(user);
        var jwtToken = jwtService.generateToken(new BaseUserDetail(user));
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPwd()));
        var user = userMapper.selectByName(request.getUsername());
        var jwtToken = jwtService.generateToken(new BaseUserDetail(user));
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}

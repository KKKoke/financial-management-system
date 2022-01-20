package com.dhy_zk.financialSystem.serviceImpl;

import com.dhy_zk.financialSystem.msg.CustomException;
import com.dhy_zk.financialSystem.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class JwtAuthService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    public String login(String username, String password) throws CustomException {
        //使用用户名密码进行登录验证
        UsernamePasswordAuthenticationToken upToken =
                new UsernamePasswordAuthenticationToken( username, password );
        Authentication authentication = authenticationManager.authenticate(upToken);
        if(SecurityContextHolder.getContext().getAuthentication()==null)
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //生成JWT
        UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        return jwtTokenUtil.generateToken(userDetails);
    }

    public String refreshToken(String oldToken) {
        if (!jwtTokenUtil.isTokenExpired(oldToken)) {
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return null;
    }

    public Boolean verify(String token)
    {
        return !jwtTokenUtil.isTokenExpired(token);
    }
}

package com.dhy_zk.financialSystem.controller;

import com.dhy_zk.financialSystem.msg.AjaxResponse;
import com.dhy_zk.financialSystem.msg.CustomException;
import com.dhy_zk.financialSystem.msg.CustomExceptionType;
import com.dhy_zk.financialSystem.serviceImpl.JwtAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author zdh
 */
@Api(tags = "token处理类")
@RestController
public class JwtAuthController {

    @Resource
    private JwtAuthService jwtAuthService;

    @ApiOperation("初次登录请求token,返回token")
    @PostMapping(value = "/authentication")
    public AjaxResponse login(@ApiParam("用户名")@RequestParam String name,@ApiParam("密码")@RequestParam String pwd) {
        String username = name;
        String password = pwd;
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
           throw  new CustomException(CustomExceptionType.USER_INPUT_ERROR,"用户名或密码不为为空");
        }
            return AjaxResponse.success(jwtAuthService.login(username, password));
    }

    @ApiOperation("刷新令牌的请求,过期返回新的,否则返回null")
    @PostMapping(value = "/refresh-token")
    public AjaxResponse refresh(@ApiParam(name="token",value = "请求头中的令牌，请求头名字是wrnm")@RequestHeader("${jwt.header}") String token) {
        return AjaxResponse.success(jwtAuthService.refreshToken(token));
    }

    @ApiOperation("检验token有效性")
    @PostMapping("/verify")
    public AjaxResponse verifyToken(@ApiParam(name="token",value = "请求头中的令牌，请求头名字是wrnm")@RequestHeader("${jwt.header}") String token)
    {
        return AjaxResponse.success(jwtAuthService.verify(token));
    }
}

package com.dhy_zk.financialSystem.config.securityConfig.handler;

import com.dhy_zk.financialSystem.msg.AjaxResponse;
import com.dhy_zk.financialSystem.msg.CustomException;
import com.dhy_zk.financialSystem.msg.CustomExceptionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zdh
 */
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private  static ObjectMapper objectMapper = new ObjectMapper();

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
                                        throws IOException{

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(
                    objectMapper.writeValueAsString(
                            AjaxResponse.error(
                                    new CustomException(
                                        CustomExceptionType.USER_INPUT_ERROR,
                                        "用户名或密码存在错误，请检查后再次登录"))));
    }
}

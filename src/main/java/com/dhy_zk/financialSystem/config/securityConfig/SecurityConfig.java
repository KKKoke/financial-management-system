package com.dhy_zk.financialSystem.config.securityConfig;

import com.dhy_zk.financialSystem.config.jwt.JwtAuthenticationTokenFilter;
import com.dhy_zk.financialSystem.config.securityConfig.handler.CustomExpiredSessionStrategy;
import com.dhy_zk.financialSystem.config.securityConfig.handler.LoginFailureHandler;
import com.dhy_zk.financialSystem.config.securityConfig.handler.LoginSuccessHandler;
import com.dhy_zk.financialSystem.serviceImpl.ManagerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.annotation.Resource;

/**
 * @author 大忽悠
 * @create 2022/1/19 20:36
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter
{
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Resource
    private ManagerServiceImpl userDetailsService;
    @Resource
    private LoginSuccessHandler successHandler;
    @Resource
    private LoginFailureHandler failureHandler;
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        //关闭csrf防护
        http.csrf().disable();

        //表单提交
        http.formLogin()
                //自定义登录页面
                .loginPage("/login.html")
                .usernameParameter("name")
                .passwordParameter("pwd")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler);

        http.authorizeRequests()
                //login不需要被认证
                .antMatchers("/login.html","/login","/authentication","/refresh-token").permitAll()
                //所有请求都必须被认证，必须登录后被访问
                .anyRequest().authenticated();

      http.sessionManagement()
              .maximumSessions(1)
              .expiredSessionStrategy(new CustomExpiredSessionStrategy());

        //jwt过滤器配置
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) {
        //将项目中静态资源路径开放出来
        web.ignoring().antMatchers( "/css/**", "/fonts/**", "/images/**", "/js/**","/webjars/**",
                "/doc.html#/**","/swagger-resources","/v3/**");
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher()
    {
        return new HttpSessionEventPublisher();
    }

}

package com.dhy_zk.financialSystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import com.dhy_zk.financialSystem.config.anno.NotSuper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.constraints.AssertTrue;

/**
 * <p>
 * 
 * </p>
 *
 * @author 大忽悠
 * @since 2022年01月19日
 */

@Data
public class Manager implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员名字
     */
    private String mname;

    /**
     * 管理员密码
     */
    private String mpwd;

    /**
     * 1:超级管理员 0：普通管理员
     * 不能创建超级管理员
     */
    @NotSuper
    private Integer type;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(type==1?"ROLE_SUPER":"ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return mpwd;
    }

    @Override
    public String getUsername() {
        return mname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

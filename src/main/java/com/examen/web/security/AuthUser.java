package com.examen.web.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class AuthUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String account;
    private String password;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.account = username;
        this.password = password;
        this.authorities = authorities;
    }

    public AuthUser(String userId, String account, String password, String nickName, String avatarUrl, String gender, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.authorities = authorities;
    }

    //-----------implements from UserDetails start----------//
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }


    @Override
    public String getUsername() {
        return this.account;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    //-----------implements from UserDetails end----------//

}

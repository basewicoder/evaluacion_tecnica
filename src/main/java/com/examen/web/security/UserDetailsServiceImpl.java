package com.examen.web.security;


import com.examen.web.entity.SysUser;
import com.examen.web.repository.SysUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserRepository.findByAccount(username).orElseThrow(() -> new UsernameNotFoundException("el usuario no existe!"));

        Set<GrantedAuthority> auths = sysUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleValue()))
                .collect(Collectors.toSet());

        return new AuthUser(sysUser.getUserId(), sysUser.getAccount(), sysUser.getPassword(), sysUser.getNickName(), sysUser.getAvatarUrl(), sysUser.getGender(), auths);
    }
}

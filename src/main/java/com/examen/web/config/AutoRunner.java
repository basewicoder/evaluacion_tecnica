package com.examen.web.config;


import com.examen.web.entity.SysRole;
import com.examen.web.entity.SysUser;
import com.examen.web.entity.pojo.RoleInfo;
import com.examen.web.entity.pojo.UserInfo;
import com.examen.web.repository.SysRoleRepository;
import com.examen.web.repository.SysUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class AutoRunner implements CommandLineRunner {

    @Resource
    private LoadProperties coreProperties;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysUserRepository sysUserRepository;

    @Override
    public void run(String... args) {
        log.info("------------Comienza la inicialización del sistema------------");
        initRoles();
        initUsers();
        log.info("------------Inicialización del sistema completa------------");
    }

    private void initRoles() {
        long count = sysRoleRepository.count();
        if (count > 0) {
            return;
        }
        List<RoleInfo> initRoles = coreProperties.getRoles();
        for (RoleInfo initRole : initRoles) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleValue(initRole.getRoleValue());
            sysRole.setRoleName(initRole.getRoleName());
            sysRoleRepository.save(sysRole);
        }

    }

    private void initUsers() {
        long count = sysUserRepository.count();
        if (count > 0) {
            return;
        }
        List<UserInfo> initUsers = coreProperties.getUsers();
        for (UserInfo initUserInfo : initUsers) {
            String username = initUserInfo.getUsername();
            String nickName = initUserInfo.getNickName();
            String encodedPassword = passwordEncoder.encode(initUserInfo.getPassword());
            SysUser sysUser = new SysUser();
            sysUser.setAccount(username);
            sysUser.setPassword(encodedPassword);
            sysUser.setNickName(nickName);
            String role = initUserInfo.getRole();
            if (StringUtils.isNotBlank(role)) {
                sysRoleRepository.findByRoleValue(role).ifPresent(sysRole -> sysUser.getRoles().add(sysRole));
            }
            sysUserRepository.save(sysUser);
        }
    }

}

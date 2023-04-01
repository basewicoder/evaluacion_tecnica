package com.examen.web.repository;


import com.examen.web.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SysUserRepository extends JpaRepository<SysUser, String> {
    Optional<SysUser> findByAccount(String account);
}

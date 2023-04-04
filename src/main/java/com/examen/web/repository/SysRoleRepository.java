package com.examen.web.repository;


import com.examen.web.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SysRoleRepository extends JpaRepository<SysRole, String> {

    boolean existsByRoleValue(String roleValue);

    boolean existsByRoleValueAndRoleIdNot(String roleValue, String roleId);

    boolean existsByRoleName(String roleName);

    boolean existsByRoleNameAndRoleIdNot(String roleName, String roleId);

    Optional<SysRole> findByRoleValue(String roleValue);
}

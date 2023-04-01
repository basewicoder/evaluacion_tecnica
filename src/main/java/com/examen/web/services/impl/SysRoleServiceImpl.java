package com.examen.web.services.impl;


import com.examen.config.CommonRuntimeException;
import com.examen.web.entity.pojo.PageInfo;
import com.examen.web.services.SysRoleService;
import com.examen.web.entity.SysRole;
import com.examen.web.repository.SysRoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleRepository sysRoleRepository;

    @Override
    public PageInfo<SysRole> queryListForPage(Pageable pageable) {
        Page<SysRole> page = sysRoleRepository.findAll(pageable);
        return PageInfo.of(page.getTotalElements(), page.getContent());
    }

    @Override
    public SysRole findOne(String id) {
        return sysRoleRepository.findById(id).orElseThrow(() -> new CommonRuntimeException("la identificación del rol no existe:" + id));
    }

    @Override
    public void insert(SysRole param) {
        param.setRoleId(null);
        String roleValue = param.getRoleValue();
        String roleName = param.getRoleName();
        if (sysRoleRepository.existsByRoleValue(roleValue)) {
            throw new CommonRuntimeException("El ID de rol ya existe：" + roleValue);
        }
        if (sysRoleRepository.existsByRoleName(roleName)) {
            throw new CommonRuntimeException("el nombre del rol ya existe：" + roleName);
        }
        sysRoleRepository.save(param);
    }

    @Override
    public void update(SysRole param) {
        String roleId = param.getRoleId();
        String roleValue = param.getRoleValue();
        String roleName = param.getRoleName();
        SysRole dataInDB = sysRoleRepository.findById(roleId).orElseThrow(() -> new CommonRuntimeException("la identificación del rol no existe:" + roleId));
        if (sysRoleRepository.existsByRoleValueAndRoleIdNot(roleValue, roleId)) {
            throw new CommonRuntimeException("El ID de rol ya existe：" + roleValue);
        }
        if (sysRoleRepository.existsByRoleNameAndRoleIdNot(roleName, roleId)) {
            throw new CommonRuntimeException("el nombre del rol ya existe：" + roleName);
        }
        dataInDB.setRoleValue(roleValue);
        dataInDB.setRoleName(roleName);
        sysRoleRepository.save(dataInDB);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysRoleRepository.deleteById(id);
    }
}

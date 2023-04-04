package com.examen.web.services.impl;


import com.examen.config.CommonRuntimeException;

import com.examen.web.entity.pojo.PageInfo;
import com.examen.web.services.SysUserService;
import com.examen.web.entity.SysUser;
import com.examen.web.repository.SysUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public PageInfo<SysUser> queryListForPage(Pageable pageable) {
        Page<SysUser> page = sysUserRepository.findAll(pageable);
        return PageInfo.of(page.getTotalElements(), page.getContent());
    }

    @Override
    public SysUser findOne(String id) {
        return sysUserRepository.findById(id).orElseThrow(() -> new CommonRuntimeException("no existe:" + id));
    }

    @Override
    public void insert(SysUser sysUser) {
        Optional<SysUser> opt = sysUserRepository.findByAccount(sysUser.getAccount());
        if (opt.isPresent()) {
            throw new CommonRuntimeException("la cuenta ya existe：" + sysUser.getAccount());
        }
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUserRepository.save(sysUser);
    }

    @Override
    public void update(SysUser sysUser) {
        SysUser userInDb = sysUserRepository.findById(sysUser.getUserId()).orElseThrow(() -> new CommonRuntimeException("no existe :" + sysUser.getUserId()));
        userInDb.setNickName(sysUser.getNickName());
        sysUserRepository.save(userInDb);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysUserRepository.deleteById(id);
    }
}

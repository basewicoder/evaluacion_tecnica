package com.examen.web.services.impl;


import com.examen.config.CommonRuntimeException;
import com.examen.web.entity.pojo.PageInfo;
import com.examen.web.services.SysAuthService;
import com.examen.web.entity.SysAuth;
//import com.examen.web.entity.SysMenu;
import com.examen.web.repository.SysAuthRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SysAuthServiceImpl implements SysAuthService {
    @Resource
    private SysAuthRepository sysAuthRepository;

    @Override
    public List<SysAuth> findAll(String menuId) {
       /* SysMenu menu = new SysMenu();
        menu.setMenuId(menuId);*/
        //return sysAuthRepository.findByMenu(menu);
        return null;
    }

    @Override
    public PageInfo<SysAuth> queryListForPage(Pageable pageable) {
        Page<SysAuth> page = sysAuthRepository.findAll(pageable);
        return PageInfo.of(page.getTotalElements(), page.getContent());
    }

    @Override
    public SysAuth findOne(String id) {
        return sysAuthRepository.findById(id).orElseThrow(() -> new CommonRuntimeException("la identificación del menú no existe:" + id));
    }

    @Override
    public void insert(SysAuth param) {
        param.setAuthId(null);
        String authValue = param.getAuthValue();
        String authName = param.getAuthName();

        sysAuthRepository.save(param);
    }

    @Override
    public void update(SysAuth sysUser) {

    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysAuthRepository.deleteById(id);
    }

}

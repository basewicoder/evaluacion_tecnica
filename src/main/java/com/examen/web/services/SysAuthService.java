package com.examen.web.services;


import com.examen.web.config.BaseService;
import com.examen.web.entity.SysAuth;

import java.util.List;


public interface SysAuthService extends BaseService<SysAuth> {
    List<SysAuth> findAll(String menuId);
}

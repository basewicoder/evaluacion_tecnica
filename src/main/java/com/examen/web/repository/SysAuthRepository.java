package com.examen.web.repository;


import com.examen.web.entity.SysAuth;
//import com.examen.web.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysAuthRepository extends JpaRepository<SysAuth, String> {

   // List<SysAuth> findByMenu(SysMenu menu);

    //boolean existsByAuthValueAndMenu(String authValue, SysMenu menu);

   // boolean existsByAuthNameAndMenu(String authName, SysMenu menu);

   // boolean existsByAuthValueAndMenuAndAuthIdNot(String authValue, SysMenu menu, String authId);

   // boolean existsByAuthNameAndMenuAndAuthIdNot(String authName, SysMenu menu, String authId);
}

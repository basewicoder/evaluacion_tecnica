package com.examen.web.controllers;


import com.examen.config.RestReturn;
import com.examen.config.RestReturnEntity;
import com.examen.web.entity.SysAuth;
import com.examen.web.services.SysAuthService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/api/auth")
@Slf4j
public class SysAuthController {
    @Resource
    private SysAuthService sysAuthService;


    @GetMapping("/all")
    @Hidden
    public ResponseEntity<RestReturnEntity<List<SysAuth>>> all(@RequestParam String menuId) {
        List<SysAuth> tree = sysAuthService.findAll(menuId);
        return RestReturn.ok(tree);
    }

    @GetMapping("/info/{id}")
    @Hidden
    public ResponseEntity<RestReturnEntity<SysAuth>> info(@PathVariable("id") String id) {
        SysAuth result = sysAuthService.findOne(id);
        return RestReturn.ok(result);
    }





}

package com.examen.web.controllers;


import com.examen.config.RestReturn;
import com.examen.config.RestReturnEntity;
import com.examen.web.entity.SysUser;
import com.examen.web.entity.pojo.PageInfo;
import com.examen.web.services.SysUserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/api/user")
@Slf4j
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @Hidden
    @GetMapping("/list")
    public ResponseEntity<RestReturnEntity<PageInfo<SysUser>>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                                    @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.Direction.ASC, "account");
        PageInfo<SysUser> pageInfo = sysUserService.queryListForPage(pageable);
        return RestReturn.ok(pageInfo);
    }


}

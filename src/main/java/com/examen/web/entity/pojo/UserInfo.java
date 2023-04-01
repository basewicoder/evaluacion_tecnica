package com.examen.web.entity.pojo;

import lombok.Data;


@Data
public class UserInfo {
    private String username;
    private String password;
    private String role;
    private String nickName;
}

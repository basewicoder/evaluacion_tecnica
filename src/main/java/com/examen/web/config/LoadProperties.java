package com.examen.web.config;


import com.examen.web.entity.pojo.RoleInfo;
import com.examen.web.entity.pojo.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



import java.util.List;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "load")
public class LoadProperties {
    private List<UserInfo> users;
    private List<RoleInfo> roles;
}

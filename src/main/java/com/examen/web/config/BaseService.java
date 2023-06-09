package com.examen.web.config;


import com.examen.web.entity.pojo.PageInfo;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {
    PageInfo<T> queryListForPage(Pageable pageable);

    T findOne(String id);

    void insert(T sysUser);

    void update(T sysUser);

    void deleteById(String id);
}

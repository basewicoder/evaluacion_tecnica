package com.examen.web.entity.pojo;

import lombok.Data;

import java.util.Collections;
import java.util.List;


@Data
public class PageInfo<T> {

    private long total;
    private List<T> list;

    public PageInfo(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public static <T> PageInfo<T> of(List<T> list) {
        if (list == null) {
            return of(0, Collections.emptyList());
        }
        return of(list.size(), list);
    }

    public static <T> PageInfo<T> of(long total, List<T> list) {
        if (list == null) {
            return of(total, Collections.emptyList());
        }
        return new PageInfo<>(total, list);
    }
}

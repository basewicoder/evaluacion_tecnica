package com.examen.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestReturnEntity<T> {
    private String msg;
    private String errMsg;
    private T data;

}

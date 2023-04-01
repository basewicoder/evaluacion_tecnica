package com.examen.web.entity.pojo;

import lombok.Data;

import java.util.List;


@Data
public class TreeNode {
    private String key;
    private String name;
    private List<TreeNode> children;
}

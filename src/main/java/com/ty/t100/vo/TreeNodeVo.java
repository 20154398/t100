package com.ty.t100.vo;

import lombok.Data;

import java.util.List;

@Data
public class TreeNodeVo<T> {
    private T data;

    private List<TreeNodeVo> children;
}

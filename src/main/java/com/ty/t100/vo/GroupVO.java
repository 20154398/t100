package com.ty.t100.vo;

import lombok.Data;

import java.util.List;

@Data
public class GroupVO {
    private String groupName;

    private String id;

    private List<UserVo> children;
}

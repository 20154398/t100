package com.ty.t100.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupVo {
    private String name;

    private String id;

    private List<UserVo> children;
}

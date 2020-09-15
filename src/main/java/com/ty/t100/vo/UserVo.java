package com.ty.t100.vo;

import com.ty.t100.entity.User;
import lombok.Data;

@Data
public class UserVo {
    private Integer power;

    private String id;

    public UserVo(User user) {
        this.power = user.getPower();
        this.id = user.getId();
    }
}

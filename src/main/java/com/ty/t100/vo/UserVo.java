package com.ty.t100.vo;

import com.ty.t100.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Integer power;

    private String id;

    private String name;

    private String nickname;

    public UserVo(User user) {
        this.power = user.getPower();
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
    }
}

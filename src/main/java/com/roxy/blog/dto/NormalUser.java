package com.roxy.blog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NormalUser {
    private String nickname;
    private String email;

    public NormalUser() {
    }

    public NormalUser(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }
}

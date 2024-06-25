package com.pillapp.dto;

import java.io.Serializable;

public class LoginOkDto implements Serializable {
    public String token;
    public ProfileDto profile;

    public LoginOkDto(String token, ProfileDto profile) {
        this.token = token;
        this.profile = profile;
    }
}

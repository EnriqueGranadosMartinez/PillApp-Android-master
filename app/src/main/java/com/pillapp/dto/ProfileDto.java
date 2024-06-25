package com.pillapp.dto;

import java.io.Serializable;

public class ProfileDto implements Serializable {

    public String userName;

    public ProfileDto(String userName) {
        this.userName = userName;
    }
}

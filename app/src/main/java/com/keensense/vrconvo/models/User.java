package com.keensense.vrconvo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panch on 8.03.2017.
 */

public class User {
    @SerializedName("username")
    private String username;

    @SerializedName("UserInfos")
    private UserInfo UserInfos;

    @SerializedName("id")
    private int id;

    public String getUsername() {
        return username;
    }

    public UserInfo getUserInfos() {
        return UserInfos;
    }

    public int getId() {
        return id;
    }
}

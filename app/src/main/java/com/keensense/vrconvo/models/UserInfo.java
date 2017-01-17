package com.keensense.vrconvo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panch on 30.11.2016.
 */

public class UserInfo {
    @SerializedName("id")
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("user_id")
    private int user_id;

    public int getUserId() {
        return this.user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    @SerializedName("coins")
    private int coins;

    public int getCoins() {
        return this.coins;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCan_upload() {
        return can_upload;
    }

    public void setCan_upload(int can_upload) {
        this.can_upload = can_upload;
    }

    public String getLast_active() {
        return last_active;
    }

    public void setLast_active(String last_active) {
        this.last_active = last_active;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    @SerializedName("can_upload")
    private int can_upload;

    public int getCanUpload() {
        return this.can_upload;
    }

    public void setCanUpload(int can_upload) {
        this.can_upload = can_upload;
    }

    @SerializedName("directory")
    private String directory;

    @SerializedName("last_active")
    private String last_active;

    @SerializedName("current_status")
    private String current_status;


    public String getDirectory() {
        return this.directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
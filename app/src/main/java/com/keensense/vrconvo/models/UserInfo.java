package com.keensense.vrconvo.models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        String toReturn = "";
            Log.e("asd",last_active);
        if (last_active.contains("1900-01-01")) {

            return "Never";

        }
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date d = sdf.parse(last_active);
            Date now = new Date(System.currentTimeMillis());
            long diff = now.getTime() - d.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            Log.e("asd", String.valueOf(diffDays) + "|" + String.valueOf(diffHours) + "|" + String.valueOf(diffMinutes) + "|" + String.valueOf(diffSeconds) + "|");
            c.setTime(d);
            if (diffDays > 0) {
                toReturn = String.valueOf(diffDays) + " days ago.";
            } else {
                if (diffHours > 0) {
                    toReturn = String.valueOf(diffHours) + " hours, " + String.valueOf(diffMinutes) + " minutes ago.";
                } else {
                    if (diffMinutes > 3) {
                        toReturn = String.valueOf(diffMinutes) + " minutes ago.";
                    } else {
                        toReturn = "Online";
                    }
                }
            }
        } catch (ParseException d) {
            Log.e("asd", "Parse exception" + d.getMessage());
        }

        return toReturn;
    }

    public void setLast_active(String last_active) {
        this.last_active = last_active;
    }

    public String getCurrent_status() {
        if (current_status == null) {
            current_status = "Registered.";
        }
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
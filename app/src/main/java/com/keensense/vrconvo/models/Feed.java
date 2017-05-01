package com.keensense.vrconvo.models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Panch on 1.05.2017.
 */

public class Feed {

    @SerializedName("id")
    private int id;

    @SerializedName("feed_image")
    private String feed_image;

    public String getFeed_image() {
        return feed_image;
    }

    @SerializedName("feed_time")
    private String feed_time;

    @SerializedName("feed_content")
    private String feed_content;

    public int getId() {
        return id;
    }

    public String getFeed_time() {
        String toReturn = "";
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date d = sdf.parse(feed_time);
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
                        toReturn = "Now";
                    }
                }
            }
        } catch (ParseException d) {
            Log.e("asd", "Parse exception" + d.getMessage());
        }

        return toReturn;
    }

    public String getFeed_content() {
        return feed_content;
    }

    public User getUser() {
        return user;
    }

    public long ts() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date d = sdf.parse(feed_time);
            return d.getTime();
        } catch (ParseException d) {

        }

        return 0;
    }

    @SerializedName("Users")
    private User user;


}

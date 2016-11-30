package com.keensense.vrconvo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panch on 30.11.2016.
 */

public class Room
{
    @SerializedName("id")
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    @SerializedName("room_name")
    private String room_name;

    public String getRoomName() { return this.room_name; }

    public void setRoomName(String room_name) { this.room_name = room_name; }

    @SerializedName("image")
    private String image;

    public String getImage() { return this.image; }

    public void setImage(String image) { this.image = image; }
}

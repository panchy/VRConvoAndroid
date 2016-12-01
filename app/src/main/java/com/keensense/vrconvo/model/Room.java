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

    private boolean isLocked;

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getRoom_name() {

        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }


    @Override
    public boolean equals(Object o){
        if(o instanceof Room){
            Room toCompare = (Room) o;
            return this.id==(toCompare.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

}

package com.keensense.vrconvo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panch on 30.11.2016.
 */

public class UnlockedRoom
{
    @SerializedName("Room")
    private Room Room;

    public Room getRoom() { return this.Room; }

    public void setRoom(Room Room) { this.Room = Room; }

    @SerializedName("id")
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    @SerializedName("player_id")
    private int player_id;

    public int getPlayerId() { return this.player_id; }

    public void setPlayerId(int player_id) { this.player_id = player_id; }

    @SerializedName("room_id")
    private int room_id;

    public int getRoomId() { return this.room_id; }

    public void setRoomId(int room_id) { this.room_id = room_id; }
}

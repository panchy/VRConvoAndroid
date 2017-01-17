package com.keensense.vrconvo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panch on 17.01.2017.
 */

public class Friendship {

    @SerializedName("id")
    private int id;

    @SerializedName("player_sent")
    private int playerSentId;

    @SerializedName("player_received")
    private int playerReceivedId;

    @SerializedName("player_sent_username")
    private String playerSentUsername;

    @SerializedName("player_received_username")
    private String playerReceivedUsername;

    @SerializedName("status")
    private int status;

    @SerializedName("Sender")
    private UserInfo sender;

    @SerializedName("Receiver")
    private UserInfo receiver;

    public UserInfo getSender() {
        return sender;
    }

    public void setSender(UserInfo sender) {
        this.sender = sender;
    }

    public UserInfo getReceiver() {
        return receiver;
    }

    public void setReceiver(UserInfo receiver) {
        this.receiver = receiver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerSentId() {
        return playerSentId;
    }

    public void setPlayerSentId(int playerSentId) {
        this.playerSentId = playerSentId;
    }

    public int getPlayerReceivedId() {
        return playerReceivedId;
    }

    public void setPlayerReceivedId(int playerReceivedId) {
        this.playerReceivedId = playerReceivedId;
    }

    public String getPlayerSentUsername() {
        return playerSentUsername;
    }

    public void setPlayerSentUsername(String playerSentUsername) {
        this.playerSentUsername = playerSentUsername;
    }

    public String getPlayerReceivedUsername() {
        return playerReceivedUsername;
    }

    public void setPlayerReceivedUsername(String playerReceivedUsername) {
        this.playerReceivedUsername = playerReceivedUsername;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

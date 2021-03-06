package com.keensense.vrconvo.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Panch on 30.11.2016.
 */

public class LoginResponse {

    @SerializedName("SavedDatas")
    private ArrayList<SavedData> SavedDatas;

    public ArrayList<SavedData> getSavedDatas() { return this.SavedDatas; }

    public void setSavedDatas(ArrayList<SavedData> SavedDatas) { this.SavedDatas = SavedDatas; }

    @SerializedName("UnlockedCharacters")
    private ArrayList<UnlockedCharacter> UnlockedCharacters;

    public ArrayList<UnlockedCharacter> getUnlockedCharacters() { return this.UnlockedCharacters; }

    public void setUnlockedCharacters(ArrayList<UnlockedCharacter> UnlockedCharacters) { this.UnlockedCharacters = UnlockedCharacters; }

    @SerializedName("UnlockedRooms")
    private ArrayList<UnlockedRoom> UnlockedRooms;

    @SerializedName("UnlockedCustomAssetBundles")
    private ArrayList<UnlockedCustomAssetBundle> UnlockedCustomAssetBundles;

    public ArrayList<UnlockedCustomAssetBundle> getUnlockedCustomAssetBundles() {
        return UnlockedCustomAssetBundles;
    }

    public void setUnlockedCustomAssetBundles(ArrayList<UnlockedCustomAssetBundle> unlockedCustomAssetBundles) {
        UnlockedCustomAssetBundles = unlockedCustomAssetBundles;
    }

    public ArrayList<UnlockedRoom> getUnlockedRooms() { return this.UnlockedRooms; }

    public void setUnlockedRooms(ArrayList<UnlockedRoom> UnlockedRooms) { this.UnlockedRooms = UnlockedRooms; }

    @SerializedName("UserInfos")
    private UserInfo UserInfos;

    public UserInfo getUserInfo() { return this.UserInfos; }

    public void setUserInfo(UserInfo mUserInfo) { this.UserInfos = mUserInfo; }

    @SerializedName("id")
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    @SerializedName("username")
    private String username;

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    @SerializedName("password")
    private String password;

    public String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }

    @SerializedName("device_id")
    private String device_id;

    public String getDeviceId() { return this.device_id; }

    public void setDeviceId(String device_id) { this.device_id = device_id; }
}

package com.keensense.vrconvo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panch on 20.03.2017.
 */

public class CustomAssetBundle {
    @SerializedName("Id")
    private int id;
    @SerializedName("type")
    private int type;

    public int getType() {
        return type;
    }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    @SerializedName("assetbundle_name")
    private String assetbundle_name;

    public String getCustomAssetbundleName() { return this.assetbundle_name; }

    public void setCustomAssetbundleName(String character_name) { this.assetbundle_name = character_name; }

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

    @SerializedName("cost")
    private int cost;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof CustomAssetBundle){
            CustomAssetBundle toCompare = (CustomAssetBundle) o;
            return this.id==(toCompare.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

package com.keensense.vrconvo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panch on 30.11.2016.
 */

public class Character
{
    @SerializedName("id")
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    @SerializedName("character_name")
    private String character_name;

    public String getCharacterName() { return this.character_name; }

    public void setCharacterName(String character_name) { this.character_name = character_name; }

    @SerializedName("image")
    private String image;

    public String getImage() { return this.image; }

    public void setImage(String image) { this.image = image; }

    private boolean isLocked;

    public String getCharacter_name() {
        return character_name;
    }

    public void setCharacter_name(String character_name) {
        this.character_name = character_name;
    }

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
        if(o instanceof Character){
            Character toCompare = (Character) o;
            return this.id==(toCompare.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

}

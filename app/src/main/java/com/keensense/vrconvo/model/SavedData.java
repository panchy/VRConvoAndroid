package com.keensense.vrconvo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panch on 30.11.2016.
 */

public class SavedData
{
    @SerializedName("Character")
    private Character Character;

    public Character getCharacter() { return this.Character; }

    public void setCharacter(Character Character) { this.Character = Character; }

    @SerializedName("id")
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    @SerializedName("current_character")
    private int current_character;

    public int getCurrentCharacter() { return this.current_character; }

    public void setCurrentCharacter(int current_character) { this.current_character = current_character; }

    @SerializedName("player_id")
    private int player_id;

    public int getPlayerId() { return this.player_id; }

    public void setPlayerId(int player_id) { this.player_id = player_id; }
}

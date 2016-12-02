package com.keensense.vrconvo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panch on 30.11.2016.
 */

public class UnlockedCharacter
{
    @SerializedName("Character")
    private Character Character;

    public Character getCharacter() { return this.Character; }

    public void setCharacter(Character Character) { this.Character = Character; }

    @SerializedName("id")
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    @SerializedName("player_id")
    private int player_id;

    public int getPlayerId() { return this.player_id; }

    public void setPlayerId(int player_id) { this.player_id = player_id; }

    @SerializedName("character_id")
    private int character_id;

    public int getCharacterId() { return this.character_id; }

    public void setCharacterId(int character_id) { this.character_id = character_id; }
}

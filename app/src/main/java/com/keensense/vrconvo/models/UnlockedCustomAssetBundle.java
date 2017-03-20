package com.keensense.vrconvo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panch on 20.03.2017.
 */

public class UnlockedCustomAssetBundle {

    @SerializedName("CustomAssetBundle")
    private CustomAssetBundle CustomAssetBundle;
    @SerializedName("Id")
    private int id;
    @SerializedName("player_id")
    private int player_id;
    @SerializedName("customassetbundle_id")
    private int character_id;

    public com.keensense.vrconvo.models.CustomAssetBundle getCustomAssetBundle() {
        return CustomAssetBundle;
    }

    public int getId() {
        return id;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public int getCharacter_id() {
        return character_id;
    }
}

package com.keensense.vrconvo.models;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

public class UserSuggestion implements SearchSuggestion {

    private String mUserName;
    private int id;
    private boolean canAdd;
    private String last_active;

    public boolean isCanAdd() {
        return canAdd;
    }

    public void setCanAdd(boolean canAdd) {
        this.canAdd = canAdd;
    }

    public String getLast_active() {
        return last_active;
    }

    public void setLast_active(String last_active) {
        this.last_active = last_active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private boolean mIsHistory = false;

    public UserSuggestion(String suggestion) {
        this.mUserName = suggestion.toLowerCase();
    }

    public UserSuggestion(Parcel source) {
        this.mUserName = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    @Override
    public String getBody() {
        return mUserName;
    }

    public static final Creator<UserSuggestion> CREATOR = new Creator<UserSuggestion>() {
        @Override
        public UserSuggestion createFromParcel(Parcel in) {
            return new UserSuggestion(in);
        }

        @Override
        public UserSuggestion[] newArray(int size) {
            return new UserSuggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUserName);
        dest.writeInt(mIsHistory ? 1 : 0);
    }
}
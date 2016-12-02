package com.keensense.vrconvo.events;

import com.keensense.vrconvo.models.UserInfo;

/**
 * Created by Panch on 2.12.2016.
 */

public class UserInfoChangedEvent {
    private UserInfo info;

    public UserInfoChangedEvent(UserInfo info) {
        this.info = info;
    }

    public UserInfo getInfo() {
        return info;
    }
}

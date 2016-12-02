package com.keensense.vrconvo.events;

/**
 * Created by Panch on 2.12.2016.
 */

public class SnackbarRequestEvent {
    private String text;

    public String getText() {
        return text;
    }

    public SnackbarRequestEvent(String text) {

        this.text = text;
    }
}

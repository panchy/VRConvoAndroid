package com.keensense.vrconvo.listeners;

/**
 * Created by Panch on 29.11.2016.
 */

public interface FragmentListener {
    void onFragmentAttached();
    void onFragmentDetached();
    void onMessageReceived(String... messages);
}

package com.keensense.vrconvo.network;

import android.util.Log;

import com.keensense.vrconvo.App;
import com.keensense.vrconvo.model.LoginResponse;
import com.keensense.vrconvo.model.Response;
import com.keensense.vrconvo.model.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Panch on 30.11.2016.
 */

public class ConvoHelper {

    private static String TAG = "ConvoHelper";
    private String username;
    private String password;

    public ConvoHelper() {
    }

    public ConvoHelper(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void register(final Callback<Response<LoginResponse>> customCallback) {

        ConvoClient.getRetrofitInstance().register("register", username, password).enqueue(new Callback<Response<LoginResponse>>() {
            @Override
            public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                if (customCallback != null) {
                    if (response.body() != null)
                        customCallback.onResponse(call, response);
                    else
                        Log.e(TAG, "Response ended with success. But no data was received.(Empty Body)");
                } else {
                    Log.e(TAG, "Response was a success.Also please implement a callback to this function.");
                }
            }

            @Override
            public void onFailure(Call<Response<LoginResponse>> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });

    }

    public void login(final Callback<Response<LoginResponse>> customCallback) {

        ConvoClient.getRetrofitInstance().login("login", username, password).enqueue(new Callback<Response<LoginResponse>>() {
            @Override
            public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                if (customCallback != null) {
                    if (response.body() != null)
                        customCallback.onResponse(call, response);
                    else
                        Log.e(TAG, "Response ended with success. But no data was received.(Empty Body)");
                } else {
                    Log.e(TAG, "Response was a success.Also please implement a callback to this function.");
                }
            }

            @Override
            public void onFailure(Call<Response<LoginResponse>> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });

    }

    public void deviceLogin(final Callback<Response<LoginResponse>> customCallback) {
        ConvoClient.getRetrofitInstance().deviceLogin("device_login", App.DEVICE_ID).enqueue(new Callback<Response<LoginResponse>>() {
            @Override
            public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                if (customCallback != null) {
                    if (response.body() != null)
                        customCallback.onResponse(call, response);
                    else
                        Log.e(TAG, "Response ended with success. But no data was received.(Empty Body)");
                } else {
                    Log.e(TAG, "Response was a success.Also please implement a callback to this function.");
                }
            }

            @Override
            public void onFailure(Call<Response<LoginResponse>> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });
    }

    public void checkUser(final Callback<Response<UserInfo>> customCallback) {

        ConvoClient.getRetrofitInstance().checkUser("checkuser", username).enqueue(new Callback<Response<UserInfo>>() {
            @Override
            public void onResponse(Call<Response<UserInfo>> call, retrofit2.Response<Response<UserInfo>> response) {
                if (customCallback != null) {
                    if (response.body() != null)
                        customCallback.onResponse(call, response);
                    else
                        Log.e(TAG, "Response ended with success. But no data was received.(Empty Body)");
                } else {
                    Log.e(TAG, "Response was a success.Also please implement a callback to this function.");
                }
            }

            @Override
            public void onFailure(Call<Response<UserInfo>> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });

    }

    public void updateDeviceId(final Callback<Response<LoginResponse>> customCallback) {
        ConvoClient.getRetrofitInstance().updateDeviceId("update_device_id", username, password, App.DEVICE_ID).enqueue(new Callback<Response<LoginResponse>>() {
            @Override
            public void onResponse(Call<Response<LoginResponse>> call, retrofit2.Response<Response<LoginResponse>> response) {
                if (customCallback != null) {
                    if (response.body() != null)
                        customCallback.onResponse(call, response);
                    else
                        Log.e(TAG, "Response ended with success. But no data was received.(Empty Body)");
                } else {
                    Log.e(TAG, "Response was a success.Also please implement a callback to this function.");
                }
            }

            @Override
            public void onFailure(Call<Response<LoginResponse>> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });
    }
}

package com.keensense.vrconvo.network;

import android.util.Log;

import com.keensense.vrconvo.App;
import com.keensense.vrconvo.models.Character;
import com.keensense.vrconvo.models.CustomAssetBundle;
import com.keensense.vrconvo.models.Feed;
import com.keensense.vrconvo.models.Friendship;
import com.keensense.vrconvo.models.LoginResponse;
import com.keensense.vrconvo.models.Response;
import com.keensense.vrconvo.models.Room;
import com.keensense.vrconvo.models.User;
import com.keensense.vrconvo.models.UserInfo;

import java.util.List;

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

    public void bindDeviceId(final Callback<Response<LoginResponse>> customCallback) {
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

    public void unbindDeviceId(final Callback<Response<LoginResponse>> customCallback) {
        ConvoClient.getRetrofitInstance().updateDeviceId("unbind_device_id", username, password, "").enqueue(new Callback<Response<LoginResponse>>() {
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

    public void getAllCharacters(final Callback<Response<List<Character>>> customCallback) {
        ConvoClient.getRetrofitInstance().getAllCharacters("get_all_characters").enqueue(new Callback<Response<List<Character>>>() {
            @Override
            public void onResponse(Call<Response<List<Character>>> call, retrofit2.Response<Response<List<Character>>> response) {
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
            public void onFailure(Call<Response<List<Character>>> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });
    }

    public void getAllRooms(final Callback<Response<List<Room>>> customCallback) {
        ConvoClient.getRetrofitInstance().getAllRooms("get_all_rooms").enqueue(new Callback<Response<List<Room>>>() {
            @Override
            public void onResponse(Call<Response<List<Room>>> call, retrofit2.Response<Response<List<Room>>> response) {
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
            public void onFailure(Call<Response<List<Room>>> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });
    }

    public void getAllCustomAssetBundles(final Callback<Response<List<CustomAssetBundle>>> customCallback) {
        ConvoClient.getRetrofitInstance().getAllCustomAssetBundles("get_all_customassetbundles").enqueue(new Callback<Response<List<CustomAssetBundle>>>() {
            @Override
            public void onResponse(Call<Response<List<CustomAssetBundle>>> call, retrofit2.Response<Response<List<CustomAssetBundle>>> response) {
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
            public void onFailure(Call<Response<List<CustomAssetBundle>>> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });
    }

    public void changePassword(String newPassword, final Callback<Response<LoginResponse>> customCallback) {

        ConvoClient.getRetrofitInstance().changePassword("change_password", username, password, newPassword).enqueue(new Callback<Response<LoginResponse>>() {
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

    public void unlockContent(String contentType, int contentId, final Callback<Response<LoginResponse>> customCallback) {

        ConvoClient.getRetrofitInstance().unlockContent("unlock_content", contentType, contentId, username, password).enqueue(new Callback<Response<LoginResponse>>() {
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

    public void getFriendships(final Callback<Response<List<Friendship>>> customCallback) {
        ConvoClient.getRetrofitInstance().getFriendships("get_friendships", username, password).enqueue(new Callback<Response<List<Friendship>>>() {
            @Override
            public void onResponse(Call<Response<List<Friendship>>> call, retrofit2.Response<Response<List<Friendship>>> response) {
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
            public void onFailure(Call<Response<List<Friendship>>> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });
    }

    public void updateStatus(String status, final Callback<Response> customCallback) {
        ConvoClient.getRetrofitInstance().updateStatus("update_status", username, password, status, "{}").enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
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
            public void onFailure(Call<Response> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });

    }

    public void updateFriendRequest(int request_id, int accept, final Callback<Response> customCallback) {
        ConvoClient.getRetrofitInstance().updateFriendRequest("update_friendrequest", username, password, request_id, accept).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
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
            public void onFailure(Call<Response> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });

    }

    public void searchUsers(String query, final Callback<Response<List<User>>> customCallback) {
        ConvoClient.getRetrofitInstance().searchUsers("search_user", query, username, password).enqueue(new Callback<Response<List<User>>>() {
            @Override
            public void onResponse(Call<Response<List<User>>> call, retrofit2.Response<Response<List<User>>> response) {
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
            public void onFailure(Call<Response<List<User>>> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });
    }

    public void checkFriendship(String to, final Callback<Response> customCallback) {
        ConvoClient.getRetrofitInstance().checkFriendship("check_friendship", to, username, password).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
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
            public void onFailure(Call<Response> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });
    }
    public void sendFriendRequest(String to, final Callback<Response> customCallback) {
        ConvoClient.getRetrofitInstance().sendFriendRequest("send_friendrequest", to, username, password).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
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
            public void onFailure(Call<Response> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });
    }

    public void getFeeds(final Callback<Response<List<Feed>>> customCallback) {
        ConvoClient.getRetrofitInstance().getFeeds("get_feeds", username, password).enqueue(new Callback<Response<List<Feed>>>() {
            @Override
            public void onResponse(Call<Response<List<Feed>>> call, retrofit2.Response<Response<List<Feed>>> response) {
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
            public void onFailure(Call<Response<List<Feed>>> call, Throwable t) {
                if (customCallback != null) {
                    customCallback.onFailure(call, t);
                } else {
                    Log.e(TAG, "Response was a failure.Also please implement a callback to this function.");
                }
            }
        });
    }
}

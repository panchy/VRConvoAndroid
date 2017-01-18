package com.keensense.vrconvo.network;

import com.keensense.vrconvo.models.Character;
import com.keensense.vrconvo.models.Friendship;
import com.keensense.vrconvo.models.LoginResponse;
import com.keensense.vrconvo.models.Response;
import com.keensense.vrconvo.models.Room;
import com.keensense.vrconvo.models.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Panch on 30.11.2016.
 */

public interface ConvoService {

    @FormUrlEncoded
    @POST("service")
    Call<Response<LoginResponse>> register(@Field("action") String action,@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("service")
    Call<Response<LoginResponse>> login(@Field("action") String action,@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("service")
    Call<Response<LoginResponse>> deviceLogin(@Field("action") String action,@Field("device_id") String deviceId);

    @FormUrlEncoded
    @POST("service")
    Call<Response<LoginResponse>> updateDeviceId(@Field("action") String action,@Field("username") String username,@Field("password") String password,@Field("device_id") String deviceId);

    @FormUrlEncoded
    @POST("service")
    Call<Response<UserInfo>> checkUser(@Field("action") String action, @Field("username") String username);

    @FormUrlEncoded
    @POST("service")
    Call<Response<List<Character>>> getAllCharacters(@Field("action") String action);

    @FormUrlEncoded
    @POST("service")
    Call<Response<List<Room>>> getAllRooms(@Field("action") String action);

    @FormUrlEncoded
    @POST("service")
    Call<Response<LoginResponse>> changePassword(@Field("action") String action,@Field("username") String username,@Field("password") String password,@Field("new_password") String newPassword);

    @FormUrlEncoded
    @POST("service")
    Call<Response<LoginResponse>> unlockContent(@Field("action") String action,@Field("content_type") String ContentType,@Field("content_id") int ContentId,@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("service")
    Call<Response<List<Friendship>>> getFriendships(@Field("action") String action, @Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("service")
    Call<Response> updateFriendRequest(@Field("action") String action, @Field("username") String username, @Field("password") String password,@Field("request_id") int request_id,@Field("accept") int accept);

    @FormUrlEncoded
    @POST("service")
    Call<Response> updateStatus(@Field("action") String action, @Field("username") String username, @Field("password") String password,@Field("status") String status,@Field("room") String room);


}

package com.keensense.vrconvo.network;

import com.keensense.vrconvo.model.LoginResponse;
import com.keensense.vrconvo.model.Response;
import com.keensense.vrconvo.model.UserInfo;

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

}

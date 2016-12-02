package com.keensense.vrconvo.network;

import com.keensense.vrconvo.App;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Panch on 30.11.2016.
 */

public class ConvoClient {

    public static String baseUrl = "http://vrconvo.onesoftwareguy.com/";
    public static String imagesFolder = "gameimages";
    private static ConvoService service = null;
    private static Retrofit retrofit = null;

    private static String headerKey = "app";
    private static String headerValue = "vrconvo_2016";

    private ConvoClient() {
    }

    public static ConvoService getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            if(App.DEBUG_MODE)
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            else
                logging.setLevel(HttpLoggingInterceptor.Level.NONE);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request;
                                request = chain.request().newBuilder().addHeader(headerKey,headerValue).build();


                            return chain.proceed(request);
                        }

                    })
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(ConvoService.class);

        }
        return service;
    }



}

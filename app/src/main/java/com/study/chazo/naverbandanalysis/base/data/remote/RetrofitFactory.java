package com.study.chazo.naverbandanalysis.base.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chazo on 2018-07-12
 */
public class RetrofitFactory {
    private static RetrofitFactory instance = new RetrofitFactory();
    private final int cacheSize = 10 * 1024 * 1024;

    public static RetrofitFactory getInstance() {
        return instance;
    }

    private Retrofit createRetrofit(final String baseUrl, final boolean isCache){
        final Gson responseGson = createGsonBuilder();
        return new Retrofit.Builder()
                .client(createOkHttpClient(isCache))
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(responseGson))
                .build();
    }

    private Gson createGsonBuilder(){
        return new GsonBuilder()
                .serializeNulls()
                .create();
    }

    private OkHttpClient createOkHttpClient(final boolean isCache){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS);
        if(isCache) {
            builder.cache(new Cache(getCacheDir(), cacheSize));
        }
        return builder.build();
    }

    private File getCacheDir(){
        File file = new File("./cache");
        return file;
    }
}

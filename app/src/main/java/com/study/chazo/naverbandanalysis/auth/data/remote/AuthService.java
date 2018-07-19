package com.study.chazo.naverbandanalysis.auth.data.remote;

import com.study.chazo.naverbandanalysis.auth.model.AuthToken;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Chazo on 2018-07-19
 */
public interface AuthService {

    @GET("oauth2/token?grant_type=authorization_code")
    Single<AuthToken> getAuth(@Query("code") String authorizationCode, @Header("Authorization") String header);
}

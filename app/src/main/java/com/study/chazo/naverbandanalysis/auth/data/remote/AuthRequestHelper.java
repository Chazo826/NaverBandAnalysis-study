package com.study.chazo.naverbandanalysis.auth.data.remote;

import android.util.Base64;

import com.study.chazo.naverbandanalysis.auth.model.AuthToken;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Chazo on 2018-07-19
 */
@Singleton
public class AuthRequestHelper {

    private AuthService authService;

    @Inject
    public AuthRequestHelper(AuthService authService) {
        this.authService = authService;
    }

    public Single<AuthToken> requestAuthToken(String authorizationCode, String clientId, String clientSecret){
        return authService.getAuth(authorizationCode, getAuthHeader(clientId, clientSecret))
                .subscribeOn(Schedulers.io());
    }

    private String getAuthHeader(String clientId, String clientSecret){
        String encodedToBase64 = Base64.encodeToString((clientId + ":" + clientSecret).getBytes(), Base64.NO_WRAP);
        return "Basic " + encodedToBase64;
    }
}

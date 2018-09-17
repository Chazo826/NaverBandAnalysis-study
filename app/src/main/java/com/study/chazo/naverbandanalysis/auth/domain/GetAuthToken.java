package com.study.chazo.naverbandanalysis.auth.domain;

import android.util.Log;

import com.study.chazo.naverbandanalysis.auth.data.AuthRepository;
import com.study.chazo.naverbandanalysis.auth.model.AuthToken;

import io.reactivex.Single;

/**
 * Created by Chazo on 2018-07-19
 */
public class GetAuthToken {

    public AuthRepository authRepository;

    public GetAuthToken(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Single<AuthToken> execute(){
        return authRepository.isAuthToken()
//                .filter(isAuthToken -> {
//                    Log.d("!!!!", "isAuthToken: " + isAuthToken);
//                    return isAuthToken;
//                })
                .flatMap(isAuthToken -> {
                    if(isAuthToken) {
                        return authRepository.getAuthToken();
                    } else {
                        return Single.error(new NullPointerException("Token is not exist"));
                    }
                });
    }

    public Single<AuthToken> execute(String authorizationCode){
        return authRepository.getAuthToken(authorizationCode);
    }


}

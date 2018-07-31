package com.study.chazo.naverbandanalysis.auth.domain;

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
                .filter(isAuthToken -> isAuthToken)
                .flatMapSingle(__ -> authRepository.getAuthToken());
    }

    public Single<AuthToken> execute(String authorizationCode){
        return authRepository.getAuthToken(authorizationCode);
    }


}

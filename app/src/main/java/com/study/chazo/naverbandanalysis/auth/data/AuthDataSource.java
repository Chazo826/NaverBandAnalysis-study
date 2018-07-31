package com.study.chazo.naverbandanalysis.auth.data;

import com.study.chazo.naverbandanalysis.auth.model.AuthToken;

import io.reactivex.Single;

/**
 * Created by Chazo on 2018-07-19
 */
public interface AuthDataSource {

    Single<AuthToken> getAuthToken();

    Single<AuthToken> getAuthToken(String authorizationCode);

    Single<Boolean> isAuthToken();
}

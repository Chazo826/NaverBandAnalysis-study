package com.study.chazo.naverbandanalysis.auth.data;

import com.study.chazo.naverbandanalysis.auth.data.local.AuthLocalDataSource;
import com.study.chazo.naverbandanalysis.auth.data.remote.AuthRemoteDataSource;
import com.study.chazo.naverbandanalysis.auth.model.AuthToken;

import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Chazo on 2018-07-19
 */
@Singleton
public class AuthRepository implements AuthDataSource {
    private AuthLocalDataSource authLocalDataSource;
    private AuthRemoteDataSource authRemoteDataSource;
    private AuthToken authTokenCache;

    @Inject
    public AuthRepository(AuthLocalDataSource authLocalDataSource, AuthRemoteDataSource authRemoteDataSource) {
        this.authLocalDataSource = authLocalDataSource;
        this.authRemoteDataSource = authRemoteDataSource;
    }

    @Override
    public Single<AuthToken> getAuthToken() {
        if(authTokenCache != null) {
            return Single.just(authTokenCache);
        }
        return authLocalDataSource.getAuthToken();
    }

    @Override
    public Single<AuthToken> getAuthToken(String authorizationCode) {
        return authRemoteDataSource.getAuthToken(authorizationCode)
                .doOnSuccess(authToken -> {
                    authLocalDataSource.saveAuthToken(authToken);
                    authTokenCache = authToken;
                });
    }
}

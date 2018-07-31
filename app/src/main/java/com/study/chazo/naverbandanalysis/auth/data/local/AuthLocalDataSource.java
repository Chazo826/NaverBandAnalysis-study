package com.study.chazo.naverbandanalysis.auth.data.local;

import com.study.chazo.naverbandanalysis.auth.data.AuthDataSource;
import com.study.chazo.naverbandanalysis.auth.model.AuthToken;
import com.study.chazo.naverbandanalysis.base.data.local.Pref;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

import static com.study.chazo.naverbandanalysis.base.Constants.PrefKeyConstant.AUTH_TOKEN_KEY;

/**
 * Created by Chazo on 2018-07-19
 */
@Singleton
public class AuthLocalDataSource implements AuthDataSource {
    private Pref mPref;

    @Inject
    public AuthLocalDataSource(Pref pref) {
        mPref = pref;
    }

    public void saveAuthToken(AuthToken authToken) {
        mPref.put(AUTH_TOKEN_KEY, authToken);
    }

    @Override
    public Single<AuthToken> getAuthToken() {
        AuthToken authToken = mPref.getObject(AUTH_TOKEN_KEY, AuthToken.class);
        if(authToken == null) {
            return Single.error(new NullPointerException("authToken is null"));
        }
        return Single.just(authToken);
    }

    @Override
    public Single<AuthToken> getAuthToken(String authorizationCode) {
        return null;
    }

    @Override
    public Single<Boolean> isAuthToken() {
        return Single.just(mPref.contains(AUTH_TOKEN_KEY));

    }
}

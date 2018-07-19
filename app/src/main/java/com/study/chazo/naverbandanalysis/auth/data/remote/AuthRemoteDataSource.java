package com.study.chazo.naverbandanalysis.auth.data.remote;

import android.content.Context;

import com.study.chazo.naverbandanalysis.R;
import com.study.chazo.naverbandanalysis.auth.data.AuthDataSource;
import com.study.chazo.naverbandanalysis.auth.model.AuthToken;

import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Chazo on 2018-07-19
 */
@Singleton
public class AuthRemoteDataSource implements AuthDataSource {
    private AuthRequestHelper authRequestHelper;
    private Context context;

    @Inject
    public AuthRemoteDataSource(Context context, AuthRequestHelper authRequestHelper) {
        this.context = context;
        this.authRequestHelper = authRequestHelper;
    }

    @Override
    public Single<AuthToken> getAuthToken() {
        return null;
    }

    @Override
    public Single<AuthToken> getAuthToken(String authorizationCode) {
        String clientId = context.getResources().getString(R.string.auth_client_id);
        String clientSecret = context.getResources().getString(R.string.auth_client_secret);
        return authRequestHelper.requestAuthToken(authorizationCode, clientId, clientSecret)
                .map(authToken -> {
                    authToken.expiresIn = Calendar.getInstance().getTimeInMillis() + authToken.expiresIn;
                    return authToken;
                });
    }
}

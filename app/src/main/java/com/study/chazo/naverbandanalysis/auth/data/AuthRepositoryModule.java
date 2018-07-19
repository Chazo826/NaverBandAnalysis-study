package com.study.chazo.naverbandanalysis.auth.data;

import android.content.Context;

import com.study.chazo.naverbandanalysis.auth.data.local.AuthLocalDataSource;
import com.study.chazo.naverbandanalysis.auth.data.remote.AuthRemoteDataSource;
import com.study.chazo.naverbandanalysis.auth.data.remote.AuthRequestHelper;
import com.study.chazo.naverbandanalysis.base.data.remote.RetrofitFactory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chazo on 2018-07-19
 */

@Module
public abstract class AuthRepositoryModule {

    @Singleton
    @Binds
    public abstract AuthDataSource provideAuthLocalDataSource(AuthLocalDataSource authLocalDataSource);

    @Singleton
    @Binds
    public abstract AuthDataSource provideAuthRemoteDataSource(AuthRemoteDataSource authRemoteDataSource);

    @Singleton
    @Binds
    public abstract AuthDataSource provideAuthRepository(AuthRepository authRepository);

    @Singleton
    @Provides
    public static AuthRequestHelper provideAuthRequestHelper(RetrofitFactory retrofitFactory) {
        return new AuthRequestHelper(retrofitFactory.getAuthService());
    }
}

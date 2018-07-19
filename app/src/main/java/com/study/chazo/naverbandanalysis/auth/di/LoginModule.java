package com.study.chazo.naverbandanalysis.auth.di;

import com.study.chazo.naverbandanalysis.auth.data.AuthRepository;
import com.study.chazo.naverbandanalysis.auth.domain.GetAuthToken;
import com.study.chazo.naverbandanalysis.auth.viewmodel.LoginViewModel;
import com.study.chazo.naverbandanalysis.base.di.ActivityScoped;
import com.study.chazo.naverbandanalysis.base.viewmodel.BaseViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chazo on 2018-07-18
 */
@Module
public abstract class LoginModule {

    @ActivityScoped
    @Binds
    public abstract BaseViewModel provideLoginViewModel(LoginViewModel loginViewModel);

    @ActivityScoped
    @Provides
    public static GetAuthToken provideGetAuthToken(AuthRepository authRepository){
        return new GetAuthToken(authRepository);
    }
}

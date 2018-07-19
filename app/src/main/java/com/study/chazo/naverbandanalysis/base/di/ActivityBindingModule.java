package com.study.chazo.naverbandanalysis.base.di;

import com.study.chazo.naverbandanalysis.auth.di.LoginModule;
import com.study.chazo.naverbandanalysis.auth.view.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Chazo on 2018-07-13
 */

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();
}

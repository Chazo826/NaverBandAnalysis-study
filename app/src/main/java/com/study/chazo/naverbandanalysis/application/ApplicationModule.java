package com.study.chazo.naverbandanalysis.application;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Chazo on 2018-07-12
 */
@Module
public abstract class ApplicationModule {
    @Binds
    abstract Context bindContext(Application application);
}

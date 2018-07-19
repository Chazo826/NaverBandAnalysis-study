package com.study.chazo.naverbandanalysis.base.data;

import android.content.Context;

import com.study.chazo.naverbandanalysis.base.data.local.Pref;
import com.study.chazo.naverbandanalysis.base.data.remote.RetrofitFactory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chazo on 2018-07-18
 */
@Module
public abstract class BaseDataModule {
    @Singleton
    @Provides
    public static Pref providePref(Context context) {
        return new Pref(context);
    }

    @Singleton
    @Provides
    public static RetrofitFactory provideRetrofitFactory() {
        return new RetrofitFactory();
    }
}

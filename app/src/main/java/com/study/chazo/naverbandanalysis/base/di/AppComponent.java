package com.study.chazo.naverbandanalysis.base.di;

import android.app.Application;

import com.study.chazo.naverbandanalysis.application.ApplicationModule;
import com.study.chazo.naverbandanalysis.application.GlobalApplication;
import com.study.chazo.naverbandanalysis.auth.data.AuthRepositoryModule;
import com.study.chazo.naverbandanalysis.bands.data.BandRepositoryModule;
import com.study.chazo.naverbandanalysis.base.data.BaseDataModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Chazo on 2018-07-12
 */
@Singleton
@Component(modules = {ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class,
        BaseDataModule.class,
        AuthRepositoryModule.class,
        BandRepositoryModule.class
})
public interface AppComponent extends AndroidInjector<GlobalApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}

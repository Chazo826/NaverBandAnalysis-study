package com.study.chazo.naverbandanalysis.bands.di;

import com.study.chazo.naverbandanalysis.auth.data.AuthRepository;
import com.study.chazo.naverbandanalysis.auth.domain.GetAuthToken;
import com.study.chazo.naverbandanalysis.auth.viewmodel.LoginViewModel;
import com.study.chazo.naverbandanalysis.bands.data.BandRepository;
import com.study.chazo.naverbandanalysis.bands.domain.GetBands;
import com.study.chazo.naverbandanalysis.bands.domain.GetBandsToRemote;
import com.study.chazo.naverbandanalysis.bands.domain.IsBandsToLocal;
import com.study.chazo.naverbandanalysis.bands.viewmodel.BandsViewModel;
import com.study.chazo.naverbandanalysis.base.di.ActivityScoped;
import com.study.chazo.naverbandanalysis.base.viewmodel.BaseViewModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chazo on 2018-07-31
 */
@Module
public abstract class BandsModule {

    @ActivityScoped
    @Binds
    public abstract BaseViewModel provideBandsViewModel(BandsViewModel bandsViewModel);

    @ActivityScoped
    @Provides
    public static GetBands provideGetBands(BandRepository bandRepository){
        return new GetBands(bandRepository);
    }

    @ActivityScoped
    @Provides
    public static GetBandsToRemote provideGetBandsToRemote(BandRepository bandRepository){
        return new GetBandsToRemote(bandRepository);
    }

    @ActivityScoped
    @Provides
    public static IsBandsToLocal provideIsBandsToLocal(BandRepository bandRepository){
        return new IsBandsToLocal(bandRepository);
    }
}

package com.study.chazo.naverbandanalysis.bands.data;

import com.study.chazo.naverbandanalysis.bands.data.local.BandLocalDataSource;
import com.study.chazo.naverbandanalysis.bands.data.remote.BandRemoteDataSource;
import com.study.chazo.naverbandanalysis.bands.data.remote.BandRequestHelper;
import com.study.chazo.naverbandanalysis.base.data.remote.RetrofitFactory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chazo on 2018-07-31
 */

@Module
public abstract class BandRepositoryModule {

    @Singleton
    @Binds
    public abstract BandDataSource provideBandLocalDataSource(BandLocalDataSource bandLocalDataSource);

    @Singleton
    @Binds
    public abstract BandDataSource provideBandRemoteDataSoure(BandRemoteDataSource bandRemoteDataSource);

    @Singleton
    @Binds
    public abstract BandDataSource provideBandRepository(BandRepository bandRepository);

    @Singleton
    @Provides
    public static BandRequestHelper provideBandRequestHelper(RetrofitFactory retrofitFactory){
        return new BandRequestHelper(retrofitFactory.getBandService());
    }

}

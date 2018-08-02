package com.study.chazo.naverbandanalysis.bands.data;

import com.study.chazo.naverbandanalysis.bands.data.local.BandLocalDataSource;
import com.study.chazo.naverbandanalysis.bands.data.remote.BandRemoteDataSource;
import com.study.chazo.naverbandanalysis.bands.model.Band;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Chazo on 2018-07-31
 */
public class BandRepository implements BandDataSource {
    private BandLocalDataSource bandLocalDataSource;
    private BandRemoteDataSource bandRemoteDataSource;

    private List<Band> cacheBands = new ArrayList<>();

    @Inject
    public BandRepository(BandLocalDataSource bandLocalDataSource, BandRemoteDataSource bandRemoteDataSource) {
        this.bandLocalDataSource = bandLocalDataSource;
        this.bandRemoteDataSource = bandRemoteDataSource;
    }

    @Override
    public Single<List<Band>> getBands() {
        if(cacheBands.size() > 0) {
            return Single.just(cacheBands);
        }
        return bandLocalDataSource.getBands()
                .flatMap(bands -> {
                    if(bands.size() > 0) {
                        cacheBands = bands;
                        return Single.just(bands);
                    } else {
                        return bandRemoteDataSource.getBands();
                    }
                });
    }

    public Single<List<Band>> getBandsFromRemote() {
        return bandRemoteDataSource.getBands()
                .map(bands -> {
                    cacheBands = bands;
                    bandLocalDataSource.saveBands(bands);
                    return bands;
                });
    }

    @Override
    public boolean isBands() {
        return bandLocalDataSource.isBands();
    }
}

package com.study.chazo.naverbandanalysis.bands.domain;

import com.study.chazo.naverbandanalysis.bands.data.BandRepository;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Chazo on 2018-08-01
 */
public class IsBandsToLocal {
    private BandRepository bandRepository;

    public IsBandsToLocal(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public Single<Boolean> execute(){
        return Single.fromCallable(() -> bandRepository.isBands())
                .subscribeOn(Schedulers.io());
    }
}

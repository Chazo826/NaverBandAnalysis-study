package com.study.chazo.naverbandanalysis.bands.domain;

import com.study.chazo.naverbandanalysis.bands.data.BandRepository;
import com.study.chazo.naverbandanalysis.bands.model.Band;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Chazo on 2018-08-01
 */
public class GetBandsToRemote {
    private BandRepository bandRepository;

    @Inject
    public GetBandsToRemote(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public Single<List<Band>> execute(){
        return bandRepository.getBandsFromRemote()
                .subscribeOn(Schedulers.io());
    }
}

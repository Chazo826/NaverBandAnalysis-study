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
public class GetBands {
    private BandRepository bandRepository;

    @Inject
    public GetBands(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public Single<List<Band>> execute(){
        return bandRepository.getBands()
                .subscribeOn(Schedulers.io());
    }
}

package com.study.chazo.naverbandanalysis.bands.data;

import com.study.chazo.naverbandanalysis.bands.model.Band;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Chazo on 2018-07-31
 */
public interface BandDataSource {

    Single<List<Band>> getBands();

    boolean isBands();

}

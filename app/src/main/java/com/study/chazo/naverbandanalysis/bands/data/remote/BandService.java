package com.study.chazo.naverbandanalysis.bands.data.remote;

import com.study.chazo.naverbandanalysis.base.model.BandRequest;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Chazo on 2018-07-31
 */
public interface BandService {

    @GET("v2.1/bands")
    Single<BandRequest> requestBands(@Query("access_token") String accessToken);
}

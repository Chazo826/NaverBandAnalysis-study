package com.study.chazo.naverbandanalysis.bands.data.remote;

import com.study.chazo.naverbandanalysis.auth.data.AuthRepository;
import com.study.chazo.naverbandanalysis.bands.data.BandDataSource;
import com.study.chazo.naverbandanalysis.bands.model.Band;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Chazo on 2018-07-31
 */
public class BandRemoteDataSource implements BandDataSource {
    private AuthRepository authRepository;
    private BandRequestHelper bandRequestHelper;

    @Inject
    public BandRemoteDataSource(AuthRepository authRepository, BandRequestHelper bandRequestHelper) {
        this.authRepository = authRepository;
        this.bandRequestHelper = bandRequestHelper;
    }

    @Override
    public Single<List<Band>> getBands() {
        return authRepository.getAuthToken()
                .flatMap(authToken -> {
                    if(authToken.expiresIn < Calendar.getInstance().getTimeInMillis()) {
                        return authRepository.getAuthToken(authToken.refreshToken);
                    } else {
                        return Single.just(authToken);
                    }
                }).flatMap(authToken -> bandRequestHelper.requestBands(authToken.accessToken));
    }

    @Override
    public boolean isBands() {
        return false;
    }
}

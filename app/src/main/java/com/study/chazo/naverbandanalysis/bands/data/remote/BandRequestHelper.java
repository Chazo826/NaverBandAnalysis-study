package com.study.chazo.naverbandanalysis.bands.data.remote;

import android.accounts.NetworkErrorException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.study.chazo.naverbandanalysis.bands.model.Band;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Chazo on 2018-07-31
 */
public class BandRequestHelper {
    private BandService bandService;

    @Inject
    public BandRequestHelper(BandService bandService){
        this.bandService = bandService;
    }

    public Single<List<Band>> requestBands(String accessToken){
        return bandService.requestBands(accessToken)
                .map(bandRequest -> {
                    if(bandRequest.resultCode != 1) {
                        throw new NetworkErrorException("failure. result code : " + bandRequest.resultCode);
                    } else {
                        JsonElement bandsJson = bandRequest.resultData.getAsJsonObject().get("bands");
                        return parseBandsFromJson(bandsJson);
                    }
                });
    }

    private List<Band> parseBandsFromJson(JsonElement jsonElement){
        Gson gson = new Gson();
        return gson.fromJson(jsonElement, new TypeToken<List<Band>>(){}.getType());
    }
}

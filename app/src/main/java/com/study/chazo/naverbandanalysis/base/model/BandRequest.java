package com.study.chazo.naverbandanalysis.base.model;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chazo on 2018-07-20
 */
public class BandRequest {
    @SerializedName("result_code")
    public int resultCode;
    @SerializedName("result_data")
    public JsonElement resultData;
}

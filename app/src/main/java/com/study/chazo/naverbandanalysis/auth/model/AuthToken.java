package com.study.chazo.naverbandanalysis.auth.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chazo on 2018-07-19
 */
public class AuthToken {
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("expires_in")
    public long expiresIn;
    @SerializedName("refresh_token")
    public String refreshToken;
}

package com.study.chazo.naverbandanalysis.bands.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Chazo on 2018-07-31
 */
public class Band extends RealmObject{
    @SerializedName("name")
    private String name;
    @PrimaryKey
    @SerializedName("band_key")
    private String bandKey;
    @SerializedName("cover")
    private String cover;
    @SerializedName("member_count")
    private int memberCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBandKey() {
        return bandKey;
    }

    public void setBandKey(String bandKey) {
        this.bandKey = bandKey;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }
}

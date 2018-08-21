package com.study.chazo.naverbandanalysis.bands.viewmodel;

import com.study.chazo.naverbandanalysis.R;
import com.study.chazo.naverbandanalysis.bands.model.Band;
import com.study.chazo.naverbandanalysis.base.viewmodel.BaseItemViewModel;

/**
 * Created by Chazo on 2018-08-16
 */
public class BandItemViewModel implements BaseItemViewModel {
    private Band band;

    public BandItemViewModel(Band band) {
        this.band = band;
    }


    public String getCover() {
        return band.getCover();
    }

    public String getBandName(){
        return band.getName();
    }

    public Band getBand() {
        return band;
    }

    @Override
    public int getViewType() {
        return R.layout.item_band;
    }
}

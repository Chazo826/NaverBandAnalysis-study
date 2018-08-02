package com.study.chazo.naverbandanalysis.bands.viewmodel;

import com.study.chazo.naverbandanalysis.bands.domain.GetBands;
import com.study.chazo.naverbandanalysis.bands.domain.GetBandsToRemote;
import com.study.chazo.naverbandanalysis.bands.domain.IsBandsToLocal;
import com.study.chazo.naverbandanalysis.base.viewmodel.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by Chazo on 2018-07-20
 */
public class BandsViewModel implements BaseViewModel {
    private GetBands getBands;
    private GetBandsToRemote getBandsToRemote;
    private IsBandsToLocal isBandsToLocal;

    @Inject
    public BandsViewModel(GetBands getBands, GetBandsToRemote getBandsToRemote, IsBandsToLocal isBandsToLocal) {
        this.getBands = getBands;
        this.getBandsToRemote = getBandsToRemote;
        this.isBandsToLocal = isBandsToLocal;
    }


}

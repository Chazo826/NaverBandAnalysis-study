package com.study.chazo.naverbandanalysis.bands.viewmodel;

import android.databinding.ObservableArrayList;

import com.study.chazo.naverbandanalysis.bands.domain.GetBands;
import com.study.chazo.naverbandanalysis.bands.domain.GetBandsToRemote;
import com.study.chazo.naverbandanalysis.bands.domain.IsBandsToLocal;
import com.study.chazo.naverbandanalysis.bands.model.Band;
import com.study.chazo.naverbandanalysis.base.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Chazo on 2018-07-20
 */
public class BandsViewModel implements BaseViewModel {
    private GetBands getBands;
    private GetBandsToRemote getBandsToRemote;
    private IsBandsToLocal isBandsToLocal;
    private ObservableArrayList<BandItemViewModel> bandItemViewModels;
    private CompositeDisposable compositeDisposable;

    @Inject
    public BandsViewModel(GetBands getBands, GetBandsToRemote getBandsToRemote, IsBandsToLocal isBandsToLocal) {
        this.getBands = getBands;
        this.getBandsToRemote = getBandsToRemote;
        this.isBandsToLocal = isBandsToLocal;
        bandItemViewModels = new ObservableArrayList<>();
    }

    public ObservableArrayList<BandItemViewModel> getBandItemViewModels(){
        return bandItemViewModels;
    }

    public void onCreate(){
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(getBands.execute()
                .map(bands -> {
                    List<BandItemViewModel> itemViewModels = new ArrayList<>();
                    for(Band band : bands) {
                        itemViewModels.add(new BandItemViewModel(band));
                    }
                    return itemViewModels;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemViewModels -> bandItemViewModels.addAll(itemViewModels))
        );
    }

    public void onDestroy(){
        compositeDisposable.dispose();
    }
}

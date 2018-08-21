package com.study.chazo.naverbandanalysis.bands.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.chazo.naverbandanalysis.R;
import com.study.chazo.naverbandanalysis.bands.viewmodel.BandItemViewModel;
import com.study.chazo.naverbandanalysis.bands.viewmodel.BandsViewModel;
import com.study.chazo.naverbandanalysis.base.view.BaseBindingActivity;
import com.study.chazo.naverbandanalysis.base.view.BindingRecyclerViewAdapter;
import com.study.chazo.naverbandanalysis.base.viewmodel.BaseViewModel;
import com.study.chazo.naverbandanalysis.databinding.ActivityBandsBinding;

import javax.inject.Inject;

public class BandsActivity extends BaseBindingActivity<ActivityBandsBinding> {

    @Inject
    public BandsViewModel bandsViewModel;

    private BindingRecyclerViewAdapter<BandItemViewModel> mBandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingContentView(R.layout.activity_bands);
        getBinding().setViewModel(bandsViewModel);

        mBandAdapter = new BindingRecyclerViewAdapter<>();
        getBinding().actBandsRecyclerview.setAdapter(mBandAdapter);

        mBandAdapter.setItems(bandsViewModel.getBandItemViewModels());

        bandsViewModel.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandsViewModel.onDestroy();
    }
}

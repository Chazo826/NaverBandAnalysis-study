package com.study.chazo.naverbandanalysis.bands.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.chazo.naverbandanalysis.R;
import com.study.chazo.naverbandanalysis.base.view.BaseBindingActivity;
import com.study.chazo.naverbandanalysis.databinding.ActivityBandsBinding;

public class BandsActivity extends BaseBindingActivity<ActivityBandsBinding> {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingContentView(R.layout.activity_bands);
    }

}

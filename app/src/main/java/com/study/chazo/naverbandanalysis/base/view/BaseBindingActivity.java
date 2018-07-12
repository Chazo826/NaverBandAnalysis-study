package com.study.chazo.naverbandanalysis.base.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.study.chazo.naverbandanalysis.base.di.DaggerAppComponent;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseBindingActivity<B extends ViewDataBinding> extends DaggerAppCompatActivity {
    private B binding;

    protected void setBindingContentView(@LayoutRes int layout) {
        if (binding == null) {
            binding = DataBindingUtil.setContentView(this, layout);
        }
    }

    protected B getBinding() {
        return binding;
    }
}

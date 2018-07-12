package com.study.chazo.naverbandanalysis.base.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.chazo.naverbandanalysis.BR;
import com.study.chazo.naverbandanalysis.base.viewmodel.BaseViewModel;

import dagger.android.support.DaggerFragment;

public abstract class BaseBindingFragment<B extends ViewDataBinding> extends DaggerFragment {
    private B binding;

    protected View setBindingContentView(@NonNull LayoutInflater inflater, @LayoutRes int layout, @Nullable ViewGroup container) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, layout, container, false);
        }
        return binding.getRoot();
    }

    protected void setViewModel(BaseViewModel viewModel) {
        //todo viewModel
//        binding.setVariable(BR.viewModel, viewModel);
        binding.executePendingBindings();
    }

    protected B getBinding() {
        return binding;
    }
}

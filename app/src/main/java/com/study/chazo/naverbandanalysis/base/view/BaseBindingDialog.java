package com.study.chazo.naverbandanalysis.base.view;

import android.content.Context;
import android.databinding.DataBindingUtil;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.study.chazo.naverbandanalysis.BR;
import com.study.chazo.naverbandanalysis.R;
import com.study.chazo.naverbandanalysis.base.viewmodel.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseBindingDialog<B extends ViewDataBinding> implements AlertDialog.OnDismissListener {
    private AlertDialog dialog;
    private LinearLayout layout;
    private Context context;
    private B binding;
    private Button positiveButton;
    private Button nativeButton;
    private PublishSubject<View> positiveButtonSubject;
    private PublishSubject<View> nativeButtonSubject;

    protected BaseBindingDialog(@NonNull Context context, boolean isDefaultButton) {
        this.context = context;
        layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog_base, null);
        setDefaultButton(isDefaultButton);
    }

    public final void show() {
        onStart();
        dialog = new AlertDialog.Builder(context)
                .setOnDismissListener(this)
                .setView(layout)
                .show();
    }

    public Observable<View> positiveOnClickObservable(){
        if(positiveButtonSubject == null) throw new RuntimeException("need setDefaultButton(true)");
        return positiveButtonSubject;
    }

    public Observable<View> nativeOnClickObservable(){
        if(nativeButtonSubject == null) throw new RuntimeException("need setDefaultButton(true)");
        return nativeButtonSubject;
    }

    protected abstract void onStart();

    protected void setView(@LayoutRes int layoutId) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, layout, false);
        layout.addView(binding.getRoot(), 0);

    }

    protected void setViewModel(BaseViewModel viewModel){
        //todo viewModel
//        binding.setVariable(BR.viewModel, viewModel);
        binding.executePendingBindings();
    }

    protected B getBinding() {
        return binding;
    }

    protected Context getContext() {
        return context;
    }

    protected void dismiss() {
        dialog.dismiss();
    }

    protected void setDefaultButton(boolean isDefaultButton) {
        if (isDefaultButton) {
            createDefaultButtonSubject();
            createDefaultButton();
        } else {
            positiveButtonSubject = null;
            nativeButtonSubject = null;
            positiveButton = null;
            nativeButton = null;
        }
        setDefaultButtonVisible(isDefaultButton);
    }

    private void createDefaultButtonSubject() {
        if (positiveButtonSubject == null)
            positiveButtonSubject = PublishSubject.create();
        if (nativeButtonSubject == null)
            nativeButtonSubject = PublishSubject.create();
    }

    private void createDefaultButton(){
        if(positiveButton == null)
            positiveButton = layout.findViewById(R.id.dialog_base_positive_btn);
        if(nativeButton == null)
            nativeButton = layout.findViewById(R.id.dialog_base_native_btn);

        positiveButton.setOnClickListener(view -> positiveButtonSubject.onNext(view));
        nativeButton.setOnClickListener(view -> nativeButtonSubject.onNext(view));
    }

    private void setDefaultButtonVisible(boolean isDefaultButton) {
        layout.findViewById(R.id.dialog_base_default_button_view).setVisibility(isDefaultButton? View.VISIBLE : View.GONE);
    }

}

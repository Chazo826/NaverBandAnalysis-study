package com.study.chazo.naverbandanalysis.auth.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.study.chazo.naverbandanalysis.bands.view.BandsActivity;
import com.study.chazo.naverbandanalysis.R;
import com.study.chazo.naverbandanalysis.auth.viewmodel.LoginViewModel;
import com.study.chazo.naverbandanalysis.base.view.BaseBindingActivity;
import com.study.chazo.naverbandanalysis.databinding.ActivityLoginBinding;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseBindingActivity<ActivityLoginBinding> {
    @Inject
    public LoginViewModel loginViewModel;

    private CompositeDisposable compositeDisposable;
    private Disposable tokenDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel.onCreate();
        Log.d("!!!!", loginViewModel.toString());

        onAuthTokenSuccessEvent();
        getAuthToken(getIntent());

        setBindingContentView(R.layout.activity_login);
        getBinding().setViewModel(loginViewModel);
    }

    private void onAuthTokenSuccessEvent(){
        tokenDisposable = loginViewModel.authTokenSuccessSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(__ -> moveMainActivity());
        Log.d("!!!!", "!EDASF: " + loginViewModel.authTokenSuccessSubject.hasObservers());
    }

    private void moveMainActivity() {
        Log.d("!!!!", "move");
        startActivity(new Intent(this, BandsActivity.class));
        finish();
    }

    private void getAuthToken(Intent intent) {
        if(intent.getData() != null) {
            loginViewModel.getAuthToken(intent.getData());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("!!!!", loginViewModel.toString());
        Log.d("!!!!", "onNewIntent");
        getAuthToken(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(loginViewModel.loginButtonSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(__ -> login())
        );
    }

    private void login(){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getLoginUrl())));
    }

    private String getLoginUrl(){
        return String.format("https://auth.band.us/oauth2/authorize?response_type=code&client_id=%s&redirect_uri=%s",
                getString(R.string.auth_client_id), getString(R.string.auth_redirect_url));
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.dispose();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("!!!!", "onDestroy");
        tokenDisposable.dispose();
        loginViewModel.onDestroy();
    }
}

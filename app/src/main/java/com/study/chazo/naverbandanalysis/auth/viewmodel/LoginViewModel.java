package com.study.chazo.naverbandanalysis.auth.viewmodel;

import android.net.Uri;
import android.util.Log;
import android.widget.Button;

import com.study.chazo.naverbandanalysis.auth.domain.GetAuthToken;
import com.study.chazo.naverbandanalysis.auth.model.AuthToken;
import com.study.chazo.naverbandanalysis.base.viewmodel.BaseViewModel;

import java.net.SocketException;
import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Chazo on 2018-07-18
 */
public class LoginViewModel implements BaseViewModel {
    public PublishSubject<Boolean> loginButtonSubject;
    public BehaviorSubject<Boolean> authTokenSuccessSubject;
    private GetAuthToken getAuthToken;

    private CompositeDisposable disposable;

    @Inject
    public LoginViewModel(GetAuthToken getAuthToken) {
        this.getAuthToken = getAuthToken;
        loginButtonSubject = PublishSubject.create();
        authTokenSuccessSubject = BehaviorSubject.create();
    }

    public void getAuthToken(Uri uri){
        Log.d("!!!!", "getAuthToken");
        String authorizationCode = uri.getQueryParameter("code");
        if(authorizationCode == null) {
            authTokenSuccessSubject.onError(new SocketException("code is null"));
            return;
        }
        disposable.add(getAuthToken.execute(authorizationCode)
                .subscribe(authToken -> authTokenSuccessSubject.onNext(true),
                        throwable -> authTokenSuccessSubject.onError(throwable))
        );
    }

    public void onLoginClick() {
        loginButtonSubject.onNext(true);
    }

    public void onCreate() {
        disposable = new CompositeDisposable();
        checkAuthToken();
    }

    public void onDestroy() {
        disposable.dispose();
    }

    private void checkAuthToken() {
        disposable.add(getAuthToken.execute()
                .flatMap(authToken -> {
                    if (authToken.expiresIn < Calendar.getInstance().getTimeInMillis()) {
                        //token이 있지만 만료
                        return getAuthToken.execute(authToken.refreshToken);
                    } else {
                        //정상 token 존재
                        return Single.just(authToken);
                    }
                })
                .filter(authToken -> authToken != null)
                .subscribe(authToken -> {
                            authTokenSuccessSubject.onNext(true);
                        },
                        throwable -> {
                            if (throwable instanceof NullPointerException) {
                                return;
                            }
                            authTokenSuccessSubject.onError(throwable);
                        })
        );
    }
}

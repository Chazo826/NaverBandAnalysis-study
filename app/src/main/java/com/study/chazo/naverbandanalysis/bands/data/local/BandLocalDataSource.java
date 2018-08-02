package com.study.chazo.naverbandanalysis.bands.data.local;

import com.study.chazo.naverbandanalysis.bands.data.BandDataSource;
import com.study.chazo.naverbandanalysis.bands.model.Band;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.realm.Realm;

/**
 * Created by Chazo on 2018-07-31
 */
public class BandLocalDataSource implements BandDataSource {

    @Inject
    public BandLocalDataSource() {

    }

    @Override
    public Single<List<Band>> getBands() {
        return Single.fromCallable(() -> {
            Realm realm = Realm.getDefaultInstance();
            List<Band> bands = realm.copyFromRealm(realm.where(Band.class).findAll());
            realm.close();
            return bands;
        });
    }

    public void saveBands(List<Band> bands){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(bands));
        realm.close();
    }

    @Override
    public boolean isBands() {
        Realm realm = Realm.getDefaultInstance();
        long count = realm.where(Band.class).count();

        return count > 0;
    }
}

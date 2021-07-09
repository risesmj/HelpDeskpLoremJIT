package com.loremjit.centraldechamados.realm;

import android.app.Application;

import io.realm.Realm;

public class AplicacaoRealm extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}

package com.example.testrealm;

import java.util.List;

import io.realm.Realm;

public class RealmHandle {
    public Realm realm = Realm.getDefaultInstance();
    private static RealmHandle instance;

    public static RealmHandle getInstance() {
        if (instance == null) {
            instance = new RealmHandle();
        }
        return instance;
    }

    public void addData(ExampleData exampleData) {
        realm.beginTransaction();
        realm.copyFromRealm(exampleData);
        realm.commitTransaction();

    }

    public List<ExampleData> getExampleDataList() {
        return realm.where(ExampleData.class).findAll();
    }
}

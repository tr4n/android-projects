package com.example.testrealm;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ExampleData extends RealmObject {
    @PrimaryKey
    public String data;

    public ExampleData(){

    };


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

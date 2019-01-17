package com.example.expensemanagement.collections;

import com.example.expensemanagement.databases.RealmHandle;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

public class Collect {
    public static int INCOMING = 2;
    public static int OUTGOING = 4;

    public static String getTime(String format){
        return (new SimpleDateFormat(format)).format(new Date()).toString();
    }
    public static String getFormatNumber(BigInteger number){
        DecimalFormat formatter = new DecimalFormat("###,###,###,###,###");
        return formatter.format(number);
    }


}

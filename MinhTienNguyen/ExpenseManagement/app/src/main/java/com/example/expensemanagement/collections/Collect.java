package com.example.expensemanagement.collections;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Collect {
    public static String INCOMING = "incoming";
    public static String OUTGOING = "outgoing";

    public static String getTime(String format){
        return (new SimpleDateFormat(format)).format(new Date()).toString();
    }
}

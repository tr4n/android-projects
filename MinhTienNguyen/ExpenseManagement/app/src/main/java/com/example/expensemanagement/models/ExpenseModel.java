package com.example.expensemanagement.models;

import io.realm.RealmObject;

public class ExpenseModel  extends RealmObject{
    private String date;
    private int type;
    private String money;
    private String content;

    public ExpenseModel(){

    }

    public void get(String date, int type, String money, String content) {
        this.date = date;
        this.type = type;
        this.money = money;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(ExpenseModel other){
        return this.getDate().equals(other.getDate());
    }
}

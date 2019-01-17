package com.example.expensemanagement.models;

public class ExpenseModel {
    public String date;
    public String type;
    public int money;
    public String content;

    public ExpenseModel(String date, String type, int money, String content) {
        this.date = date;
        this.type = type;
        this.money = money;
        this.content = content;
    }
}

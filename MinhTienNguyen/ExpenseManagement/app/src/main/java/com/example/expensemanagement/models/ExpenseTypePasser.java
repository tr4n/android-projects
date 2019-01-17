package com.example.expensemanagement.models;

import java.io.Serializable;

public class ExpenseTypePasser implements Serializable {
    private String type;

    public ExpenseTypePasser(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

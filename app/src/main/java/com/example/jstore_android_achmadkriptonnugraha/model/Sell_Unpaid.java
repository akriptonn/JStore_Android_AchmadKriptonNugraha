package com.example.jstore_android_achmadkriptonnugraha.model;

import java.util.ArrayList;

public class Sell_Unpaid extends Invoice {
    private String dueDate;

    public Sell_Unpaid(int id, ArrayList<Integer> item, String date, int totalPrice, boolean isActive, String INVOICE_TYPE, String INVOICE_STATUS, String dueDate) {
        super(id, item, date, totalPrice, isActive, INVOICE_TYPE, INVOICE_STATUS);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}

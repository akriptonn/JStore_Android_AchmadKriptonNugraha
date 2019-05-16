package com.example.jstore_android_achmadkriptonnugraha.model;

import java.util.ArrayList;

public class Sell_Paid extends Invoice {

    public Sell_Paid(int id, ArrayList<Integer> item, String date, int totalPrice, boolean isActive, String INVOICE_TYPE, String INVOICE_STATUS) {
        super(id, item, date, totalPrice, isActive, INVOICE_TYPE, INVOICE_STATUS);
    }

}

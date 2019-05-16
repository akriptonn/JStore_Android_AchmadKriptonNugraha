package com.example.jstore_android_achmadkriptonnugraha.model;


import java.util.ArrayList;

public class Sell_Installment extends Invoice {
    private int installmentPeriod;
    private int installmentPrice;

    public Sell_Installment(int id, ArrayList<Integer> item, String date, int totalPrice, boolean isActive, String INVOICE_TYPE, String INVOICE_STATUS, int installmentPeriod, int installmentPrice) {
        super(id, item, date, totalPrice, isActive, INVOICE_TYPE, INVOICE_STATUS);
        this.installmentPeriod = installmentPeriod;
        this.installmentPrice = installmentPrice;
    }

    public int getInstallmentPeriod() {
        return installmentPeriod;
    }

    public void setInstallmentPeriod(int installmentPeriod) {
        this.installmentPeriod = installmentPeriod;
    }

    public int getInstallmentPrice() {
        return installmentPrice;
    }

    public void setInstallmentPrice(int installmentPrice) {
        this.installmentPrice = installmentPrice;
    }

}

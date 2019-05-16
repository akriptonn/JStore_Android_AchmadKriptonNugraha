package com.example.jstore_android_achmadkriptonnugraha.model;

import java.util.ArrayList;

public class Invoice {
    protected int id;
    protected ArrayList<Integer> item;
    protected String date;
    protected int totalPrice;
    protected boolean isActive;
    protected String INVOICE_TYPE;
    protected String INVOICE_STATUS;

    public Invoice(int id, ArrayList<Integer> item, String date, int totalPrice, boolean isActive, String INVOICE_TYPE, String INVOICE_STATUS) {
        this.id = id;
        this.item = item;
        this.date = date;
        this.totalPrice = totalPrice;
        this.isActive = isActive;
        this.INVOICE_TYPE = INVOICE_TYPE;
        this.INVOICE_STATUS = INVOICE_STATUS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getItem() {
        return item;
    }

    public void setItem(ArrayList<Integer> item) {
        this.item = item;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getINVOICE_TYPE() {
        return INVOICE_TYPE;
    }

    public void setINVOICE_TYPE(String INVOICE_TYPE) {
        this.INVOICE_TYPE = INVOICE_TYPE;
    }

    public String getINVOICE_STATUS() {
        return INVOICE_STATUS;
    }

    public void setINVOICE_STATUS(String INVOICE_STATUS) {
        this.INVOICE_STATUS = INVOICE_STATUS;
    }

}

package com.example.jstore_android_achmadkriptonnugraha.RequestActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class HistoryInvoiceRequest extends StringRequest {

    private static final String Regis_URL = "http://10.0.2.2:8080/allinvoicecustomer/";
    private Map<String, String> params;

    public HistoryInvoiceRequest(int id_customer, Response.Listener<String> listener)
    {
        super(Method.GET, Regis_URL + Integer.toString(id_customer), listener, null);
        params = new HashMap<>();

    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }

}

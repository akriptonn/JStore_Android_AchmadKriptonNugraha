package com.example.jstore_android_achmadkriptonnugraha.RequestActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class BuatPesananRequest extends StringRequest {
    private static final String Regis_URL_sp = "http://10.0.2.2:8080/createinvoicepaid";
    private static final String Regis_URL_su = "http://10.0.2.2:8080/createinvoiceunpaid";
    private static final String Regis_URL_in = "http://10.0.2.2:8080/createinvoiceinstallment";
    private Map<String,String> params;

    public static BuatPesananRequest staticBuatPesananRequest(int id, int barang,Response.Listener<String> listener, String status){
        if (status.equals("Pay Now")){
            return new BuatPesananRequest(id, barang, listener, Regis_URL_sp);
        }else{
            return new BuatPesananRequest(id, barang, listener, Regis_URL_su);
        }
    }

    public static BuatPesananRequest staticBuatPesananRequest(int id, int barang,Response.Listener<String> listener, String status, int installment_period){
            return new BuatPesananRequest(id, barang, listener, installment_period);
    }

    public BuatPesananRequest (int id, int barang, Response.Listener<String> listener, String link){
        super(Method.POST, link, listener, null);
        params = new HashMap<>();
        params.put("listItem",String.valueOf(barang));
        params.put("id_customer",String.valueOf(id));
    }


    public BuatPesananRequest (int id, int barang, Response.Listener<String> listener, int installment_period ){
        this(id, barang, listener, Regis_URL_in);
        params.put("installment_period",String.valueOf(installment_period));
    }

    @Override
    public Map<String,String> getParams(){
        return params;
    }
}

package com.example.jstore_android_achmadkriptonnugraha.RequestActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ItemFromIDRequest extends StringRequest {
    private static final String Regis_URL_sp = "http://10.0.2.2:8080/items/";

    private Map<String,String> params;


    public ItemFromIDRequest (int id, Response.Listener<String> listener){
        super(Method.GET, Regis_URL_sp+id, listener, null);
        params = new HashMap<>();

    }


    @Override
    public Map<String,String> getParams(){
        return params;
    }
}

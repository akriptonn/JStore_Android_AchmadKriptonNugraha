package com.example.jstore_android_achmadkriptonnugraha.RequestActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PesananSelesaiRequest extends StringRequest {
    private static final String Regis_URL = "http://10.0.2.2:8080/finishtransaction/";
    private Map<String, String> params;

    public PesananSelesaiRequest(int id_invoice, Response.Listener<String> listener)
    {
        super(Method.POST, Regis_URL , listener, null);
        params = new HashMap<>();
        params.put("id_invoice", Integer.toString(id_invoice));

    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}

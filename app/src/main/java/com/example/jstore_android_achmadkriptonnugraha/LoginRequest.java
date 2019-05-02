package com.example.jstore_android_achmadkriptonnugraha;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String Regis_URL = "http://192.168.43.136/logincust";
    private Map<String, String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener)
    {
        super(Method.POST, Regis_URL, listener, null);
        params = new HashMap<>();
//        params.put("name", name);
        params.put("email", email);
//        params.put("username", email);
        params.put("password", password);
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }
}

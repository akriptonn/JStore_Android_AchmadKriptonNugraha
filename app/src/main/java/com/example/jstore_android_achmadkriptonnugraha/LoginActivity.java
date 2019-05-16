package com.example.jstore_android_achmadkriptonnugraha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
//    final String constEmail = "test@test.com";
//    final String constPassword = "test";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText emailInput = (EditText) findViewById(R.id.editText);
        final EditText passInput = (EditText) findViewById(R.id.editText2);
        final ImageView loginButton = (ImageView) findViewById(R.id.button);
        final TextView registerClickable = (TextView) findViewById(R.id.textView);

        registerClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regisIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regisIntent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailInput.getText().toString();
                final String password = passInput.getText().toString();
//                AlertDialog.Builder builder2 = new AlertDialog.Builder(LoginActivity.this);
//                builder2.setMessage(email+" "+password).create().show();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
                                builder1.setMessage("Login Success").create().show();
                                Intent regisIntent = new Intent(LoginActivity.this, MainActivity.class);
                                regisIntent.putExtra("id_customer", jsonResponse.getInt("id"));
                                regisIntent.putExtra("name_customer", jsonResponse.getString("name"));
                                startActivity(regisIntent);
                            }
                        }
                        catch (JSONException e)
                        {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
                            builder1.setMessage("Login Failed").create().show();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(email,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}

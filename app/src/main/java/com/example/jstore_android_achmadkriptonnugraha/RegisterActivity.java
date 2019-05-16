package com.example.jstore_android_achmadkriptonnugraha;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText emailInput = (EditText) findViewById(R.id.editText);
        final EditText passInput = (EditText) findViewById(R.id.editText2);
        final EditText nameInput = (EditText) findViewById(R.id.editText4);
        final Button loginButton = (Button) findViewById(R.id.button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailInput.getText().toString();
                final String password = passInput.getText().toString();
                final String fullName = nameInput.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null)
                            {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this);
                                builder1.setMessage("Register Success").create().show();
                            }else{
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this);
                                builder1.setMessage("Register Failed").create().show();
                            }
                        }catch (JSONException e)
                        {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this);
                            builder1.setMessage("Register Failed").create().show();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(fullName, email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });

    }
}

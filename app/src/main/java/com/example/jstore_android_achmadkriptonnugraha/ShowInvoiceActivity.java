package com.example.jstore_android_achmadkriptonnugraha;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.PesananBatalRequest;
import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.PesananFetchRequest;
import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.PesananSelesaiRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowInvoiceActivity extends MainInvoiceActivity {

    private ImageView buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonBack = findViewById(R.id.back_button);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
        fetchPesanan(currentUserId);
    }

    @Override
    protected void backToMain() {
        Intent i = new Intent(ShowInvoiceActivity.this, HistoryCustomerActivity.class);
        i.putExtra("id_customer",currentUserId);
        i.putExtra("name_customer", getIntent().getExtras().getString("name_customer"));
        startActivity(i);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_show_invoice);

        buttonBack = findViewById(R.id.back_button);

        currentUserId = getIntent().getExtras().getInt("id_customer");
    }

    @Override
    protected void hideActivity() {
        super.hideActivity();
        buttonBack.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void showActivity() {
        super.showActivity();
        buttonBack.setVisibility(View.VISIBLE);
    }

    @Override
    protected RequestQueue addRequestQueue() {
        return Volley.newRequestQueue(ShowInvoiceActivity.this);
    }

    @Override
    protected void fetchPesanan(int id_customer) {
        super.fetchPesanan(id_customer);

        showActivity();

        invoiceType.setText(getIntent().getExtras().getString("invoiceType"));
        invoiceDate.setText(getIntent().getExtras().getString("invoiceDate"));
        invoiceId.setText(Integer.toString(getIntent().getExtras().getInt("invoiceId")));
        customerName.setText(getIntent().getExtras().getString("name_customer"));

        status.setText(getIntent().getExtras().getString("invoiceStatus"));
        totalPrice.setText(Integer.toString(getIntent().getExtras().getInt("totalPrice")));


        if (getIntent().getExtras().getString("invoiceStatus").equals("Installment")) {
            installmentPeriod.setVisibility(View.VISIBLE);
            textView15.setVisibility(View.VISIBLE);
            installmentPeriod.setText((getIntent().getExtras().getString("installmentPeriod")));
        }else if (getIntent().getExtras().getString("invoiceStatus").equals("Unpaid")) {
            dueDate.setText(getIntent().getExtras().getString("dueDate"));
            dueDate.setVisibility(View.VISIBLE);
            textView14.setVisibility(View.VISIBLE);
        }
    }
}

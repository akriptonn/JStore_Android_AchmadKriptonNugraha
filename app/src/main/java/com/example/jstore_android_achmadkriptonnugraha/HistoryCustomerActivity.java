package com.example.jstore_android_achmadkriptonnugraha;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.HistoryInvoiceRequest;
import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.MenuRequest;
import com.example.jstore_android_achmadkriptonnugraha.model.Invoice;
import com.example.jstore_android_achmadkriptonnugraha.model.Item;
import com.example.jstore_android_achmadkriptonnugraha.model.Location;
import com.example.jstore_android_achmadkriptonnugraha.model.Sell_Installment;
import com.example.jstore_android_achmadkriptonnugraha.model.Sell_Paid;
import com.example.jstore_android_achmadkriptonnugraha.model.Sell_Unpaid;
import com.example.jstore_android_achmadkriptonnugraha.model.Supplier;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HistoryCustomerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InvoiceAdapter adapter;
    private ArrayList<Invoice> invoiceArrayList;
    private ArrayList<Item> itemList;
    private int currentUserId;

    private ImageView buttonBack;
    private TextView historyTulisan;
    private TextView notHistoryTulisan;

    private View.OnClickListener listenerChildRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_customer);

        buttonBack = findViewById(R.id.back_button);
        historyTulisan = findViewById(R.id.tulisan_history);
        notHistoryTulisan = findViewById(R.id.tidak_history);

        buttonBack.setVisibility(View.VISIBLE);
        historyTulisan.setVisibility(View.INVISIBLE);
        notHistoryTulisan.setVisibility(View.INVISIBLE);

        recyclerView = findViewById(R.id.view_recycler);
        currentUserId = getIntent().getExtras().getInt("id_customer");
        invoiceArrayList = new ArrayList<>();

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });

        listenerChildRecycler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryCustomerActivity.this, ShowInvoiceActivity.class);
                i.putExtra("id_customer", currentUserId);
                i.putExtra("name_customer", getIntent().getExtras().getString("name_customer"));
                startActivity(i);
            }
        };

        fetchData();


    }

    protected void fetchData()
    {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    if (response.length()>0) {
                        JSONArray jsonResponse = new JSONArray(response);
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject invoice = jsonResponse.getJSONObject(i);
                            JSONArray arr = invoice.getJSONArray("item");
                            ArrayList<Integer> integerArrayList = new ArrayList<>();

                            for (int j=0;j<arr.length();j++)
                                integerArrayList.add(arr.getInt(j));

                            Invoice invoice1;
                            if (invoice.getString("invoiceStatus").equals("Paid")){
                                 invoice1 = new Sell_Paid(invoice.getInt("id"),integerArrayList, invoice.getString("date"), invoice.getInt("totalPrice"), invoice.getBoolean("isActive"), invoice.getString("invoiceType"), invoice.getString("invoiceStatus"));
                            }else if(invoice.getString("invoiceStatus").equals("Unpaid")){
                                 invoice1 = new Sell_Unpaid(invoice.getInt("id"),integerArrayList, invoice.getString("date"), invoice.getInt("totalPrice"), invoice.getBoolean("isActive"), invoice.getString("invoiceType"), invoice.getString("invoiceStatus"), invoice.getString("dueDate"));
                            }else{
                                 invoice1 = new Sell_Installment(invoice.getInt("id"),integerArrayList, invoice.getString("date"), invoice.getInt("totalPrice"), invoice.getBoolean("isActive"), invoice.getString("invoiceType"), invoice.getString("invoiceStatus"), invoice.getInt("installmentPeriod"), invoice.getInt("installmentPrice"));
                            }
                            invoiceArrayList.add(invoice1);
                        }

                        adapter = new InvoiceAdapter(invoiceArrayList, listenerChildRecycler);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryCustomerActivity.this);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(layoutManager);
                        historyTulisan.setVisibility(View.VISIBLE);
                    }else{
                        notHistoryTulisan.setVisibility(View.VISIBLE);

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(HistoryCustomerActivity.this);
                    builder1.setMessage("Fatal Error").create().show();
                    backToMain();
                }
            }
        };

        HistoryInvoiceRequest m = new HistoryInvoiceRequest(currentUserId,responseListener);
        RequestQueue queue = Volley.newRequestQueue(HistoryCustomerActivity.this);
        queue.add(m);
    }

    private void backToMain(){
        Intent i = new Intent(HistoryCustomerActivity.this, MainActivity.class);
        i.putExtra("id_customer", currentUserId);
        i.putExtra("name_customer", getIntent().getExtras().getString("name_customer"));
        startActivity(i);
    }

}

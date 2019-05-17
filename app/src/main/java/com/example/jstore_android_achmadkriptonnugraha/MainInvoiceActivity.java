package com.example.jstore_android_achmadkriptonnugraha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.ItemFromIDRequest;
import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.PesananBatalRequest;
import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.PesananFetchRequest;
import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.PesananSelesaiRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class MainInvoiceActivity extends AppCompatActivity {

    protected ImageView logo;
    protected ImageView logo2;
    protected TextView textView3 ;
    protected TextView textView7;
    protected TextView textView4 ;
    protected TextView textView9 ;
    protected TextView textView11 ;
    protected TextView textView13 ;
    protected TextView textView14 ;
    protected TextView textView15 ;
    protected TextView textView17 ;

    protected TextView invoiceType ;
    protected TextView invoiceId ;
    protected TextView customerName ;
    protected TextView invoiceDate;
    protected TextView itemName ;
    protected TextView totalPrice;
    protected TextView dueDate;
    protected TextView installmentPeriod ;
    protected TextView status ;

    protected TableRow tableRow;
    protected TableLayout tableLayout;

    protected int currentUserId;

    protected RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();

        textView3 =  (TextView) findViewById(R.id.textView3);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView9 =  (TextView)findViewById(R.id.textView9);
        textView11 = (TextView)findViewById(R.id.textView11);
        textView13 = (TextView)findViewById(R.id.textView13);
        textView14 = (TextView)findViewById(R.id.textView14);
        textView15 = (TextView)findViewById(R.id.textView15);
        textView17 = (TextView)findViewById(R.id.textView17);
        logo = findViewById(R.id.imageView4);
        logo2 = findViewById(R.id.imageView6);

        invoiceType = (TextView)findViewById(R.id.type_invoice);
        invoiceId = (TextView)findViewById(R.id.id_invoice);
        customerName = (TextView)findViewById(R.id.name_customer);
        invoiceDate = (TextView)findViewById(R.id.date_invoice);
        itemName = (TextView)findViewById(R.id.name_item);
        totalPrice = (TextView)findViewById(R.id.price_total);
        dueDate = (TextView)findViewById(R.id.date_due);
        installmentPeriod = (TextView)findViewById(R.id.period_installment);
        status = (TextView)findViewById(R.id.status_invoice);
        tableRow = (TableRow) findViewById(R.id.tableRow);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        hideActivity();
        queue = addRequestQueue();

    }

    protected void fetchPesanan(int id_customer)
    {

        Response.Listener<String> responseListener2 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject item = new JSONObject(response);

                    itemName.setText(item.getString("name"));
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };

        ItemFromIDRequest it = new ItemFromIDRequest(id_customer, responseListener2);
        queue.add(it);

    }

    abstract void backToMain();

    protected void hideActivity(){
        textView3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        textView7.setVisibility(View.INVISIBLE);
        textView9.setVisibility(View.INVISIBLE);
        textView11.setVisibility(View.INVISIBLE);
        textView13.setVisibility(View.INVISIBLE);
        textView14.setVisibility(View.INVISIBLE);
        textView15.setVisibility(View.INVISIBLE);
        textView17.setVisibility(View.INVISIBLE);
        invoiceType.setVisibility(View.INVISIBLE);
        invoiceId.setVisibility(View.INVISIBLE);
        customerName.setVisibility(View.INVISIBLE);
        invoiceDate.setVisibility(View.INVISIBLE);
        itemName.setVisibility(View.INVISIBLE);
        totalPrice.setVisibility(View.INVISIBLE);
        dueDate.setVisibility(View.INVISIBLE);
        installmentPeriod.setVisibility(View.INVISIBLE);
        status.setVisibility(View.INVISIBLE);
        tableLayout.setVisibility(View.INVISIBLE);
        tableRow.setVisibility(View.INVISIBLE);
        logo.setVisibility(View.INVISIBLE);
        logo2.setVisibility(View.INVISIBLE);
    }

    protected void showActivity(){
        textView3.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.VISIBLE);
        textView7.setVisibility(View.VISIBLE);
        textView9.setVisibility(View.VISIBLE);
        textView11.setVisibility(View.VISIBLE);
        textView13.setVisibility(View.VISIBLE);


        textView17.setVisibility(View.VISIBLE);
        invoiceType.setVisibility(View.VISIBLE);
        invoiceId.setVisibility(View.VISIBLE);
        customerName.setVisibility(View.VISIBLE);
        invoiceDate.setVisibility(View.VISIBLE);
        itemName.setVisibility(View.VISIBLE);
        totalPrice.setVisibility(View.VISIBLE);
        tableRow.setVisibility(View.VISIBLE);
        logo.setVisibility(View.VISIBLE);
        logo2.setVisibility(View.VISIBLE);

        status.setVisibility(View.VISIBLE);
        tableLayout.setVisibility(View.VISIBLE);
    }

    abstract void setLayout();

    abstract RequestQueue addRequestQueue();
}

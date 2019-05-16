package com.example.jstore_android_achmadkriptonnugraha;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SelesaiPesananActivity extends AppCompatActivity {

    private ImageView cancelTransaction;
    private ImageView finishTransaction ;
    private ImageView logo;
    private ImageView logo2;
    private TextView textView3 ;
    private TextView textView7;
    private TextView textView4 ;
    private TextView textView9 ;
    private TextView textView11 ;
    private TextView textView13 ;
    private TextView textView14 ;
    private TextView textView15 ;
    private TextView textView17 ;

    private TextView invoiceType ;
    private TextView invoiceId ;
    private TextView customerName ;
    private TextView invoiceDate;
    private TextView itemName ;
    private TextView totalPrice;
    private TextView dueDate;
    private TextView installmentPeriod ;
    private TextView status ;

    private TableRow tableRow;
    private TableLayout tableLayout;

    private int currentUserId;
    private String itemNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);

          cancelTransaction = (ImageView) findViewById(R.id.cancel_button);
          finishTransaction = (ImageView) findViewById(R.id.finish_button);
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

        cancelTransaction.setVisibility(View.INVISIBLE);
        finishTransaction.setVisibility(View.INVISIBLE);
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
        currentUserId = getIntent().getExtras().getInt("id_customer");


        fetchPesanan(currentUserId);

        cancelTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                                if (response!=null){
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                    builder1.setMessage("Success to cancel").create().show();
                                    backToMain();
                                }else{
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                    builder1.setMessage("Fail to cancel").create().show();
                                }


                        }catch (Exception e){

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder1.setMessage("Fatal Error").create().show();

                        }
                    }
                };

                PesananBatalRequest bp = new PesananBatalRequest(Integer.parseInt(invoiceId.getText().toString()),responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(bp);
            }
        });

        finishTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{

//                            JSONObject cancelInvoice = new JSONObject(response);
                            if (response!=null){
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder1.setMessage("Success to finish").create().show();
                                backToMain();
                            }else{
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder1.setMessage("Fail to finish").create().show();
                            }


                        }catch (Exception e){

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder1.setMessage("Fatal Error").create().show();

                        }
                    }
                };

                PesananSelesaiRequest bp = new PesananSelesaiRequest(Integer.parseInt(invoiceId.getText().toString()),responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(bp);
            }
        });

    }

    private void fetchPesanan(int id_customer)
    {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length()>0) {
                    try {
                        JSONArray jsonResponse = new JSONArray(response);
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject invoice = jsonResponse.getJSONObject(i);
                            JSONObject customer = invoice.getJSONObject("customer");
                            cancelTransaction.setVisibility(View.VISIBLE);
                            finishTransaction.setVisibility(View.VISIBLE);
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

                            invoiceType.setText(invoice.getString("invoiceType"));
                            invoiceDate.setText(invoice.getString("date"));
                            invoiceId.setText(Integer.toString(invoice.getInt("id")));
                            customerName.setText(customer.getString("name"));

                            status.setText(invoice.getString("invoiceStatus"));
                            totalPrice.setText(invoice.getString("totalPrice"));

                            try {
                                if (invoice.getString("installmentPeriod").length() > 0) {
                                    installmentPeriod.setVisibility(View.VISIBLE);
                                    textView15.setVisibility(View.VISIBLE);
                                    installmentPeriod.setText(invoice.getString("installmentPeriod"));
                                }
                            } catch (Exception e) {

                            }
                            dueDate.setText(invoice.getString("dueDate"));
                            dueDate.setVisibility(View.VISIBLE);
                            textView14.setVisibility(View.VISIBLE);
                        }


                    } catch (JSONException e) {

                    }
                }else{
                    backToMain();

                }
            }
        };

        PesananFetchRequest m = new PesananFetchRequest(id_customer, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);


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
        queue.add(m);
    }

    private void backToMain(){
        Intent i = new Intent(SelesaiPesananActivity.this, MainActivity.class);
        i.putExtra("id_customer",currentUserId);
        startActivity(i);
    }
}

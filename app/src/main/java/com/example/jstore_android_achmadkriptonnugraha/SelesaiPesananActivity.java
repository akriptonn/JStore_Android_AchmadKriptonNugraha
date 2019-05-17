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

public class SelesaiPesananActivity extends MainInvoiceActivity {

    private ImageView cancelTransaction;
    private ImageView finishTransaction ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        fetchPesanan(currentUserId);

    }

    @Override
    protected void fetchPesanan(int id_customer) {
        super.fetchPesanan(id_customer);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length()>0) {
                    try {
                        JSONArray jsonResponse = new JSONArray(response);
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject invoice = jsonResponse.getJSONObject(i);
                            JSONObject customer = invoice.getJSONObject("customer");

                            showActivity();

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
                                e.printStackTrace();
                            }
                            dueDate.setText(invoice.getString("dueDate"));
                            dueDate.setVisibility(View.VISIBLE);
                            textView14.setVisibility(View.VISIBLE);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    backToMain();
                }
            }
        };

        PesananFetchRequest m = new PesananFetchRequest(id_customer, responseListener);
        queue.add(m);
    }

    @Override
    protected void backToMain() {
        Intent i = new Intent(SelesaiPesananActivity.this, MainActivity.class);
        i.putExtra("id_customer",currentUserId);
        i.putExtra("name_customer", getIntent().getExtras().getString("name_customer"));
        startActivity(i);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_selesai_pesanan);

        cancelTransaction = (ImageView) findViewById(R.id.cancel_button);
        finishTransaction = (ImageView) findViewById(R.id.finish_button);

        currentUserId = getIntent().getExtras().getInt("id_customer");
    }

    @Override
    protected void hideActivity() {
        super.hideActivity();
        cancelTransaction.setVisibility(View.INVISIBLE);
        finishTransaction.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void showActivity() {
        super.showActivity();
        cancelTransaction.setVisibility(View.VISIBLE);
        finishTransaction.setVisibility(View.VISIBLE);
    }

    @Override
    protected RequestQueue addRequestQueue() {
        return Volley.newRequestQueue(SelesaiPesananActivity.this);
    }
}

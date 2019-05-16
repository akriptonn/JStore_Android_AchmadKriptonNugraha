package com.example.jstore_android_achmadkriptonnugraha;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.BuatPesananRequest;
import com.example.jstore_android_achmadkriptonnugraha.model.Item;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

public class BuatPesananActivity extends AppCompatActivity {

    private int currentUserId, itemId, installmentPeriod;
    private double itemPrice;
    private String itemName,itemCategory,itemStatus,selectedPayment;
    private BuatPesananRequest bp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);
        currentUserId = getIntent().getExtras().getInt("id_customer");
        Gson gson = new Gson();
        Item item = gson.fromJson(getIntent().getExtras().getString("Item"), Item.class);
        itemId = item.getId();
        itemPrice = item.getPrice();
        itemName = item.getName();
        itemCategory = item.getCategory();
        itemStatus = item.getStatus();
        TextView item_name = (TextView)findViewById(R.id.item_name);
        TextView item_category = (TextView)findViewById(R.id.item_category);
        TextView item_status = (TextView)findViewById(R.id.item_status);
        final TextView item_price = (TextView)findViewById(R.id.item_price);
        final TextView total_price = (TextView)findViewById(R.id.total_price);
        final TextView textPeriod = (TextView)findViewById(R.id.textPeriod);
        final Button hitung = (Button) findViewById(R.id.hitung);
        final Button pesan = (Button) findViewById(R.id.pesan);
        final EditText period = (EditText) findViewById(R.id.installment_period);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton paid = (RadioButton) findViewById(R.id.paid);
        RadioButton unpaid = (RadioButton) findViewById(R.id.unpaid);
        RadioButton installment = (RadioButton) findViewById(R.id.installment);

        pesan.setVisibility(View.INVISIBLE);
        textPeriod.setVisibility(View.INVISIBLE);
        period.setVisibility(View.INVISIBLE);
        total_price.setText("0");
        item_name.setText(itemName);
        item_category.setText(itemCategory);
        item_status.setText(itemStatus);
        item_price.setText(Double.toString(itemPrice));

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId= rg.getCheckedRadioButtonId();
                RadioButton chosen = (RadioButton) findViewById(checkedId);
                selectedPayment = chosen.getText().toString();
                Log.d("id", selectedPayment);
                if (selectedPayment.equals("Installment")){
                    textPeriod.setVisibility(View.VISIBLE);
                    period.setVisibility(View.VISIBLE);
                }else{
                    textPeriod.setVisibility(View.INVISIBLE);
                    period.setVisibility(View.INVISIBLE);
                }
            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if ((selectedPayment.equals("Paid Later")) || (selectedPayment.equals("Pay Now"))) {

                        total_price.setText(Double.toString(itemPrice));
                    } else if (selectedPayment.equals("Installment") && (!(TextUtils.isEmpty(period.getText().toString())))) {
                        int durationInstall = Integer.parseInt(period.getText().toString());
                        double tprice = itemPrice * 1.00 / durationInstall;
                        total_price.setText(Double.toString(tprice));
                        installmentPeriod = Integer.parseInt(period.getText().toString());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    hitung.setVisibility(View.INVISIBLE);
                    pesan.setVisibility(View.VISIBLE);
                }

            }
        });




        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder b1 = new AlertDialog.Builder(BuatPesananActivity.this);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                b1.setMessage("Berhasil di pesan!").create().show();
                            }
                            else {
                                b1.setMessage("Ada masalah, silahkan coba lagi!").create().show();
                            }

                        } catch (JSONException e) {
                            b1.setMessage("Fatal Error").create().show();
                        }
                    }
                };

                if (selectedPayment.equals("Paid Later") || selectedPayment.equals("Pay Now")){
                    bp = BuatPesananRequest.staticBuatPesananRequest(currentUserId, itemId, responseListener, selectedPayment);
                }
                else{
                    bp = BuatPesananRequest.staticBuatPesananRequest(currentUserId, itemId, responseListener, selectedPayment, installmentPeriod);
                }
//                final Location lokasi = new Location("Jawa Barat","Sebuah kota","Depok");
//                final Supplier sup = new Supplier(1,"bambang","bambang@gmail.com","081222222222",lokasi);
//                final Item barang = new Item(1,"ian tidur",1000000,"Stationery","New",sup);

                RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                queue.add(bp);
            }


        });
    }


}

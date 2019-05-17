package com.example.jstore_android_achmadkriptonnugraha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.*;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import com.example.jstore_android_achmadkriptonnugraha.RequestActivity.MenuRequest;
import com.example.jstore_android_achmadkriptonnugraha.model.Item;
import com.example.jstore_android_achmadkriptonnugraha.model.Location;
import com.example.jstore_android_achmadkriptonnugraha.model.Supplier;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.View;
import android.widget.ExpandableListView.OnChildClickListener;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    int currentUserId;
    private ArrayList<Supplier> listSupplier = new ArrayList<>();
    private ArrayList<Item> listItem = new ArrayList<>();
    private HashMap<Supplier, ArrayList<Item>> childMapping = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentUserId = getIntent().getExtras().getInt("id_customer");
        final ImageView pesanan =  findViewById(R.id.pesanan);
        final ImageView history = findViewById(R.id.history);

        pesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SelesaiPesananActivity.class);
                i.putExtra("id_customer", currentUserId);
                i.putExtra("name_customer", getIntent().getExtras().getString("name_customer"));
                startActivity(i);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HistoryCustomerActivity.class);
                i.putExtra("id_customer", currentUserId);
                i.putExtra("name_customer", getIntent().getExtras().getString("name_customer"));
                startActivity(i);
            }
        });
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        // preparing list data
        refreshList();
        // setting list adapter
        expListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Item selected = childMapping.get(listSupplier.get(groupPosition)).get(childPosition);
                Intent i = new Intent(MainActivity.this, BuatPesananActivity.class);
                i.putExtra("id_customer", currentUserId);
                Gson gson = new Gson();
                i.putExtra("Item", gson.toJson(selected, Item.class));
                i.putExtra("name_customer", getIntent().getExtras().getString("name_customer"));
                startActivity(i);
                return true;
            }
        });


    }


    protected void refreshList(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonResponse = new JSONArray(response);
                    for (int i=0; i<jsonResponse.length();i++)
                    {
                        JSONObject item = jsonResponse.getJSONObject(i);
                        JSONObject supplier = item.getJSONObject("supplier");
                        JSONObject location = supplier.getJSONObject("location");
                        Location l = new Location(location.getString("province"), location.getString("description"), location.getString("city"));
                        Supplier s = new Supplier(supplier.getInt("id"),supplier.getString("name"), supplier.getString("email"), supplier.getString("phoneNumber"), l );
                        Item it = new Item(item.getInt("id"), item.getString("name"), item.getInt("price"), item.getString("category"), item.getString("status"), s);
                        listItem.add(it);
                        if (CollectionFunction.testSupplier(s, listSupplier))
                            listSupplier.add(s);
                    }

                    CollectionFunction.createChild(listSupplier, listItem, childMapping);
                    listAdapter = new MainListAdapter(MainActivity.this, listSupplier, childMapping);

                    expListView.setAdapter(listAdapter);
                }catch (JSONException e){

                }
            }
        };

        MenuRequest m = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(m);

    }


}

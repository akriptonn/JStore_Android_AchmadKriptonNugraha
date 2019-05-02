package com.example.jstore_android_achmadkriptonnugraha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    private ArrayList<Supplier> listSupplier = new ArrayList<>();
    private ArrayList<Item> listItem = new ArrayList<>();
    private HashMap<Supplier, ArrayList<Item>> childMapping = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        refreshList();


        listAdapter = new MainListAdapter(this, listSupplier, childMapping);

        // setting list adapter
        expListView.setAdapter(listAdapter);
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
                        for (Supplier rr:listSupplier
                        ) {
                            if (rr.equals(s))
                                continue;
                        }
                        listSupplier.add(s);

                    }

                    for (Supplier rr:listSupplier
                    ) {
                        ArrayList<Item> temp = new ArrayList<>();
                        for (Item ii:listItem
                             ) {
                            if (ii.getSupplier().equals(rr))
                                temp.add(ii);
                        }
                        childMapping.put(rr, temp);
                    }
                }catch (JSONException e){

                }
            }
        };

        MenuRequest m = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(m);

    }


}

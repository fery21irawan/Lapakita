package asshohabah_borneo.cv.lapaksampit.Data.Kategori;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import asshohabah_borneo.cv.lapaksampit.NavBottom.Timeline.Adapter;
import asshohabah_borneo.cv.lapaksampit.NavBottom.Timeline.Model;
import asshohabah_borneo.cv.lapaksampit.R;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;

/*
 * Created by Fery Irawan on 10/10/18 2:52 PM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/10/18 2:52 PM
 */

public class KategoriActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    Adapter adapter;
    SwipeRefreshLayout swipe;
    List<Model> modelList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        recyclerView = findViewById(R.id.recylcerView);

        layoutManager = new GridLayoutManager(this, 3);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new Adapter(this, modelList);

        recyclerView.setAdapter(adapter);

        swipe = findViewById(R.id.swlayout);
        swipe.setVisibility(View.VISIBLE);
        swipe.setOnRefreshListener(this);

       // loadData();

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           loadData();
                           modelList.clear();
                           adapter.notifyDataSetChanged();
                       }
                   }
        );
    }

    @Override
    public void onRefresh() {
        modelList.clear();
        adapter.notifyDataSetChanged();
        loadData();
    }

    private void loadData() {
        intent = getIntent();
        final String KD_KATEGORI = intent.getStringExtra(Endpoints.Kategori_KD);
        final String url = Endpoints.Produk_URL+"?kd_kategori="+KD_KATEGORI;
        Log.d("URL", url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject jo = array.getJSONObject(i);
                                modelList.add(new Model(
                                        jo.getString(Endpoints.Produk_ID),
                                        jo.getString(Endpoints.Produk_KD),
                                        jo.getString(Endpoints.Kategori_KD),
                                        jo.getString(Endpoints.Pengguna_KD),
                                        jo.getString(Endpoints.Pengguna_NM),
                                        jo.getString(Endpoints.Pengguna_Alamat),
                                        jo.getString(Endpoints.Pengguna_No_Telp),
                                        jo.getString(Endpoints.Pengguna_No_WA),
                                        jo.getString(Endpoints.Kategori_NM),
                                        jo.getString(Endpoints.Produk_NM),
                                        jo.getString(Endpoints.Produk_GBR),
                                        jo.getString(Endpoints.Produk_Keterangan),
                                        jo.getString(Endpoints.Produk_Harga)
                                ));
                                Log.d("lihat", jo.getString(Endpoints.Produk_GBR));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();

                        swipe.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
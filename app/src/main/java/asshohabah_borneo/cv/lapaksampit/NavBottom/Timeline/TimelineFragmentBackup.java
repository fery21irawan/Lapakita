package asshohabah_borneo.cv.lapaksampit.NavBottom.Timeline;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import asshohabah_borneo.cv.lapaksampit.Helper.OnLoadMoreListener;
import asshohabah_borneo.cv.lapaksampit.R;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;


/*
 * Created by Fery Irawan on 10/11/18 12:11 PM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/11/18 12:09 PM
 */

public class TimelineFragmentBackup extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    View view;
    RecyclerView recyclerView;
    DataAdapter adapter;
    SwipeRefreshLayout swipe;
    List<Model> modelList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    protected Handler handler;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timeline, container, false);

        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.recylcerView);

        layoutManager = new GridLayoutManager(getContext().getApplicationContext(),3);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new DataAdapter(this.getContext(),modelList, recyclerView);

        recyclerView.setAdapter(adapter);
        handler = new Handler();
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void OnLoadMore() {
                modelList.add(null);
                adapter.notifyItemInserted(modelList.size() - 1);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //   remove progress item
                        modelList.remove(modelList.size() - 1);
                        adapter.notifyItemRemoved(modelList.size());
                        //add items one by one
                        int start = modelList.size();
                        int end = start + 20;

                        for (int i = start + 1; i <= end; i++) {
                            //
                            adapter.notifyItemInserted(modelList.size());
                        }
                        adapter.setLoaded();
                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
                    }
                }, 2000);

            }
        });
        swipe   = (SwipeRefreshLayout) view.findViewById(R.id.swlayout);
        swipe.setVisibility(View.VISIBLE);
        swipe.setOnRefreshListener(this);

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

        return view;
    }
    @Override
    public void onRefresh() {
        modelList.clear();
        adapter.notifyDataSetChanged();
        loadData();
    }
    private void loadData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.Produk_URL,
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

        Volley.newRequestQueue(this.getActivity()).add(stringRequest);
    }
}

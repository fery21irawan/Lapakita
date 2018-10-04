package asshohabah_borneo.cv.lapaksampit.Timeline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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

import asshohabah_borneo.cv.lapaksampit.Helper.Utility;
import asshohabah_borneo.cv.lapaksampit.R;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;


public class TimelineFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    View view;
    RecyclerView recyclerView;
    Adapter adapter;
    SwipeRefreshLayout swipe;
    List<Model> modelList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timeline, container, false);

        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.recylcerView);

        layoutManager = new GridLayoutManager(getContext().getApplicationContext(),3);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new Adapter(this.getContext(),modelList);

        recyclerView.setAdapter(adapter);

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

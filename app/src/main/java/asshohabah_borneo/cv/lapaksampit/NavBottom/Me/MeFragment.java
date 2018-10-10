package asshohabah_borneo.cv.lapaksampit.NavBottom.Me;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.ImageView;
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

import asshohabah_borneo.cv.lapaksampit.MainActivity;
import asshohabah_borneo.cv.lapaksampit.R;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;


public class MeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    View view;
    RecyclerView recyclerView;
    Adapter adapter;
    Button btnCall, btnChat, btnSettings;
    SharedPreferences sharedPreferences;
    ImageView p_image;
    TextView p_nama, p_alamat;

    SwipeRefreshLayout swipe;
    List<Model> modelList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    /**
     * Membuat Fungsi setting data untuk sharedpreferences
     * @param data
     * @return
     */
    private String setPref(String data){
        return sharedPreferences.getString(data,"");
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, container, false);
        p_image = view.findViewById(R.id.p_image);
        p_nama  = view.findViewById(R.id.p_nama);
        p_alamat = view.findViewById(R.id.p_alamat);
        sharedPreferences = getActivity().getSharedPreferences(Endpoints.SharedPref_Nama, Context.MODE_PRIVATE);
        btnCall = view.findViewById(R.id.btnCall);
        btnChat = view.findViewById(R.id.btnChat);
        btnSettings = view.findViewById(R.id.btnSettings);
        btnCall.setText(sharedPreferences.getString(Endpoints.SharedPref_No_Telp, ""));
        btnSettings.setVisibility(View.VISIBLE);
        btnSettings.setText("Edit Profile");
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Fetch data yang di sharedpreferences
         */

        p_nama.setText(setPref(Endpoints.SharedPref_NM));
        p_alamat.setText(setPref(Endpoints.SharedPref_Alamat));

       /* if(UserId == SharedUserId){
            btnSettings.setVisibility(View.VISIBLE);
        }
        if(UserId != SharedUserId){
            btnCall.setVisibility(View.VISIBLE);
            btnChat.setVisibility(View.VISIBLE);
        }*/


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
        final String url = Endpoints.Produk_URL+"?kd_pengguna="+setPref(Endpoints.SharedPref_KD);
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
                                Log.d("my_URL", url);
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

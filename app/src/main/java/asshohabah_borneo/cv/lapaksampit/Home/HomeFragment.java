package asshohabah_borneo.cv.lapaksampit.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

import asshohabah_borneo.cv.lapaksampit.R;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;

import static asshohabah_borneo.cv.lapaksampit.Home.InitializeHomeAdapter.Special;
import static asshohabah_borneo.cv.lapaksampit.Home.InitializeHomeAdapter.getModel;
import static asshohabah_borneo.cv.lapaksampit.Home.InitializeHomeAdapter.getModelPopuler;


public class HomeFragment extends Fragment {
    View view;
    private RecyclerView recyclerView, recyclerViewSpecial, recyclerViewPopuler ;
    Adapter adapter;
    AdapterSpecial adapterSpecial;
    AdapterPopuler adapterPopuler;
    List<ModelSpecial> Special = new ArrayList<>();
    //  Model model;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerViewSpecial = view.findViewById(R.id.recyclerview_special);
        recyclerViewPopuler = view.findViewById(R.id.recyclerview_populer);
        //Setting Adapter
        adapter = new Adapter(getActivity(), getModel());
        adapterSpecial = new AdapterSpecial(getActivity(), Special);
        adapterPopuler = new AdapterPopuler(getActivity(), getModelPopuler());

        recyclerView.setAdapter(adapter);
        recyclerViewSpecial.setAdapter(adapterSpecial);
        recyclerViewPopuler.setAdapter(adapterPopuler);
        recyclerView.setHasFixedSize(true);
        recyclerViewSpecial.setHasFixedSize(true);
        recyclerViewPopuler.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext().getApplicationContext(), 4);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext().getApplicationContext(), 3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerViewSpecial.setLayoutManager(gridLayoutManager2);
        recyclerViewPopuler.setLayoutManager(linearLayoutManager);
        getSpecial();

        /*sliderImg = new ArrayList<>();

        viewPager = view.findViewById(R.id.viewPager);

        indicator = view.findViewById(R.id.indicator);

        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new MenuAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext().getApplicationContext(), 3);
        //gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(gridLayoutManager);


        sendRequest();
*/
        return view;
    }
    private void getSpecial(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.Produk_Special_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject jo = array.getJSONObject(i);
                                ModelSpecial item = new ModelSpecial();
                                item.setGbr_produk(jo.getString(Endpoints.Produk_GBR));
                                item.setNm_produk(jo.getString(Endpoints.Produk_NM));
                                Special.add(item);
                                Log.d("lihat", jo.getString(Endpoints.Produk_GBR));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterSpecial.notifyDataSetChanged();

                     //   swipe.setRefreshing(false);
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

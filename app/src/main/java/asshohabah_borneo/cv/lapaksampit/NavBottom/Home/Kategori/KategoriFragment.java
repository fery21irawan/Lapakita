package asshohabah_borneo.cv.lapaksampit.NavBottom.Home.Kategori;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import asshohabah_borneo.cv.lapaksampit.R;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;
import asshohabah_borneo.cv.lapaksampit.Server.RequestHandler;

public class KategoriFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    AdapterKategori adapter;
    List<ModelKategori> modelList = new ArrayList<>();
    private String JSON_STRING;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kategori, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);

        //Setting AdapterKategori
        adapter = new AdapterKategori(getActivity(), modelList);

        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext().getApplicationContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        getJSON();
        return view;
    }
    private void FetchKategori() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Endpoints.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Endpoints.Kategori_KD);
                String nama = jo.getString(Endpoints.Kategori_NM);
                Log.d("URLNYA KTG", id);
                ModelKategori model = new ModelKategori();
                model.setKode(id);
                model.setNama(nama);
                modelList.add(model);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(KategoriFragment.this.getContext(), "Mengambil Data", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                FetchKategori();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Endpoints.Kategori_URL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();

    }

}

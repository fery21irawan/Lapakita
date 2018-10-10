package asshohabah_borneo.cv.lapaksampit.NavBottom.Jual;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import asshohabah_borneo.cv.lapaksampit.R;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;
import asshohabah_borneo.cv.lapaksampit.Server.RequestHandler;

public class GetKategoriFragment extends DialogFragment implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener {
    ListView listView;
    private String JSON_STRING;
    SearchView sv;
    ListAdapter adapter;

    public GetKategoriFragment() {
        // Constructor kosong diperlukan untuk DialogFragment.
        // Pastikan tidak memberikan argument/parameter apapun ke
        // constructor ini.
    }

    /*public static GetKategoriFragment newInstance(String title) {
        GetKategoriFragment frag = new GetKategoriFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_kategori_view, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * Remove Background
         */
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
        /**
         * Baca view yang dibuat di XML
         */
        sv      = view.findViewById(R.id.searchView1);
        sv.setQueryHint("Search..");
        sv.setOnQueryTextListener(this);
        listView = view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        ((SimpleAdapter)GetKategoriFragment.this.adapter).getFilter().filter(text);
        return false;
    }

    private void FetchKategori() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Endpoints.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Endpoints.Kategori_KD);
                String nama = jo.getString(Endpoints.Kategori_NM);

                HashMap<String, String> pelanggan = new HashMap<>();
                pelanggan.put(Endpoints.Kategori_KD, id);
                pelanggan.put(Endpoints.Kategori_NM, nama);
                list.add(pelanggan);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new SimpleAdapter(
                GetKategoriFragment.this.getContext(), list, R.layout.custom_view_kategori,
                new String[]{Endpoints.Kategori_KD, Endpoints.Kategori_NM},
                new int[]{R.id.kd, R.id.nm});

        listView.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GetKategoriFragment.this.getContext(), "Mengambil Data", "Wait...", false, false);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String kd = map.get(Endpoints.Kategori_KD).toString();
        String nm = map.get(Endpoints.Kategori_NM).toString();
        JualActivity.kategori.setText(nm);
        JualActivity.i_kategori.setText(kd);
        GetKategoriFragment.super.dismiss();
    }

}

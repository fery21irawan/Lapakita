package asshohabah_borneo.cv.lapaksampit.Home;

import android.util.Log;

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

public class InitializeHomeAdapter {
    public static List<ModelSpecial> Special = new ArrayList<>();
    AdapterSpecial adapterSpecial;
    public static List<Model> getModel() {
        List<Model> model = new ArrayList<>();
        Integer image[] = {
                R.drawable.icon_fashion_man,
                R.drawable.icon_laptop,
                R.drawable.icon_rumah,
                R.drawable.icon_otomotif,
                R.drawable.icon_sepakbola,
                R.drawable.icon_minuman,
                R.drawable.icon_kado,
                R.drawable.icon_gaming
        };
        String name[] = {
                "Fashion",
                "Elektronik & Aksesoris",
                "Perabotan",
                "Otomatif",
                "Olahraga",
                "Makanan & Minuman",
                "Souvenir",
                "Mainan"
        };

        for (int i = 0; i < image.length && i < name.length; i++) {
            Model current = new Model();
            current.image = image[i];
            current.name = name[i];
            model.add(current);
        }
        return model;
    }

    public static List<ModelPopuler> getModelPopuler() {
        List<ModelPopuler> modelPopuler = new ArrayList<>();
        Integer image[] = {
                R.drawable.sepatu,
                R.drawable.sepatu,
                R.drawable.sepatu,
                R.drawable.sepatu,
                R.drawable.sepatu,
                R.drawable.sepatu
        };
        String name[] = {
                "Fashion",
                "Elektronik & Aksesoris",
                "Perabotan",
                "Otomatif",
                "Olahraga",
                "Makanan & Minuman",
                "Souvenir",
                "Mainan"
        };
        int harga[] = {
                30000,
                30000,
                30000,
                30000,
                30000,
                30000,
                30000,
                30000
        };

        for (int i = 0; i < image.length && i < name.length && i < harga.length; i++) {
            ModelPopuler current = new ModelPopuler();
            current.image = image[i];
            current.name = name[i];
            current.harga = Double.valueOf(harga[i]);
            modelPopuler.add(current);
        }
        return modelPopuler;
    }
}

package asshohabah_borneo.cv.lapaksampit.NavBottom.Home;

import java.util.ArrayList;
import java.util.List;

import asshohabah_borneo.cv.lapaksampit.NavBottom.Home.Kategori.ModelKategori;
import asshohabah_borneo.cv.lapaksampit.NavBottom.Home.Populer.ModelPopuler;
import asshohabah_borneo.cv.lapaksampit.R;

public class InitializeHomeAdapter {
    /*public static List<ModelSpecial> Special = new ArrayList<>();
    AdapterSpecial adapterSpecial;*/
    /*public static List<ModelKategori> getModel() {
        List<ModelKategori> model = new ArrayList<>();
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
        String kd[] = {
                "KTG003",
                "Elektronik & Aksesoris",
                "KTG005",
                "KTG011",
                "KTG009",
                "KTG004",
                "Souvenir",
                "KTG001"
        };

        for (int i = 0; i < image.length && i < name.length && i < kd.length; i++) {
            ModelKategori current = new ModelKategori();
            current.image = image[i];
            current.name = name[i];
            current.kode = kd[i];
            model.add(current);
        }
        return model;
    }*/

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

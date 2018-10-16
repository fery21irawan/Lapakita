package asshohabah_borneo.cv.lapaksampit.NavBottom.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import asshohabah_borneo.cv.lapaksampit.NavBottom.Home.Kategori.AdapterKategori;
import asshohabah_borneo.cv.lapaksampit.NavBottom.Home.Kategori.KategoriFragment;
import asshohabah_borneo.cv.lapaksampit.R;



public class HomeFragmentV2 extends Fragment {
    View view;
    RelativeLayout layout_kategori;
    FragmentManager mManager = getActivity().getSupportFragmentManager();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_v2, container, false);
        //layout_kategori = view.findViewById(R.id.layout_kategori);
        mManager.beginTransaction().replace(
                R.id.layout_kategori,
                new KategoriFragment()).commit();

        return view;
    }
}

package asshohabah_borneo.cv.lapaksampit.NavBottom.Lainnya;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import asshohabah_borneo.cv.lapaksampit.R;

/*
 * Created by Fery Irawan on 10/9/18 3:30 PM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/9/18 3:30 PM
 */
public class MoreFragment extends Fragment{
    private RecyclerView recyclerView;
    private Adapter adapter;

    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lainnya, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        adapter = new Adapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    public static List<Model> getData() {
        List<Model> data = new ArrayList<>();
        int[] icons = {
                R.drawable.icon_gaming,
                R.drawable.icon_kado,
                R.drawable.icon_laptop,
                R.drawable.icon_fashion_man
        };
        String[] titles = {
                "Tentang Kami",
                "Keluhan & Saran",
                "Bagikan Aplikasi",
                "Logout"
        };

        for (int i = 0; i < titles.length && i < icons.length; i++) {
            Model current = new Model();
            current.iconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }



}

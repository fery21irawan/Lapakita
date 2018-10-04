package asshohabah_borneo.cv.lapaksampit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import asshohabah_borneo.cv.lapaksampit.Home.Adapter;
import asshohabah_borneo.cv.lapaksampit.Home.Model;


public class HomeFragment extends Fragment {
    View view;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_v2, container, false);
        /*mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        // recyclerview set layoutmanager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //adding data to arraylist
        feedItemList = new ArrayList<GetterSetter>();
        for (int i = 0; i < 20; i++) {
            GetterSetter getterSetter = new GetterSetter();
            feedItemList.add(getterSetter);
        }

        //recyclerview adapter
        mAdapter = new RecyclerAdapter(getActivity(), feedItemList);

        //set adpater for recyclerview
        mRecyclerView.setAdapter(mAdapter);*/
        return view;
    }

}

package asshohabah_borneo.cv.lapaksampit.NavBottom.Timeline;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import asshohabah_borneo.cv.lapaksampit.Helper.OnLoadMoreListener;
import asshohabah_borneo.cv.lapaksampit.R;

/*
 * Created by Fery Irawan on 10/11/18 8:21 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/11/18 6:15 AM
 */

public class DataAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<Model> modelList;
    private Context mCtx;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;



    public DataAdapter(Context mCtx, List<Model> modelList, RecyclerView recyclerView) {
        this.mCtx = mCtx;
        modelList = modelList;

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {

            final GridLayoutManager linearLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.OnLoadMore();
                                }
                                loading = true;
                            }
                        }
                    });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return modelList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        if (viewType == VIEW_ITEM) {
            View v = inflater.inflate(R.layout.timeline_view_grid, null);
            vh = new StudentViewHolder(v);
        } else {
            View v = inflater.inflate(R.layout.progress_item, null);
            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StudentViewHolder) {

            Model GetModel= modelList.get(position);
            final String Image = GetModel.getGbr_produk();
            YoYo.with(Techniques.FadeInRight)
                    .playOn(holder.itemView);
            Glide.with(mCtx)
                    .load(Image)
                    .into(((StudentViewHolder) holder).Gambar);

            /*((StudentViewHolder) holder).Gambar= GetModel;*/

        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return modelList == null ? 0 : modelList.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


    //
    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        ImageView Gambar;

        public StudentViewHolder(View v) {
            super(v);
            Gambar = itemView.findViewById(R.id.image);

            /*v.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),
                            "OnClick :" + student.getName() + " \n "+student.getEmailId(),
                            Toast.LENGTH_SHORT).show();

                }
            });*/
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }
}
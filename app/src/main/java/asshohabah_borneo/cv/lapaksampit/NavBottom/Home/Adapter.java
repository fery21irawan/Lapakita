package asshohabah_borneo.cv.lapaksampit.NavBottom.Home;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import asshohabah_borneo.cv.lapaksampit.Data.Kategori.KategoriActivity;
import asshohabah_borneo.cv.lapaksampit.R;


/**
 * Created by Belal on 10/18/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    private Context mCtx;
    private List<Model> modelList;

    public Adapter(Context mCtx, List<Model> modelList) {
        this.mCtx = mCtx;
        this.modelList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.grid_home_view_category, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Model GetModel = modelList.get(position);
        final int Image = GetModel.image;
        final String Name = GetModel.name;
        holder.Gambar.setImageResource(Image);
        holder.Nama.setText(Name);
        //loading the image
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCtx, "Anda Menyentuh "+Name, Toast.LENGTH_SHORT).show();
                mCtx.startActivity(new Intent(mCtx, KategoriActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView Nama;
        ImageView Gambar;

        public ViewHolder(View itemView) {
            super(itemView);
            Gambar = itemView.findViewById(R.id.image);
            Nama = itemView.findViewById(R.id.name);
        }
    }
}

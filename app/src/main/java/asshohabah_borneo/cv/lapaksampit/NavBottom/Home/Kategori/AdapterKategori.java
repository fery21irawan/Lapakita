package asshohabah_borneo.cv.lapaksampit.NavBottom.Home.Kategori;


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
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;


/**
 * Created by Belal on 10/18/2017.
 */

public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.ViewHolder> {


    private Context mCtx;
    private List<ModelKategori> modelList;

    public AdapterKategori(Context mCtx, List<ModelKategori> modelList) {
        this.mCtx = mCtx;
        this.modelList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.kategori_view_grid, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelKategori GetModel = modelList.get(position);
        final String Name = GetModel.nama;
        final String kd = GetModel.kode;
        holder.Nama.setText(Name);
        //loading the image
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCtx, "Anda Menyentuh "+kd, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mCtx, KategoriActivity.class);
                intent.putExtra(Endpoints.Kategori_KD, kd);
                mCtx.startActivity(intent);
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
            Gambar = itemView.findViewById(R.id.aa);
            Nama = itemView.findViewById(R.id.bb);
        }
    }
}

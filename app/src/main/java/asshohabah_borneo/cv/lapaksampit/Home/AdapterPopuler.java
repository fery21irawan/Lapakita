package asshohabah_borneo.cv.lapaksampit.Home;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import asshohabah_borneo.cv.lapaksampit.R;


/**
 * Created by Belal on 10/18/2017.
 */

public class AdapterPopuler extends RecyclerView.Adapter<AdapterPopuler.ViewHolder> {


    private Context mCtx;
    private List<ModelPopuler> modelList;

    public AdapterPopuler(Context mCtx, List<ModelPopuler> modelList) {
        this.mCtx = mCtx;
        this.modelList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.populer_home_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ModelPopuler GetModel = modelList.get(position);
        final int Image = GetModel.image;
        final String Name = GetModel.name;
        final String Price = Double.toString(GetModel.harga);
        holder.Gambar.setImageResource(Image);
        holder.Nama.setText(Name);
        holder.Harga.setText("Rp. "+Price);
        //loading the image
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCtx, "Anda Menyentuh "+Name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Gambar;
        TextView Nama, Harga;

        public ViewHolder(View itemView) {
            super(itemView);
            Gambar = itemView.findViewById(R.id.image);
            Nama = itemView.findViewById(R.id.nama);
            Harga = itemView.findViewById(R.id.harga);
        }
    }
}

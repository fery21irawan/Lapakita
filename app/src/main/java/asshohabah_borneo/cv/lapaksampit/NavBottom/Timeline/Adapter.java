package asshohabah_borneo.cv.lapaksampit.NavBottom.Timeline;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import asshohabah_borneo.cv.lapaksampit.Data.Produk.DetailProdukActivity;
import asshohabah_borneo.cv.lapaksampit.R;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;

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
        View view = inflater.inflate(R.layout.grid_two_column, null);
        return new ViewHolder(view);
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Gambar;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            Gambar = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Model GetModel = modelList.get(position);
        final String Image = GetModel.getGbr_produk();
        final String Name = GetModel.getNm_produk();
        YoYo.with(Techniques.FadeInRight)
                .playOn(holder.itemView);
        Glide.with(mCtx)
                .load(Image)
                .into(holder.Gambar);
        holder.title.setText(Name);
        //loading the image
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, DetailProdukActivity.class);
                intent.putExtra(Endpoints.Produk_NM, GetModel.getNm_produk());
                intent.putExtra(Endpoints.Produk_GBR, GetModel.getGbr_produk());
                intent.putExtra(Endpoints.Pengguna_No_Telp, GetModel.getNo_telp());
                intent.putExtra(Endpoints.Pengguna_No_WA, GetModel.getNo_wa());
                intent.putExtra(Endpoints.Produk_Keterangan, GetModel.getKeterangan());
                intent.putExtra(Endpoints.Pengguna_Alamat, GetModel.getAlamat());
                intent.putExtra(Endpoints.Produk_Harga, GetModel.getHarga());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

}


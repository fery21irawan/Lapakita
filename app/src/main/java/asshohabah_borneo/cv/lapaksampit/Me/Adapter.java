package asshohabah_borneo.cv.lapaksampit.Me;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import asshohabah_borneo.cv.lapaksampit.R;

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
        View view = inflater.inflate(R.layout.grid_timeline_view, null);
        return new ViewHolder(view);
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

        public ViewHolder(View itemView) {
            super(itemView);
            Gambar = itemView.findViewById(R.id.image);
        }
    }
}

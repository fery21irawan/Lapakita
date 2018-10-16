package asshohabah_borneo.cv.lapaksampit.NavBottom.Lainnya;

/*
 * Created by Fery Irawan on 10/9/18 3:31 PM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 10/9/18 3:31 PM
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import asshohabah_borneo.cv.lapaksampit.MainActivity;
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
        View view = inflater.inflate(R.layout.fragment_lainnya_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model current = modelList.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.nama_layanan);
            icon  = itemView.findViewById(R.id.icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (getPosition()) {
                case 0:

                    break;

                case 1:

                    break;

                case 2:
                    Intent sendint = new Intent(Intent.ACTION_SEND);
                    sendint.putExtra(Intent.EXTRA_SUBJECT, mCtx.getString(R.string.app_name));
                    sendint.putExtra(Intent.EXTRA_TEXT, mCtx.getString(R.string.app_name) + "\" \nhttps://play.google.com/store/apps/details?id=" + mCtx.getPackageName());
                    sendint.setType("text/plain");
                    mCtx.startActivity(Intent.createChooser(sendint, "Bagikan Aplikasi"));
                    break;

                case 3:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                    alertDialogBuilder.setMessage("Logout ?");
                    alertDialogBuilder.setPositiveButton("Ya",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Endpoints.SharedPref_Nama, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.clear();
                                    editor.commit();
                                    Intent intent = new Intent(mCtx, MainActivity.class);
                                    mCtx.startActivity(intent);
                                    ((Activity)mCtx).finish();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("Tidak",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                }
                            });

                    //Showing the alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    break;
            }

        }
    }
}

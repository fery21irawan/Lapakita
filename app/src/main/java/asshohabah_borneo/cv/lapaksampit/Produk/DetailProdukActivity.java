package asshohabah_borneo.cv.lapaksampit.Produk;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.icu.text.Replaceable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;

import java.net.URLEncoder;

import asshohabah_borneo.cv.lapaksampit.MainActivity;
import asshohabah_borneo.cv.lapaksampit.R;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;

public class DetailProdukActivity extends AppCompatActivity {
    String GAMBAR,NAMA,NOTELP,NOWA,KETERANGAN,ALAMAT,HARGA;
    String newNum;
    ImageView imageView;
    TextView tv_nama_produk, tv_harga, tv_keterangan, tv_alamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);
        /**
         * Mengambil nilai dari intent.putExtra
         */
        Intent intent = getIntent();
        GAMBAR      = intent.getStringExtra(Endpoints.Produk_GBR);
        NAMA        = intent.getStringExtra(Endpoints.Produk_NM);
        NOTELP      = intent.getStringExtra(Endpoints.Pengguna_No_Telp);
        NOWA        = intent.getStringExtra(Endpoints.Pengguna_No_WA);
        KETERANGAN  = intent.getStringExtra(Endpoints.Produk_Keterangan);
        ALAMAT      = intent.getStringExtra(Endpoints.Pengguna_Alamat);
        HARGA       = intent.getStringExtra(Endpoints.Produk_Harga);
        newNum = NOTELP.replaceFirst("0","+62");
        /**
         * Initialize
         */
        imageView = findViewById(R.id.ivGambarBerita);
        tv_nama_produk = findViewById(R.id.tv_nama_produk);
        tv_alamat = findViewById(R.id.tv_alamat);
        tv_harga = findViewById(R.id.tv_harga);
        tv_keterangan = findViewById(R.id.tv_keterangan);

        FloatingActionButton FBNo_Telp = findViewById(R.id.fb_no_telp);
        FBNo_Telp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(DetailProdukActivity.this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(DetailProdukActivity.this, "Meminta Izin..", Toast.LENGTH_LONG).show();

                    if (ActivityCompat.shouldShowRequestPermissionRationale(DetailProdukActivity.this,
                            Manifest.permission.CALL_PHONE)) {

                    } else {
                        ActivityCompat.requestPermissions(DetailProdukActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE}, 0);
                    }
                } else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+newNum));
                    startActivity(callIntent);
                }
            }
        });

        FloatingActionButton FBNo_Wa = findViewById(R.id.fb_no_wa);
        FBNo_Wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager packageManager = getApplicationContext().getPackageManager();
                Intent i = new Intent(Intent.ACTION_VIEW);
                final String message = "Saya ingin bertanya tentang produk "+'"'+NAMA+'"'+"\n";
                try {
                    String url = "https://api.whatsapp.com/send?phone="+ newNum +"&text=" + URLEncoder.encode(message, "UTF-8");
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
                    if (i.resolveActivity(packageManager) != null) {
                        getApplicationContext().startActivity(i);
                    }else{
                        Toast.makeText(DetailProdukActivity.this, "Silahkan Install Aplikasi Whatsapp", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        loadDetail();
    }

    private void loadDetail() {
        Glide.with(this)
                .load(GAMBAR)
                .into(imageView);
        tv_nama_produk.setText(NAMA);
        tv_harga.setText(HARGA);
        tv_keterangan.setText(KETERANGAN);
        tv_alamat.setText(ALAMAT);
    }
}

package asshohabah_borneo.cv.lapaksampit.NavBottom.Jual;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import asshohabah_borneo.cv.lapaksampit.R;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;

public class JualActivity extends AppCompatActivity {
    EditText name, keterangan, harga;
    ImageView imageView;
    Button pickImage, upload;
    public static EditText kategori;
    public static TextView i_kategori;
    SharedPreferences sharedPreferences;

    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jual);
        sharedPreferences = this.getSharedPreferences(Endpoints.SharedPref_Nama, Context.MODE_PRIVATE);
        name= findViewById(R.id.name);
        keterangan= findViewById(R.id.keterangan);
        harga= findViewById(R.id.harga);
        kategori = findViewById(R.id.s_kategori);
        i_kategori = findViewById(R.id.i_kategori);
        kategori.setInputType(InputType.TYPE_NULL);
        kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getKategori();
            }
        });


        pickImage= findViewById(R.id.pickImgaeButton);
        upload = findViewById(R.id.upload);

        imageView = findViewById(R.id.previewImage);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().length() <= 0)
                {
                    name.setError("Masukan Nama Produk !");
                }
                else if (keterangan.getText().toString().length() <= 0)
                {
                    keterangan.setError("Tambahkan Keterangan !");
                }
                else if (harga.getText().toString().length() <= 0)
                {
                    harga.setError("Masukan Harga Produk !");
                }
                else if (bitmap==null)
                {
                    Toast.makeText(mActivity,"Please Upload Image",Toast.LENGTH_SHORT).show();
                }
                else {
                    uploadImage();

                }
            }
        });
        showFileChooser();
    }

    private void getKategori() {
        FragmentManager fm = getSupportFragmentManager();
        GetKategoriFragment editNameDialogFragment = new GetKategoriFragment();
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == this.RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                imageView.setVisibility(View.VISIBLE);
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.Produk_ADD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(JualActivity.this,s , Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(JualActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                String name1 = name.getText().toString().trim();
                String keterangan1 = keterangan.getText().toString().trim();
                String harga1 = harga.getText().toString().trim();
                String kategori = i_kategori.getText().toString().trim();
                String pemilik = sharedPreferences.getString(Endpoints.SharedPref_KD, "");

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(Endpoints.Produk_GBR, image);
                params.put(Endpoints.Produk_NM, name1);
                params.put(Endpoints.Produk_Keterangan, keterangan1);
                params.put(Endpoints.Kategori_KD, kategori);
                params.put(Endpoints.Produk_Harga, harga1);
                params.put(Endpoints.Pengguna_KD, pemilik);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
}

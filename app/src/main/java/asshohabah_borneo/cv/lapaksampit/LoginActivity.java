package asshohabah_borneo.cv.lapaksampit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;
import asshohabah_borneo.cv.lapaksampit.Server.RequestHandler;
import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextView signin, signup, signin_signup_txt, forgot_password;
    CircleImageView circleImageView;
    Button masuk, daftar;
    LinearLayout layoutSignup;
    EditText NamaLengkap, Alamat, NoHp, NoWA, Username, Password;
    String pass,username;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /**
         * Initialize Input pada Edit Text
         */
        NamaLengkap = findViewById(R.id.i_nama_lengkap);
        Alamat = findViewById(R.id.i_alamat);
        NoHp = findViewById(R.id.i_no_telp);
        NoWA = findViewById(R.id.i_no_wa);
        Username = findViewById(R.id.i_username);
        Password = findViewById(R.id.i_password);
        /**
         * Initialize Layout yang menjadi parent input pendaftaran akun
         * secara default layout ini sudah di set sembunyi
         * di initialize dikarenakan akan digunakan secara toggle terlihat / tersembunyi
         */
        layoutSignup = findViewById(R.id.layoutSignUp);
        /**
         * Initialze Textview yang seperti switch sebelah kiri dan kanan Gambar yang terdapat di layout
         */
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        /**
         * Initialize Text yang saling berganti antara Sign In atau Sign Up
         */
        signin_signup_txt = findViewById(R.id.signin_signup_txt);
        /**
         * initialize TextView untuk lupa password
         */
        forgot_password = findViewById(R.id.forgot_password);
        circleImageView = findViewById(R.id.circleImageView);
        daftar = findViewById(R.id.signup_btn);
        masuk = findViewById(R.id.signin_btn);
        masuk.setOnClickListener(this);
        daftar.setOnClickListener(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin.setTextColor(Color.parseColor("#FFFFFF"));
                signin.setBackgroundColor(Color.parseColor("#FF2729C3"));
                signup.setTextColor(Color.parseColor("#FF2729C3"));
                signup.setBackgroundResource(R.drawable.bordershape);
                circleImageView.setImageResource(R.drawable.sigin_boy_img);
                signin_signup_txt.setText("Sign In");
                masuk.setText("Sign In");
                forgot_password.setVisibility(View.VISIBLE);
                masuk.setVisibility(View.VISIBLE);
                if (layoutSignup.getVisibility() == View.VISIBLE && daftar.getVisibility() == View.VISIBLE) {
                    layoutSignup.setVisibility(View.GONE);
                    daftar.setVisibility(View.GONE);
                } else if (masuk.getVisibility() == View.GONE) {
                    masuk.setVisibility(View.VISIBLE);
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup.setTextColor(Color.parseColor("#FFFFFF"));
                signup.setBackgroundColor(Color.parseColor("#FF2729C3"));
                signin.setTextColor(Color.parseColor("#FF2729C3"));
                signin.setBackgroundResource(R.drawable.bordershape);
                circleImageView.setImageResource(R.drawable.sigup_boy_img);
                signin_signup_txt.setText("Sign Up");
                masuk.setText("Sign Up");
                forgot_password.setVisibility(View.INVISIBLE);
                layoutSignup.setVisibility(View.VISIBLE);
                daftar.setVisibility(View.VISIBLE);
                masuk.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == masuk) {
            username = Username.getText().toString();
            pass = Password.getText().toString();

            if (username.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "fill details", Toast.LENGTH_SHORT).show();

            } else {

                login(username, pass);
            }
        }
        if (v == daftar) {
            Register();
        }
    }

    private void Register() {

    }
    private void login(final String user, final String pass){

        url = Endpoints.Pengguna_Login_URL+"?username="+user+"&password="+pass+"";
        Log.i("Hiteshurl",""+url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray(Endpoints.TAG_JSON_ARRAY);
                    JSONObject c = jsonArray.getJSONObject(0);
                    String Public_KD = c.getString(Endpoints.Pengguna_KD);
                    String Public_NM = c.getString(Endpoints.Pengguna_NM);
                    String Public_Username = c.getString(Endpoints.Pengguna_Username);
                    String Public_Password = c.getString(Endpoints.Pengguna_Password);
                    String Public_Alamat = c.getString(Endpoints.Pengguna_Alamat);
                    String Public_No_Telp = c.getString(Endpoints.Pengguna_No_Telp);
                    String Public_No_Wa = c.getString(Endpoints.Pengguna_No_WA);
                    String Public_Kunci = c.getString(Endpoints.Pengguna_Kunci);
Log.d("lihat", Public_No_Telp);
                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Endpoints.SharedPref_Nama, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Adding values to editor
                    editor.putBoolean(Endpoints.SharedPref_Loggedin, true);
                    editor.putString(Endpoints.SharedPref_KD, Public_KD);
                    editor.putString(Endpoints.SharedPref_NM, Public_NM);
                    editor.putString(Endpoints.SharedPref_Alamat, Public_Alamat);
                    editor.putString(Endpoints.SharedPref_No_Telp, Public_No_Telp);
                    editor.putString(Endpoints.SharedPref_No_WA, Public_No_Wa);
                    editor.putString(Endpoints.SharedPref_Username, Public_Username);
                    editor.putString(Endpoints.SharedPref_Password, Public_Password);
                    editor.putString(Endpoints.SharedPref_Kunci, Public_Kunci);

                    //Saving values to editor
                    editor.commit();
                    //Starting profile activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("HiteshURLerror",""+error);
            }
        });

        requestQueue.add(stringRequest);

    }
}

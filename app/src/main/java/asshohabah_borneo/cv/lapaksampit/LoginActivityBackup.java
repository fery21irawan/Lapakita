package asshohabah_borneo.cv.lapaksampit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivityBackup extends AppCompatActivity implements View.OnClickListener {
    TextView signin, signup, signin_signup_txt, forgot_password;
    CircleImageView circleImageView;
    Button masuk, daftar;
    LinearLayout layoutSignup;
    EditText NamaLengkap, Alamat, NoHp, NoWA, Username, Password;

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
            Login();
        }
        if (v == daftar) {
            Register();
        }
    }

    private void Register() {

    }

    private void Login() {
        final String username = Username.getText().toString().trim();
        final String password = Password.getText().toString().trim();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.Pengguna_Login_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if (response.equalsIgnoreCase(Endpoints.LOGIN_SUCCESS)) {
                            class lihatdata extends AsyncTask<Void, Void, String> {
                                @Override
                                protected void onPreExecute() {
                                    super.onPreExecute();
                                }

                                @Override
                                protected void onPostExecute(String s) {
                                    super.onPostExecute(s);
                                    tampildatapelanggan(s);
                                }

                                private void tampildatapelanggan(String json) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(json);
                                        JSONArray result = jsonObject.getJSONArray(Endpoints.TAG_JSON_ARRAY);
                                        JSONObject c = result.getJSONObject(0);
                                        Endpoints.Public_KD[0] = c.getString(Endpoints.Pengguna_KD);
                                        Endpoints.Public_NM[0] = c.getString(Endpoints.Pengguna_NM);
                                        Endpoints.Public_Username[0] = c.getString(Endpoints.Pengguna_Username);
                                        Endpoints.Public_Password[0] = c.getString(Endpoints.Pengguna_Password);
                                        Endpoints.Public_Alamat[0] = c.getString(Endpoints.Pengguna_Alamat);
                                        Endpoints.Public_No_Telp[0] = c.getString(Endpoints.Pengguna_No_Telp);
                                        Endpoints.Public_No_Wa[0] = c.getString(Endpoints.Pengguna_No_WA);
                                        Endpoints.Public_Kunci[0] = c.getString(Endpoints.Pengguna_Kunci);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                protected String doInBackground(Void... params) {
                                    RequestHandler rh = new RequestHandler();
                                    String s = rh.sendGetRequestParam(Endpoints.Pengguna_GetInformation_URL, "'" + username + "'");
                                    return s;
                                }
                            }
                            lihatdata ld = new lihatdata();
                            ld.execute();
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = LoginActivityBackup.this.getSharedPreferences(Endpoints.SharedPref_Nama, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Endpoints.SharedPref_Loggedin, true);
                            editor.putString(Endpoints.SharedPref_KD, Endpoints.Public_KD[0]);
                            editor.putString(Endpoints.SharedPref_NM, Endpoints.Public_NM[0]);
                            editor.putString(Endpoints.SharedPref_Alamat, Endpoints.Public_Alamat[0]);
                            editor.putString(Endpoints.SharedPref_No_Telp, Endpoints.Public_No_Telp[0]);
                            editor.putString(Endpoints.SharedPref_No_WA, Endpoints.Public_No_Wa[0]);
                            editor.putString(Endpoints.SharedPref_Username, Endpoints.Public_Username[0]);
                            editor.putString(Endpoints.SharedPref_Password, Endpoints.Public_Password[0]);
                            editor.putString(Endpoints.SharedPref_Kunci, Endpoints.Public_Kunci[0]);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(LoginActivityBackup.this, MainActivity.class);

                            startActivity(intent);
                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(LoginActivityBackup.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request

                params.put(Endpoints.Pengguna_Username, username);
                params.put(Endpoints.Pengguna_Password, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

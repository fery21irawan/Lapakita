package asshohabah_borneo.cv.lapaksampit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;

import asshohabah_borneo.cv.lapaksampit.NavBottom.Home.HomeFragment;
import asshohabah_borneo.cv.lapaksampit.NavBottom.Home.HomeFragmentV2;
import asshohabah_borneo.cv.lapaksampit.NavBottom.Home.Kategori.KategoriFragment;
import asshohabah_borneo.cv.lapaksampit.NavBottom.Jual.JualActivity;
import asshohabah_borneo.cv.lapaksampit.NavBottom.Lainnya.MoreFragment;
import asshohabah_borneo.cv.lapaksampit.NavBottom.Me.MeFragment;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;
import asshohabah_borneo.cv.lapaksampit.NavBottom.Timeline.TimelineFragment;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final int REQUEST_GALLERY_CODE = 200;
    SearchView searchView;
    public static String DataSearch = null;
    public static String UserId, SharedUserId;
    SharedPreferences sharedPreferences;
    FragmentManager mManager = getSupportFragmentManager();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mManager.beginTransaction().replace(
                            R.id.ganti_tampilan,
                            new TimelineFragment()).commit();
                    return true;
                case R.id.navigation_kategori:
                    mManager.beginTransaction().replace(
                            R.id.ganti_tampilan,
                            new KategoriFragment()).commit();
                    return true;
                case R.id.navigation_jual:
                    Endpoints.loggedIn = sharedPreferences.getBoolean(Endpoints.SharedPref_Loggedin, false);
                    if (Endpoints.loggedIn) {
                        startActivity(new Intent(MainActivity.this, JualActivity.class));
                    }else{
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                    return true;
                case R.id.navigation_favorit:
                    Endpoints.loggedIn = sharedPreferences.getBoolean(Endpoints.SharedPref_Loggedin, false);
                    if (Endpoints.loggedIn) {
                        mManager.beginTransaction().replace(
                                R.id.ganti_tampilan,
                                new MeFragment()).commit();
                    }else{
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                    return true;
                case R.id.navigation_pesan:
                    mManager.beginTransaction().replace(
                            R.id.ganti_tampilan,
                            new MoreFragment()).commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);

        UserId = null;
        SharedUserId = "1";
        sharedPreferences = getSharedPreferences(Endpoints.SharedPref_Nama, Context.MODE_PRIVATE);
        /**
         * Fetching the boolean value form sharedpreferences
         */
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
       // BottomNavigationViewHelper.disableShiftMode(navigation);
        mManager.beginTransaction().replace(
                R.id.ganti_tampilan,
                new TimelineFragment()).commit();
       /* mManager.beginTransaction().replace(
                R.id.layout_populer,
                new HomeFragment()).commit();*/
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        DataSearch = query;
        Toast.makeText(MainActivity.this, DataSearch, Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //Toast.makeText(MainActivity.this, "Yeeeeeeeee", Toast.LENGTH_LONG).show();
        return false;
    }
}

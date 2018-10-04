package asshohabah_borneo.cv.lapaksampit;

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
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.mancj.materialsearchbar.MaterialSearchBar;

import asshohabah_borneo.cv.lapaksampit.Home.HomeFragment;
import asshohabah_borneo.cv.lapaksampit.Me.MeFragment;
import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;
import asshohabah_borneo.cv.lapaksampit.Timeline.TimelineFragment;


public class MainActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {
    private static final int REQUEST_GALLERY_CODE = 200;

    public static String UserId, SharedUserId;
    SharedPreferences sharedPreferences;
    MaterialSearchBar searchBar;

    FragmentManager mManager = getSupportFragmentManager();
    LinearLayout linearLayout;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    linearLayout.setVisibility(View.VISIBLE);
                    mManager.beginTransaction().replace(
                            R.id.ganti_tampilan,
                            new HomeFragment()).commit();
                    return true;
                case R.id.navigation_kategori:
                    linearLayout.setVisibility(View.VISIBLE);
                    mManager.beginTransaction().replace(
                            R.id.ganti_tampilan,
                            new TimelineFragment()).commit();
                    return true;
                case R.id.navigation_jual:
                    linearLayout.setVisibility(View.VISIBLE);
                    Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                    openGalleryIntent.setType("image/*");
                    startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
                    return true;
                case R.id.navigation_pesan:
                    linearLayout.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_favorit:
                    Endpoints.loggedIn = sharedPreferences.getBoolean(Endpoints.SharedPref_Loggedin, false);
                    if (Endpoints.loggedIn) {
                        mManager.beginTransaction().replace(
                                R.id.ganti_tampilan,
                                new MeFragment()).commit();
                        linearLayout.setVisibility(View.GONE);
                    }else{
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initialize and set SearchBar
         */
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);
        searchBar.setText("Hello World!");
        Log.d("LOG_TAG", getClass().getSimpleName() + ": text " + searchBar.getText());
        searchBar.setCardViewElevation(10);

        UserId = null;
        SharedUserId = "1";
        sharedPreferences = getSharedPreferences(Endpoints.SharedPref_Nama, Context.MODE_PRIVATE);
        /**
         * Fetching the boolean value form sharedpreferences
         */

        linearLayout = findViewById(R.id.linear);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
       // BottomNavigationViewHelper.disableShiftMode(navigation);
        mManager.beginTransaction().replace(
                R.id.ganti_tampilan,
                new HomeFragment()).commit();
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode){
            /*case MaterialSearchBar.BUTTON_NAVIGATION:
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;*/
            case MaterialSearchBar.BUTTON_BACK:
                searchBar.disableSearch();
                break;
        }

    }
}
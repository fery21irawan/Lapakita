package asshohabah_borneo.cv.lapaksampit.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import asshohabah_borneo.cv.lapaksampit.Server.Endpoints;

public class UserData {
    public String setUserData(String data){
        SharedPreferences sharedPreferences;
        Context context = null;
        sharedPreferences = context.getApplicationContext().getSharedPreferences(Endpoints.SharedPref_Nama, Context.MODE_PRIVATE);
        return sharedPreferences.getString(data,"");
    }
}

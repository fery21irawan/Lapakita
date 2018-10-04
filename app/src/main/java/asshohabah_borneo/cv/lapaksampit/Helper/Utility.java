package asshohabah_borneo.cv.lapaksampit.Helper;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utility {
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 140);
        return noOfColumns;
    }
}
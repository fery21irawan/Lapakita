package asshohabah_borneo.cv.lapaksampit;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class TextViewQuiska extends android.support.v7.widget.AppCompatTextView {

    public TextViewQuiska(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewQuiska(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewQuiska(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/quiska.ttf");
            setTypeface(tf);
        }
    }

}
package cn.com.kjer.practice.touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import cn.com.kjer.practice.R;

public class TouchActivity extends AppCompatActivity {
    private final  String Tag ="touch_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
    }
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(Tag, "activity     onTouchEvent");
        return super.onTouchEvent(event);
    }
}

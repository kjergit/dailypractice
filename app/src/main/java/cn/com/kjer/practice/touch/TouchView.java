package cn.com.kjer.practice.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by simon on 2017/3/14.
 */

public class TouchView extends View {
    private final String Tag = "touch_view";


    /**
     * 分发事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(Tag, "view     dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    /**
     * 触摸事件
     *
     * @param event
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(Tag, "view     onTouchEvent");
//        return super.onTouchEvent(event);
        return true;
    }

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

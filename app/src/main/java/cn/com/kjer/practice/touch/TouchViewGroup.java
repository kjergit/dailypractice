package cn.com.kjer.practice.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by simon on 2017/3/14.
 */

public class TouchViewGroup extends LinearLayout {
    private final String Tag = "touch_view_group";


    /**
     * 触摸拦截事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(Tag, "group     onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 分发事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(Tag, "group     dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 、触摸事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(Tag, "group     onTouchEvent");
        return super.onTouchEvent(event);
    }

    public TouchViewGroup(Context context) {
        super(context);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}

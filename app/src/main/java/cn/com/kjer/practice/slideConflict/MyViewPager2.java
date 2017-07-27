package cn.com.kjer.practice.slideConflict;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by simon on 2017/7/27.
 * 内部拦截法 view group
 */

public class MyViewPager2 extends ViewPager {

    private final static String TAG = "MyViewPager2";

    public MyViewPager2(Context context) {
        super(context);
    }

    public MyViewPager2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //内部拦截法  除down 以外 ，拦截其他事件
        if ((ev.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN) {
            //调用ViewPager的onInterceptTouchEvent方法初始化mActivePointerId,否则 默认为-1.如果为-1 ，在action_move 中 直接break；
            super.onInterceptTouchEvent(ev);
            return false;
        } else {
            Log.d(TAG, "pager2 onInterceptTouchEvent     other action  true ");
            return true;
        }
    }
}

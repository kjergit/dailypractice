package cn.com.kjer.practice.slideConflict;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by simon on 2017/7/14.
 */

public class MyViewPager extends ViewPager {
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int mLastXIntercept;
    private int mLastYIntercept;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //注释原代码，模拟滑动冲突事件
//     return super.onInterceptTouchEvent(ev);
        // over

        //以下为 解决viewpager 和listview 横向与纵向冲突问题 外部解决方法
        boolean intercepted = false;//默认false ，会造成冲突
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                //调用ViewPager的onInterceptTouchEvent方法初始化mActivePointerId
                super.onInterceptTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                //横坐标位移增量
                int deltaX = x - mLastXIntercept;
                //纵坐标位移增量
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaX)>Math.abs(deltaY)){
                    intercepted = true;//拦截 交给自己处理
                }else{
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:
                break;
        }

        mLastXIntercept = x;
        mLastYIntercept = y;

        Log.e("pager","intercepted = "+intercepted);
        return intercepted;
    }
}

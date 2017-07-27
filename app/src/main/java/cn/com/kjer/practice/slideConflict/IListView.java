package cn.com.kjer.practice.slideConflict;

import android.content.Context;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by simon on 2017/7/27.
 * 滑动冲突 内部拦截法 练习 内部listView
 */

public class IListView extends ListView {

    private final static String TAG = "IListView";

    public IListView(Context context) {
        super(context);
    }

    public IListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mLastX = 0;
    private int mLastY = 0;


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //内部拦截法 实现

        int x = (int) ev.getX();
        int y = (int) ev.getY();
        Log.d(TAG, "dispatchTouchEvent   x = " + x + "y=" + y);
        switch (ev.getAction()& MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN :
                //不用父类分发处理
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                //通过滑动距离判断交给谁来消耗事件
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    //交由父类viewgroup 处理左右滑动
                    Log.d(TAG, "dispatchTouchEvent   x >y  ");
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }


}

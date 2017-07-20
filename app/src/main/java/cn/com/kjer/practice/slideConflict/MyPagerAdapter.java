package cn.com.kjer.practice.slideConflict;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by simon on 2017/7/14.
 */

public class MyPagerAdapter extends PagerAdapter {

    private List<View> mList;
    private Context mContext;

    public MyPagerAdapter(Context context, List<View> list) {
        this.mContext = context;
        this.mList = list;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

//        TextView view = (TextView)mList.get(position);
        View view = mList.get(position);
        container.addView(view);
        return view;
//        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        if (mList.isEmpty() || mList.size() == 0) {
            return 0;
        } else {
            return mList.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));

    }
}

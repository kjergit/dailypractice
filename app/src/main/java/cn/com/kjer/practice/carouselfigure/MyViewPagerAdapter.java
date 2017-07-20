package cn.com.kjer.practice.carouselfigure;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by simon on 2017/3/22.
 */

class MyViewPagerAdapter extends PagerAdapter {

    private List<ImageView> mList;
    private Context mContext;



    public MyViewPagerAdapter (List<ImageView> list, Context context) {
        this.mList = list;
        this.mContext = context;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        position %= mList.size();
//        Log.d("view_pager","position%="+position);
//        if (position<0) {
//            position = mList.size() + position;
//        }

        ImageView view = mList.get(position);
        ViewParent viewParent = view.getParent();
        if (viewParent != null) {
            ViewGroup parent = (ViewGroup) viewParent;
            parent.removeView(view);
        }
        container.addView(view);
        Log.d("view_pager", "position=" + position);
        return view;
//        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
//        container.removeView(mList.get(position));
    }
}

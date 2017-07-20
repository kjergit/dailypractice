package cn.com.kjer.practice.carouselfigure;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


import java.util.logging.LogRecord;

import cn.com.kjer.practice.BaseActivity;
import cn.com.kjer.practice.R;

/**
 * Created by simon on 2017/3/22.
 *
 * @page 轮播图练习
 */

public class CarouselActivity extends BaseActivity {
    private ViewPager myViewPager;
    private List<ImageView> myList;
    private LinearLayout myLinearLayout;
    private List<ImageView> mImageViewDotList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel_layout);
        Handler hd = new Handler();
    }


    @Override
    protected void onSetData() {
        super.onSetData();
        initImageViews();
        setDot();
        if (myList != null && !myList.isEmpty()) {
            myViewPager.setAdapter(new MyViewPagerAdapter(myList, this.getApplicationContext()));
            myViewPager.setCurrentItem(1);
        }
    }

    private void initImageViews() {
        myList = new ArrayList<ImageView>();

        ImageView oneIV = new ImageView(this.getApplicationContext());
        ImageView twoIV = new ImageView(this.getApplicationContext());
        ImageView threeIV = new ImageView(this.getApplicationContext());
        ImageView fourIV = new ImageView(this.getApplicationContext());
        ImageView fiveIv = new ImageView(this.getApplicationContext());

        oneIV.setImageResource(R.drawable.one);
        twoIV.setImageResource(R.drawable.two);
        threeIV.setImageResource(R.drawable.three);
        fourIV.setImageResource(R.drawable.four);
        fiveIv.setImageResource(R.drawable.five);

        // turn 5 1 2 3 4 5 1
        myList.add(fiveIv);

        myList.add(oneIV);
        myList.add(twoIV);
        myList.add(threeIV);
        myList.add(fourIV);
        myList.add(fiveIv);

        myList.add(oneIV);

        mImageViewDotList = new ArrayList();


    }

    private void setDot() {
        //  设置LinearLayout的子控件的宽高，这里单位是像素。
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
        params.rightMargin = 20;
        //  for循环创建images.length个ImageView（小圆点）
        for (int i = 0; i < myList.size() - 2; i++) {
            ImageView imageViewDot = new ImageView(this);
            imageViewDot.setLayoutParams(params);
            //  设置小圆点的背景为暗红图片


            imageViewDot.setBackgroundResource(R.drawable.red_dot_night);
            if (i == 1) {
                //设置当索引为 1，也就是第二张图片时 小圆点图片背景为红色 5  1 2 3 4 5 1
                mImageViewDotList.get(0).setBackgroundResource(R.drawable.red_dot);
            }
            myLinearLayout.addView(imageViewDot);
            mImageViewDotList.add(imageViewDot);
        }

    }

    @Override
    protected void onFindViews() {
        super.onFindViews();
        myViewPager = (ViewPager) findViewById(R.id.my_view_pager);
        myLinearLayout = (LinearLayout) findViewById(R.id.my_pager_dot);
    }

    private int currentPosition;
    private int dotPosition;
    private int oldDotPosition;

    @Override
    protected void onSetListener() {
        super.onSetListener();
        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //positionOffset是当前页面滑动比例，如果页面向右翻动，这个值不断变大，
                // 最后在趋近1的情况后突变为0。如果页面向左翻动，这个值不断变小，最后变为0。
                //如果为倒数第二个，则为 1 。  顺序为  5 1 2 3 4 5 1


                if (position == myList.size() - 1) {
                    // 当目标界面为第六个图 1时，设界面索引为 1图的角标 1   (position 从0开始)
                    currentPosition = 1;
                    dotPosition = 0;
                } else if (position == 0) {
                    // 如果索引值为0了,就设置索引值为倒数第二个
                    currentPosition = myList.size() - 2;
                    dotPosition = myList.size() - 3;
                } else {
                    currentPosition = position;
                    dotPosition = position - 1;
                }
                mImageViewDotList.get(oldDotPosition).setBackgroundResource(R.drawable.red_dot_night);
                mImageViewDotList.get(dotPosition).setBackgroundResource(R.drawable.red_dot);
                oldDotPosition = dotPosition;
                Log.d("view_pager_cg_Scrolled", "position = " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("view_pager_cg_elected", "position = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //如果是静止状态,将当前页进行替换
                if (state == ViewPager.SCROLL_STATE_IDLE)
                    Log.d("view_pager_", "onPageScrollStateChanged==currentPosition = " + currentPosition);
                myViewPager.setCurrentItem(currentPosition, false);

            }
        });
    }
}

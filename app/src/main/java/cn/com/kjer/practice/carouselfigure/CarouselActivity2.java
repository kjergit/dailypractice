package cn.com.kjer.practice.carouselfigure;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.com.kjer.practice.BaseActivity;
import cn.com.kjer.practice.R;

/**
 * Created by simon on 2017/3/22.
 *
 * @page 轮播图练习
 */

public class CarouselActivity2 extends BaseActivity implements Runnable {

    private static final String TAG = "CarouselActivity2";

    private ViewPager mBanner;
    private BannerAdapter mBannerAdapter;
    private ImageView[] mIndicators;//指示点
    private Timer mTimer = new Timer();

    private int mBannerPosition = 0;
    //图片数量的倍数。本例子中为一共五个图片，初始位置0 ，向左滑动，需要展示 4的图片，因此在初始化时，将item的位置指向6，而从4 滑动到9时，将item位置指向9，实现无限循环
    private final int DEFAULT_BANNER_SIZE = 5;
    private final int FAKE_BANNER_SIZE = 10;
    private boolean mIsUserTouched = false;

    //图片资源
    private int[] mImagesSrc = {
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five
    };
    //存储imageview的集合
    private List<View> mImageViewList = new ArrayList<View>();
    //指示点 fatherlayout
    LinearLayout mDootLayout;

    //PagerTitleStrip  标题   可在 title 中 扩展，例如 span 中添加图片，xml中配置为pager 子布局
    // PagerTabStrip    上边子类，指示标题可点击跳转  带下划线 .setTabIndicatorColorResource(R.color.green)更改下划线颜色
    PagerTabStrip mPagerTitleStrip;
    List<String> titleList= new ArrayList<String>();

    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (!mIsUserTouched) {
                mBannerPosition = (mBannerPosition + 1) % FAKE_BANNER_SIZE;
                runOnUiThread(CarouselActivity2.this);
                Log.d(TAG, "tname:" + Thread.currentThread().getName() + "  ---2 CarouselActivity2.this=" + CarouselActivity2.this.hashCode());
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel_layout2);
        initView();
        mTimer.schedule(mTimerTask, 5000, 5000);

    }



    private void initView() {
        mDootLayout = (LinearLayout) findViewById(R.id.my_pager_dot);
        mIndicators = new ImageView[]{
                (ImageView) findViewById(R.id.indicator1),
                (ImageView) findViewById(R.id.indicator2),
                (ImageView) findViewById(R.id.indicator3),
                (ImageView) findViewById(R.id.indicator4),
                (ImageView) findViewById(R.id.indicator5)
        };

        for (int i = 0; i < mImagesSrc.length; i++) {
            ImageView mView = new ImageView(this.getApplicationContext());
            mView.setImageResource(mImagesSrc[i]);
            mImageViewList.add(mView);
        }
        mBanner = (ViewPager) findViewById(R.id.my_view_pager);
        mBannerAdapter = new BannerAdapter(this, mImageViewList);
        mBanner.setAdapter(mBannerAdapter);
        mBanner.setOnPageChangeListener(mBannerAdapter);
        mBanner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN
                        || action == MotionEvent.ACTION_MOVE) {
                    mIsUserTouched = true;
                } else if (action == MotionEvent.ACTION_UP) {
                    mIsUserTouched = false;
                }
                return false;
            }
        });

        mPagerTitleStrip  = (PagerTabStrip) findViewById(R.id.pagertitle);
        titleList = new ArrayList<String>();// 每个页面的Title数据
        titleList.add("11111111111111");
        titleList.add("22222222222222");
        titleList.add("33333333333333");
        titleList.add("44444444444444");
        titleList.add("55555555555555");

    }

    private void setIndicator(int position) {
        //求于数，每次算出position 在list中的具体指向那个 imagevew
        position %= DEFAULT_BANNER_SIZE;
        for (ImageView indicator : mIndicators) {
            indicator.setImageResource(R.drawable.red_dot_night);
        }
        mIndicators[position].setImageResource(R.drawable.red_dot);
    }

    @Override
    public void run() {
        if (mBannerPosition == FAKE_BANNER_SIZE - 1) {
            mBanner.setCurrentItem(DEFAULT_BANNER_SIZE - 1, false);
        } else {
            mBanner.setCurrentItem(mBannerPosition);
        }
    }

    @Override
    protected void onDestroy() {
        mTimer.cancel();
        super.onDestroy();
    }

    private class BannerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

        private List<View> mImageDataList = new ArrayList<View>();

        public BannerAdapter(Context context, List<View> mViewList) {
            this.mImageDataList = mViewList;
        }

        @Override
        public int getCount() {
            return FAKE_BANNER_SIZE;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= DEFAULT_BANNER_SIZE;

            View view = null;
            if (mImageDataList == null || mImageDataList.isEmpty()) {
                throw new NullPointerException("imageview data list is null or  empty!");
            }
            //如果之前已经初始化，此时 container 中已经包含此view ，因此再此调用此处，
            // 需要将此view 从container中移除，再次添加，不需要重新创建iamgeview ，达到重用的目的
            view = mImageDataList.get(position);
            ViewParent viewParent = view.getParent();
            if (viewParent != null) {
                ViewGroup parent = (ViewGroup) viewParent;
                parent.removeView(view);//因为是循环利用 因此 再次利用需要将此view从父view中移除
            }

            final int pos = position;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CarouselActivity2.this, "click  item =:" + pos, Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(view);//将imageviewlist 中的view 再次添加到 container中
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //instantiateItem 中已经进行移除操作
//            container.removeView((View) object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            int position = mBanner.getCurrentItem();
            Log.d(TAG, "finish update before, position=" + position);
            if (position == 0) {//如果加载后是 0，重置为6
                position = DEFAULT_BANNER_SIZE;
                mBanner.setCurrentItem(position, false);
            } else if (position == FAKE_BANNER_SIZE - 1) {//如果加载后是 9，重置为 4
                position = DEFAULT_BANNER_SIZE - 1;
                mBanner.setCurrentItem(position, false);
            }
            Log.d(TAG, "finish update after, position=" + position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mBannerPosition = position;
            setIndicator(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            position %= DEFAULT_BANNER_SIZE;//计算真实的界面position
            //标题 需要重写此方法  返回对应 的标题
            return titleList.get(position);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }



}
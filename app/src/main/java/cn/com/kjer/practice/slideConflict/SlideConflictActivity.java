package cn.com.kjer.practice.slideConflict;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.com.kjer.practice.BaseActivity;
import cn.com.kjer.practice.R;

/**
 * 滑动冲突练习
 */
public class SlideConflictActivity extends BaseActivity {


//    private ViewPager myViewPager;

    private MyViewPager2 myViewPager;
    private List<View> mViewList = new ArrayList<View>();
    private Context mContext = this;
    MyPagerAdapter myPagerAdapter;
    boolean isList =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_conflict);
    }

    @Override
    protected void onFindViews() {
        super.onFindViews();
        myViewPager = (MyViewPager2) findViewById(R.id.my_pager_view);
    }

    ArrayList<String> datas;

    @Override
    protected void onSetData() {
        super.onSetData();

        datas = new ArrayList<>();
        for (int data = 0; data < 25; data++) {
            datas.add("data= " + data);
        }
        initData(isList);
    }

    /**
     * @param isListView son view is listviw or view
     */
    private void initData(boolean isListView) {
        for (int i = 0; i < 5; i++) {//viewpager have five son
            View view;
            if (isListView) {
                //内部拦截法 list 为 ListView
//                ListView mListView = new ListView(mContext);


//                //外部拦截法
                IListView mListView = new IListView(mContext);

                ArrayAdapter<String> adapter = new ArrayAdapter<>
                        (mContext, android.R.layout.simple_list_item_1, datas);
                //设置adapter
                mListView.setAdapter(adapter);
                //将ListView赋值给当前View
                view = mListView;
            } else {
                TextView textView = new TextView(mContext);
                textView.setGravity(Gravity.CENTER);
                textView.setText("text=" + i);
                textView.setTextColor(getResources().getColor(R.color.blue));
                view = textView;
            }
            mViewList.add(view);
        }
        myPagerAdapter = new MyPagerAdapter(mContext, mViewList);
        myViewPager.setAdapter(myPagerAdapter);
    }
}

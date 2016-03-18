package cn.com.kjer.practice.activitys;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by kjer
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onInit();
        onFindViews();
        onSetData();
        onSetListener();
    }


    protected void onInit() {
        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();//隐藏
//        actionBar.show();

        if (actionBar != null) {
            //设置左上角返回键是否可以点击
            actionBar.setHomeButtonEnabled(true);
            //给左上角图标的左边加上一个返回的图标
            actionBar.setDisplayHomeAsUpEnabled(true);
            // 把ActionBar下面的阴影去掉（Android5.0下才有的效果）
            getSupportActionBar().setElevation(0);
        }
    }

    protected void onFindViews() {

    }

    protected void onSetData() {

    }

    protected void onSetListener() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://back click
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

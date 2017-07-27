package cn.com.kjer.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import cn.com.kjer.practice.binderTest.BookManagerActivity;
import cn.com.kjer.practice.canvasTest.activitys.CanvasActivity;
import cn.com.kjer.practice.carouselfigure.CarouselActivity;
import cn.com.kjer.practice.carouselfigure.CarouselActivity2;
import cn.com.kjer.practice.collectionitempicker.ListDataActivity;
import cn.com.kjer.practice.drawerLayout.DrawerLayoutActivity;
import cn.com.kjer.practice.loadImage.BitmapActivity;
import cn.com.kjer.practice.loadImage.DownLoadActivity;
import cn.com.kjer.practice.rxjava.RxJavaBaseTest;
import cn.com.kjer.practice.slideConflict.SlideConflictActivity;
import cn.com.kjer.practice.touch.TouchActivity;
import cn.com.kjer.practice.utils.SystemUtil;
import cn.com.kjer.practice.volleryTest.VolleryTestActivity;

public class HomeActivity extends BaseActivity {

    private ListView mListView;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private final String Tag = "HomeActivity ";


    @Override
    protected void onResume() {
        Log.d(Tag, "onResume ...");
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("keyvalue", "saved data!!!");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        String s = (String) savedInstanceState.get("keyvalue");
        Log.d(Tag, " onRestoreInstanceState  value=" + s);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState != null) {
            String value = (String) savedInstanceState.get("keyvalue");
            int keyNum = savedInstanceState.getInt("key");
            Log.d(Tag, "oncreat get value = " + value);
        }
    }

    @Override
    protected void onStop() {
        Log.d(Tag, "onStop。。。");
        super.onStop();

    }

    @Override
    protected void onPostResume() {
        Log.d(Tag, "onStart。。。");

        super.onPostResume();
    }

    @Override
    protected void onStart() {
        Log.d(Tag, "onStart。。。");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(Tag, "onPause。。。");
        super.onPause();
    }

    @Override
    protected void onFindViews() {
        super.onFindViews();
        initToolBar();
        initDrawableLayout();
        mListView = (ListView) findViewById(R.id.home_listview);
    }

    private void initDrawableLayout() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.home_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


    @Override
    protected void onInit() {
        super.onInit();
        String maxMemory = SystemUtil.getSystemMaxMemory();
        Log.e("max", "maxMemory=" + maxMemory + "m");
    }

    @Override
    protected void onSetData() {
        super.onSetData();
    }

    @Override
    protected void onSetListener() {
        super.onSetListener();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(HomeActivity.this, BitmapActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(HomeActivity.this, ListDataActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(HomeActivity.this, CanvasActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(HomeActivity.this, VolleryTestActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(HomeActivity.this, DownLoadActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(HomeActivity.this, DrawerLayoutActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(HomeActivity.this, TouchActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(HomeActivity.this, CarouselActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(HomeActivity.this, RxJavaBaseTest.class));
                        break;
                    case 9:
                        startActivity(new Intent(HomeActivity.this, CarouselActivity2.class));
                        break;
                    case 10:
                        startActivity(new Intent(HomeActivity.this, SlideConflictActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(HomeActivity.this, BookManagerActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.base_top_tool_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.layout_tool_bar);
//        mToolbar.setLogo(R.drawable.title_bar_more);
//        mToolbar.setNavigationIcon(R.drawable.back_arrow);
        mToolbar.setTitle(getResources().getString(R.string.app_name));// 标题的文字需在setSupportActionBar之前，不然会无效
        setSupportActionBar(mToolbar);

//        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
//        //用 ActionBar 设置返回图标
//        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.radio:
                Toast.makeText(this, "radio", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.star:
                Toast.makeText(this, "star", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        //点击back键不会回到启动页面，因而需要监听
        //启动并且不接收数据
        Intent i = new Intent(Intent.ACTION_MAIN);
        //新的activity栈中开启，或者已经存在就调到栈前
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //添加种类，为设备首次启动显示的页面
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }

    /**
     * 刷新 menu (再次触发menu creat)
     */
    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }
}

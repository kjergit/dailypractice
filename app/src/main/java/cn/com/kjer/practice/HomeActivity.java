package cn.com.kjer.practice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import cn.com.kjer.practice.canvasTest.activitys.CanvasActivity;
import cn.com.kjer.practice.collectionitempicker.ListDataActivity;
import cn.com.kjer.practice.loadImage.BitmapActivity;
import cn.com.kjer.practice.loadImage.DownLoadActivity;
import cn.com.kjer.practice.utils.SystemUtil;
import cn.com.kjer.practice.volleryTest.VolleryTestActivity;

public class HomeActivity extends BaseActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onFindViews() {
        super.onFindViews();
        listView = (ListView) findViewById(R.id.home_listview);
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bar_home_top_action_bar, menu);
        return true;
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //点击back键不会回到启动页面，因而需要监听
            //启动并且不接收数据
            Intent i = new Intent(Intent.ACTION_MAIN);
            //新的activity栈中开启，或者已经存在就调到栈前
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //添加种类，为设备首次启动显示的页面
            i.addCategory(Intent.CATEGORY_HOME);
            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 刷新 menu (再次触发menu creat)
     */
    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }
}

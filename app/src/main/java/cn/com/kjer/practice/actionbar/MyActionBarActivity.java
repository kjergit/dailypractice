package cn.com.kjer.practice.actionbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import cn.com.kjer.practice.BaseActivity;
import cn.com.kjer.practice.R;

/**
 * @author kjer
 * @page 标题栏界面
 */
public class MyActionBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.left_bar:
                Toast.makeText(this, "left click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.left_bar2:
                Toast.makeText(this, "left2 click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.left_bar3:
                Toast.makeText(this, "left3 click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.right_bar:
                Toast.makeText(this, "right click", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * 刷新 menu (再次触发menu creat)
     */
    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

    @Override
    protected void onInit() {
        super.onInit();
    }

    @Override
    protected void onFindViews() {
        super.onFindViews();
    }


    @Override
    protected void onPrepareData() {
        super.onPrepareData();
    }

    @Override
    protected void onSetListener() {
        super.onSetListener();
    }
}

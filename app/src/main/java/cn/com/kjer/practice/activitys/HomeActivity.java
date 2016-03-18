package cn.com.kjer.practice.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import cn.com.kjer.practice.R;
import cn.com.kjer.practice.collectionitempicker.ListDataActivity;
import cn.com.kjer.practice.temp.CanvasActivity;

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

    /**
     * 刷新 menu (再次触发menu creat)
     */
    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }
}

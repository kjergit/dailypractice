package cn.com.kjer.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cn.com.kjer.practice.actionbar.MyActionBarActivity;
import cn.com.kjer.practice.image.BitmapActivity;
import cn.com.kjer.practice.temp.SavePicToSDCard;

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
    protected void onPrepareData() {
        super.onPrepareData();
    }

    @Override
    protected void onSetListener() {
        super.onSetListener();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(HomeActivity.this, MyActionBarActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(HomeActivity.this, BitmapActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(HomeActivity.this, SavePicToSDCard.class));
                        break;
//                    case 3:
//                        startActivity(new Intent(HomeActivity.this, SwitchButtonActivity.class));
//                        break;
//                    case 4:
//                        startActivity(new Intent(HomeActivity.this, SavePicToSDCard.class));
                    default:
                        break;
                }
            }
        });
    }
}

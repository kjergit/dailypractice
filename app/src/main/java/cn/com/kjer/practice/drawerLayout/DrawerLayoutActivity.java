package cn.com.kjer.practice.drawerLayout;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cn.com.kjer.practice.R;

/**
 * Created by simon on 2016/11/8.
 */

public class DrawerLayoutActivity extends AppCompatActivity {

    private ListView drawerLeftListView;
    private DrawerLayout drawerLayout;
    private String[] drawerListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_left_drawer);
        initDrawerLayout();
    }

    private void initDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.home_drawer_layout);
        drawerLeftListView = (ListView) findViewById(R.id.home_drawer_listView);
        drawerListData = getResources().getStringArray(R.array.drawer_data_list);
        drawerLeftListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.item_drawer_list_layout, drawerListData));
        drawerLeftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(DrawerLayoutActivity.this, "position=" +
//                        ((drawerListData == null || drawerListData.length > 0)
//                                ? drawerListData[position] : position), Toast.LENGTH_SHORT).show();
                //收回抽屉
//                drawerLayout.closeDrawer(drawerLeftListView);
                ContentFragment contentFragment = new ContentFragment();
                Bundle args = new Bundle();
                args.putString("content", (drawerListData == null || drawerListData.length > 0)
                        ? drawerListData[position] : String.valueOf(position));
                contentFragment.setArguments(args);
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.ly_content, contentFragment).commit();
                drawerLayout.closeDrawer(drawerLeftListView);

            }
        });
//        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
//                drawerLayout, R.drawable.ic_star_black_24dp,
//                R.string.drawer_open, R.string.drawer_colse) {
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                getActionBar().setTitle("opened");
//                invalidateOptionsMenu();
//                Log.d("drawer", "onDrawerOpened");
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                getActionBar().setTitle();
//                invalidateOptionsMenu();
//                Log.d("drawer", "onDrawerClosed");
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//                super.onDrawerStateChanged(newState);
//                Log.d("drawer", "onDrawerStateChanged");
//            }
//
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView, slideOffset);
//                Log.d("drawer", "onDrawerSlide");
//            }
//        };
//        drawerLayout.setDrawerListener(drawerToggle);
//        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    }
}

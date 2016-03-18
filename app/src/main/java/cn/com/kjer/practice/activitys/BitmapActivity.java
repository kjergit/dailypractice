package cn.com.kjer.practice.activitys;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.leakcanary.RefWatcher;

import cn.com.kjer.practice.activitys.BaseActivity;
import cn.com.kjer.practice.MyApplication;
import cn.com.kjer.practice.R;

public class BitmapActivity extends BaseActivity implements View.OnClickListener {
    private ImageView imageView;
    private Button getLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
    }


    @Override
    protected void onFindViews() {
        super.onFindViews();
        imageView = (ImageView) findViewById(R.id.big_image);
        getLog = (Button) findViewById(R.id.get_iamge_info_bt);

    }

    @Override
    protected void onSetData() {
        super.onSetData();
    }

    @Override
    protected void onSetListener() {
        super.onSetListener();
        imageView.setOnClickListener(this);
        getLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.big_image:
                break;
            case R.id.get_iamge_info_bt:
                getImageViewBitmap();
                break;
            default:
                break;
        }
    }

    private void getImageViewBitmap() {
        Drawable drawable = imageView.getDrawable();
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getMinimumHeight();
        Log.d("iamge_view", "width" + width + "  height=" + height);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        int size = sizeOf(bitmapDrawable.getBitmap());
        Log.d("image_view", "size=" + size);
    }

    protected int sizeOf(Bitmap data) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            return data.getRowBytes() * data.getHeight();
        } else {
            return data.getByteCount();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}

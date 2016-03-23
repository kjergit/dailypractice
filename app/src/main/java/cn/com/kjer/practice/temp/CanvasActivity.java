package cn.com.kjer.practice.temp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import cn.com.kjer.practice.R;
import cn.com.kjer.practice.activitys.BaseActivity;
import cn.com.kjer.practice.customviews.CanvasView;

/**
 * Created by kjer on 2016/3/18.
 */
public class CanvasActivity extends BaseActivity {

    private CanvasView canvasView;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            canvasView.pathAnimation();
            mHandler.sendEmptyMessageDelayed(0, 3000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        initView();
    }


    private void initView() {
        canvasView = (CanvasView) findViewById(R.id.canvas_layout);
        //是否启动动画
        mHandler.sendEmptyMessage(0);
    }
}

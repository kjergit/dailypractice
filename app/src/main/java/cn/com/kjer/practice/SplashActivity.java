package cn.com.kjer.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

/**
 * Created by simon on 2016/11/7.
 * 启动页
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //无title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_splash);
        jumpToHome();

        //Test AsyncTask
        testAsyncTask();
    }

    private void testAsyncTask() {
        new MyAsyncTask().execute();
    }

    /**
     * test code
     */
    public class MyAsyncTask extends AsyncTask {

        public MyAsyncTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //完成一些准备操作，比如显示进度对话框
            //mDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            //异步耗时任务处理 网络数据请求。。。
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            //定时 间断  进行UI操作，更新进度条的进度
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Object o) {
            //完成任务后，数据更新UI等操作
            super.onPostExecute(o);
        }

        @Override
        protected void onCancelled(Object o) {
            super.onCancelled(o);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


    /**
     * 跳转至hone页 停顿三秒
     */
    private void jumpToHome() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
        //启动过程中不许返回 因此是空实现
    }
}

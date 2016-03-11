package cn.com.kjer.practice.temp;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;

import cn.com.kjer.practice.BaseActivity;
import cn.com.kjer.practice.R;
import cn.com.kjer.practice.utils.BitmapUtil;
import cn.com.kjer.practice.utils.Constant;
import cn.com.kjer.practice.utils.SDcardUtils;
import cn.com.kjer.practice.utils.TextUtil;

/**
 * Created by kjer on 2016/1/18.
 */
public class SavePicToSDCard extends BaseActivity {

    private ScrollView rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_pic_to_sdcard);
        saveTask = new SaveTask();
    }

    @Override
    protected void onFindViews() {
        super.onFindViews();

        rootView = (ScrollView) findViewById(R.id.scroll_root_layout);
        findViewById(R.id.city_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextUtil.hideSoftInput(SavePicToSDCard.this);
            }
        });
        findViewById(R.id.edit).setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        //输入后hint消失
                        EditText view = (EditText) v;
                        if (hasFocus) {
                            view.setTag(view.getHint().toString());
                            view.setHint("");
                        } else {
                            view.setHint(view.getTag().toString());
                        }
                    }
                }
        );
        //保存图片到手机
        findViewById(R.id.save_layout_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                savePicToSdCard();
                //// FIXME: 2016/3/11  task error
                executeSave();
            }
        });
    }

    @Override
    protected void onInit() {
        super.onInit();
    }

    @Override
    protected void onSetListener() {
        super.onSetListener();
    }

    SaveTask saveTask;

    private void executeSave() {
        saveTask.execute();
    }

    private class SaveTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            savePicToSdCard();
            Log.d("test", "doInBackground");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d("test", "onCancelled");
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (saveTask.getStatus() == AsyncTask.Status.RUNNING) {
            saveTask.cancel(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("test", "onDestroy");
    }

    private String savePicToSdCard() {
        String hint = null;
        long timeStart = System.currentTimeMillis();
        Bitmap bitmap = BitmapUtil.getViewCacheBitMap(rootView);
        if (bitmap != null) {
            hint = BitmapUtil.saveBitmapToSdcard(getApplicationContext()
                    , bitmap, SDcardUtils.getRoot() + File.separator + System.currentTimeMillis() + Constant.PNG_SUFFIX);
            Toast.makeText(SavePicToSDCard.this, hint, Toast.LENGTH_SHORT).show();
        }
        Log.d("test", "time=" + (System.currentTimeMillis() - timeStart));
        return hint;
    }
}

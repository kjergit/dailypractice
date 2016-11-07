package cn.com.kjer.practice.volleryTest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import cn.com.kjer.practice.BaseActivity;
import cn.com.kjer.practice.R;

/**
 * Created by simon on 2016/9/1.
 */
public class VolleryTestActivity extends BaseActivity {

    private TextView contentTv;
    private Button contentbt;
    private ImageView imageView;

    private static final String Tag = "Vollery test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vollery_test);
    }

    @Override
    protected void onInit() {
        super.onInit();
    }

    @Override
    protected void onFindViews() {
        super.onFindViews();
        contentTv = (TextView) findViewById(R.id.content_tv);
        contentbt = (Button) findViewById(R.id.request_button);
        imageView = (ImageView) findViewById(R.id.imageView);
        contentbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStringRequest();
                doImageRequest();
            }
        });
    }

    private void doStringRequest() {
        RequestQueue mQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("http://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(Tag, response);
                        setResultContent(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(Tag, error.getMessage(), error);
            }
        });
        mQueue.add(stringRequest);
    }

    private void doImageRequest() {
        ImageRequest imageRequest = new ImageRequest(
                "http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1126541908,2603454962&os=3355752586,552577105&simid=4125984377,377518992&pn=72&rn=1&di=148610359700&ln=1958&fr=&fmq=1472714291753_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=&istype=0&ist=&jit=&bdtype=0&adpicid=0&pi=&gsm=1e&objurl=http%3A%2F%2Fi1.hexunimg.cn%2F2014-08-15%2F167580248.jpg&rpstart=0&rpnum=0&adpicid=0",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                        Log.e(Tag,"response"+ response.toString());
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView.setImageResource(R.drawable.ic_star_border_black_24dp);
                Log.e(Tag, "error"+error.toString());
            }
        });
        RequestQueue mQueue2 = Volley.newRequestQueue(this);
        mQueue2.add(imageRequest);
    }

    /**
     * vollery test
     */


    private void setResultContent(String s) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        contentTv.setText(s);
    }

}

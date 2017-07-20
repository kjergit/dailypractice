package cn.com.kjer.practice.volleryTest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
    private Button contentBt;
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
        contentBt = (Button) findViewById(R.id.request_button);
        imageView = (ImageView) findViewById(R.id.imageView);
        contentBt.setOnClickListener(new View.OnClickListener() {
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
                "http://img1.imgtn.bdimg.com/it/u=1794894692,1423685501&fm=23&gp=0.jpg",
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

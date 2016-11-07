package cn.com.kjer.practice.loadImage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cn.com.kjer.practice.BaseActivity;
import cn.com.kjer.practice.R;

public class BitmapActivity extends BaseActivity implements View.OnClickListener {
    private ImageView imageView;
    private Button getLog;
    private static final String Tag = "big_bitmap";

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
                getImageView();
                break;
            default:
                break;
        }
    }

    public void getImageView() {
        getImageViewBitmap(imageView);
    }

    /**
     * 获取图片大小
     *
     * @param mImageView
     */
    private void getImageViewBitmap(ImageView mImageView) {
        Drawable drawable = mImageView.getDrawable();
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getMinimumHeight();
        Log.d(Tag, "imagevie 原始 宽高  width" + width + "  height=" + height);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        int size = sizeOf(bitmapDrawable.getBitmap());
        Log.d(Tag, "原有图片 大小  size=" + size);

        Bitmap bitmap = decodeBitmapFromResource(getResources(), R.drawable.big_img_1800k, width, height);
        imageView.setImageBitmap(bitmap);
    }

    protected int sizeOf(Bitmap data) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            return data.getRowBytes() * data.getHeight();
        } else {
            return data.getByteCount();
        }
    }

    public static Bitmap decodeBitmapFromResource(Resources res, int resId,
                                                  int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        String imageType = options.outMimeType;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            Log.d("Tag", "inSampleSize=" + inSampleSize + "缩放比例  heightRatio=" + heightRatio + " widthRatio=" + widthRatio
                    + "加载目标图片原有 宽高 =height=" + height + "  width=" + width+"imageType="+imageType);
        }
        return inSampleSize;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}

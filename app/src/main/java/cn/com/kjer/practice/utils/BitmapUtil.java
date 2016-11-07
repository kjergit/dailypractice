package cn.com.kjer.practice.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 */
public class BitmapUtil {
    public static final String TAG = "BitmapUtil";


    /**
     * 大图加载首先要清楚图片的质量与所占内存的计算规则：Bitmap.Config
     * Bitmap.Config 介绍（每个像素点的构成）     1pix所占空间  1byte = 8位   1024*1024图片大小所占大小
     * ALPHA_8   只有透明度，没有颜色，那么一个像素点占8位。                      1byte 1M
     * RGB_565   即R=5，G=6，B=5，没有透明度，那么一个像素点占5+6+5=16位          2byte 2M
     * ARGB_4444 由4个4位组成，即A=4，R=4，G=4，B=4，那么一个像素点占4+4+4+4=16位 2byte 2M
     * ARGB_8888 由4个8位组成，即A=8，R=8，G=8，B=8，那么一个像素点占8+8+8+8=32位 4byte 4M
     *
     * @return 图片所占内存大小  (1px所占btyes)
     */
    static int getBytesPerPixel(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888) {
            return 4;
        } else if (config == Bitmap.Config.RGB_565) {
            return 2;
        } else if (config == Bitmap.Config.ARGB_4444) {
            return 2;
        } else if (config == Bitmap.Config.ALPHA_8) {
            return 1;
        }
        return 1;
    }

    /**
     * 获取当前view的缓存bitmap
     */
    public static Bitmap getViewCacheBitMap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * 保存layout 图像到手机
     *
     * @param rootView 需要截图的根layout
     */
    public static String doSavePicture(Context context, ViewGroup rootView) {
        String hint = "保存失败";
        Bitmap bitmap = BitmapUtil.getViewCacheBitMap(rootView);
        if (bitmap != null) {
            hint = BitmapUtil.saveBitmapToSdcard(context.getApplicationContext()
                    , bitmap, SDcardUtils.getRoot() + File.separator + System.currentTimeMillis() + Constant.PNG_SUFFIX);
            Toast.makeText(context.getApplicationContext(), hint, Toast.LENGTH_SHORT).show();
        }
        return hint;
    }

    /**
     * 将bitmap 生成图片并保存到sd卡
     */
    public static String saveBitmapToSdcard(Context context, Bitmap b, String fileName) {
        String hint = "保存图片成功";
        if (SDcardUtils.sdcardExists()) {
            if (SDcardUtils.getAvailableExternalMemorySize() > Constant.VALUE1024) {
                FileOutputStream fos = null;
                FileUtil.parentFolder(fileName);
                File file = new File(fileName);
                try {
                    if (!file.exists()) {
                        FileUtil.createNewFile(file);
                    }
                    fos = new FileOutputStream(file);
                    b.compress(Bitmap.CompressFormat.PNG, Constant.VALUE90, fos);
                    fos.flush();
                    fos.close();
                    ImageUtils.photoUpdates(context, file);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    FileUtil.close(fos);
                }
            } else {
                hint = "存储空间不足";
                Log.e(TAG, "No space left on the device... free size ="
                        + SDcardUtils.getAvailableExternalMemorySize());
            }
        } else {
            hint = "请插入存储卡";
        }
        return hint;
    }
}

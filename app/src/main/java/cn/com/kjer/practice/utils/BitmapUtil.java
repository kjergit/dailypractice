package cn.com.kjer.practice.utils;

import android.content.Context;
import android.graphics.Bitmap;
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

    public static Bitmap getViewCacheBitMap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * 保存layout 图像到手机
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

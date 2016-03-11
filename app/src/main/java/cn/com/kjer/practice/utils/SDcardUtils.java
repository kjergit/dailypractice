package cn.com.kjer.practice.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by kjer on 2016/1/21.
 */
public class SDcardUtils {

    public static File getRoot() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "kjer";
        return new File(path);
    }

    /**
     * 判断SD卡是否存在
     *
     * @return
     */
    public static boolean sdcardExists() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机外部存储大小
     *
     * @return
     */
    public static long getAvailableExternalMemorySize() {
        if (sdcardExists()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return -1;
        }
    }
}

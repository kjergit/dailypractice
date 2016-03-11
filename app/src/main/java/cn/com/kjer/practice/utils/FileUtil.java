package cn.com.kjer.practice.utils;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by kjer on 2015/12/8.
 */
public class FileUtil {
    private static final String TAG = "FileUtil";

    /**
     * 判断某个文件所在的文件夹是否存在，不存在时直接创建
     *
     * @param path
     */
    public static void parentFolder(String path) {
        File file = new File(path);
        String parent = file.getParent();

        File parentFile = new File(parent + File.separator);
        if (!parentFile.exists()) {
            mkdirs(parentFile);
        }
    }

    /**
     * 创建文件对应的所有父目录。如果创建失败，则打出error级别的log
     *
     * @param file 文件
     * @return 成功与否
     */
    public static boolean mkdirs(File file) {
        if (file == null) {
            return false;
        }
        if (!file.mkdirs() && !file.isDirectory()) {
            Log.e(TAG, "FileUtil cannot make dirs: " + file);
            return false;
        }
        return true;
    }

    /**
     * 文件或目录重命名。如果失败，则打出error级别的log
     *
     * @param srcFile 原始文件或目录
     * @param dstFile 重命名后的文件或目录
     * @return 成功与否
     */
    public static boolean renameTo(File srcFile, @Nullable File dstFile) {
        if (srcFile == null || dstFile == null) {
            return false;
        }
        if (!srcFile.renameTo(dstFile)) {
            Log.e(TAG, "FileUtil cannot rename " + srcFile + " to " + dstFile);
            return false;
        }

        return true;
    }

    /**
     * 获取文件扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 保存文件至SD卡
     *
     * @param path
     * @param bytes
     * @return
     */
    public static boolean saveFile2SDcard(String path, byte[] bytes) {

        parentFolder(path);

        File file = new File(path);

        boolean flag = false;
        BufferedOutputStream bos;
        try {
            bos = new BufferedOutputStream(
                    new FileOutputStream(file), 8192);
            bos.write(bytes, 0, bytes.length);
            bos.close();
            flag = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 文件或文件夹拷贝
     * 如果是文件夹拷贝 目标文件必须也是文件夹
     *
     * @param srcFile 源文件
     * @param dstFile 目标文件
     * @return
     */
    public static boolean copy(File srcFile, File dstFile) {
        if (!srcFile.exists()) { //源文件不存在
            return false;
        }

        if (srcFile.isDirectory()) { //整个文件夹拷贝
            if (!dstFile.isDirectory()) {    //如果目标不是目录，返回false
                return false;
            }

            for (File f : srcFile.listFiles()) {
                if (!copy(f, new File(dstFile, f.getName()))) {
                    return false;
                }
            }
            return true;

        } else { //单个文价拷贝
            return copyFile(srcFile, dstFile);
        }

    }

    /**
     * 拷贝文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件，如果是目录，则生成该目录下的同名文件再拷贝
     */
    private static boolean copyFile(File srcFile, File destFile) {
        if (!destFile.exists()) {
            if (!mkdirs(destFile.getParentFile()) || !createNewFile(destFile)) {
                return false;
            }
        } else if (destFile.isDirectory()) {
            destFile = new File(destFile, srcFile.getName());
        }

        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            FileChannel src = in.getChannel();
            FileChannel dst = out.getChannel();
            dst.transferFrom(src, 0, src.size());
            src.close();
            dst.close();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(out);
            close(in);
        }

        return false;
    }

    /**
     * 创建文件。如果创建失败，则打出error级别的log
     *
     * @param file 文件
     * @return 成功与否
     */
    public static boolean createNewFile(File file) {
        if (file == null) {
            return false;
        }

        boolean result;
        try {
            result = file.createNewFile() || file.isFile();
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }

        if (!result) {
            Log.e(TAG, "FileUtil cannot create file: " + file);
        }
        return result;
    }

    public static void close(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}

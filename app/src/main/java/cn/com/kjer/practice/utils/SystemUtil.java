package cn.com.kjer.practice.utils;

import android.util.Log;

/**
 * Created by simon on 2016/9/7.
 */
public class SystemUtil {


    /**
     * 获取系统分配可用最大内存（单位）
     */
    public static String getSystemMaxMemory() {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        return Long.toString(maxMemory / (Constant.VALUE1024 * Constant.VALUE1024));
    }
}

package cn.com.kjer.practice.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by kjer on 2015/12/4.
 */
public class IntentUtil {

    /**
     * 跳转界面方法 （dq 咒语传送）
     */
    public static void luLa(Context contetxt, Class clazz) {
        Intent intent = new Intent();
        intent.setClass(contetxt, clazz);
        contetxt.startActivity(intent);
    }
}

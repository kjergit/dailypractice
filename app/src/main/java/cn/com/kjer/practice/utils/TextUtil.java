package cn.com.kjer.practice.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by kjer on 2016/1/21.
 */
public class TextUtil {

    public static void hideSoftInput(Activity context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            if (context.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
                context.getCurrentFocus().clearFocus();
            }
        }
    }


    public static boolean hideInputMethod(@Nullable Context context, @Nullable View view) {
        if (context == null || view == null) {
            return false;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        return imm != null && imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }
}

package cn.com.kjer.practice.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by kjer on 2016/3/17.
 */
public class InputUtil {
    private static InputUtil instance;
    private InputMethodManager mInputMethodManager;
    private static Activity mActivity;

    private InputUtil() {
        mInputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static InputUtil getInstance(Activity activity) {
        mActivity = activity;
        if (instance == null) {
            instance = new InputUtil();
        }
        return instance;
    }

    /**
     * 强制显示输入法
     */
    public void show() {
        show(mActivity.getWindow().getCurrentFocus());
    }

    public void show(View view) {
        mInputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 强制关闭输入法
     */
    public void hide() {
        hide(mActivity.getWindow().getCurrentFocus());
    }

    public void hide(View view) {
        mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 如果输入法已经显示，那么就隐藏它；如果输入法现在没显示，那么就显示它
     */
    public void showOrHide() {
        mInputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hideSoftInput(Activity context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            if (context.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
                context.getCurrentFocus().clearFocus();
            }
        }
    }

    /**
     * 设置EditText 获取光标后清除hint，失去光标回显
     * EditText.OnFocusChangeListener
     */
    public static void switchFocus(EditText v, boolean hasFocus) {
        EditText view = v;
        if (hasFocus) {
            view.setTag(view.getHint().toString());
            view.setHint("");
        } else {
            view.setHint(view.getTag().toString());
        }
    }
}

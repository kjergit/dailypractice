package cn.com.kjer.practice.loadImage;

import android.content.Context;

/**
 * Created by simon on 2017/3/30.
 */

public class Single {
    private static Single instance;
    private Context mContext;

    private Single(Context context) {
        this.mContext = context;
    }

    public static Single getInstance(Context context) {
        if (instance == null) {
            synchronized (Single.class) {
                if (instance == null) {
                    instance = new Single(context);
                }
            }
        }
        return instance;
    }
}

package cn.com.kjer.practice.utils;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import cn.com.kjer.practice.R;

/**
 * Created by kjer on 2015/12/7.
 */
public class MyCrashHandler implements Thread.UncaughtExceptionHandler {

    private final String Tag = "MyCrashHandler";

    private static MyCrashHandler instance;
    private Context context;
    private Thread.UncaughtExceptionHandler defalutHandler;

    private MyCrashHandler() {

    }


    public static MyCrashHandler getInstance() {
        if (instance == null) {
            synchronized (MyCrashHandler.class) {
                if (instance == null) {
                    instance = new MyCrashHandler();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        // 获取系统默认的UncaughtException处理器
        defalutHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e(Tag, "uncaughtExceptio..... thread=" + thread.getName() + " message=" + ex.getLocalizedMessage());

        if (!handleException(ex) && defalutHandler != null) {
            //程序默认处理方式
            defalutHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.e(Tag, "error : ", e);
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, context.getResources().getString(R.string.app_crash_prompt),
                        Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        //Todo save crash log to sdcard
        return true;
    }
}

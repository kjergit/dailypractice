package cn.com.kjer.practice;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.com.kjer.practice.utils.MyCrashHandler;

/**
 * Created by simon on 2015/12/8.
 */
public class MyApplication extends MultiDexApplication {

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        //内存泄露检测 debug版本
        refWatcher = LeakCanary.install(this);
//        //release 版本
//        refWatcher = installLeakCanary();
        //初始化全局异常监测器
        MyCrashHandler.getInstance().init(this);
    }

//    protected RefWatcher installLeakCanary() {
//        return RefWatcher.DISABLED;
//    }

public static RefWatcher getRefWatcher(Context context) {
    MyApplication application = (MyApplication) context.getApplicationContext();
    return application.refWatcher;
}

        @Override
        public void onTerminate () {
            super.onTerminate();
        }

        @Override
        public void onConfigurationChanged (Configuration newConfig){
            super.onConfigurationChanged(newConfig);
        }

        @Override
        public void onLowMemory () {
            super.onLowMemory();
        }

        @Override
        public void onTrimMemory ( int level){
            super.onTrimMemory(level);
        }
    }

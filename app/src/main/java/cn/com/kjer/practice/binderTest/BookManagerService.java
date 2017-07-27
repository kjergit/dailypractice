package cn.com.kjer.practice.binderTest;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.interrupted;

/**
 * Created by simon on 2017/7/20.
 */

public class BookManagerService extends Service {

    private final String Tag = "book_manager_service";
    /**
     * 因为 方法是再 服务端的 Binder线程池中执行，
     * 因此多个客户端同时连接，会存在多个线程同时的访问的情况，
     * 因此选用  支持并发读写 CopyOnWirtArrayList
     */
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<Book>();
    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    /**
     * RemoteCallbackList 是系统专门用来删除跨进程listener接口的接口
     * 其内部有一个map 专门窜访所有的aidl回调，map的key 就是Ibinder，value 是callback类型
     * 其内部自动实现了了线程同步，客户端进程终止后，他能够自动移除客户端所注册listener
     */
    private RemoteCallbackList<IWhenNewBookArrivedListener> mListenerList =
            new RemoteCallbackList<IWhenNewBookArrivedListener>();

    private Thread mThread;


    @Override
    public boolean onUnbind(Intent intent) {
        if(mThread!=null && mThread.isAlive()){
            mThread.interrupt();
        }
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "android_dev_book"));
        mBookList.add(new Book(2, "head_first"));

        mThread = new Thread(new IWorkRunnable());
        mThread.start();
    }

    private Binder mBinder = new IBookManager.Stub() {//服务端，因此stub 可以实现，如果不同线程，需要proxy 处理
        @Override
        public IBinder asBinder() {
            return super.asBinder();
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            //权限验证，目前没有用到，如果失败 程序shutdown
//            int check = checkCallingOrSelfPermission("cn.com.kjer.practice.binderTest.permission.ACCESS_BOOK_SERVICE");
//            Log.d(Tag, "onTransact check=" + check);
//            if (check == PackageManager.PERMISSION_DENIED) {
//                return false;
//            }

            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(
                    getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            Log.d(Tag, "onTransact: " + packageName);
            if (!packageName.startsWith("cn.com.kjer.practice")) {
                return false;
            }
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        //观察者模式 注册与取消
        @Override
        public void registerListener(IWhenNewBookArrivedListener listener) {
            mListenerList.register(listener);
            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(Tag, "registerListener, current size:" + N);
        }

        @Override
        public void unregisterListener(IWhenNewBookArrivedListener listener) {
            boolean success = mListenerList.unregister(listener);

            if (success) {
                Log.d(Tag, "unregister success.");
            } else {
                Log.d(Tag, "not found, can not unregister.");
            }
            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(Tag, "unregisterListener, current size:" + N);
        }
    };

    private void whenNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);
        final int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IWhenNewBookArrivedListener l = mListenerList.getBroadcastItem(i);
            if (l != null) {
                try {
                    l.whenNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListenerList.finishBroadcast();
    }

    private class IWorkRunnable implements Runnable {
        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                    if(interrupted()){//测试较安全的 退出thread
                        throw new InterruptedException();
                    }
                } catch (InterruptedException e) {
                    Log.e(Tag,"thread InterruptedException " +e.toString());
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "how to use as" + bookId);

                try {
                    whenNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
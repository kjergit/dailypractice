package cn.com.kjer.practice.binderTest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;

import cn.com.kjer.practice.BaseActivity;
import cn.com.kjer.practice.R;

public class BookManagerActivity extends BaseActivity {

    private final static String Tag = "book_manager_activity";
    private final static int MESSAGE_NEW_BOOK_ARRIVED = 1;
    private IBookManager mRemoteBookManager;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED: {
                    Log.d(Tag, "receive new book :" + msg.obj);
                }
            }
        }
    };

    //此方法中 开启并关联远程服务
    private ServiceConnection mServiceConnection =
            new ServiceConnection() {

                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    IBookManager bookManager = IBookManager.Stub.asInterface(service);
                    try {
                        List<Book> list = bookManager.getBookList();
                        Book book = list.get(0);
                        Book book2 = list.get(1);
                        Log.i(Tag, "onServiceConnected   qurey list type :" + list.getClass() +
                                "name=" + book.getBookName() + "   " + book2.getBookName());


                        //观察者 逻辑
                        mRemoteBookManager = bookManager;
                        Book newBook = new Book(2, "android up");
                        mRemoteBookManager.addBook(newBook);
                        Log.d(Tag," connection... register  need linstener");
                        mRemoteBookManager.registerListener(mWhenNewBookArrivedListener);

                    } catch (RemoteException e) {
                        e.printStackTrace();
                        Log.e(Tag, "onServiceConnection... " + e.toString());
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mRemoteBookManager = null;
                    Log.i(Tag, "onServiceDisconnected");
                }
            };


    private IWhenNewBookArrivedListener mWhenNewBookArrivedListener =
            new IWhenNewBookArrivedListener.Stub() {

                @Override
                public void whenNewBookArrived(Book newBook) throws RemoteException {
                    //whenNewBookArrived 方法运行在 binder 线程，因此需要通知ui 需要通过handler
                    mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED
                            , newBook).sendToTarget();
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);

        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            Log.d(Tag, "unregister listener");
            try {
                mRemoteBookManager.unregisterListener(mWhenNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        unbindService(mServiceConnection);
        super.onDestroy();
    }


}

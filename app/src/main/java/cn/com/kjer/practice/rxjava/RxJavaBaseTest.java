package cn.com.kjer.practice.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import cn.com.kjer.practice.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by simon on 2017/4/10.
 * rxjava test
 */

public class RxJavaBaseTest extends Activity {

    private static String Tag = "Rxjava_base_tag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_layout);
        initViews();
    }


    private void initViews() {
        findViewById(R.id.rx_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //基本操作
                doBaseThings();
            }
        });
        findViewById(R.id.rx_button_muilt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do someting on mulit thread
                doMuiltThrad();
            }
        });
        findViewById(R.id.rx_button_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMap();
            }


        });
    }

    private void doMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(Tag, "Observable subscribe");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(Tag, s);
            }
        });
    }

    private void doMuiltThrad() {
        Observable<Integer> observableMuiltThread = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(Tag, "subscribe  Observable thread is : " + Thread.currentThread().getName());
                Log.d(Tag, "emit 1");
                emitter.onNext(1);

            }
        });

        //消费者 consumer
        //消费者 consumer
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(Tag, "accept Observer thread is :" + Thread.currentThread().getName());
                Log.d(Tag, "onNext: " + integer);
            }
        };
        //main thread do
        //  observableMuiltThread.subscribe(consumer);

        //  subscribeOn() 指定的是上游发送事件的线程,
        observableMuiltThread.subscribeOn(Schedulers.newThread())
                // observeOn() 指定的是下游接收事件的线程.
                // 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);

    }

    private void doBaseThings() {
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(Tag, "observable subscribe");
                //need do things 1.2.3 etc
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                //complete
                emitter.onComplete();
            }
        });
        //下游 Observer 观察者
        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(Tag, "Observer onSubscribe");

            }

            @Override
            public void onNext(Integer value) {
                Log.d(Tag, "onNext=" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(Tag, "onError" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(Tag, "onComplete");

            }
        };

        //建立连接
        observable.subscribe(observer);
    }

}

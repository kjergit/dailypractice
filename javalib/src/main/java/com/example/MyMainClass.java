package com.example;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class MyMainClass {

    //base test

    static int[] list = {3, 85, 1234, 34, 657, 67, 45, 856};

    public static void main(String[] args) {
        System.out.println("----------main--------start------ ");
//        testRxJava();
//        try {
//            Thread.sleep(10000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //test sort

//        int secondNum = bubbleSortFindSecondValue(list);
//        System.out.println(secondNum);
//        int sortSecondValue = bubbleSort2FindSecondValue(list);
//        System.out.println(" sortSecondValue = " + sortSecondValue);

        //test runable run
//        testMultiThread();
//        equals();
//        fcukD();
//        selectionSort(selectionArray);

    }


    private static int[] selectionArray = {125, 5, 8, 66, 83, 74, 4, 9};//length = 8

    /**
     * 选择排序
     * 选择排序的本质即从第一位开始，遍历之后的所有剩余元素，找出其中最小的一个放到第一位；接下来再从第二位开始，找出数组中第二小的数，依次类推。
     * <p>
     * 选择排序有两个重要特点:
     * 运行时间和输入无关
     * 即不论数组的初始状态的有序程度，选择排序的比较次数都没有变化。考虑到比较次数与元素个数的关系是N²/2，所以当一个已经比较有序的数组使用选择排序会很不划算。
     * 2.数据的移动操作最少
     * 移动操作次数是一个常量，最多为N，其他的算法都不具备这个特征。
     */
    private static void selectionSort(int[] array) {
        int length = array.length;
        for (int ii = 0, min = 0; ii < length; ii++, min = ii) {//min 记录 数组中最小值的角标

            for (int jj = ii + 1; jj < length; jj++) {//jj为第二个元素
                if (array[min] > array[jj]) {
                    min = jj;//记录数组中 最小值的角标
                }

            }
            if (min != ii) {
                int temp = array[ii];
                array[ii] = array[min];
                array[min] = temp;
            }

        }
    }

    /**
     * 位运算
     */
    private static void fcukD() {
        int abc = 4;
        //将4 向整体右位移一位
        abc = abc >> 1;
        System.out.println("abc=" + abc);

        int intTEmp = 8;    //1000
        int intA = intTEmp ^ 2;//1000 ^0010= 10010 =10
        int intB = intTEmp ^ 1;//1000 0001= 10001 = 9
        System.out.println("intA= " + intA + "   intB=" + intB);

    }

    /**
     * == and equals 基本知识
     */
    private static void equals() {
        int a = 5;
        int b = 5;

        Integer ai = 5;
        Integer bi = 5;

        String aa = "aaa";
        String a2 = "aaa";
        String bb = new String("aaa");

        Object c = new Object();
        Object cc = new Object();

        System.out.println(a == b);//true 比较两个基本数据类型值是否相等
        System.out.println(a == ai);//true
        System.out.println(ai == bi);//true


        System.out.println(aa == a2);//true 创建aa时，同时将“aaa”放入字符串缓冲区,此时a2会直接引用缓冲区中的对象，不会重新创建
        System.out.println(aa == bb);//false，虽然缓冲区中存在“aaa”,而new String()是从新创建了一个新的，不会重用
        System.out.println(c == cc);//false 不是同一个对象，所以不会指向同一个内存空间地址

        System.out.println(aa.equals(a2));//true JDK类中有一些类覆盖了oject类的equals()方法，比较规则为：如果两个对象的类型一致，并且内容一致，则返回true,
        // 这些类有： java.io.file, java.util.Date, java.lang.string, 包装类（Integer,Double等）
        System.out.println(ai.equals(bi));//true
        System.out.println(aa.equals(bb));//true

        System.out.println(c.equals(cc));//false 两个变量是否是对同一个对象的引用,不是同一个对象
    }

    private static void testMultiThread() {

        //this thread run  in main
        new Runnable() {
            @Override
            public void run() {
                System.out.println(" thread in " + Thread.currentThread().getName());
            }
        }.run();

        //thread run in main thread
        new Thread() {
            @Override
            public void run() {
                System.out.println("Thread run  " + Thread.currentThread().getName());
            }
        }.run();


        //使用匿名内部类创建线程
        Thread t1 = new Thread() {
            public void run() {
                System.out.println("0" + Thread.currentThread().getName());
            }
        };
        t1.start();

        //使用runnable接口创建线程
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("1" + Thread.currentThread().getName());
            }
        };
        Thread t2 = new Thread(r);
        t2.start();

        //使用runnable接口创建创建匿名类，创建线程实例
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2" + Thread.currentThread().getName());
            }
        });
        t3.start();

        //创建匿名类实例，直接启动线程
        new Thread() {
            public void run() {
                System.out.println("3" + Thread.currentThread().getName());
            }
        }.start();

        //创建匿名类实例，实现runnable接口，直接启动线程
        new Thread(new Runnable() {
            public void run() {
                System.out.println("4" + Thread.currentThread().getName());
            }
        });


    }

    /**冒泡进行排序 并且输出数组中第二大元素
     */
    private static int bubbleSort2FindSecondValue(int[] mList) {
        int maxValue;
        int secondValue;
        int temp;

        if (mList == null || mList.length < 2)
            return -1;
        //首先从list中取出 前两位，进行比较，分别赋值给max 和second
        if (mList[0] > mList[1]) {
            maxValue = mList[0];
            secondValue = mList[1];
        } else {
            maxValue = mList[1];
            secondValue = mList[0];
        }
        int times = mList.length - 1;//总是将当前j 和j+1 元素进行对比，因此length-1
        for (int j = 0; j < times; j++) {
            if (mList[j] > mList[j + 1]) {
                //进行前后两个值的比较，如果前一个值大于后一个，交换位置
                temp = mList[j];
                mList[j] = mList[j + 1];
                mList[j + 1] = temp;
            }

            if (mList[j] > secondValue) {
                if (mList[j] > maxValue) {
                    maxValue = mList[j+1];
                    secondValue = mList[j];
                } else {
                    secondValue = mList[j];
                }
            }
            if (j == times -1) {
                j = -1;//i重置为-1，随后for循环会++，因此下次循环开始时j值为0
                times--;//比较次数递减1
            }
            System.out.println(" j=" + j+"  maxvalue="+maxValue );
        }

        for (int value = 0; value < mList.length; value++) {
            System.out.print(mList[value] + "  ");
        }
        return secondValue;
    }


    /**
     * 不排序，冒泡排序查找数组中第二大的元素
     */
    private static int bubbleSortFindSecondValue(int[] mList) {
        int maxValue;
        int secondValue;

        //首先从list中取出 前两位，进行比较，分别赋值给max 和second
        if (mList == null || mList.length < 2)
            return -1;

        if (mList[0] > mList[1]) {
            maxValue = mList[0];
            secondValue = mList[1];
        } else {
            maxValue = mList[1];
            secondValue = mList[0];
        }

        //取出数组中第二大元素 无序
        for (int i = 2; i < mList.length; i++) {//0 1 号元素已经持有（maxValue,secondValue），从第三位开始遍历比较
            if (mList[i] > secondValue) {
                if (mList[i] > maxValue) {
                    secondValue = maxValue;
                    maxValue = mList[i];
                } else {
                    secondValue = mList[i];
                }
            }

        }
        return secondValue;
    }

    private static void testRxJava() {

        Flowable.create(new FlowableOnSubscribe<String>() {

            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                System.out.println("------subscribe start  read  mulan.text------ ");
                try {
                    FileReader reader = new FileReader("D:/android_studio_workspace/dailypractice/javalib/mulan.txt");
                    BufferedReader br = new BufferedReader(reader);
                    String line;
                    while ((line = br.readLine()) != null && !emitter.isCancelled()) {
                        while (emitter.requested() == 0) {
                            if (emitter.isCancelled()) {
                                break;
                            }
                        }
                        emitter.onNext(line);

                    }
                    reader.close();
                    br.close();
                } catch (IOException e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())//指定上游 线程 io 线程
                .observeOn(Schedulers.newThread())//下游线程 新线程
                .subscribe(new Subscriber<String>() {
                    Subscription mSubscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("------onSubscribe  s.request(1);------ ");
                        mSubscription = s;
                        s.request(1);
                    }

                    @Override
                    public void onNext(String string) {
                        System.out.println("onnext   " + string);
                        try {
                            Thread.sleep(2000);
                            mSubscription.request(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println(" onComplete");
                    }
                });

    }
}

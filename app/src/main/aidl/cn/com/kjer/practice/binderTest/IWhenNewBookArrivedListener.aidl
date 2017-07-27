// IWhenNewBookArrivedListener.aidl
package cn.com.kjer.practice.binderTest;

// Declare any non-default types here with import statements
import cn.com.kjer.practice.binderTest.Book;

interface IWhenNewBookArrivedListener {
 void whenNewBookArrived(in Book newBook);
}

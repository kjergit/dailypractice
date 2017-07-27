// IBookManager.aidl
package cn.com.kjer.practice.binderTest;

// Declare any non-default types here with import statements
import cn.com.kjer.practice.binderTest.Book;
import cn.com.kjer.practice.binderTest.IWhenNewBookArrivedListener;
interface IBookManager {
    /**
    *aidl ： int long ,char ,boolean ,double , string ,charSequence
     *       List: arrayList
     *       map
     *       parcelable
     *       import Bean package etc...
    */

            List<Book> getBookList();

            void addBook(in Book book);

           void registerListener(IWhenNewBookArrivedListener listener);
           void unregisterListener(IWhenNewBookArrivedListener listener);
}

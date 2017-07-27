package cn.com.kjer.practice.binderTest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by simon on 2017/7/20.
 */

public class Book implements Parcelable {

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int bookId;
    public String bookName;

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public Book(int bookId, String bookName) {

        this.bookId = bookId;
        this.bookName = bookName;
    }


    protected Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }
}

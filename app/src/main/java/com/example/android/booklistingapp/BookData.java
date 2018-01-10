package com.example.android.booklistingapp;

/**
 * Created by mostafa on 1/6/18.
 */

public class BookData {
    private String mBookName, mAuthorName, mPrice;

    public BookData(String mBookName, String mAuthorName, String mPrice) {
        this.mBookName = mBookName;
        this.mAuthorName = mAuthorName;
        this.mPrice = mPrice;
    }

    public String getmAuthorName() {
        return mAuthorName;
    }

    public String getmBookName() {
        return mBookName;
    }

    public String getmPrice() {
        return mPrice;
    }
}

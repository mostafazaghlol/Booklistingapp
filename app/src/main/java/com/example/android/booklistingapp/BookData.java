package com.example.android.booklistingapp;

/**
 * Created by mostafa on 1/6/18.
 */

public class BookData {
    private String mBookName, mAuthorName, mPrice, mUrl, mGoogleLink;

    public BookData(String mBookName, String mAuthorName, String mPrice, String mUrl, String mGoogleLink) {
        this.mBookName = mBookName;
        this.mAuthorName = mAuthorName;
        this.mPrice = mPrice;
        this.mUrl = mUrl;
        this.mGoogleLink = mGoogleLink;
    }

    public BookData(String mBookName, String mAuthorName, String mPrice, String mUrl) {
        this.mBookName = mBookName;
        this.mAuthorName = mAuthorName;
        this.mPrice = mPrice;
        this.mUrl = mUrl;
    }

    public BookData(String mBookName, String mAuthorName, String mPrice) {
        this.mBookName = mBookName;
        this.mAuthorName = mAuthorName;
        this.mPrice = mPrice;

    }

    public String getmGoogleLink() {
        return mGoogleLink;
    }

    public String getmAuthorName() {
        return this.mAuthorName;
    }

    public String getmBookName() {
        return this.mBookName;
    }

    public String getmPrice() {
        return this.mPrice;
    }

    public String getmUrl() {
        return this.mUrl;
    }
}

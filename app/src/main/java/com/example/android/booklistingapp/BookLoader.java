package com.example.android.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.util.List;

/**
 * Created by mostafa on 1/29/18.
 */

public class BookLoader extends AsyncTaskLoader<List<BookData>> {
    private static final String LOG_TAG = BookLoader.class.getName();
    private String url;

    public BookLoader(Context context, String mUrl) {
        super(context);
        this.url = mUrl;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<BookData> loadInBackground() {
        if (url == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<BookData> earthquakes = null;
        try {
            earthquakes = QuaryUtils.fetchData(url);
            return earthquakes;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

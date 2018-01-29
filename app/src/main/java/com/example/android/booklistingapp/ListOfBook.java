package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListOfBook extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookData>> {
    private final static String LOG_TAG = ListOfBook.class.getName();
    private static final int Book_LOADER_ID = 1;
    String url;
    private ArrayList<BookData> list = new ArrayList<>();
    private CustomListAdapter customListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_book);
        Intent i = getIntent();
        url = i.getStringExtra("url");
        //Toast.makeText(this, "" + url, Toast.LENGTH_SHORT).show();
        customListAdapter = new CustomListAdapter(this, new ArrayList<BookData>());
//        ListOfBookAsyncTask listOfBookAsyncTask = new ListOfBookAsyncTask();
//        listOfBookAsyncTask.execute(url);

        ListView list1 = (ListView) findViewById(R.id.list);

        list1.setAdapter(customListAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(Book_LOADER_ID, null, this);
        }
    }

    @Override
    public Loader<List<BookData>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new BookLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<BookData>> loader, List<BookData> data) {
        try {
            customListAdapter.clear();
            if (data != null && !data.isEmpty()) {
                customListAdapter.addAll(data);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    @Override
    public void onLoaderReset(Loader<List<BookData>> loader) {
        // Loader reset, so we can clear out our existing data.
        customListAdapter.clear();
    }

}

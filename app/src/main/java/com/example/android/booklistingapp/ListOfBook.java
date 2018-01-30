package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListOfBook extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookData>> {
    private final static String LOG_TAG = ListOfBook.class.getName();
    private static final int Book_LOADER_ID = 1;
    String url;
    private ArrayList<BookData> list = new ArrayList<>();
    private CustomListAdapter customListAdapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_book);
        Intent i = getIntent();
        textView = (TextView) findViewById(R.id.text11);
        url = i.getStringExtra("url");
        //Toast.makeText(this, "" + url, Toast.LENGTH_SHORT).show();
        customListAdapter = new CustomListAdapter(this, new ArrayList<BookData>());

        ListView list1 = (ListView) findViewById(R.id.list);

        list1.setAdapter(customListAdapter);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BookData bookData = customListAdapter.getItem(i);
                String url = bookData.getmGoogleLink();
                if (url != null) {
                    Intent I = new Intent(Intent.ACTION_VIEW);
                    I.setData(Uri.parse(url));
                    startActivity(I);
                } else {
                    Toast.makeText(ListOfBook.this, "Not Availabe in your country in the Google play.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(Book_LOADER_ID, null, this);
            Toast.makeText(this, "here is a list of " + i.getStringExtra("search item"), Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            textView.setText("No Internet Connection ! ");
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
            // Hide loading indicator because the data has been loaded
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
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

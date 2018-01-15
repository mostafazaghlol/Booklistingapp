package com.example.android.booklistingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class ListOfBook extends AppCompatActivity {
    private final static String LOG_TAG = ListOfBook.class.getName();
    private ArrayList<BookData> list = new ArrayList<>();
    private CustomListAdapter customListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_book);
        Intent i = getIntent();
        String url = i.getStringExtra("url");
        //Toast.makeText(this, "" + url, Toast.LENGTH_SHORT).show();
        customListAdapter = new CustomListAdapter(this, new ArrayList<BookData>());
        ListOfBookAsyncTask listOfBookAsyncTask = new ListOfBookAsyncTask();
        listOfBookAsyncTask.execute(url);
        ListView list1 = (ListView) findViewById(R.id.list);

        list1.setAdapter(customListAdapter);
    }

    class ListOfBookAsyncTask extends AsyncTask<String, Void, ArrayList> {
        ArrayList<BookData> arrayList = new ArrayList<>();

        @Override
        protected ArrayList doInBackground(String... url) {
            try {
                arrayList = QuaryUtils.fetchData(url[0]);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error with Do in background method");
            }
            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList data) {
            try {
                customListAdapter.clear();
                if (data != null && !data.isEmpty()) {
                    customListAdapter.addAll(data);
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, e.getMessage());
            }
        }
    }
}

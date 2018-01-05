package com.example.android.booklistingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BookMainActivity extends AppCompatActivity {
    private final static String LOG_TAG = BookMainActivity.class.getName();
    protected EditText editText;
    protected Button BuSearch;
    private String GoogleApiURL = "https://www.googleapis.com/books/v1/volumes?q=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_main);
        /*
        * initailize the edit text .
         */
        editText = (EditText) findViewById(R.id.volume_description_view);

        /*
        * initailize the edit text .
        */
        BuSearch = (Button) findViewById(R.id.BuSearch);
        BuSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userSelectedName = editText.getText().toString();
                Log.e(LOG_TAG, "search for : " + finishTheUrl(userSelectedName));
            }
        });



    }

    private String finishTheUrl(String searchedWord) {
        StringBuilder url = new StringBuilder(GoogleApiURL);
        if (searchedWord.length() != 0) {
            url.append(searchedWord);
            return url.toString();
        }
        return null;
    }
}

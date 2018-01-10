package com.example.android.booklistingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ListOfBook extends AppCompatActivity {
    private ArrayList<BookData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_book);
        list.add(new BookData("Android", "Mostafa", "50$"));
        list.add(new BookData("Android2", "Mostafa2", "60$"));
        list.add(new BookData("Android3", "Mostafa3", "60$"));
        ListView list1 = (ListView) findViewById(R.id.list);
        CustomListAdapter customListAdapter = new CustomListAdapter(this, list);
        list1.setAdapter(customListAdapter);
    }
}

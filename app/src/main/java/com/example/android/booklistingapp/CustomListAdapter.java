package com.example.android.booklistingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mostafa on 1/6/18.
 */

public class CustomListAdapter extends ArrayAdapter<BookData> {
    public CustomListAdapter(@NonNull Context context, ArrayList<BookData> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView txNameOfBook = (TextView) listItemView.findViewById(R.id.NameOfTheBook);
        txNameOfBook.setText(getItem(position).getmBookName());
        TextView txAuthorOfBook = (TextView) listItemView.findViewById(R.id.NameOfTheAuthor);
        txAuthorOfBook.setText(getItem(position).getmAuthorName().toLowerCase());
        TextView txPrice = (TextView) listItemView.findViewById(R.id.Price);
        txPrice.setText(getItem(position).getmPrice().toLowerCase());
        return listItemView;

    }
}

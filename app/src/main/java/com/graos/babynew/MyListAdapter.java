package com.graos.babynew;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

// ************* to ListView ************** //
class MyListAdapter extends CursorAdapter {

    private LayoutInflater inflater;
    private TextView my_exam;
    private TextView my_date;

    public MyListAdapter(Context context, Cursor cursor) {
        super(context, cursor, true);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        my_exam = (TextView) view.findViewById(R.id.textView_exam);
        my_date = (TextView) view.findViewById(R.id.textView_date);

        if(cursor.getString(1)!= null){
            my_exam.setText(cursor.getString(1));
        }

        if(cursor.getString(2) != null){
            my_date.setText(cursor.getString(2));
        }

        notifyDataSetChanged();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup)
    {
        return inflater.inflate(R.layout.any_list, viewGroup, false);
    }
}

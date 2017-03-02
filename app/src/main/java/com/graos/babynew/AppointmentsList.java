package com.graos.babynew;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class AppointmentsList extends AppCompatActivity{

    String the_exam;
    String the_date;

    SQLiteDatabase db;
    ListView _my_list;
    AssignmentsDBHelper dbHelper;
    Cursor cursor;
    MyListAdapter mla;
    String [] projection = new String[]{Constants.Items._ID,Constants.Items.EXAM, Constants.Items.DATE };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointments_list);
        setTitle("הבדיקות שלי");

        Intent i_result = getIntent();
        the_exam = i_result.getStringExtra("EXAMINATION");
        the_date = i_result.getStringExtra("THEDATE");
        //Toast.makeText(this,"Date in new intent: "+the_date,Toast.LENGTH_SHORT).show();

        _my_list = (ListView) findViewById(R.id.listItems);

        // DB
        dbHelper = new AssignmentsDBHelper(this);
        db = dbHelper.getWritableDatabase(); //lock to write and read

        // Insert data to DB
        insertToDB();

       // DB - Cursor approaching to the TableDB-Contacts
        cursor = db.query(Constants.Items.TABLE_NAME, null, null, null, null, null, null);

        mla = new MyListAdapter(this, cursor);
        _my_list.setAdapter(mla);
        _my_list.invalidateViews();
        db.close();

    }

    // -------------------- Insert To DB --------------------------------------------
    public void insertToDB() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values;

        if(the_exam != null && the_date !=null){
            values = new ContentValues();
            values.put(Constants.Items.EXAM, the_exam);
            values.put(Constants.Items.DATE, the_date);
            db.insert(Constants.Items.TABLE_NAME, null, values);
            cursor = db.query(Constants.Items.TABLE_NAME, projection, null, null, null, null, null);
            mla = new MyListAdapter(this, cursor);
            _my_list.setAdapter(mla);
        }

    }

}

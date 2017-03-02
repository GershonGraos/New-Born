package com.graos.babynew;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LoginPage extends AppCompatActivity {

    private final long FOR_DAYS = 86400000;

    private EditText nameText;
    private EditText lastDateText;

    String name;
    String the_date;

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    Date date_temp;
    long date_long;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        nameText = (EditText)findViewById(R.id.nameText);
        lastDateText = (EditText)findViewById(R.id.lastDateText);

        // Show Calendar
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

    }


    // ----------------- Select Date ------------------------------------------------
    public void Set_Date(View view){
        new DatePickerDialog(this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void updateLabel(){
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_temp = myCalendar.getTime();
        date_long = date_temp.getTime();
        lastDateText.setText(sdf.format(myCalendar.getTime()));
        the_date = sdf.format(myCalendar.getTime());
    }


    // -------------------- Check Date ----------------------------------------------
    private boolean checkDate() {
        Date d = new Date();
        if (d.before(date_temp))
            return false;
        long l = d.getTime();
        long temp = 300*FOR_DAYS;
        long re = l - temp;
        d.setTime(re);
        if (date_temp.before(d)){
//            Toast.makeText(this,"2" + d.toString(),Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    // -------------------- Button OK ----------------------------------------------
    public void press_OK(View view){
        name = nameText.getText().toString();
        if( !name.equals("") && the_date != null && checkDate()){
            name = nameText.getText().toString();
            Intent i = new Intent(this, MainPage.class);
            i.putExtra("THENAME", name);
            i.putExtra("THEDATE", date_long);
            startActivity(i);
        }
        else
            Toast.makeText(this,"יש להכניס שם ותאריך תקין",Toast.LENGTH_SHORT).show();
    }
}

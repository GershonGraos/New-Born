package com.graos.babynew;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MakeAnAppointment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText _choose_date;
    Spinner _spinner_examination;

    private final long FOR_DAYS = 86400000;
    Date date_temp;

    ArrayAdapter<CharSequence> adapter;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    String s_examination;
    String s_date;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_an_appointment);
        setTitle("לקבוע בדיקות");

        _spinner_examination = (Spinner) findViewById(R.id.spinnerExaminations);
        _choose_date = (EditText) findViewById(R.id.TextChooseDate);


        // Show Calendar
         myCalendar = Calendar.getInstance();
         date = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        // Show the spinner
        _spinner_examination.setOnItemSelectedListener(this);
        adapter = ArrayAdapter.createFromResource(MakeAnAppointment.this,R.array.spinner_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        //adapter.setDropDownViewResource(android.R.layout.select_dialog_multichoice);
        _spinner_examination.setAdapter(adapter);
    }



    // ------------ Selected Examination --------------------------------------------
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
        s_examination = adapterView.getSelectedItem().toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this," Please Select Examination ",Toast.LENGTH_SHORT).show();
    }


    // ---------------- Select Date ------------------------------------------------
    public void Set_Date_Examination(View view){
        new DatePickerDialog(this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void updateLabel(){
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_temp = myCalendar.getTime();
        _choose_date.setText(sdf.format(myCalendar.getTime()));
         s_date = sdf.format(myCalendar.getTime());
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
            return false;
        }
        return true;
    }


    // -------------- BUTTON Next ------------------------------------------------
    public void press_Next(View view){
        if( s_date != null && checkDate()==false){
            Intent i = new Intent(this,AppointmentsList.class);
            i.putExtra("EXAMINATION", s_examination);
            i.putExtra("THEDATE", s_date);
            startActivity(i);
            _choose_date.setText("");
            s_date = null;
        }

        else
            Toast.makeText(this,"יש להכניס תאריך תקין",Toast.LENGTH_SHORT).show();
    }

}

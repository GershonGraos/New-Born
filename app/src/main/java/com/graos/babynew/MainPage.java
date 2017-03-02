package com.graos.babynew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainPage extends AppCompatActivity {

    TextView nameText;
    TextView weekText;
    TextView weightText;
    TextView birthText;
    TextView nextExamText;
    TextView sinceText;
    TextView tillText;

    String s_name;
    Long l_date;

    private final long FOR_DAYS = 86400000;
    private final int PREGNANCY_TIME = 281;
    private final String WEEK_TEXT = "משקל התינוק הממוצע הוא: ";
    private final String NEXT_EXAM = "הבדיקה הבאה היא: ";
    private final String SINCE_DATE = "ניתן לקבוע מתאריך: ";
    private final String TILL_DATE = "עד תאריך: ";

    long days;
    int week;
    int daysWeek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Intent i_result = getIntent();
        s_name = i_result.getStringExtra("THENAME");
        l_date = i_result.getLongExtra("THEDATE", 0);
        days = l_date/FOR_DAYS;

        nameText = (TextView)findViewById(R.id.nameText);
        weekText = (TextView)findViewById(R.id.weekText);
        weightText = (TextView)findViewById(R.id.weightText);
        birthText = (TextView)findViewById(R.id.birthText);
        nextExamText = (TextView)findViewById(R.id.nextExamText);
        sinceText = (TextView)findViewById(R.id.sinceText);
        tillText = (TextView)findViewById(R.id.tillText);

        // Add Data
        nameText.setText("שלום, "+s_name);
        makeWeek();
        makeWeight();
        makeBirthDate();
        makeNextExam();
    }


    // ------------------------ Next Exam ----------------------------------------------
    private void makeNextExam() {
        Date firstDate = new Date();
        Date lastDate = new Date();
        if (week < 11)
        {
            nextExamText.setText(NEXT_EXAM + "בדיקות דם ושתן");
            firstDate.setTime((days + 49)*FOR_DAYS);
            lastDate.setTime((days + 77)*FOR_DAYS);
            sinceText.setText(SINCE_DATE + changeDateToString(firstDate));
            tillText.setText(TILL_DATE + changeDateToString(lastDate));
        }
        else if (week < 11)
        {
            nextExamText.setText(NEXT_EXAM + "אולטרה סאונד");
            firstDate.setTime((days + 49)*FOR_DAYS);
            lastDate.setTime((days + 77)*FOR_DAYS);
            sinceText.setText(SINCE_DATE + changeDateToString(firstDate));
            tillText.setText(TILL_DATE + changeDateToString(lastDate));
        }
        else if (week < 14 && week >= 11)
        {
            nextExamText.setText(NEXT_EXAM + "שקיפות עורפית");
            firstDate.setTime((days + 77)*FOR_DAYS);
            lastDate.setTime((days + 97)*FOR_DAYS);
            sinceText.setText(SINCE_DATE + changeDateToString(firstDate));
            tillText.setText(TILL_DATE + changeDateToString(lastDate));
        }
        else if (week < 18 && week >= 13)
        {
            nextExamText.setText(NEXT_EXAM + "סקירה מוקדמת");
            firstDate.setTime((days + 91)*FOR_DAYS);
            lastDate.setTime((days + 119)*FOR_DAYS);
            sinceText.setText(SINCE_DATE + changeDateToString(firstDate));
            tillText.setText(TILL_DATE + changeDateToString(lastDate));
        }
        else if (week < 21 && week >= 16)
        {
            nextExamText.setText(NEXT_EXAM + "חלבון עוברי");
            firstDate.setTime((days + 112)*FOR_DAYS);
            lastDate.setTime((days + 147)*FOR_DAYS);
            sinceText.setText(SINCE_DATE + changeDateToString(firstDate));
            tillText.setText(TILL_DATE + changeDateToString(lastDate));
        }
        else if (week >= 16)
        {
            nextExamText.setText(NEXT_EXAM + "מי שפיר");
            firstDate.setTime((days + 112)*FOR_DAYS);
            lastDate.setTime((days + 280)*FOR_DAYS);
            sinceText.setText(SINCE_DATE + changeDateToString(firstDate));
            tillText.setText(TILL_DATE + changeDateToString(lastDate));
        }
        else if (week < 26 && week >= 19)
        {
            nextExamText.setText(NEXT_EXAM + "סקירת מערכות");
            firstDate.setTime((days + 133)*FOR_DAYS);
            lastDate.setTime((days + 182)*FOR_DAYS);
            sinceText.setText(SINCE_DATE + changeDateToString(firstDate));
            tillText.setText(TILL_DATE + changeDateToString(lastDate));
        }
        else if (week < 29 && week >= 24)
        {
            nextExamText.setText(NEXT_EXAM + "סוכרת הריונית");
            firstDate.setTime((days + 168)*FOR_DAYS);
            lastDate.setTime((days + 203)*FOR_DAYS);
            sinceText.setText(SINCE_DATE + changeDateToString(firstDate));
            tillText.setText(TILL_DATE + changeDateToString(lastDate));
        }
        else if (week < 35 && week >= 30)
        {
            nextExamText.setText(NEXT_EXAM + "אולטרה סאונד");
            firstDate.setTime((days + 210)*FOR_DAYS);
            lastDate.setTime((days + 245)*FOR_DAYS);
            sinceText.setText(SINCE_DATE + changeDateToString(firstDate));
            tillText.setText(TILL_DATE + changeDateToString(lastDate));
        }
    }


    // -------------------- Change Date ------------------------------------------------
    private String changeDateToString(Date d) {
        int year = d.getYear() + 1900;
        int month = d.getMonth() + 1;
        int day = d.getDate() + 1;
        return "" + day +"/" + month + "/" + year;
    }


    // ----------------- Birth Date-----------------------------------------------------
    private void makeBirthDate() {
        long tempBirth = days + PREGNANCY_TIME;
        tempBirth = tempBirth * FOR_DAYS;
        Date tempDate = new Date();
        tempDate.setTime(tempBirth);
        birthText.setText("תאריך לידה משוער: " + changeDateToString(tempDate));
    }


    // ------------------ Week Pregnant------------------------------------------------
    private void makeWeek() {
        Date tempDate = new Date();
        long tempDays = tempDate.getTime();
        tempDays = tempDays/FOR_DAYS;
        tempDays = tempDays - days;
        week = (int)tempDays/7;
        daysWeek = (int)tempDays%7;
        weekText.setText("הנך נמצאת בשבוע: "+week + " + " + daysWeek + " ימים");
    }


    // -------------------- Baby Weight-------------------------------------------------------
    private void makeWeight() {
        if (week < 6)
            weightText.setText(WEEK_TEXT + "פחות מ-1 גרם");
        else if (week < 8 && week >= 6)
            weightText.setText(WEEK_TEXT + "1 גרם");
        else if (week < 10 && week >= 8)
            weightText.setText(WEEK_TEXT + "5 גרם");
        else if (week < 12 && week >= 10)
            weightText.setText(WEEK_TEXT + "20 גרם");
        else if (week < 14 && week >= 12)
            weightText.setText(WEEK_TEXT + "60 גרם");
        else if (week < 16 && week >= 14)
            weightText.setText(WEEK_TEXT + "120 גרם");
        else if (week < 18 && week >= 16)
            weightText.setText(WEEK_TEXT + "220 גרם");
        else if (week < 20 && week >= 18)
            weightText.setText(WEEK_TEXT + "330 גרם");
        else if (week < 22 && week >= 20)
            weightText.setText(WEEK_TEXT + "460 גרם");
        else if (week < 24 && week >= 22)
            weightText.setText(WEEK_TEXT + "650 גרם");
        else if (week < 26 && week >= 24)
            weightText.setText(WEEK_TEXT + "850 גרם");
        else if (week < 28 && week >= 26)
            weightText.setText(WEEK_TEXT + "1100 גרם");
        else if (week < 30 && week >= 28)
            weightText.setText(WEEK_TEXT + "1420 גרם");
        else if (week < 32 && week >= 30)
            weightText.setText(WEEK_TEXT + "1750 גרם");
        else if (week < 34 && week >= 32)
            weightText.setText(WEEK_TEXT + "2080 גרם");
        else if (week < 36 && week >= 34)
            weightText.setText(WEEK_TEXT + "2420 גרם");
        else if (week < 38 && week >= 36)
            weightText.setText(WEEK_TEXT + "2900 גרם");
        else if (week < 45 && week >= 38)
            weightText.setText(WEEK_TEXT + "3250 גרם");
    }



    // ------------------- BUTTONS --------------------------------
    public void press_My_Exams(View v){
        Intent i_my_exams = new Intent(this, AppointmentsList.class);
        startActivity(i_my_exams);
    }

    public void press_Make_Exam(View v){
        Intent i_make_exam = new Intent(this, MakeAnAppointment.class);
        startActivity(i_make_exam);
    }

    public void press_view_all_exams(View v){
        Intent i_all_exams = new Intent(this, AllExamsPage.class);
        startActivity(i_all_exams);
    }

    public void press_sign_Out(View v){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}





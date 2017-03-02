package com.graos.babynew;

import android.provider.BaseColumns;

// **************** CREATE CONTACTS TABLE *********************
public final class Constants
{
    private Constants(){
        throw  new AssertionError(" Can't create constants class");
    }

    public static abstract  class Items implements BaseColumns {
        public static final String TABLE_NAME = "examsTable";
        public static final String EXAM = "Exam";
        public static final String DATE = "Date";
    }
}

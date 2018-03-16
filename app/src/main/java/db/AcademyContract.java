package db;

import android.provider.BaseColumns;

/**
 * Created by Velimir on 3/7/2018.
 */

public final class AcademyContract {


    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Academy";

    private AcademyContract(){}

    public static class StudentTable implements BaseColumns{



        public static final String TABLE_NAME = "student";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LASTNAME = "lastname";
        public static final String COLUMN_YEAR_OF_REGISTRATION = "year_of_registration";
        public static final String COLUMN_POINTS_NUMBER = "number_of_points";


        public static final String SQL_CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +  _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_LASTNAME + " TEXT," +
                COLUMN_YEAR_OF_REGISTRATION + " INTEGER," +
                COLUMN_POINTS_NUMBER + " INTEGER)";


        public static final String SQL_DELETE_STUDENT_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }




}

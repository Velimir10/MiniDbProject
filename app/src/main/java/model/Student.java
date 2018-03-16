package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

import java.util.ArrayList;


import db.AcademyContract.StudentTable;

import db.DatabaseHelper;

public class Student {

    private Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase db;


    private long id;
    private String name;
    private String lastname;
    private int yearOfReg;
    private int points;


    public Student(String name, String lastname, int yearOfReg, int points, Context context) {

        this.name = name;
        this.lastname = lastname;
        this.yearOfReg = yearOfReg;
        this.points = points;
        this.context = context;

    }

    public Student(long id, String name, String lastname, int yearOfReg, int points) {

        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.yearOfReg = yearOfReg;
        this.points = points;

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getYearOfReg() {
        return yearOfReg;
    }

    public int getPoints() {
        return points;
    }


    public boolean insert() {

        boolean success = false;

        if (helper == null) {
            helper = new DatabaseHelper(context);
        }

        db = helper.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put(StudentTable.COLUMN_NAME, name);
            values.put(StudentTable.COLUMN_LASTNAME, lastname);
            values.put(StudentTable.COLUMN_YEAR_OF_REGISTRATION, yearOfReg);
            values.put(StudentTable.COLUMN_POINTS_NUMBER, points);

            long rowId = db.insert(StudentTable.TABLE_NAME, null, values);

            if (rowId != -1) {

                this.id = rowId;
                success = true;

            }

        } catch (Exception e) {

            Log.e("Exception ---> ", e.toString());

        } finally {

            db.close();
        }

        return success;

    }

    public static void deleteTable(Context context) {

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL(StudentTable.SQL_DELETE_STUDENT_TABLE);
        db.close();

    }

    public static boolean tableExist(Context context) {

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        if (StudentTable.TABLE_NAME == null || db == null || !db.isOpen()) {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' AND name = 'student'", null);
        if (!cursor.moveToFirst()) {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    public static ArrayList<Student> read(Context context) {

        ArrayList<Student> lista = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM student WHERE number_of_points >= 80 LIMIT 5", null);

        try {

            while (cursor.moveToNext()) {

                long id = cursor.getInt(cursor.getColumnIndexOrThrow(StudentTable._ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(StudentTable.COLUMN_NAME));
                String lastname = cursor.getString(cursor.getColumnIndexOrThrow(StudentTable.COLUMN_LASTNAME));
                int yearOfReg = cursor.getInt(cursor.getColumnIndexOrThrow(StudentTable.COLUMN_YEAR_OF_REGISTRATION));
                int points = cursor.getInt(cursor.getColumnIndexOrThrow(StudentTable.COLUMN_POINTS_NUMBER));

                lista.add(new Student(id, name, lastname, yearOfReg, points));
            }

        } catch (Exception e) {

            Log.e("Exception ---> ", e.toString());

        } finally {

            cursor.close();
            db.close();

        }

        return lista;

    }


}

package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import model.Student;


public class RepositoryManager {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    private static RepositoryManager manager;


    private RepositoryManager() {}


    public static RepositoryManager getInstance(){

        if(manager == null)
            manager = new RepositoryManager();
        return manager;

    }

    public boolean insert(Student student, Context context) {

        boolean success = false;

        if (helper == null) {
            helper = new DatabaseHelper(context);
        }

        db = helper.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put(AcademyContract.StudentTable.COLUMN_NAME, student.getName());
            values.put(AcademyContract.StudentTable.COLUMN_LASTNAME, student.getLastname());
            values.put(AcademyContract.StudentTable.COLUMN_YEAR_OF_REGISTRATION, student.getYearOfReg());
            values.put(AcademyContract.StudentTable.COLUMN_POINTS_NUMBER, student.getPoints());

            long rowId = db.insert(AcademyContract.StudentTable.TABLE_NAME, null, values);

            if (rowId != -1) {

                student.setId(rowId);
                success = true;

            }

        } catch (Exception e) {

            Log.e("Exception ---> ", e.toString());

        } finally {

            db.close();
        }

        return success;

    }

    public void deleteTable(Context context) {

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL(AcademyContract.StudentTable.SQL_DELETE_STUDENT_TABLE);
        db.close();

    }

    public boolean tableExist(Context context) {

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        if (AcademyContract.StudentTable.TABLE_NAME == null || db == null || !db.isOpen()) {
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

    public  ArrayList<Student> read(Context context) {

        ArrayList<Student> lista = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM student WHERE number_of_points >= 80 LIMIT 5", null);

        try {

            while (cursor.moveToNext()) {

                long id = cursor.getInt(cursor.getColumnIndexOrThrow(AcademyContract.StudentTable._ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(AcademyContract.StudentTable.COLUMN_NAME));
                String lastname = cursor.getString(cursor.getColumnIndexOrThrow(AcademyContract.StudentTable.COLUMN_LASTNAME));
                int yearOfReg = cursor.getInt(cursor.getColumnIndexOrThrow(AcademyContract.StudentTable.COLUMN_YEAR_OF_REGISTRATION));
                int points = cursor.getInt(cursor.getColumnIndexOrThrow(AcademyContract.StudentTable.COLUMN_POINTS_NUMBER));

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

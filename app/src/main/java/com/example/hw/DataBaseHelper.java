package com.example.hw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String STUDENT_TABLE = "STUDENT_TABLE";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_PRIORITY = "PRIORITY";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "student_list.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + STUDENT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_STUDENT_NAME + " TEXT, " + COLUMN_PRIORITY + " INT )";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(studentModel student_list){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_STUDENT_NAME, student_list.getName());
        cv.put(COLUMN_PRIORITY, student_list.getPriority());

        long insert = db.insert(STUDENT_TABLE, null, cv);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean deleteOne(studentModel student_list){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + STUDENT_TABLE + " WHERE " + COLUMN_ID + " = " + student_list.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }

    }

    public List<studentModel> getEveryone(){
        List<studentModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + STUDENT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int studentID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                String priority = cursor.getString(2);

                studentModel newStudent = new studentModel(studentID, studentName, priority);
                returnList.add(newStudent);

            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }
}

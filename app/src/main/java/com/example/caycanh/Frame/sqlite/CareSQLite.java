package com.example.caycanh.Frame.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.caycanh.Frame.model.Care;

import java.util.ArrayList;
import java.util.List;

public class CareSQLite extends SQLiteOpenHelper {
    public static final String DATABASE = "caycanh2.db";
    private static final String TABLE_NAME = "care";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String IMAGE = "image";
    private static final String COUNT = "count";

    public CareSQLite( Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTB = "CREATE TABLE " +
                TABLE_NAME + " ( " +
                ID + " TEXT PRIMARY KEY," +
//                MSP + " TEXT PRIMARY KEY,  " +
                TITLE + " TEXT, " +
                CONTENT + " TEXT, " +
                IMAGE + " TEXT)";
        db.execSQL(createTB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTB = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTB);
        onCreate(db);
    }

    public void InsertCare(Care Care) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, Care.getId_care());
        contentValues.put(TITLE, Care.getTitle_care());
        contentValues.put(CONTENT, Care.getContent_care());
        contentValues.put(IMAGE, Care.getImage_care());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public Care getCare(String msp) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                null,
                ID + " =?",
                new String[]{String.valueOf(msp)}, null, null, null);

        if (!cursor.isAfterLast()){
            cursor.moveToFirst();
            Care Care = new Care(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            return Care;

        }
        return null;


    }

    public List<Care> getAllCare(){
        List<Care> CareList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Care Care = new Care(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            CareList.add(Care);
            cursor.moveToNext();
        }
        return CareList;
    }

    public void updateCare(Care Care){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(TITLE, Care.getTitle_care());
        contentValues.put(CONTENT, Care.getContent_care());
        contentValues.put(IMAGE, Care.getImage_care());

        db.update(TABLE_NAME,contentValues,ID + " = ? ", new String[]{Care.getId_care()});
        db.close();
    }

    public void deleteCare(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ? ", new String[]{id});
        db.close();
    }

    public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String drop = "DELETE FROM  " + TABLE_NAME;
        db.execSQL(drop);
        db.close();
    }

    //    public void deleteAllCare(){
//
//    }

    public int getItemsCount(){
        Cursor cursor = this.getReadableDatabase().
                rawQuery("SELECT * FROM " + TABLE_NAME,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}

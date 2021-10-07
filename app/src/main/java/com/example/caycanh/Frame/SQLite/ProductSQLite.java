package com.example.caycanh.Frame.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.caycanh.Frame.OOP.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductSQLite extends SQLiteOpenHelper {

    public static final String DATABASE = "caycanh.db";
    private static final String TABLE_NAME = "productRecent";
    private static final String ID = "id";
    private static final String MSP = "msp";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String DESCRIPT = "descript";
    private static final String STATUS = "status";
    private static final String IMAGES = "images";

    public ProductSQLite(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTB = "CREATE TABLE " +
                TABLE_NAME + " ( " +
//                ID + " INTEGER PRIMARY KEY," +
                MSP + " TEXT PRIMARY KEY,  " +
                NAME + " TEXT, " +
                DESCRIPT + " TEXT, " +
                STATUS + " BIT, " +
                IMAGES + " TEXT, " +
                PRICE + " INTEGER )";
        db.execSQL(createTB);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            String dropTB = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(dropTB);
            onCreate(db);
        }

    }

    public void InsertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MSP, product.getMsp());
        contentValues.put(NAME, product.getName());
        contentValues.put(DESCRIPT, product.getDescript());
        contentValues.put(STATUS, product.isStatus());
        contentValues.put(IMAGES, product.getImages());
        contentValues.put(PRICE, product.getPrice());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public Product getProduct(String msp) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                null,
                MSP + " =?",
                new String[]{String.valueOf(msp)}, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
            Product product = new Product(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    Boolean.valueOf(cursor.getString(3)),
                    cursor.getString(4),
                    cursor.getInt(5));
            return product;

        }
        return null;


    }

    public List<Product> getAllProduct(){
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Product product = new Product(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    Boolean.valueOf(cursor.getString(3)),
                    cursor.getString(4),
                    cursor.getInt(5));
            productList.add(product);
            cursor.moveToNext();
        }
        return productList;
    }

    public void updateProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(NAME, product.getName());
        contentValues.put(DESCRIPT, product.getDescript());
        contentValues.put(STATUS, product.isStatus());
        contentValues.put(IMAGES, product.getImages());
        contentValues.put(PRICE, product.getPrice());

        db.update(TABLE_NAME,contentValues,MSP + " = ? ", new String[]{product.getMsp()});
        db.close();
    }

    public void deleteProduct(String msp){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, MSP + " = ? ", new String[]{msp});
        db.close();
    }

    public void dropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String drop = "DROP TABLE " + TABLE_NAME;
        db.execSQL(drop);
        db.close();
    }

//    public void deleteAllProduct(){
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

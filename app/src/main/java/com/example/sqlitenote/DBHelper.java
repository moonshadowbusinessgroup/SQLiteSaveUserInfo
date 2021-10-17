package com.example.sqlitenote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DB1";
    private static final String TABLE_NAME = "TB1";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String Order_No = "Order_No";
    private static final String status = "status";
    private static final String User_Name = "User_Name";
    private static final String User_Location = "User_Location";
    private static final String Phone_Number = "Phone_Number";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE " + TABLE_NAME + " (" +
                Order_No + " INTEGER PRIMARY KEY," +
                status + " INTEGER," +
                User_Name + " TEXT," +
                User_Location + " TEXT," +
                Phone_Number + " TEXT" + " )";

        db.execSQL(createDb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addData(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(status, model.getStatus());
        v.put(User_Name, model.getUser_Name());
        v.put(User_Location, model.getUser_Location());
        v.put(Phone_Number, model.getPhone_Number());

        long ID = db.insert(TABLE_NAME, null, v);
        return ID;
    }

    public Model getData(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] query = new String[]{Order_No, status, User_Name, User_Location, Phone_Number};
        Cursor cursor = db.query(TABLE_NAME, query, Order_No + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return new Model(
                Long.parseLong(cursor.getString(0)),
                Long.parseLong(cursor.getString(1)),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
    }

    public ArrayList<Model> getAllData() {
        ArrayList<Model> allModels = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + Order_No + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Model model = new Model();
                model.setOrder_No(Long.parseLong(cursor.getString(0)));
                model.setStatus(Long.parseLong(cursor.getString(1)));
                model.setUser_Name(cursor.getString(2));
                model.setUser_Location(cursor.getString(3));
                model.setPhone_Number(cursor.getString(4));
                allModels.add(model);
            } while (cursor.moveToNext());
        }
        return allModels;
    }

    public void updateData(long orderNo, long status) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET status = " + status + " WHERE " + Order_No + " = " + orderNo);
        db.close();
    }
}

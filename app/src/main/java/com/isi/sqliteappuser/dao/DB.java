package com.isi.sqliteappuser.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    //USER
    public static  final String TABLE_NAME_USER="user";
    public static  final String Col_ID_USER="id";
    public static  final String Col_EMAIL_USER="email";
    public static  final String Col_PWD_USER="password";
    public static  final String Col_ETAT_USER="etat";
   //public static  final String CREATE_TABLE_USER=" CREATE TABLE user( id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT NOT NULL, password TEXT NOT NULL, etat INTEGER NOT NULL );";
    public static  final String CREATE_TABLE_USER=" CREATE TABLE " +
            TABLE_NAME_USER+" ( " +Col_ID_USER+
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Col_EMAIL_USER+" TEXT NOT NULL, "+
            Col_PWD_USER+" TEXT NOT NULL, "+
            Col_ETAT_USER+" INTEGER NOT NULL );";

    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_NAME_USER);
        onCreate(db);
    }
}

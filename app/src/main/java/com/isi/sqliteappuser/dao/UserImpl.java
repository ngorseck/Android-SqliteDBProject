package com.isi.sqliteappuser.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.isi.sqliteappuser.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserImpl implements IUser {
    private static final String TABLE_USER = "user";
    private static final int VERSION = 1;
    private static final String NOM_BASE = "dbappuser.db";
    private SQLiteDatabase db;
    private DB dbUser;

    public UserImpl(Context context){
        dbUser = new DB(context, NOM_BASE, null, VERSION);
    }

    public void openForWrite(){
        db = dbUser.getWritableDatabase();
    }

    public void openForRead(){
        db = dbUser.getReadableDatabase();
    }

    public void close(){
        db.close();
    }


    @Override
    public long add(User user) {
        openForWrite();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", user.getEmail());
        contentValues.put("password", user.getPassword());
        contentValues.put("etat", user.getEtat());

        return db.insert(TABLE_USER, null, contentValues);
    }

    @Override
    public long update(User user) {
        openForWrite();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", user.getEmail());
        contentValues.put("password", user.getPassword());
        contentValues.put("etat", user.getEtat());

        return db.update(TABLE_USER, contentValues, " id = " + user.getId(), null);
    }

    @Override
    public long delete(int id) {
        openForWrite();
        return db.delete(TABLE_USER, " id = " + id, null);
    }

    @Override
    public User get(int id) {
        openForRead();
        Cursor cursor = db.query(TABLE_USER,
                new String[]{"id", "email", "password", "etat"},
                " id = " + id, null, null, null, null);
        if (cursor.getCount() == 0){
            return null;
        }else {
            User user = new User();
            cursor.moveToFirst();
            user.setId(cursor.getInt(0));
            user.setEmail(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setEtat(cursor.getInt(3));

            return user;
        }
    }

    @Override
    public List<User> getAll() {
        openForRead();
        Cursor cursor = db.query(TABLE_USER,
                new String[]{"id", "email", "password", "etat"},
                null, null, null, null, null);
        if (cursor.getCount() == 0){
            return null;
        }else {
            List<User> users = new ArrayList<>();

            cursor.moveToFirst();
            while (!cursor.isLast())
            {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setEmail(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setEtat(cursor.getInt(3));

                users.add(user);

                cursor.moveToNext();
            }
            if (cursor.isLast())
            {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setEmail(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setEtat(cursor.getInt(3));

                users.add(user);

                cursor.moveToNext();
            }
            cursor.close();
            close();

            return users;
        }
    }

    @Override
    public User login(String emailParam, String passwordParam) {
        openForRead();
        Cursor cursor = db.query(TABLE_USER,
                new String[]{"id", "email", "password", "etat"},
                " email = '" + emailParam + "' and password = '" + passwordParam + "'", null, null, null, null);
        if (cursor.getCount() == 0){
            return null;
        }else {
            User user = new User();
            cursor.moveToFirst();
            user.setId(cursor.getInt(0));
            user.setEmail(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setEtat(cursor.getInt(3));

            return user;
        }
    }
}

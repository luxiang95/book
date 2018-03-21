package com.example.forceoffline.Tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luxia on 2018/3/19.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_USER = "create table user ("
            + "id integer  primary key autoincrement,"
            + "user text ,"
            + "pwd text)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

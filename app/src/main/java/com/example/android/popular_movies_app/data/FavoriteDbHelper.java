package com.example.android.popular_movies_app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FavoriteDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favotite.db";

    private static final int DATABASE_VERSION = 1;

    public static final String LOGTAG = "FAVORITE";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase db;

    public FavoriteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void open(){
        Log.i(LOGTAG, "Database opened");
        db = dbhandler.getWritableDatabase();

    }

    public void close() {
        Log.i(LOGTAG, "Database closed");
        dbhandler.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE_TABLE " + FavoriteContract.FavoriteEntry. TABLE_NAME + " (" +
                FavoriteContract.FavoriteEntry._ID + " INTERGER PRIMARY KEY AUTOINCREMENT, " +
                FavoriteContract.FavoriteEntry.


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    }


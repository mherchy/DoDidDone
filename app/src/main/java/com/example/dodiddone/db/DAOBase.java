package com.example.dodiddone.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class DAOBase {

    protected final static int DB_VERSION = 5;

    protected final static String DB_NAME = "dodiddone";

    protected static SQLiteDatabase mDb = null;
    protected static DatabaseHandler mHandler = null;

    public DAOBase(Context pContext) {
        mHandler = new DatabaseHandler(pContext, DB_NAME, null, DB_VERSION);
    }

    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}

package com.example.dodiddone.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.*;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.resetDb(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.resetDb(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.resetDb(db);
    }

    private void resetDb(SQLiteDatabase db) {
        //Drop
        db.execSQL(LigneDAO.TABLE_DROP);
        db.execSQL(PageDAO.TABLE_DROP);
        db.execSQL(RegleDAO.TABLE_DROP);
        db.execSQL(CahierDAO.TABLE_DROP);
        //Create
        db.execSQL(CahierDAO.TABLE_CREATE);
        db.execSQL(RegleDAO.TABLE_CREATE);
        db.execSQL(PageDAO.TABLE_CREATE);
        db.execSQL(LigneDAO.TABLE_CREATE);
    }
}

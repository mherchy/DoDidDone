package com.example.dodiddone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.dodiddone.db.DAOBase;
import com.example.dodiddone.metier.Cahier;

public class CahierDAO extends DAOBase {

    public static final String TABLE_NAME = "cahier";
    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NOM + " TEXT" +
            "); ";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + "; ";


    public CahierDAO(Context pContext) {
        super(pContext);
    }


    public long insert(Cahier c) {
        ContentValues row = new ContentValues();
        row.put(COL_NOM, c.getNom());
        return mDb.insertOrThrow(TABLE_NAME,null, row);
    }


    /**
     */
    public int remove(long id) {
        return mDb.delete(TABLE_NAME, COL_ID+" = ?", new String[] {""+id});
    }

    /**
     */
    public int update(Cahier c) {
        ContentValues row = new ContentValues();
        row.put(COL_NOM, c.getNom());
        return mDb.update(TABLE_NAME, row, COL_ID+" = ?", new String[] {""+c.getID()});
    }

    /**
     * @param id
     * @return
     */
    public Cahier select(long id) {
        Cursor cursor = mDb.rawQuery("SELECT * FROM "+ TABLE_NAME + " WHERE id = ?", new String[] {""+id});
        cursor.moveToFirst();
        Cahier c = new Cahier(cursor.getLong(0), cursor.getString(1));
        cursor.close();
        return c;
    }

}

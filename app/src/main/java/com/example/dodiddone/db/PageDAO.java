package com.example.dodiddone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Page;

import java.util.HashSet;
import java.util.Set;

public class PageDAO extends AbstractDAO {

    public static final String TABLE_NAME = "page";
    public static final String COL_ID = "id";
    public static final String COL_CREATION = "creation";
    public static final String COL_FK_CAHIER = "fk_cahier";

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_CREATION + " INTEGER, " +
                    COL_FK_CAHIER + " INTEGER, " +
                    "FOREIGN KEY(" + COL_FK_CAHIER + ") REFERENCES " + CahierDAO.TABLE_NAME + "(" + CahierDAO.COL_ID + ")" +
            "); ";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + "; ";

    private Cahier pCahier;

    public PageDAO(Context pContext, Cahier pCahier) {
        super(pContext, TABLE_NAME);
        this.pCahier = pCahier;
    }

    /**
     * @param e Page
     * @return boolean
     */
    public boolean insert(Page e) {
        ContentValues row = new ContentValues();
        row.put(COL_CREATION, e.getCreation().getTime());
        row.put(COL_FK_CAHIER, this.pCahier.getId());
        long id = this.insert(row);
        if(id != -1) {
            e.setId(id);
            return true;
        }
        return false;
    }

    /**
     */
    public boolean update(Page e) {
        ContentValues row = new ContentValues();
        row.put(COL_CREATION, e.getCreation().getTime());
        row.put(COL_FK_CAHIER, this.pCahier.getId());
        return this.update(e.getId(), row) > 0;
    }



    /**
     * @return HashSet<Page>
     * @// TODO: 23/05/2020 change to likedList
     */
    public HashSet<Page> selectCahierPages() {
        HashSet<Page> list = new HashSet<>();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_FK_CAHIER + " = ?", new String[] {""+this.pCahier.getId()});
        while(cursor.moveToNext()) {
            Page p = new Page(cursor.getLong(0), cursor.getLong(1), pCahier);
            list.add(p);
        }
        cursor.close();
        return list;
    }

}

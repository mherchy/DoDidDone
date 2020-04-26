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


    public PageDAO(Context pContext) {
        super(pContext, TABLE_NAME);
    }


    public long insert(Page p) {
        ContentValues row = new ContentValues();
        row.put(COL_CREATION, p.getCreation().getTime());
        row.put(COL_FK_CAHIER, p.getCahierRef());
        return this.insert(row);
    }

    /**
     */
    public int update(Page p) {
        ContentValues row = new ContentValues();
        row.put(COL_CREATION, p.getCreation().getTime());
        row.put(COL_FK_CAHIER, p.getCahierRef());
        return this.update(p.getId(), row);
    }



    /**
     * @param cahierId long
     * @return Set<Page>
     */
    public Set<Page> selectCahierPages(long cahierId) {
        Set<Page> list = new HashSet<>();
        Cursor cursor = this.mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_FK_CAHIER + " = ?", new String[] {""+cahierId});
        while(cursor.moveToNext()) {
            Page p = new Page(cursor.getLong(0), cursor.getLong(1), cursor.getLong(2));
            list.add(p);
        }
        cursor.close();
        return list;
    }

}

package com.example.dodiddone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.services.AppStateSync;

import java.util.HashSet;
import java.util.Set;

public class CahierDAO extends AbstractDAO {

    public static final String TABLE_NAME = "cahier";
    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";
    public static final String COL_FK_USER = "fk_user";

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NOM + " TEXT, " +
                    COL_FK_USER + " TEXT " +
            "); ";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + "; ";

    private String userAccount;


    public CahierDAO(Context pContext, String userAccount) {
        super(pContext, TABLE_NAME);
        this.userAccount = userAccount;
    }

    /**
     * @param e Cahier
     * @return boolean
     */
    public boolean insert(Cahier e) {
        ContentValues row = new ContentValues();
        row.put(COL_NOM, e.getNom());
        row.put(COL_FK_USER, userAccount);
        long id = this.insert(row);
        if(id != -1) {
            e.setId(id);
            AppStateSync.syncCahier(e);
            return true;
        }
        return false;
    }

    /**
     */
    public boolean update(Cahier e) {
        ContentValues row = new ContentValues();
        row.put(COL_NOM, e.getNom());
        row.put(COL_FK_USER, userAccount);
        return this.update(e.getId(), row) > 0;
    }

    /**
     * @param id long
     * @return Cahier
     */
    public Cahier select(long id) {
        Cursor cursor = super.selectRowById(id);
        if(!cursor.moveToFirst()) {
            return null;
        }
        Cahier c = new Cahier(cursor.getLong(0), cursor.getString(1));
        cursor.close();
        return c;
    }

    /**
     * @return Set<Cahier>
     */
    public Set<Cahier> selectAll() {
        Set<Cahier> list = new HashSet<>();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_FK_USER + " = ?", new String[] {""+ userAccount});
        while(cursor.moveToNext()) {
            Cahier c = new Cahier(cursor.getLong(0), cursor.getString(1));
            list.add(c);
        }
        cursor.close();
        return list;
    }

}

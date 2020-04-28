package com.example.dodiddone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.dodiddone.metier.Ligne;

import java.util.HashSet;
import java.util.Set;

public class LigneDAO extends AbstractDAO {

    public static final String TABLE_NAME = "ligne";
    public static final String COL_ID = "id";
    public static final String COL_TYPE = "type";
    public static final String COL_VALEUR = "valeur";
    public static final String COL_FK_PAGE = "fk_page";
    public static final String COL_FK_REGLE = "fk_regle";

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_TYPE + " TEXT, " +
                    COL_VALEUR + " TEXT, " +
                    COL_FK_PAGE + " INTEGER, " +
                    COL_FK_REGLE + " INTEGER, " +
                    "FOREIGN KEY(" + COL_FK_PAGE + ") REFERENCES " + PageDAO.TABLE_NAME + "(" + PageDAO.COL_ID + ")" +
                    "FOREIGN KEY(" + COL_FK_REGLE + ") REFERENCES " + RegleDAO.TABLE_NAME + "(" + RegleDAO.COL_ID + ")" +
            "); ";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + "; ";


    public LigneDAO(Context pContext) {
        super(pContext, TABLE_NAME);
    }



    public long insert(Ligne e) {
        ContentValues row = new ContentValues();
        row.put(COL_TYPE, e.getType().toString());
        row.put(COL_VALEUR, e.getValeur());
        row.put(COL_FK_PAGE, e.getRefPage());
        row.put(COL_FK_REGLE, e.getRefRegle());
        return this.insert(row);
    }


    public int update(Ligne e) {
        ContentValues row = new ContentValues();
        row.put(COL_TYPE, e.getType().toString());
        row.put(COL_VALEUR, e.getValeur());
        row.put(COL_FK_PAGE, e.getRefPage());
        row.put(COL_FK_REGLE, e.getRefRegle());
        return this.update(e.getId(), row);
    }



    /**
     * @param pageId long
     * @return Set<Ligne>
     */
    public Set<Ligne> selectPageLignes(long pageId) {
        Set<Ligne> list = new HashSet<>();
        Cursor cursor = this.mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_FK_PAGE + " = ?", new String[] {""+pageId});
        while(cursor.moveToNext()) {
            Ligne e = new Ligne(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4)
            );
            list.add(e);
        }
        cursor.close();
        return list;
    }
}

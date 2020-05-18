package com.example.dodiddone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.dodiddone.metier.Ligne;
import com.example.dodiddone.metier.Page;
import com.example.dodiddone.metier.Regle;

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

    private Page pPage;

    public LigneDAO(Context pContext, Page pPage) {
        super(pContext, TABLE_NAME);
        this.pPage = pPage;
    }

    /**
     * @param e Ligne
     * @return boolean
     */
    public boolean insert(Ligne e) {
        ContentValues row = new ContentValues();
        row.put(COL_TYPE, e.getType().toString());
        row.put(COL_FK_PAGE, pPage.getId());
        row.put(COL_FK_REGLE, e.getRegle().getId());
        try {
            row.put(COL_VALEUR, e.getValeur().toStorable());
        }
        catch(NullPointerException nullE) {
            row.putNull(COL_VALEUR);
        }
        long id = this.insert(row);
        if(id != -1) {
            e.setId(id);
            return true;
        }
        return false;
    }


    public boolean update(Ligne e) {
        ContentValues row = new ContentValues();
        row.put(COL_TYPE, e.getType().toString());
        row.put(COL_FK_PAGE, pPage.getId());
        row.put(COL_FK_REGLE, e.getRegle().getId());
        try {
            row.put(COL_VALEUR, e.getValeur().toStorable());
        }
        catch(NullPointerException nullE) {
            row.putNull(COL_VALEUR);
        }
        return this.update(e.getId(), row) > 0;
    }



    /**
     * @return Set<Ligne>
     */
    public Set<Ligne> selectPageLignes() {
        Set<Ligne> list = new HashSet<>();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_FK_PAGE + " = ?", new String[] {""+pPage.getId()});
        while(cursor.moveToNext()) {
            Ligne e = new Ligne(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    pPage,
                    pPage.getCahier().getRegles().get(cursor.getLong(4))
            );
            list.add(e);
        }
        cursor.close();
        return list;
    }



    /**
     * @return Set<Ligne>
     */
    public Ligne selectPageRegleLignes(Regle regle) {
        Ligne l = null;
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_FK_PAGE + " = ? AND "+COL_FK_REGLE+" = ?", new String[] {""+pPage.getId(),""+regle.getId()});
        if(cursor.moveToNext()) {
            l = new Ligne(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    pPage,
                    pPage.getCahier().getRegles().get(cursor.getLong(4))
            );
        }
        cursor.close();
        return l;
    }
}

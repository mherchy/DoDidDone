package com.example.dodiddone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Regle;
import com.example.dodiddone.services.AppStateSync;

import java.util.HashSet;
import java.util.Set;

public class RegleDAO extends AbstractDAO {

    public static final String TABLE_NAME = "regle";
    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";
    public static final String COL_UNITE = "unite";
    public static final String COL_TYPE = "type";
    public static final String COL_FK_CAHIER = "fk_cahier";

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NOM + " TEXT, " +
                    COL_UNITE + " TEXT, " +
                    COL_TYPE + " TEXT, " +
                    COL_FK_CAHIER + " INTEGER, " +
                    "FOREIGN KEY(" + COL_FK_CAHIER + ") REFERENCES " + CahierDAO.TABLE_NAME + "(" + CahierDAO.COL_ID + ")" +
                    "); ";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + "; ";

    private Cahier pCahier;

    public RegleDAO(Context pContext, Cahier pCahier) {
        super(pContext, TABLE_NAME);
        this.pCahier = pCahier;
    }

    /**
     * @param e Regle
     * @return boolean
     */
    public boolean insert(Regle e) {
        ContentValues row = new ContentValues();
        row.put(COL_NOM, e.getNom());
        row.put(COL_UNITE, e.getUnite());
        row.put(COL_TYPE, e.getType().toString());
        row.put(COL_FK_CAHIER, pCahier.getId());
        long id = this.insert(row);
        if(id != -1) {
            e.setId(id);
            AppStateSync.syncCahier(pCahier);
            return true;
        }
        return false;
    }


    public boolean update(Regle e) {
        ContentValues row = new ContentValues();
        row.put(COL_NOM, e.getNom());
        row.put(COL_UNITE, e.getUnite());
        row.put(COL_TYPE, e.getType().toString());
        row.put(COL_FK_CAHIER, pCahier.getId());
        return this.update(e.getId(), row) > 0;
    }



    /**
     * @return Set<Regle>
     */
    public Set<Regle> selectCahierRegles() {
        Set<Regle> list = new HashSet<>();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_FK_CAHIER + " = ?", new String[] {""+pCahier.getId()});
        while(cursor.moveToNext()) {
            Regle e = new Regle(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    pCahier
            );
            list.add(e);
        }
        cursor.close();
        return list;
    }

}


package com.example.dodiddone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.dodiddone.metier.Regle;
import com.example.dodiddone.metier.typedValues.calcul.Calcul;
import com.example.dodiddone.metier.typedValues.calcul.Calculs;
import com.example.dodiddone.services.AppStateSync;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalculDAO extends AbstractDAO {

    public static final String TABLE_NAME = "clacul";
    public static final String COL_ID = "id";
    public static final String COL_FK_REGLE = "fk_regle";
    public static final String COL_NOM = "name";


    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_FK_REGLE + " INTEGER, " +
                    COL_NOM + " TEXT, " +
                    "FOREIGN KEY(" + COL_FK_REGLE + ") REFERENCES " + RegleDAO.TABLE_NAME + "(" + RegleDAO.COL_ID + ")" +
                    "); ";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + "; ";

    private Regle pRegle;

    public CalculDAO(Context pContext, Regle pRegle) {
        super(pContext, TABLE_NAME);
        this.pRegle = pRegle;
    }


    /**
     * @param calcul Calcul
     * @return boolean
     */
    public boolean insert(Calcul calcul) {
        ContentValues row = new ContentValues();
        row.put(COL_NOM, calcul.getName());
        row.put(COL_FK_REGLE, pRegle.getId());
        long id = this.insert(row);
        if(id != -1) {
            AppStateSync.syncCahier(pRegle.getCahier());
            return true;
        }
        return false;
    }

    /**
     *
     * @param calcul Calcul
     * @return int
     */
    public int remove(Calcul calcul) {
        return mDb.delete(this.tableName, COL_NOM+" = ? AND "+COL_FK_REGLE+" = ?", new String[] {calcul.getName(),""+pRegle.getId()});
    }



    /**
     * @return List<Calcul>
     */
    public List<Calcul> selectRegleCalculs() {
        ArrayList<Calcul> list = new ArrayList<>();
        Cursor cursor = mDb.rawQuery("SELECT "+ COL_NOM +" FROM " + TABLE_NAME + " WHERE " + COL_FK_REGLE + " = ?", new String[] {""+pRegle.getId()});
        while(cursor.moveToNext()) {
            String CalculName = cursor.getString(0);
            list.add(Calculs.get(CalculName));
        }
        cursor.close();
        return list;
    }
}

package com.example.dodiddone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public abstract class AbstractDAO extends DAOBase {

    public static final String COL_ID = "id";

    public String tableName;

    public AbstractDAO(Context pContext, String tableName) {
        super(pContext);
        this.tableName = tableName;
    }

    /**
     * @param row ContentValues
     * @return long the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insert(ContentValues row) {
        return mDb.insertOrThrow(this.tableName,null, row);
    }

    /**
     *
     * @param id long
     * @param row ContentValues
     * @return int number of updated rows
     */
    public int update(long id, ContentValues row) {
        return mDb.update(this.tableName, row, COL_ID+" = ?", new String[] {""+id});
    }

    /**
     *
     * @param id long
     * @return int number of removed rows
     */
    public int remove(long id) {
        return mDb.delete(this.tableName, COL_ID+" = ?", new String[] {""+id});
    }

    /**
     * @param id long
     * @return Cursor
     */
    protected Cursor selectCursor(long id) {
        return mDb.rawQuery("SELECT * FROM "+ this.tableName + " WHERE id = ?", new String[] {""+id});
    }

}

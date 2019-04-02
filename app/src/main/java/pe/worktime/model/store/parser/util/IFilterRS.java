package pe.worktime.model.store.parser.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public interface IFilterRS {

    public Cursor find(SQLiteDatabase db) throws Exception;
    
}
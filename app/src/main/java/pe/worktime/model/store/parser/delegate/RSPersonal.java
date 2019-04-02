package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import pe.worktime.model.entity.Personal;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.PersonalParser;

public class RSPersonal extends RSAbstract<Personal> {
    public RSPersonal(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        this.parser = new PersonalParser(context, name, factory, version);
        this.db = this.parser.getWritableDatabase();
    }
}

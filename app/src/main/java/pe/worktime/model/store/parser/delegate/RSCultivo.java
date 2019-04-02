package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import pe.worktime.model.entity.Cultivo;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.CultivoParser;

public class RSCultivo extends RSAbstract<Cultivo> {
    public RSCultivo(Context context, String name, SQLiteDatabase.CursorFactory factory,
                       int version) {
        this.parser = new CultivoParser(context, name, factory, version);
        this.db = this.parser.getWritableDatabase();
    }
}

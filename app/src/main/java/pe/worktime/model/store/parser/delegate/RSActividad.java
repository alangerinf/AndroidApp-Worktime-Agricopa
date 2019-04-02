package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.Actividad;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.ActividadParser;

public class RSActividad extends RSAbstract<Actividad> {

	public RSActividad(Context context, String name, CursorFactory factory,
			int version) {
		this.parser = new ActividadParser(context, name, factory, version);
		this.db = this.parser.getWritableDatabase();
	}
}
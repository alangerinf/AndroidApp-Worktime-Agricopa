package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.Productividad;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.ProductividadParser;

public class RSProductividad extends RSAbstract<Productividad> {

	public RSProductividad(Context context, String name, CursorFactory factory,
			int version) {
		this.parser = new ProductividadParser(context, name, factory, version);
		this.db = this.parser.getWritableDatabase();
	}
}
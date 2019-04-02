package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.ServicioATerceros;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.ServicioATercerosParser;

public class RSServicioATerceros extends RSAbstract<ServicioATerceros> {

	public RSServicioATerceros(Context context, String name, CursorFactory factory,	int version) {
		this.parser = new ServicioATercerosParser(context, name, factory, version);
		this.db = this.parser.getWritableDatabase();
	}
}
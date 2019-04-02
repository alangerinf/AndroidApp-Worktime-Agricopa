package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.ConsumidorIndirectoParser;

public class RSConsumidorIndirecto extends RSAbstract<ConsumidorIndirecto> {

	public RSConsumidorIndirecto(Context context, String name, CursorFactory factory,
			int version) {
		this.parser = new ConsumidorIndirectoParser(context, name, factory, version);
		this.db = this.parser.getWritableDatabase();
	}
}
	
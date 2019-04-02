package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.HorasConsumidorParser;

public class RSHorasConsumidor extends RSAbstract<HorasConsumidor> {

	public RSHorasConsumidor(Context context, String name, CursorFactory factory,
			int version) {
		this.parser = new HorasConsumidorParser(context, name, factory, version);
		this.db = this.parser.getWritableDatabase();
	}
}
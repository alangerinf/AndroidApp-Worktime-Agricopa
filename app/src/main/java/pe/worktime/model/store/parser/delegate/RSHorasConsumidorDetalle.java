package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.HorasConsumidorDetalleParser;
import pe.worktime.model.store.parser.HorasConsumidorParser;

public class RSHorasConsumidorDetalle extends RSAbstract<HorasConsumidorDetalle> {

	public RSHorasConsumidorDetalle(Context context, String name, CursorFactory factory,
                                    int version) {
		this.parser = new HorasConsumidorDetalleParser(context, name, factory, version);
		this.db = this.parser.getWritableDatabase();
	}
}
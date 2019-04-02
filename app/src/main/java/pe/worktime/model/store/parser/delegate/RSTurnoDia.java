package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.TurnoDia;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.TurnoDiaParser;

public class RSTurnoDia extends RSAbstract<TurnoDia> {

	public RSTurnoDia(Context context, String name, CursorFactory factory,
			int version) {
		this.parser = new TurnoDiaParser(context, name, factory, version);
		this.db = this.parser.getWritableDatabase();
	}
}

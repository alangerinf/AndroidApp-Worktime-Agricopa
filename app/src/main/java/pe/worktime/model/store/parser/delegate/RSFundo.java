package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.Fundo;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.FundoParser;

public class RSFundo extends RSAbstract<Fundo> {

	public RSFundo(Context context, String name, CursorFactory factory,
			int version) {
		this.parser = new FundoParser(context, name, factory, version);
		this.db = this.parser.getWritableDatabase();
	}
}
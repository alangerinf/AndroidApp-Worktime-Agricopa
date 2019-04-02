package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.Grupo;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.GrupoParser;

public class RSGrupo  extends RSAbstract<Grupo> {

	public RSGrupo(Context context, String name, CursorFactory factory,
			int version) {
		this.parser = new GrupoParser(context, name, factory, version);
		this.db = this.parser.getWritableDatabase();
	}
}
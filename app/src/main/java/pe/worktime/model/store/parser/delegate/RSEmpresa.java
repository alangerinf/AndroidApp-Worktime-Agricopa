package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import pe.worktime.model.entity.Empresa;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.EmpresaParser;

public class RSEmpresa extends RSAbstract<Empresa> {

	public RSEmpresa(Context context, String name, CursorFactory factory,
			int version) {
		this.parser = new EmpresaParser(context, name, factory, version);
		this.db = this.parser.getWritableDatabase();
	}
}

package pe.worktime.model.store.parser.delegate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.Usuario;
import pe.worktime.model.store.RSAbstract;
import pe.worktime.model.store.parser.UsuarioParser;


public class RSUsuario extends RSAbstract<Usuario> {

	public RSUsuario(Context context, String name, CursorFactory factory,
			int version) {
		this.parser = new UsuarioParser(context, name, factory, version);		
		this.db = this.parser.getWritableDatabase();
	}
}
package pe.worktime.model.store.parser;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

//import Actividad;
import pe.worktime.model.entity.Empresa;
//import Fundo;
import pe.worktime.model.entity.Grupo;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class GrupoParser extends AbstractParserRS<Grupo> {

	public GrupoParser(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void insert(SQLiteDatabase db, Grupo entity) throws Exception {
		ContentValues registro = new ContentValues();
		registro.put("Codigo", entity.getCodGrupo());
		registro.put("Nombre", entity.getGrupo());		
		db.insert(getTableName(), null, registro);
	}
	
	@Override
	public void insertWithParent(SQLiteDatabase db, Grupo entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	@Override
	public List<Grupo> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public Grupo buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

	@Override
	public void update(SQLiteDatabase db, Grupo entity) throws Exception {
		ContentValues registro = new ContentValues();
		registro.put("Nombre", entity.getGrupo());
		String[] whereArgs = {entity.getCodGrupo()};
		db.update(getTableName(), registro, " Codigo = ? ", whereArgs);		
	}

	@Override
	public void delete(SQLiteDatabase db, Grupo entity) throws Exception {
		String[] whereArgs = { entity.getCodGrupo()};
		db.delete(getTableName(), " Codigo = ? ", whereArgs);
	}
		
	@Override
	public Grupo read(Cursor c) throws Exception {
		Grupo obj = new Grupo();
		obj.setCodGrupo(c.getString(c.getColumnIndex("Codigo")));
		obj.setGrupo(c.getString(c.getColumnIndex("Nombre")));
		return obj;
	}

	@Override
	protected String getSqlCreate() {
		StringBuffer bufer = new StringBuffer();
		bufer.append("CREATE TABLE ");
		bufer.append(this.getTableName());
		bufer.append("( ");
		{			
			bufer.append("Codigo TEXT, ");
			bufer.append("Nombre TEXT ");			
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {
		return "Grupo";
	}
}
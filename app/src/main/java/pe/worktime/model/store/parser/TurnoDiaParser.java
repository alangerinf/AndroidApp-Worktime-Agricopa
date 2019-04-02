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
import pe.worktime.model.entity.TurnoDia;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class TurnoDiaParser extends AbstractParserRS<TurnoDia> {

	public TurnoDiaParser(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void insert(SQLiteDatabase db, TurnoDia entity) throws Exception {

		ContentValues registro = new ContentValues();
		registro.put("codigo", entity.getCodigo());
		registro.put("nombre", entity.getNombre());
		registro.put("inicio", entity.getInicio());
		registro.put("fin", entity.getFin());
		db.insert(getTableName(), null, registro);
	}
	
	@Override
	public void insertWithParent(SQLiteDatabase db, TurnoDia entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public List<TurnoDia> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public TurnoDia buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

	@Override
	public void update(SQLiteDatabase db, TurnoDia entity) throws Exception {

		ContentValues registro = new ContentValues();
		registro.put("codigo", entity.getCodigo());
		registro.put("nombre", entity.getNombre());
		registro.put("inicio", entity.getInicio());
		registro.put("fin", entity.getFin());
		String[] whereArgs = { entity.getCodigo() };
		db.update(getTableName(), registro, "codigo = ? ", whereArgs);
	}

	@Override
	public void delete(SQLiteDatabase db, TurnoDia entity) throws Exception {
		String[] whereArgs = { entity.getCodigo() };
		db.delete(getTableName(), "codigo = ? ", whereArgs);
	}

	@Override
	public TurnoDia read(Cursor c) throws Exception {
		TurnoDia obj = new TurnoDia();
		obj.setCodigo(c.getString(c.getColumnIndex("codigo")));
		obj.setNombre(c.getString(c.getColumnIndex("nombre")));
		obj.setInicio(c.getString(c.getColumnIndex("inicio")));
		obj.setFin(c.getString(c.getColumnIndex("fin")));
		return obj;
	}

	@Override
	protected String getSqlCreate() {
		
		StringBuffer bufer = new StringBuffer();
		bufer.append("CREATE TABLE ");
		bufer.append(this.getTableName());
		bufer.append("( ");
		{
			bufer.append("codigo TEXT, ");
			bufer.append("nombre TEXT, ");
			bufer.append("inicio TEXT, ");
			bufer.append("fin TEXT ");
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {
		return "TurnoDia";
	}
}
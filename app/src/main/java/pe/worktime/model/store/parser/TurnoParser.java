package pe.worktime.model.store.parser;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

//import Actividad;
import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Modulo;
import pe.worktime.model.entity.Turno;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class TurnoParser extends AbstractParserRS<Turno> {

	public TurnoParser(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
	}

	@Override
	public void insert(SQLiteDatabase db, Turno entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	public void insertDeta(SQLiteDatabase db, Fundo gParent, Modulo parent,Turno entity) throws Exception {
		
		ContentValues registro = new ContentValues();
		registro.put("codFundo", gParent.getCodFundo());
		registro.put("codModulo", parent.getCodModulo());
		registro.put("cod_turno", entity.getCod_turno());
		registro.put("nombre", entity.getNombre());		
		db.insert(getTableName(), null, registro);
	}
	
	public void insertDeta(SQLiteDatabase db, Empresa grandparent, Fundo gParent, Modulo parent, Turno entity) throws Exception {
		
		ContentValues registro = new ContentValues();
		registro.put("codEmpresa", grandparent.getCodEmpresa());
		registro.put("codFundo", gParent.getCodFundo());
		registro.put("codModulo", parent.getCodModulo());
		registro.put("cod_turno", entity.getCod_turno());
		registro.put("nombre", entity.getNombre());		
		db.insert(getTableName(), null, registro);
	}
	
	@Override
	public void insertWithParent(SQLiteDatabase db, Turno entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	@Override
	public List<Turno> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public Turno buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

	@Override
	public void update(SQLiteDatabase db, Turno entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	public void updateDetas(SQLiteDatabase db, Fundo gParent, Modulo parent,
			List<Turno> entitys) {
		ContentValues registro = null;
		deleteDetas(db, gParent, parent);
		for (Turno entity : entitys) {
			registro = new ContentValues();
			registro.put("codFundo", gParent.getCodFundo());
			registro.put("codModulo", parent.getCodModulo());
			registro.put("cod_turno", entity.getCod_turno());
			registro.put("nombre", entity.getNombre());
			db.insert(getTableName(), null, registro);
		}
	}

	@Override
	public void delete(SQLiteDatabase db, Turno entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	public void delete(SQLiteDatabase db, Fundo entity) throws Exception {
		String[] whereArgs = {entity.getFundo()};
		db.delete(getTableName(), " codFundo = ? ", whereArgs);		
	}

	public void deleteDetas(SQLiteDatabase db, Fundo gParent, Modulo parent) {
		String[] whereArgs = { gParent.getCodFundo(), parent.getCodModulo() };
		db.delete(getTableName(), " codFundo = ? AND codModulo = ? ", whereArgs);
	}
	
	public List<Turno> list(SQLiteDatabase db,Empresa grandparent, Fundo gParent, Modulo parent) throws Exception {
		String sql = "SELECT * FROM "+ getTableName() +" where codFundo = '"+ gParent.getCodFundo()+"' AND codModulo = '"+parent.getCodModulo()+"' AND codEmpresa = '" + grandparent.getCodEmpresa()+"'";
		Cursor c = db.rawQuery(sql, null);
		List<Turno> result = new ArrayList<Turno>();
		if (c.moveToFirst()) {
			do {
				result.add(read(c));
			} while (c.moveToNext());
		}
		c.close();
		db.close();
		return result;
	}

	@Override
	public Turno read(Cursor c) throws Exception {
		Turno obj = new Turno();
		obj.setCod_turno(c.getString(c.getColumnIndex("cod_turno")));
		obj.setNombre(c.getString(c.getColumnIndex("nombre")));
		return obj;
	}

	@Override
	protected String getSqlCreate() {
		StringBuffer bufer = new StringBuffer();
		bufer.append("CREATE TABLE ");
		bufer.append(this.getTableName());
		bufer.append("( ");
		{	
			bufer.append("codEmpresa TEXT, ");
			bufer.append("codFundo TEXT, ");
			bufer.append("codModulo TEXT, ");
			bufer.append("cod_turno TEXT, ");
			bufer.append("nombre TEXT ");
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {
		return "Turno";
	}
}
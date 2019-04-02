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

public class ModuloParser extends AbstractParserRS<Modulo> {

	private TurnoParser tParser;

	public ModuloParser(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		tParser = new TurnoParser(context, name, factory, version);
	}

	@Override
	public void insert(SQLiteDatabase db, Modulo entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	@Override
	public void insertWithParent(SQLiteDatabase db, Modulo entity) throws Exception {
		throw new Exception("No Implement Exception");
	}


	public void insertDeta(SQLiteDatabase db, Fundo parent, Modulo entity)
			throws Exception {
		ContentValues registro = new ContentValues();
		registro.put("codFundo", parent.getCodFundo());
		registro.put("codModulo", entity.getCodModulo());
		registro.put("modulo", entity.getModulo());
		db.insert(getTableName(), null, registro);
		for (Turno turno : entity.getTurnos()) {
			tParser.insertDeta(db, parent, entity, turno);
		}
	}
	
	public void insertDeta(SQLiteDatabase db,Empresa grandparent, Fundo parent, Modulo entity)
			throws Exception {
		ContentValues registro = new ContentValues();
		registro.put("codEmpresa", grandparent.getCodEmpresa());
		registro.put("codFundo", parent.getCodFundo());
		registro.put("codModulo", entity.getCodModulo());
		registro.put("modulo", entity.getModulo());
		db.insert(getTableName(), null, registro);
		for (Turno turno : entity.getTurnos()) {
			tParser.insertDeta(db,grandparent, parent, entity, turno);
		}
	}
	
	@Override
	public List<Modulo> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	public void updateDetas(SQLiteDatabase db, Fundo parent,
			List<Modulo> entitys) throws Exception {
		ContentValues registro = null;
		deleteDetas(db, parent);
		for (Modulo entity : entitys) {
			registro = new ContentValues();
			registro.put("codFundo", parent.getCodFundo());
			registro.put("codModulo", entity.getCodModulo());
			registro.put("modulo", entity.getModulo());
			db.insert(getTableName(), null, registro);
			tParser.updateDetas(db, parent, entity, entity.getTurnos());
		}
	}

	public void deleteDetas(SQLiteDatabase db, Fundo parent) {
		String[] whereArgs = { parent.getFundo() };
		db.delete(getTableName(), " codFundo = ? ", whereArgs);
	}

	@Override
	public void update(SQLiteDatabase db, Modulo entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public void delete(SQLiteDatabase db, Modulo entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	public void delete(SQLiteDatabase db, Fundo entity) throws Exception {
		String[] whereArgs = {entity.getFundo()};
		db.delete(getTableName(), " codFundo = ? ", whereArgs);
		tParser.delete(db, entity);
	}

	public List<Modulo> list(SQLiteDatabase db, Fundo entity) throws Exception {
		String sql = "SELECT * FROM "+getTableName() +" where codFundo = '"+entity.getCodFundo()+"'";
		Cursor c = db.rawQuery(sql, null);
		List<Modulo> result = new ArrayList<Modulo>();
		if (c.moveToFirst()) {
			do {
				result.add(read(c));
			} while (c.moveToNext());
		}
		c.close();
		db.close();
		return result;
	}
	
	public List<Modulo> list(SQLiteDatabase db,Empresa grandparent, Fundo gParent) throws Exception {
		String sql = "SELECT * FROM "+ getTableName() +" where codFundo = '"+ gParent.getCodFundo()+"' AND codEmpresa = '" + grandparent.getCodEmpresa()+"'";
		Cursor c = db.rawQuery(sql, null);
		List<Modulo> result = new ArrayList<Modulo>();
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
	public Modulo read(Cursor c) throws Exception {
		Modulo obj = new Modulo();
		obj.setCodModulo(c.getString(c.getColumnIndex("codModulo")));
		obj.setModulo(c.getString(c.getColumnIndex("modulo")));
		obj.setTurnos(tParser.list(this.getWritableDatabase(), new Empresa(c.getString(c.getColumnIndex("codEmpresa")), "") ,new Fundo(c.getString(c.getColumnIndex("codFundo")), ""), obj));
		return obj;
	}

	@Override
	protected String getSqlCreate() {
		StringBuffer bufer = new StringBuffer();
		bufer.append("CREATE TABLE ");
		bufer.append(this.getTableName());
		bufer.append("( ");
		{
			/*
			 * codFundo private String codModulo; private String modulo;
			 */
			bufer.append("codEmpresa TEXT, ");
			bufer.append("codFundo TEXT, ");
			bufer.append("codModulo TEXT, ");
			bufer.append("modulo TEXT ");
		}
		bufer.append(" );");
		return bufer.toString();
	}
	
	public void cleanData(SQLiteDatabase db) {
		db.execSQL(sqlDrop);
		db.execSQL(sqlCreate);
		tParser.cleanData(db);
	}

	@Override
	public Modulo buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

	@Override
	protected String getTableName() {
		return "Modulo";
	}



}

package pe.worktime.model.store.parser;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Modulo;
//import Turno;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class FundoParser extends AbstractParserRS<Fundo> {
	
	private ModuloParser mParser;

	public FundoParser(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		mParser = new ModuloParser(context, name, factory, version);
	}

	@Override
	public void insert(SQLiteDatabase db, Fundo entity) throws Exception {
		ContentValues registro = new ContentValues();		
		//Header
		registro.put("codFundo", entity.getCodFundo());
		registro.put("fundo", entity.getFundo());
		db.insert(getTableName(), null, registro);		
		//Detalle
		for (Modulo modulo : entity.getModulos()) {
			mParser.insertDeta(db, entity,modulo);
		}	
	}
	
	@Override
	public void insertWithParent(SQLiteDatabase db, Fundo entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	
	public void insertWithFundos(SQLiteDatabase db, Empresa parent, Fundo entity) throws Exception {
		ContentValues registro = new ContentValues();
		registro.put("codEmpresa", parent.getCodEmpresa());
		registro.put("codFundo", entity.getCodFundo());
		registro.put("fundo", entity.getFundo());
		db.insert(getTableName(), null, registro);		
		//Detalle
		for (Modulo modulo : entity.getModulos()) {
			mParser.insertDeta(db,parent,entity,modulo);
		}	
	}
	
    @Override
	public List<Fundo> list(SQLiteDatabase db, Empresa entity) throws Exception {
		String sql = "SELECT * FROM "+getTableName() +" where codEmpresa = '"+entity.getCodEmpresa()+"'";
		Cursor c = db.rawQuery(sql, null);
		List<Fundo> result = new ArrayList<Fundo>();
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
	public void update(SQLiteDatabase db, Fundo entity) throws Exception {
		ContentValues registro = new ContentValues();				
		registro.put("fundo", entity.getFundo());		
		String[] whereArgs = {entity.getCodFundo()};		
		db.update(getTableName(), registro, " codFundo = ? ", whereArgs);		
		mParser.updateDetas(db, entity, entity.getModulos());		
	}

	@Override
	public void delete(SQLiteDatabase db, Fundo entity) throws Exception {
		String[] whereArgs = {entity.getFundo()};
		db.delete(getTableName(), " codFundo = ? ", whereArgs);
		mParser.delete(db, entity);
	}

	@Override
	public Fundo read(Cursor c) throws Exception {
		Fundo obj = new Fundo();		
		obj.setCodFundo(c.getString(c.getColumnIndex("codFundo")));
		obj.setFundo(c.getString(c.getColumnIndex("fundo")));		
		obj.setModulos(mParser.list(this.getWritableDatabase(), new Empresa(c.getString(c.getColumnIndex("codEmpresa")), "") , obj));		
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
			bufer.append("fundo TEXT ");
		}
		bufer.append(" );");
		return bufer.toString();
	}

	public void cleanData(SQLiteDatabase db) {
		db.execSQL(sqlDrop);
		db.execSQL(sqlCreate);
		mParser.cleanData(db);
	}

	@Override
	public Fundo buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

	@Override
	protected String getTableName() {
		return "Fundo";
	}
}
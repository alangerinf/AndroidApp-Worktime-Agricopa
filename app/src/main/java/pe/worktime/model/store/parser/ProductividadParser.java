package pe.worktime.model.store.parser;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

//import Actividad;
import pe.worktime.model.entity.Empresa;
//import Fundo;
import pe.worktime.model.entity.Productividad;
import pe.worktime.model.entity.ProductividadDetalle;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class ProductividadParser extends AbstractParserRS<Productividad> {

	private ProductividadDetalleParser dParser;
	
	public ProductividadParser(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		dParser = new ProductividadDetalleParser(context, name, factory, version);
	}
	
	@Override
	public void insert(SQLiteDatabase db, Productividad entity)
			throws Exception {
		ContentValues registro = new ContentValues();		
		//Header
		registro.put("CodUser", entity.getCodUser());
		registro.put("IMEI", entity.getIMEI());
		registro.put("CodEmpresa", entity.getCodEmpresa());
		registro.put("CodFundo", entity.getCodFundo());
		registro.put("CodModulo", entity.getCodModulo());
		registro.put("CodActvidad", entity.getCodActvidad());
		registro.put("FechaRegistroMovil", entity.getFechaRegistroMovil());
		registro.put("Fecha", entity.getFecha());
		registro.put("codCultivo", entity.getIdCultivo());
		registro.put("migrado", entity.getMigrado());
		
		db.insert(getTableName(), null, registro);		
		entity.setCodigo(Integer.parseInt(getLastIdAutoincrement(db)));
		
		Log.d("INFO.ID", ""+entity.getCodigo());
		//Detalle		
		for (ProductividadDetalle item : entity.getDetalle()) {
			item.setCodProductividad(entity.getCodigo());
			dParser.insertDeta(db, entity,item);
		}
	}
	
	@Override
	public void insertWithParent(SQLiteDatabase db, Productividad entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	@Override
	public List<Productividad> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	
	@Override
	public void update(SQLiteDatabase db, Productividad entity)
			throws Exception {
		ContentValues registro = new ContentValues();		
				
		registro.put("CodUser", entity.getCodUser());
		registro.put("IMEI", entity.getIMEI());
		registro.put("CodEmpresa", entity.getCodEmpresa());
		registro.put("CodFundo", entity.getCodFundo());
		registro.put("CodModulo", entity.getCodModulo());
		registro.put("CodActvidad", entity.getCodActvidad());
		registro.put("FechaRegistroMovil", entity.getFechaRegistroMovil());
		registro.put("Fecha", entity.getFecha());
		registro.put("codCultivo", entity.getIdCultivo());
		registro.put("migrado", entity.getMigrado());
		
		String[] whereArgs = {String.valueOf(entity.getCodigo())};
		db.update(getTableName(), registro, " codigo = ? ", whereArgs);		
		dParser.updateDetas(db, entity, entity.getDetalle());	
		
	}

	@Override
	public void delete(SQLiteDatabase db, Productividad entity)
			throws Exception {
		String[] whereArgs = { String.valueOf(entity.getCodigo())};
		db.delete(getTableName(), " codigo = ? ", whereArgs);
		dParser.delete(db, entity);
	}
	
	@Override
	public Productividad read(Cursor c) throws Exception {
		Productividad obj = new Productividad();
		
		obj.setCodigo(Integer.parseInt(c.getString(c.getColumnIndex("codigo"))));
		
		obj.setCodUser(c.getString(c.getColumnIndex("CodUser")));
		obj.setIMEI(c.getString(c.getColumnIndex("IMEI")));
		obj.setCodEmpresa(c.getString(c.getColumnIndex("CodEmpresa")));
		obj.setCodFundo(c.getString(c.getColumnIndex("CodFundo")));
		obj.setCodModulo(c.getString(c.getColumnIndex("CodModulo")));
		obj.setCodActvidad(c.getString(c.getColumnIndex("CodActvidad")));
		obj.setFechaRegistroMovil(c.getString(c.getColumnIndex("FechaRegistroMovil")));
		obj.setFecha(c.getString(c.getColumnIndex("Fecha")));
		obj.setMigrado(Integer.parseInt(c.getString(c.getColumnIndex("migrado"))));
		obj.setIdCultivo(c.getString(c.getColumnIndex("codCultivo")));
				
		obj.setDetalle(dParser.list(this.getWritableDatabase(), obj));
		
		return obj;
	}

	@Override
	protected String getSqlCreate() {
		StringBuffer bufer = new StringBuffer();
		bufer.append("CREATE TABLE ");
		bufer.append(this.getTableName());
		bufer.append("( ");
		{
			bufer.append("codigo INTEGER PRIMARY KEY AUTOINCREMENT, ");			
			bufer.append("CodUser TEXT, ");									
			bufer.append("IMEI TEXT, ");
			bufer.append("CodEmpresa TEXT, ");
			bufer.append("CodFundo TEXT, ");
			bufer.append("CodModulo TEXT, ");
			bufer.append("CodActvidad TEXT, ");
			bufer.append("FechaRegistroMovil TEXT, ");
			bufer.append("Fecha TEXT, ");
			bufer.append("migrado INTEGER,");
			bufer.append("codCultivo TEXT ");
			
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {		
		return "Productividad";
	}
	
	protected String getLastIdAutoincrement(SQLiteDatabase db) {
		Cursor c = db.rawQuery("select seq from sqlite_sequence where name=\""+getTableName()+"\"", null);		
		String result = null;
		if (c.moveToFirst()) {
			do {
				result = c.getString(c.getColumnIndex("seq"));
			} while (c.moveToNext());
		}
		c.close();
		return result;
	}
	
	public void cleanData(SQLiteDatabase db) {
		dParser.cleanData(db);
		db.execSQL(sqlDrop);
		db.execSQL(sqlCreate);		
	}

	@Override
	public Productividad buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

}
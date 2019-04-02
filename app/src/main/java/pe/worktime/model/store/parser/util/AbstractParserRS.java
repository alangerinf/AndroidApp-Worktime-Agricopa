package pe.worktime.model.store.parser.util;

import java.util.ArrayList;
import java.util.List;

import pe.worktime.model.entity.Empresa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Sources: 
 * 		-	http://www.sgoliver.net/blog/?p=1611
 * 		-	FLEX SOFT SAC
 * */

public abstract class AbstractParserRS<E> extends SQLiteOpenHelper {

	protected String sqlCreate = null;
	protected String sqlDrop = null;
	protected String sqlSelect = null;

	public AbstractParserRS(Context context, String name,CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.sqlCreate = getSqlCreate();
		this.sqlDrop = getSqlDrop();
		this.sqlSelect = getSqlSelect();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se ejecuta la sentencia SQL de creación de la tabla
		db.execSQL(sqlCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior,
			int versionNueva) {
		// NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la
		// opción de
		// eliminar la tabla anterior y crearla de nuevo vacía con el nuevo
		// formato.
		// Sin embargo lo normal será que haya que migrar datos de la tabla
		// antigua
		// a la nueva, por lo que este método debería ser más elaborado.
		// Se elimina la versión anterior de la tabla
		db.execSQL(sqlDrop);
		// Se crea la nueva versión de la tabla
		db.execSQL(sqlCreate);
	}

	public abstract void insert(SQLiteDatabase db, E entity) throws Exception;
	
	public abstract void insertWithParent(SQLiteDatabase db, E entity) throws Exception;
	
	public abstract void update(SQLiteDatabase db, E entity) throws Exception;

	public abstract void delete(SQLiteDatabase db, E entity) throws Exception;

	public abstract E read(Cursor c) throws Exception;

	protected abstract String getSqlCreate();
	
	protected abstract String getTableName();
	
	protected String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + getTableName();
	}
	
	protected String getSqlSelect(){
		return "SELECT * FROM " + getTableName();
	}
	
	public List<E> list(SQLiteDatabase db) throws Exception {
		Cursor c = db.rawQuery(sqlSelect, null);
		List<E> result = new ArrayList<E>();
		if (c.moveToFirst()) {
			do {
				result.add(read(c));
			} while (c.moveToNext());
		}		
		c.close();
		return result;
	}
	
	public abstract List<E> list(SQLiteDatabase db, Empresa entity) throws Exception;

	public List<E> filter(SQLiteDatabase db, IFilterRS sqlFilter)
			throws Exception {
		Cursor c = sqlFilter.find(db);
		List<E> result = new ArrayList<E>();
		if (c.moveToFirst()) {
			do {
				result.add(read(c));
			} while (c.moveToNext());
		}
		c.close();
		return result;
	}
	
	public void cleanData(SQLiteDatabase db) {
		db.execSQL(sqlDrop);
		db.execSQL(sqlCreate);
	}
	public abstract E buscar(SQLiteDatabase db, String f) throws Exception;

}
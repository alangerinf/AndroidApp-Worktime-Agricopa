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
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.ServicioATerceros;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class ServicioATercerosParser extends AbstractParserRS<ServicioATerceros> {

	public ServicioATercerosParser(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void insert(SQLiteDatabase db, ServicioATerceros entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	public void insertDeta(SQLiteDatabase db, HorasConsumidor parent, ServicioATerceros entity) throws Exception {
		ContentValues registro = new ContentValues();
		registro.put("HoraConsumidor", parent.getCodigo());	
		registro.put("codEmpresa", entity.getCodEmpresa());
		registro.put("codFundo", entity.getCodFundo());
		registro.put("codModulo",entity.getCodModulo());
		registro.put("codTurno", entity.getCodTurno());
		//registro.put("codConsumidor", entity.getCodConsumidor());
		registro.put("codActividad", entity.getCodActividad());
		
		db.insert(getTableName(), null, registro);
	}
	
	@Override
	public void insertWithParent(SQLiteDatabase db, ServicioATerceros entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public void update(SQLiteDatabase db, ServicioATerceros entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	@Override
	public List<ServicioATerceros> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public ServicioATerceros buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

	public void updateDetas(SQLiteDatabase db, HorasConsumidor parent, ServicioATerceros entity) {
		ContentValues registro = null;
		deleteDetas(db, parent);
		
			registro = new ContentValues();			
			registro.put("HoraConsumidor", parent.getCodigo());
			registro.put("codEmpresa", entity.getCodEmpresa());
			registro.put("codFundo", entity.getCodFundo());
			registro.put("codModulo", entity.getCodModulo());
			registro.put("codTurno", entity.getCodTurno());
			//registro.put("codConsumidor", entity.getCodConsumidor());
			registro.put("codActividad", entity.getCodActividad());
					
			db.insert(getTableName(), null, registro);
		
	}

	@Override
	public void delete(SQLiteDatabase db, ServicioATerceros entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	public void delete(SQLiteDatabase db, HorasConsumidor entity) throws Exception {
		String[] whereArgs = {String.valueOf(entity.getCodigo())};
		db.delete(getTableName(), " HoraConsumidor = ? ", whereArgs);		
	}

	public void deleteDetas(SQLiteDatabase db, HorasConsumidor parent) {
		String[] whereArgs = { String.valueOf(parent.getCodigo()) };
		db.delete(getTableName(), " HoraConsumidor = ?  ", whereArgs);
	}
	
	public ServicioATerceros listar(SQLiteDatabase db, HorasConsumidor parent) throws Exception {
		String sql = "SELECT * FROM "+getTableName() +" where HoraConsumidor = "+ parent.getCodigo()+" ";
		Cursor c = db.rawQuery(sql, null);	
		ServicioATerceros result = new ServicioATerceros(); 
		//c.close();
		//return (ServicioATerceros) (read(c));
		if (c.moveToFirst()) {
			
				result = read(c);
			
		}
		
		return result;
	}
	
	@Override
	public ServicioATerceros read(Cursor c) throws Exception {
		ServicioATerceros obj = new ServicioATerceros();
		obj.setCodEmpresa(c.getString(c.getColumnIndex("codEmpresa")));
		obj.setCodFundo(c.getString(c.getColumnIndex("codFundo")));
		obj.setCodModulo(c.getString(c.getColumnIndex("codModulo")));
		obj.setCodTurno(c.getString(c.getColumnIndex("codTurno")));
		//obj.setCodConsumidor(c.getString(c.getColumnIndex("codConsumidor")));
		obj.setCodActividad(c.getString(c.getColumnIndex("codActividad")));
	    c.close();
		return obj;
	}
	
	@Override
	protected String getSqlCreate() {
		StringBuffer bufer = new StringBuffer();
		bufer.append("CREATE TABLE ");
		bufer.append(this.getTableName());
		bufer.append("( ");
		{			
			bufer.append("HoraConsumidor INTEGER, ");
			bufer.append("codEmpresa TEXT, ");
			bufer.append("codFundo TEXT, ");
			bufer.append("codModulo TEXT, ");
			bufer.append("codTurno TEXT, ");
			//bufer.append("codConsumidor TEXT, ");
			bufer.append("codActividad TEXT ");
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {
		return "ServicioATerceros";
	}
	
}

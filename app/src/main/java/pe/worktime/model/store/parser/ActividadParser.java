package pe.worktime.model.store.parser;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.Empresa;
//import Fundo;
//import Fundo;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class ActividadParser extends AbstractParserRS<Actividad> {

	public ActividadParser(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void insert(SQLiteDatabase db, Actividad entity) throws Exception {
		ContentValues registro = new ContentValues();
		
		registro.put("codAcividad", entity.getCodActividad());
		registro.put("actividad", entity.getActividad());
		registro.put("horas",  String.valueOf(entity.getHoras()));
		registro.put("tipo", entity.getTipo());
		registro.put("codCultivo", entity.getCodCultivo());
		registro.put("asistencia_b", entity.getAsitencia_b());

		db.insert(getTableName(), null, registro);
	}

	
	@Override
	public void insertWithParent(SQLiteDatabase db, Actividad entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public List<Actividad> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public Actividad buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

	@Override
	public void update(SQLiteDatabase db, Actividad entity) throws Exception {
		ContentValues registro = new ContentValues();
		
		registro.put("actividad", entity.getActividad());
		registro.put("horas", String.valueOf(entity.getHoras()));
		registro.put("tipo", entity.getTipo());
		registro.put("codCultivo", entity.getCodCultivo());
		registro.put("asistencia_b", entity.getAsitencia_b());

		
		String[] whereArgs = {
				entity.getCodActividad()};
		
		db.update(getTableName(), registro, " codAcividad = ? ", whereArgs);
	}

	@Override
	public void delete(SQLiteDatabase db, Actividad entity) throws Exception {
		String[] whereArgs = {
				entity.getCodActividad()};
		db.delete(getTableName(), " codAcividad = ? ", whereArgs);
	}

	@Override
	public Actividad read(Cursor c) throws Exception {
		Actividad obj = new Actividad();
		
		//obj.setCodActividad(c.getString(c.getColumnIndex("codEmpresa")));		
		obj.setCodActividad(c.getString(c.getColumnIndex("codAcividad")));
		obj.setActividad(c.getString(c.getColumnIndex("actividad")));
		obj.setHoras(c.getString(c.getColumnIndex("horas")));
		obj.setTipo(c.getString(c.getColumnIndex("tipo")));
		obj.setCodCultivo(c.getInt(c.getColumnIndex("codCultivo")));
		obj.setAsitencia_b(c.getInt(c.getColumnIndex("asistencia_b")));
		
		return obj;
	}

	@Override
	protected String getSqlCreate() {
		StringBuffer bufer = new StringBuffer();
		bufer.append("CREATE TABLE ");
		bufer.append(this.getTableName());
		bufer.append("( ");
		{			
			bufer.append("codAcividad TEXT, ");
			bufer.append("actividad TEXT, ");
			bufer.append("horas TEXT, ");
			bufer.append("tipo TEXT, ");
			bufer.append("codCultivo INTEGER, ");
			bufer.append("asistencia_b INTEGER ");
			
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {
		return "Actividad";
	}
}

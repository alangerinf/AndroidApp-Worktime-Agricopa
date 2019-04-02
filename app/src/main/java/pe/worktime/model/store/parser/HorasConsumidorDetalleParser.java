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
//import Fundo;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class HorasConsumidorDetalleParser extends AbstractParserRS<HorasConsumidorDetalle> {

	public HorasConsumidorDetalleParser(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void insert(SQLiteDatabase db, HorasConsumidorDetalle entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	public void insertDeta(SQLiteDatabase db, HorasConsumidor parent,
			HorasConsumidorDetalle entity) throws Exception {
		ContentValues registro = new ContentValues();
		registro.put("HoraConsumidor", parent.getCodigo());		
		registro.put("codTrabajador", entity.getCodTrabajador());
		registro.put("codTurno", entity.getCodTurno());
		registro.put("codConsumidor", entity.getCodConsumidor());
		registro.put("horaInicio", entity.getHoraInicio());
		registro.put("horaFin", entity.getHoraFin());
		registro.put("horas", entity.getHoras());
		registro.put("horaInicioMovil", entity.getHoraInicioMovil());
		registro.put("horaFinMovil", entity.getHoraFinMovil());
		db.insert(getTableName(), null, registro);
	}
	
	@Override
	public void insertWithParent(SQLiteDatabase db, HorasConsumidorDetalle entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public List<HorasConsumidorDetalle> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public HorasConsumidorDetalle buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

	@Override
	public void update(SQLiteDatabase db, HorasConsumidorDetalle entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	public void updateDetas(SQLiteDatabase db, HorasConsumidor parent,
			List<HorasConsumidorDetalle> entitys) {
		ContentValues registro = null;
		deleteDetas(db, parent);
		for (HorasConsumidorDetalle entity : entitys) {
			registro = new ContentValues();			
			registro.put("HoraConsumidor", parent.getCodigo());
			registro.put("codTrabajador", entity.getCodTrabajador());
			registro.put("codTurno", entity.getCodTurno());
			registro.put("codConsumidor", entity.getCodConsumidor());
			registro.put("horaInicio", entity.getHoraInicio());
			registro.put("horaFin", entity.getHoraFin());
			registro.put("horas", entity.getHoras());
			registro.put("horaInicioMovil", entity.getHoraInicioMovil());
			registro.put("horaFinMovil", entity.getHoraFinMovil());
			registro.put("horasDescanso",entity.getHoras_descanso());
			db.insert(getTableName(), null, registro);
		}
	}

	@Override
	public void delete(SQLiteDatabase db, HorasConsumidorDetalle entity) throws Exception {
		String[] whereArgs = {String.valueOf(entity.getCodTrabajador())};
		db.delete(getTableName(), " codTrabajador = ? AND horaFin = '' ", whereArgs);
	}
	
	public void delete(SQLiteDatabase db, HorasConsumidor entity) throws Exception {
		String[] whereArgs = {String.valueOf(entity.getCodigo())};
		db.delete(getTableName(), " HoraConsumidor = ? ", whereArgs);
	}

	public void deleteDetas(SQLiteDatabase db, HorasConsumidor parent) {
		String[] whereArgs = { String.valueOf(parent.getCodigo()) };
		db.delete(getTableName(), " HoraConsumidor = ?  ", whereArgs);
	}
	
	public List<HorasConsumidorDetalle> list(SQLiteDatabase db, HorasConsumidor parent) throws Exception {
		String sql = "SELECT * FROM "+getTableName() +" where HoraConsumidor = "+ parent.getCodigo()+" ";
		Cursor c = db.rawQuery(sql, null);
		List<HorasConsumidorDetalle> result = new ArrayList<HorasConsumidorDetalle>();
		if (c.moveToFirst()) {
			do {
				result.add(read(c));
			} while (c.moveToNext());
		}
		c.close();
		return result;
	}
	
	@Override
	public HorasConsumidorDetalle read(Cursor c) throws Exception {
		HorasConsumidorDetalle obj = new HorasConsumidorDetalle();
		obj.setCodTrabajador(c.getString(c.getColumnIndex("codTrabajador")));
		obj.setCodTurno(c.getString(c.getColumnIndex("codTurno")));
		obj.setCodConsumidor(c.getString(c.getColumnIndex("codConsumidor")));
		obj.setHoraInicio(c.getString(c.getColumnIndex("horaInicio")));
		obj.setHoraFin(c.getString(c.getColumnIndex("horaFin")));
		obj.setHoras(c.getInt(c.getColumnIndex("horas")));
		obj.setHoraInicioMovil(c.getString(c.getColumnIndex("horaInicioMovil")));
		obj.setHoraFinMovil(c.getString(c.getColumnIndex("horaFinMovil")));
		obj.setHoras_descanso(c.getInt(c.getColumnIndex("horasDescanso")));
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
			bufer.append("codTrabajador TEXT, ");
			bufer.append("codTurno TEXT, ");
			bufer.append("codConsumidor TEXT, ");
			bufer.append("horaInicio TEXT, ");
			bufer.append("horaFin TEXT, ");
			bufer.append("horas INTEGER, ");
			bufer.append("horaInicioMovil TEXT, ");
			bufer.append("horaFinMovil TEXT, ");
			bufer.append("horasDescanso TEXT ");
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {
		return "HorasConsumidorDetalle";
	}
	
}

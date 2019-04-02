package pe.worktime.model.store.parser;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

//import Actividad;
import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.entity.Empresa;
//import Fundo;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class ConsumidorIndirectoParser extends
        AbstractParserRS<ConsumidorIndirecto> {

	public ConsumidorIndirectoParser(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void insert(SQLiteDatabase db, ConsumidorIndirecto entity)
			throws Exception {

		ContentValues registro = new ContentValues();
		registro.put("codConsumidor", entity.getCodConsumidor());
		registro.put("descripcion", entity.getDescripcion());
		db.insert(getTableName(), null, registro);
	}

	@Override
	public void insertWithParent(SQLiteDatabase db, ConsumidorIndirecto entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public List<ConsumidorIndirecto> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public ConsumidorIndirecto buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

	@Override
	public void update(SQLiteDatabase db, ConsumidorIndirecto entity)
			throws Exception {

		ContentValues registro = new ContentValues();
		registro.put("codConsumidor", entity.getCodConsumidor());
		registro.put("descripcion", entity.getDescripcion());
	
		String[] whereArgs = { entity.getCodConsumidor()};
		db.update(getTableName(), registro, "CodConsumidor = ? ", whereArgs);
	}

	@Override
	public void delete(SQLiteDatabase db, ConsumidorIndirecto entity)
			throws Exception {
	
		String[] whereArgs = {entity.getCodConsumidor()};
		db.delete(getTableName(), " CodConsumidor = ? ", whereArgs);
	}

	@Override
	public ConsumidorIndirecto read(Cursor c) throws Exception {
		ConsumidorIndirecto obj = new ConsumidorIndirecto();
		
		obj.setCodConsumidor(c.getString(c.getColumnIndex("codConsumidor")));
		obj.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
		return obj;
	}

	@Override
	protected String getSqlCreate() {
		
		StringBuffer bufer = new StringBuffer();
		bufer.append("CREATE TABLE ");
		bufer.append(this.getTableName());
		bufer.append("( ");
		{
			bufer.append("codConsumidor TEXT, ");
			bufer.append("descripcion TEXT ");
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {
		return "ConsumidorIndirecto";
	}
}

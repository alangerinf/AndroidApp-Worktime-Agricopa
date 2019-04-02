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
import pe.worktime.model.entity.Productividad;
import pe.worktime.model.entity.ProductividadDetalle;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class ProductividadDetalleParser extends AbstractParserRS<ProductividadDetalle> {

	public ProductividadDetalleParser(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void insert(SQLiteDatabase db, ProductividadDetalle entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	public void insertDeta(SQLiteDatabase db, Productividad parent,
			ProductividadDetalle entity) throws Exception {
		ContentValues registro = new ContentValues();
		
		registro.put("codProductividad", entity.getCodProductividad());
		registro.put("codTrabajador", entity.getCodTrabajador());
		registro.put("codConsumidor", entity.getCodConsumidor());
		registro.put("cantidad", entity.getCantidad());		
		registro.put("horaRegMovil", entity.getHoraRegMovil());
		
		db.insert(getTableName(), null, registro);
	}
	
	@Override
	public void insertWithParent(SQLiteDatabase db, ProductividadDetalle entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public List<ProductividadDetalle> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public ProductividadDetalle buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

	@Override
	public void update(SQLiteDatabase db, ProductividadDetalle entity) throws Exception {
		throw new Exception("No Implement Exception");
	}

	public void updateDetas(SQLiteDatabase db, Productividad parent,
			List<ProductividadDetalle> entitys) {
		ContentValues registro = null;
		//deleteDetas(db, parent);
		for (ProductividadDetalle entity : entitys) {
			registro = new ContentValues();			
						
			//registro.put("codProductividad", entity.getCodProductividad());
			//registro.put("codTrabajador", entity.getCodTrabajador());
			//registro.put("codConsumidor", entity.getCodConsumidor());
			registro.put("cantidad", entity.getCantidad());
			registro.put("horaRegMovil", entity.getHoraRegMovil());

			String[] whereArgs = {String.valueOf(entity.getCodTrabajador()),String.valueOf(entity.getCodProductividad())};
			db.update(getTableName(), registro, " codTrabajador = ? AND codProductividad=? AND cantidad > 0", whereArgs);
			//db.insert(getTableName(), null, registro);
		}
	}

	@Override
	public void delete(SQLiteDatabase db, ProductividadDetalle entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	public void delete(SQLiteDatabase db, Productividad entity) throws Exception {
		String[] whereArgs = {String.valueOf(entity.getCodigo())};
		db.delete(getTableName(), " codProductividad = ? ", whereArgs);		
	}

	public void deleteDetas(SQLiteDatabase db, Productividad parent) {
		String[] whereArgs = { String.valueOf(parent.getCodigo()) };
		db.delete(getTableName(), " codProductividad = ?  ", whereArgs);
	}
	
	public List<ProductividadDetalle> list(SQLiteDatabase db, Productividad parent) throws Exception {
		String sql = "SELECT * FROM "+getTableName() +" where codProductividad = "+ parent.getCodigo()+" ";
		Cursor c = db.rawQuery(sql, null);
		List<ProductividadDetalle> result = new ArrayList<ProductividadDetalle>();
		if (c.moveToFirst()) {
			do {
				result.add(read(c));
			} while (c.moveToNext());
		}
		c.close();
		return result;
	}
	
	@Override
	public ProductividadDetalle read(Cursor c) throws Exception {
		ProductividadDetalle obj = new ProductividadDetalle();
						
		obj.setCodProductividad(c.getInt(c.getColumnIndex("codProductividad")));
		obj.setCodTrabajador(c.getString(c.getColumnIndex("codTrabajador")));
		obj.setCodConsumidor(c.getString(c.getColumnIndex("codConsumidor")));
		obj.setCantidad(c.getString(c.getColumnIndex("cantidad")));
		obj.setHoraRegMovil(c.getString(c.getColumnIndex("horaRegMovil")));
				
		return obj;
	}
	
	@Override
	protected String getSqlCreate() {
		StringBuffer bufer = new StringBuffer();
		bufer.append("CREATE TABLE ");
		bufer.append(this.getTableName());
		bufer.append("( ");
		{	
			bufer.append("codProductividad INTEGER, ");
			bufer.append("codTrabajador TEXT, ");
			bufer.append("codConsumidor TEXT, ");
			bufer.append("cantidad TEXT, ");
			bufer.append("horaRegMovil TEXT ");
			
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {
		return "ProductividadDetalle";
	}
	
}

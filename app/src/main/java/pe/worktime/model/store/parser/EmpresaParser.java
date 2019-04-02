package pe.worktime.model.store.parser;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

//import Actividad;
import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.Fundo;
//import Modulo;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class EmpresaParser extends AbstractParserRS<Empresa> {
	
	private FundoParser fParser;

	public EmpresaParser(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		fParser = new FundoParser(context, name, factory, version);
	}

	@Override
	public void insert(SQLiteDatabase db, Empresa entity) throws Exception {

		ContentValues registro = new ContentValues();
		registro.put("codEmpresa", entity.getCodEmpresa());
		registro.put("empresa", entity.getEmpresa());
		db.insert(getTableName(), null, registro);
	}
	

	public void insertWithParent(SQLiteDatabase db, Empresa entity) throws Exception {
		ContentValues registro = new ContentValues();
		registro.put("codEmpresa", entity.getCodEmpresa());
		registro.put("empresa", entity.getEmpresa());
		db.insert(getTableName(), null, registro);
		//Detalle
		for (Fundo fundo : entity.getFundos()) {
			fParser.insertWithFundos(db, entity, fundo);
		}	
	}
	
	@Override
	public List<Empresa> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public Empresa buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

	@Override
	public void update(SQLiteDatabase db, Empresa entity) throws Exception {

		ContentValues registro = new ContentValues();
		registro.put("codEmpresa", entity.getCodEmpresa());
		registro.put("empresa", entity.getEmpresa());
		String[] whereArgs = { entity.getCodEmpresa() };
		db.update(getTableName(), registro, "codEmpresa = ? ", whereArgs);
	}

	@Override
	public void delete(SQLiteDatabase db, Empresa entity) throws Exception {
		String[] whereArgs = { entity.getCodEmpresa() };
		db.delete(getTableName(), "codEmpresa = ? ", whereArgs);
	}

	@Override
	public Empresa read(Cursor c) throws Exception {
		Empresa obj = new Empresa();
		obj.setCodEmpresa(c.getString(c.getColumnIndex("codEmpresa")));
		obj.setEmpresa(c.getString(c.getColumnIndex("empresa")));
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
			bufer.append("empresa TEXT ");			
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {
		return "Empresa";
	}
}
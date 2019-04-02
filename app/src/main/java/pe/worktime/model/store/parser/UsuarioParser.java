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
import pe.worktime.model.entity.Usuario;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class UsuarioParser extends AbstractParserRS<Usuario> {

	public UsuarioParser(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);		
	}
	
	@Override
	public void insert(SQLiteDatabase db, Usuario entity) throws Exception {
		ContentValues registro = new ContentValues();
		registro.put("codigo", entity.getCodigo());
		registro.put("nombre", entity.getNombre());
		registro.put("user", entity.getUser());
		registro.put("descripcion", entity.getDescripcion());
		registro.put("clave", entity.getClave());
		registro.put("cfs", entity.getCfs());
		registro.put("fechaServer", entity.getFechaServer());
		registro.put("fechaCelular", entity.getFechaCelular());
		registro.put("flagautoloadmaster", entity.getFlagautoloadmaster());
		registro.put("flagautocleandata", entity.getFlagautocleandata());
		registro.put("flagautocleanlastdata", entity.getFlagautocleanlastdata());
		db.insert(getTableName(), null, registro);
	}

		
	@Override
	public void insertWithParent(SQLiteDatabase db, Usuario entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	@Override
	public List<Usuario> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}

	@Override
	public Usuario buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}


	@Override
	public void update(SQLiteDatabase db, Usuario entity) throws Exception {

		ContentValues registro = new ContentValues();
		registro.put("codigo", entity.getCodigo());
		registro.put("nombre", entity.getNombre());
		registro.put("user", entity.getUser());
		registro.put("descripcion", entity.getDescripcion());
		registro.put("clave", entity.getClave());
		registro.put("cfs", entity.getCfs());
		registro.put("fechaServer", entity.getFechaServer());
		registro.put("fechaCelular", entity.getFechaCelular());
		registro.put("flagautoloadmaster", entity.getFlagautoloadmaster());
		registro.put("flagautocleandata", entity.getFlagautocleandata());
		registro.put("flagautocleanlastdata", entity.getFlagautocleanlastdata());
	
		String[] whereArgs = { entity.getCodigo()};
		db.update(getTableName(), registro, "codigo = ? ", whereArgs);
	}

	@Override
	public void delete(SQLiteDatabase db, Usuario entity) throws Exception {
		String[] whereArgs = { entity.getCodigo()};
		db.delete(getTableName(), "codigo = ? ", whereArgs);
	}
	
	@Override
	public Usuario read(Cursor c) throws Exception {
		
		Usuario obj = new Usuario();	
		obj.setCodigo(c.getString(c.getColumnIndex("codigo")));
		obj.setNombre(c.getString(c.getColumnIndex("nombre")));
		obj.setUser(c.getString(c.getColumnIndex("user")));
		obj.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
		obj.setClave(c.getString(c.getColumnIndex("clave")));
		obj.setCfs(c.getString(c.getColumnIndex("cfs")));
		obj.setFechaServer(c.getString(c.getColumnIndex("fechaServer")));
		obj.setFechaCelular(c.getString(c.getColumnIndex("fechaCelular")));
		obj.setFlagautoloadmaster(c.getString(c.getColumnIndex("flagautoloadmaster")));
		obj.setFlagautocleandata(c.getString(c.getColumnIndex("flagautocleandata")));
		obj.setFlagautocleanlastdata(c.getString(c.getColumnIndex("flagautocleanlastdata")));
			
		return obj;
	}

	@Override
	protected String getSqlCreate() {
		
		StringBuffer bufer = new StringBuffer();
		bufer.append("CREATE TABLE ");
		bufer.append(this.getTableName());
		bufer.append("( ");
		{
			bufer.append("codigo TEXT, ");
			bufer.append("nombre TEXT, ");
			bufer.append("user TEXT, ");
			bufer.append("descripcion TEXT, ");
			bufer.append("clave TEXT, ");
			bufer.append("cfs TEXT, ");
			bufer.append("fechaServer TEXT, ");
			bufer.append("fechaCelular TEXT, ");
			bufer.append("flagautoloadmaster TEXT, ");
			bufer.append("flagautocleandata TEXT, ");
			bufer.append("flagautocleanlastdata TEXT ");
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {
		return "Usuario";
	}
}

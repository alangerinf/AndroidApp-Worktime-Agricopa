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
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.store.parser.util.AbstractParserRS;

public class HorasConsumidorParser extends AbstractParserRS<HorasConsumidor> {

	private HorasConsumidorDetalleParser dParser;
	private ServicioATercerosParser satParser;
	
	public HorasConsumidorParser(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		dParser = new HorasConsumidorDetalleParser(context, name, factory, version);
		satParser = new ServicioATercerosParser(context, name, factory, version);
	}
	
	@Override
	public void insert(SQLiteDatabase db, HorasConsumidor entity)
			throws Exception {
		ContentValues registro = new ContentValues();		
		//Header
		registro.put("codEmpresa", entity.getCodEmpresa());
		registro.put("codFundo", entity.getCodFundo());
		registro.put("codModulo", entity.getCodModulo());
		
		registro.put("codTurnoDia", entity.getCodTurnoDia());
		registro.put("codActividad", entity.getCodActividad());
		registro.put("codSupervisor", entity.getCodSupervisor());		
		registro.put("codPlantilla", entity.getCodPlantilla());
		registro.put("codGrupo", entity.getCodGrupo());
		registro.put("fecha", entity.getFecha());
		registro.put("imei", entity.getImei());
		registro.put("fechaRegistroMovil", entity.getFechaRegistroMovil());	
		registro.put("tipoLabor", entity.getTipolabor());
		registro.put("migrado", entity.getMigrado());
		registro.put("migradoProd", entity.getMigradoProd());
		registro.put("codCultivo", entity.getIdCultivo());
		registro.put("tipoActividad", entity.getTipoActividad());
		registro.put("codigoCecoOModulo", entity.getCodigoCecoOModulo());
		registro.put("nombreCecoOModulo", entity.getNombreCecoOModulo());
		registro.put("asistencia", entity.getAsistencia());
		//System.out.println("InsertTipolabor: " + entity.getTipolabor() );
		db.insert(getTableName(), null, registro);		
		entity.setCodigo(Integer.parseInt(getLastIdAutoincrement(db)));
		
		Log.d("INFO.ID", ""+entity.getCodigo());
		//Detalle		
		for (HorasConsumidorDetalle item : entity.getDetalle()) {
			dParser.insertDeta(db, entity,item);
		}
		
		//ServicioATerceros
		if(entity.getServicioATerceros() != null){
			satParser.insertDeta(db, entity, entity.getServicioATerceros());
		}
	}
	
	@Override
	public void insertWithParent(SQLiteDatabase db, HorasConsumidor entity) throws Exception {
		throw new Exception("No Implement Exception");
	}
	
	@Override
	public List<HorasConsumidor> list(SQLiteDatabase db, Empresa entity)  throws Exception {
		throw new Exception("No Implement Exception");
	}


	@Override
	public void update(SQLiteDatabase db, HorasConsumidor entity)
			throws Exception {
		ContentValues registro = new ContentValues();		
		
		registro.put("codEmpresa", entity.getCodEmpresa());
		registro.put("codFundo", entity.getCodFundo());
		registro.put("codModulo", entity.getCodModulo());
		
		registro.put("codTurnoDia", entity.getCodTurnoDia());
		registro.put("codActividad", entity.getCodActividad());
		registro.put("codSupervisor", entity.getCodSupervisor());		
		registro.put("codPlantilla", entity.getCodPlantilla());
		registro.put("codGrupo", entity.getCodGrupo());
		registro.put("fecha", entity.getFecha());
		registro.put("imei", entity.getImei());
		registro.put("fechaRegistroMovil", entity.getFechaRegistroMovil());
		registro.put("tipoLabor",entity.getTipolabor());
		registro.put("migrado", entity.getMigrado());
		registro.put("migradoProd", entity.getMigradoProd());
		registro.put("codCultivo", entity.getIdCultivo());
		registro.put("tipoActividad", entity.getTipoActividad());
		registro.put("codigoCecoOModulo", entity.getCodigoCecoOModulo());
		registro.put("nombreCecoOModulo", entity.getNombreCecoOModulo());
		registro.put("asistencia", entity.getAsistencia());

		String[] whereArgs = {String.valueOf(entity.getCodigo())};
		db.update(getTableName(), registro, " codigo = ? ", whereArgs);	
		
		//DetalleHorasConsumidor
		dParser.updateDetas(db, entity, entity.getDetalle());
		
		//ServicioATerceros
		if(entity.getServicioATerceros() != null){
			satParser.updateDetas(db, entity, entity.getServicioATerceros());
		}
		
	}

	@Override
	public void delete(SQLiteDatabase db, HorasConsumidor entity)
			throws Exception {
		String[] whereArgs = { String.valueOf(entity.getCodigo())};
		db.delete(getTableName(), " codigo = ? ", whereArgs);
		dParser.delete(db, entity);
		
		//ServicioATerceros
		if(entity.getServicioATerceros() != null){
			satParser.delete(db, entity);
		}
		
	}
	
	@Override
	public HorasConsumidor read(Cursor c) throws Exception {
		HorasConsumidor obj = new HorasConsumidor();
		
		obj.setCodigo(Integer.parseInt(c.getString(c.getColumnIndex("codigo"))));
		obj.setCodTurnoDia(c.getString(c.getColumnIndex("codTurnoDia")));
		obj.setCodActividad(c.getString(c.getColumnIndex("codActividad")));
		obj.setCodSupervisor(c.getString(c.getColumnIndex("codSupervisor")));		
		obj.setCodPlantilla(c.getString(c.getColumnIndex("codPlantilla")));
		obj.setCodGrupo(c.getString(c.getColumnIndex("codGrupo")));
		obj.setFecha(c.getString(c.getColumnIndex("fecha")));
		obj.setImei(c.getString(c.getColumnIndex("imei")));
		obj.setFechaRegistroMovil(c.getString(c.getColumnIndex("fechaRegistroMovil")));		
		
		obj.setCodEmpresa(c.getString(c.getColumnIndex("codEmpresa")));
		obj.setCodFundo(c.getString(c.getColumnIndex("codFundo")));
		obj.setCodModulo(c.getString(c.getColumnIndex("codModulo")));
		
		obj.setTipolabor(Integer.parseInt(c.getString(c.getColumnIndex("tipoLabor"))));
		obj.setMigrado(Integer.parseInt(c.getString(c.getColumnIndex("migrado"))));
		obj.setMigradoProd(Integer.parseInt(c.getString(c.getColumnIndex("migradoProd"))));
		obj.setIdCultivo(c.getString(c.getColumnIndex("codCultivo")));
		obj.setTipoActividad(c.getString(c.getColumnIndex("tipoActividad")));
        obj.setCodigoCecoOModulo(c.getString(c.getColumnIndex("codigoCecoOModulo")));
		obj.setNombreCecoOModulo(c.getString(c.getColumnIndex("nombreCecoOModulo")));
		obj.setAsistencia(Integer.parseInt(c.getString(c.getColumnIndex("asistencia"))));
		
		obj.setDetalle(dParser.list(this.getWritableDatabase(), obj));
	//	if (obj.getTipolabor() == 1){
		obj.setServicioATerceros(satParser.listar(this.getWritableDatabase(), obj));
		//}
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
			
			bufer.append("codEmpresa TEXT, ");
			bufer.append("codFundo TEXT, ");
			bufer.append("codModulo TEXT, ");
			
			bufer.append("codTurnoDia TEXT, ");
			bufer.append("codActividad TEXT, ");
			bufer.append("codSupervisor TEXT, ");
			bufer.append("codPlantilla TEXT, ");
			bufer.append("codGrupo TEXT, ");
			bufer.append("fecha TEXT, ");
			bufer.append("imei TEXT, ");
			bufer.append("fechaRegistroMovil TEXT, ");
			bufer.append("tipoLabor INTEGER, ");
			bufer.append("migrado INTEGER,");
			bufer.append("migradoProd INTEGER,");
			bufer.append("codCultivo TEXT, ");
			bufer.append("tipoActividad TEXT, ");
			bufer.append("codigoCecoOModulo TEXT, ");
			bufer.append("nombreCecoOModulo TEXT, ");
			bufer.append("asistencia INTEGER ");
		}
		bufer.append(" );");
		return bufer.toString();
	}

	@Override
	protected String getTableName() {		
		return "HorasConsumidor";
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
	public HorasConsumidor buscar(SQLiteDatabase db, String f) throws Exception {
		return null;
	}

}
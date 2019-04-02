package pe.worktime.model.store;

import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import pe.worktime.app.Application;
import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.entity.Cultivo;
import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Grupo;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.entity.Personal;
import pe.worktime.model.entity.Productividad;
import pe.worktime.model.entity.TurnoDia;
import pe.worktime.model.entity.Usuario;
import pe.worktime.model.entity.util.Credencial;
import pe.worktime.model.store.parser.delegate.RSActividad;
import pe.worktime.model.store.parser.delegate.RSConsumidorIndirecto;
import pe.worktime.model.store.parser.delegate.RSCultivo;
import pe.worktime.model.store.parser.delegate.RSEmpresa;
import pe.worktime.model.store.parser.delegate.RSFundo;
import pe.worktime.model.store.parser.delegate.RSGrupo;
import pe.worktime.model.store.parser.delegate.RSHorasConsumidor;
import pe.worktime.model.store.parser.delegate.RSHorasConsumidorDetalle;
import pe.worktime.model.store.parser.delegate.RSPersonal;
import pe.worktime.model.store.parser.delegate.RSProductividad;
import pe.worktime.model.store.parser.delegate.RSTurnoDia;
import pe.worktime.model.store.parser.delegate.RSUsuario;
import pe.worktime.model.store.parser.delegate.RSServicioATerceros;
import pe.worktime.model.store.parser.util.IFilterRS;

/**
 * 
 * @author Alfa
 */
public class RSManager {

	/* UTIL IMPLEMENTAR DESPUES */
	
    private RSServicioATerceros getRSServicioATerceros(){
    	return new RSServicioATerceros(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
    }


	private RSActividad getRSActividad() {
		return new RSActividad(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
	}

	private RSCultivo getRSCultivo() {
		return new RSCultivo(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
	}



	private RSGrupo getRSGrupo() {
		return new RSGrupo(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
	}

	private RSConsumidorIndirecto getRSConsumidorIndirecto() {
		return new RSConsumidorIndirecto(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
	}

	private RSTurnoDia getRSTurnoDia() {
		return new RSTurnoDia(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
	}

	private RSPersonal getRSPersonal() {
		return new RSPersonal(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
	}
	
	private RSEmpresa getRSEmpresa() {
		return new RSEmpresa(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
	}
	
	private RSUsuario getRSUsuario() {
		return new RSUsuario(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
	}
	
	private RSFundo getRSFundo() {
		return new RSFundo(getActualActivity(), getDBName(), getCursorFactory(), getVersion());
	}
	
	private RSHorasConsumidor getRSHorasConsumidor() {
		return new RSHorasConsumidor(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
	}

	private RSHorasConsumidorDetalle getRSHorasConsumidorDetalle() {
		return new RSHorasConsumidorDetalle(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
	}
	
	private RSProductividad getRSProductividad() {
		return new RSProductividad(getActualActivity(), getDBName(),
				getCursorFactory(), getVersion());
	}

	private Activity getActualActivity() {
		return Application.context.getManagerView().actual;
	}

	private String getDBName() {
		return "DBTareo.db";
	}

	private CursorFactory getCursorFactory() {
		return null;
	}

	private int getVersion() {
		return 5;
	}

	/* OPERACIONES DE USUARIO */

	public void setUsuario(Usuario usuario) throws Exception {
		RSUsuario delegate = getRSUsuario();
		delegate.cleanData();
		delegate.insert(usuario);
		delegate.closeDB();
	}

	public Usuario getUsuario() throws Exception {
		RSUsuario delegate = getRSUsuario();
		List<Usuario> lst = delegate.list();
		delegate.closeDB();
		if (lst == null)
			return null;
		if (lst.isEmpty())
			return null;
		return lst.get(0);
	}

	public Usuario loginUsuario(Credencial credencial) throws Exception {
		Usuario user = getUsuario();
		if (user.getClave().equalsIgnoreCase(credencial.getPassword())
				&& user.getUser().equalsIgnoreCase(credencial.getUsuario())
				&& user.getEmpresa().equalsIgnoreCase(Application.context.getEmpresaController().getSelected().getCodEmpresa())) {
			return user;
		}
		return null;
	}
	/*OPERACICIONES PERSONAL*/
	public List<Personal> getPersonal(String User) throws Exception {

		RSPersonal delegate = getRSPersonal();
		List<Personal> aux = delegate.list();
		delegate.closeDB();
		return aux;
	}

	public List<Personal> getPersonal(String User, IFilterRS filter)
			throws Exception {
		RSPersonal delegate = getRSPersonal();
		List<Personal> aux = delegate.filter(filter);
		delegate.closeDB();
		return aux;
	}

	public void selListPersonal(String User, List<Personal> list)
			throws Exception {
		RSPersonal delegate = getRSPersonal();
		delegate.cleanData();
		for (Personal personal : list) {
			delegate.insert(personal);
		}
		delegate.closeDB();
	}

	public Personal buscarPersonal(String dni) throws Exception {

		RSPersonal delegate = getRSPersonal();
		Personal aux = delegate.find(dni);
		delegate.closeDB();
		return aux;
	}
	/* OPERACIONES DE FUNDO */

	public void selListFundo(String User, List<Fundo> list) throws Exception {
		RSFundo delegate = getRSFundo();
		delegate.cleanData();
		for (Fundo fundo : list) {
			delegate.insert(fundo);
		}
		delegate.closeDB();
	}

	public void addFundo(String User, Fundo fundo) throws Exception {
		RSFundo delegate = getRSFundo();
		delegate.insert(fundo);
		delegate.closeDB();
	}

	public void setFundo(String User, Fundo fundo) throws Exception {
		RSFundo delegate = getRSFundo();
		delegate.update(fundo);
		delegate.closeDB();
	}

	public void delFundo(String User, Fundo fundo) throws Exception {
		RSFundo delegate = getRSFundo();
		delegate.delete(fundo);
		delegate.closeDB();
	}

	public List<Fundo> getFundos(String User) throws Exception {
		RSFundo delegate = getRSFundo();
		List<Fundo> aux = delegate.list();
		delegate.closeDB();
		return aux;
	}
	
	public List<Fundo> getFundos(String User, Empresa entity) throws Exception {
		RSFundo delegate = getRSFundo();
		List<Fundo> aux = delegate.listDeEmpresa(entity);
		delegate.closeDB();
		return aux;
	}
	
	public List<Fundo> getFundos(String User, IFilterRS filter)
			throws Exception {
		RSFundo delegate = getRSFundo();
		List<Fundo> aux = delegate.filter(filter);
		delegate.closeDB();
		return aux;
	}

	public void clearFundo(String User) throws Exception {
		RSFundo delegate = getRSFundo();
		delegate.cleanData();
		delegate.closeDB();
	}
	
	

	/* OPERACIONES DE ACTIVIDAD */

	public void selListActividad(String User, List<Actividad> list)
			throws Exception {
		RSActividad delegate = getRSActividad();
		delegate.cleanData();
		for (Actividad actividad : list) {
			delegate.insert(actividad);
		}
		delegate.closeDB();
	}

	public void addActividad(String User, Actividad actividad) throws Exception {
		RSActividad delegate = getRSActividad();
		delegate.insert(actividad);
		delegate.closeDB();
	}

	public void setActividad(String User, Actividad actividad) throws Exception {
		RSActividad delegate = getRSActividad();
		delegate.update(actividad);
		delegate.closeDB();
	}

	public void delActividad(String User, Actividad actividad) throws Exception {
		RSActividad delegate = getRSActividad();
		delegate.delete(actividad);
		delegate.closeDB();
	}

	public List<Actividad> getActividades(String User) throws Exception {

		RSActividad delegate = getRSActividad();
		List<Actividad> aux = delegate.list();
		delegate.closeDB();
		return aux;
	}

	public List<Actividad> getActividades(String User, IFilterRS filter)
			throws Exception {
		RSActividad delegate = getRSActividad();
		List<Actividad> aux = delegate.filter(filter);
		delegate.closeDB();
		return aux;
	}

	public void clearActividad(String User) throws Exception {
		RSActividad delegate = getRSActividad();
		delegate.cleanData();
		delegate.closeDB();
	}
	
	/*OPERACICIONES CULTIVO*/
	public List<Cultivo> getCultivo(String User) throws Exception {

		RSCultivo delegate = getRSCultivo();
		List<Cultivo> aux = delegate.list();
		delegate.closeDB();
		return aux;
	}

	public List<Cultivo> getCultivo(String User, IFilterRS filter)
			throws Exception {
		RSCultivo delegate = getRSCultivo();
		List<Cultivo> aux = delegate.filter(filter);
		delegate.closeDB();
		return aux;
	}

	public void selListCultivo(String User, List<Cultivo> list)
			throws Exception {
		RSCultivo delegate = getRSCultivo();
		delegate.cleanData();
		for (Cultivo cultivo : list) {
			delegate.insert(cultivo);
		}
		delegate.closeDB();
	}
	
	/* OPERACIONES DE GRUPO */

	public void selListGrupo(String User, List<Grupo> list) throws Exception {
		RSGrupo delegate = getRSGrupo();
		delegate.cleanData();
		for (Grupo grupo : list) {
			delegate.insert(grupo);
		}
		delegate.closeDB();
	}

	public void addGrupo(String User, Grupo grupo) throws Exception {
		RSGrupo delegate = getRSGrupo();
		delegate.insert(grupo);
		delegate.closeDB();
	}

	public void setGrupo(String User, Grupo grupo) throws Exception {
		RSGrupo delegate = getRSGrupo();
		delegate.update(grupo);
		delegate.closeDB();
	}

	public void delGrupo(String User, Grupo grupo) throws Exception {
		RSGrupo delegate = getRSGrupo();
		delegate.delete(grupo);
		delegate.closeDB();
	}

	public List<Grupo> getGrupos(String User) throws Exception {

		RSGrupo delegate = getRSGrupo();
		List<Grupo> aux = delegate.list();
		delegate.closeDB();
		return aux;
	}

	public List<Grupo> getGrupos(String User, IFilterRS filter)
			throws Exception {
		RSGrupo delegate = getRSGrupo();
		List<Grupo> aux = delegate.filter(filter);
		delegate.closeDB();
		return aux;
	}

	public void clearGrupo(String User) throws Exception {
		RSGrupo delegate = getRSGrupo();
		delegate.cleanData();
		delegate.closeDB();
	}

	
	
	/* OPERACIONES TURNO DIA */

	public void selListTurnoDia(String User, List<TurnoDia> list) throws Exception {
		RSTurnoDia delegate = getRSTurnoDia();
		delegate.cleanData();
		for (TurnoDia turnoDia : list) {
			delegate.insert(turnoDia);
		}
		delegate.closeDB();
	}

	public void addTurnoDia(String User, TurnoDia turnoDia) throws Exception {
		RSTurnoDia delegate = getRSTurnoDia();
		delegate.insert(turnoDia);
		delegate.closeDB();
	}

	public void setTurnoDia(String User, TurnoDia turnoDia) throws Exception {
		RSTurnoDia delegate = getRSTurnoDia();
		delegate.update(turnoDia);
		delegate.closeDB();
	}

	public void delTurnoDia(String User, TurnoDia turnoDia) throws Exception {

		RSTurnoDia delegate = getRSTurnoDia();
		delegate.delete(turnoDia);
		delegate.closeDB();
	}

	public List<TurnoDia> getTurnoDia(String User) throws Exception {
		RSTurnoDia delegate = getRSTurnoDia();
		List<TurnoDia> aux = delegate.list();
		delegate.closeDB();
		return aux;
	}

	public List<TurnoDia> getTurnoDias(String User, IFilterRS filter) throws Exception {
		RSTurnoDia delegate = getRSTurnoDia();
		List<TurnoDia> aux = delegate.filter(filter);
		delegate.closeDB();
		return aux;
	}

	public void clearTurnoDia(String User) throws Exception {
		RSTurnoDia delegate = getRSTurnoDia();
		delegate.cleanData();
		delegate.closeDB();
	}

	/* OPERACIONES EMPRESA */

	public void selListEmpresa(String User, List<Empresa> list) throws Exception {
		RSEmpresa delegate = getRSEmpresa();
		delegate.cleanData();
		for (Empresa empresa : list) {
			delegate.insert(empresa);
		}
		delegate.closeDB();
	}

	public void selListEmpresaWithFundos(String User, List<Empresa> list) throws Exception {
		RSEmpresa delegate = getRSEmpresa();
		RSFundo delegate2 = getRSFundo();
		delegate2.cleanData();
		delegate.cleanData();
		for (Empresa empresa : list) {
			delegate.insertWithParent(empresa);
		
		}
		delegate.closeDB();
		delegate2.closeDB();
	}
	
	public void addEmpresa(String User, Empresa empresa) throws Exception {
		RSEmpresa delegate = getRSEmpresa();
		delegate.insert(empresa);
		delegate.closeDB();
	}

	public void setEmpresa(String User, Empresa empresa) throws Exception {
		RSEmpresa delegate = getRSEmpresa();
		delegate.update(empresa);
		delegate.closeDB();
	}

	public void delEmpresa(String User, Empresa empresa) throws Exception {
		RSEmpresa delegate = getRSEmpresa();
		delegate.delete(empresa);
		delegate.closeDB();
	}

	public List<Empresa> getEmpresa(String User) throws Exception {
		RSEmpresa delegate = getRSEmpresa();
		List<Empresa> aux = delegate.list();
		delegate.closeDB();
		return aux;
	}

	public List<Empresa> getEmpresa(String User, IFilterRS filter)
			throws Exception {
		RSEmpresa delegate = getRSEmpresa();
		List<Empresa> aux = delegate.filter(filter);
		delegate.closeDB();
		return aux;
	}

	public void clearEmpresa(String User) throws Exception {
		RSEmpresa delegate = getRSEmpresa();
		delegate.cleanData();
		delegate.closeDB();
	}
	
	/* OPERACIONES DE CONSUMIDOR INDIRECTO */

	public void selListConsumidorIndirecto(String User,
			List<ConsumidorIndirecto> list) throws Exception {

		RSConsumidorIndirecto delegate = getRSConsumidorIndirecto();
		delegate.cleanData();
		for (ConsumidorIndirecto consumidorindirecto : list) {
			delegate.insert(consumidorindirecto);
		}
		delegate.closeDB();
	}

	public void addConsumidorIndirecto(String User,
			ConsumidorIndirecto consumidorIndirecto) throws Exception {
		RSConsumidorIndirecto delegate = getRSConsumidorIndirecto();
		delegate.insert(consumidorIndirecto);
		delegate.closeDB();
	}

	public void setConsumidorIndirecto(String User,
			ConsumidorIndirecto consumidorIndirecto) throws Exception {
		RSConsumidorIndirecto delegate = getRSConsumidorIndirecto();
		delegate.update(consumidorIndirecto);
		delegate.closeDB();
	}

	public void delConsumidorIndirecto(String User,
			ConsumidorIndirecto consumidorIndirecto) throws Exception {
		RSConsumidorIndirecto delegate = getRSConsumidorIndirecto();
		delegate.delete(consumidorIndirecto);
		delegate.closeDB();
	}

	public List<ConsumidorIndirecto> getConsumidorIndirecto(String User)
			throws Exception {
		RSConsumidorIndirecto delegate = getRSConsumidorIndirecto();
		List<ConsumidorIndirecto> aux = delegate.list();
		delegate.closeDB();
		return aux;
	}

	public List<ConsumidorIndirecto> getConsumidorIndirecto(String User,IFilterRS filter) throws Exception {
		RSConsumidorIndirecto delegate = getRSConsumidorIndirecto();
		List<ConsumidorIndirecto> aux = delegate.filter(filter);
		delegate.closeDB();
		return aux;
	}

	public void clearConsumidorIndirecto(String User) throws Exception {
		RSConsumidorIndirecto delegate = getRSConsumidorIndirecto();
		delegate.cleanData();
		delegate.closeDB();
	}
	
	
	
	/* OPERACIONES DE HORA CONSUMIDOR ( R E G I S T R O )  */

	public void selHorasConsumidor(String User,
			List<HorasConsumidor> list) throws Exception {
		RSHorasConsumidor delegate = getRSHorasConsumidor();
		delegate.cleanData();
		for (HorasConsumidor horasConsumidor : list) {
			delegate.insert(horasConsumidor);
		}
		delegate.closeDB();
	}

	public void addOrUpdateHorasConsumidor(String User,	final HorasConsumidor horasConsumidor) throws Exception {
		RSHorasConsumidor delegate = getRSHorasConsumidor();				
		List<HorasConsumidor> result = delegate.filter(new IFilterRS() {			
			@Override
			public Cursor find(SQLiteDatabase db) throws Exception {		
				/*String query = "select * from HorasConsumidor where fechaRegistroMovil = '"+horasConsumidor.getFechaRegistroMovil()+"' "+
						" AND imei = '"+horasConsumidor.getImei()+"' AND codSupervisor = '"+horasConsumidor.getCodSupervisor()+"' "+
						" AND codActividad = '"+ horasConsumidor.getCodActividad() +"' AND codGrupo = '"+horasConsumidor.getCodGrupo()+"' ";*/


				String query = "select * from HorasConsumidor where fechaRegistroMovil = '"+horasConsumidor.getFechaRegistroMovil()+"' "+
						" AND imei = '"+horasConsumidor.getImei()+"' AND codSupervisor = '"+horasConsumidor.getCodSupervisor()+"' "+
						" AND codActividad = '"+ horasConsumidor.getCodActividad() +"' AND codGrupo = '"+horasConsumidor.getCodGrupo()+"'";				
				return db.rawQuery(query, null);
			}
		});
		Log.d("INFo...FILTER", ""+result.size());
		if(result.size()==0){
			Log.d("INSERT OR UPDATE", "INSERT");
			delegate.insert(horasConsumidor);
		}else{
			Log.d("INSERT OR UPDATE", "UPDATE");
			horasConsumidor.setCodigo(result.get(0).getCodigo());
			delegate.update(horasConsumidor);
		}
		delegate.closeDB();
	}
	
	public void addHorasConsumidor(String User,
			HorasConsumidor horasConsumidor) throws Exception {
		RSHorasConsumidor delegate = getRSHorasConsumidor();
		delegate.insert(horasConsumidor);
		delegate.closeDB();
	}

	public void setHorasConsumidor(String User,
			HorasConsumidor horasConsumidor) throws Exception {
		RSHorasConsumidor delegate = getRSHorasConsumidor();
		delegate.update(horasConsumidor);
		delegate.closeDB();
	}

	public void delHorasConsumidor(String User,
			HorasConsumidor horasConsumidor) throws Exception {
		RSHorasConsumidor delegate = getRSHorasConsumidor();
		delegate.delete(horasConsumidor);
		delegate.closeDB();
	}

	public void eliminarTareoTrabajador(HorasConsumidorDetalle horasConsumidorDetalle) throws Exception {
		RSHorasConsumidorDetalle delegate = getRSHorasConsumidorDetalle();
		delegate.delete(horasConsumidorDetalle);
		delegate.closeDB();
	}


	public List<HorasConsumidor> getHorasConsumidor(String User)
			throws Exception {
		RSHorasConsumidor delegate = getRSHorasConsumidor();
		List<HorasConsumidor> aux = delegate.list();
		delegate.closeDB();
		return aux;
	}

	public List<HorasConsumidor> getHorasConsumidor(String User,IFilterRS filter) throws Exception {
		RSHorasConsumidor delegate = getRSHorasConsumidor();
		List<HorasConsumidor> aux = delegate.filter(filter);
		delegate.closeDB();
		return aux;
	}

	public void clearHorasConsumidor(String User) throws Exception {
		RSHorasConsumidor delegate = getRSHorasConsumidor();
		delegate.cleanData();
		delegate.closeDB();
	}


	/* P R O D U C T I V I D A D */
	public void selProductividad(String User,
			List<Productividad> list) throws Exception {
		RSProductividad delegate = getRSProductividad();
		delegate.cleanData();
		for (Productividad horasConsumidor : list) {
			delegate.insert(horasConsumidor);
		}
		delegate.closeDB();
	}

	public void addOrUpdateProductividad(String User,final Productividad horasConsumidor) throws Exception {
		RSProductividad delegate = getRSProductividad();				
		List<Productividad> result = delegate.filter(new IFilterRS() {			
			@Override
			public Cursor find(SQLiteDatabase db) throws Exception {		
//				String query = "select * from Productividad where FechaRegistroMovil = '"
//						+horasConsumidor.getFechaRegistroMovil()+"' "+
//						" AND IMEI = '"+horasConsumidor.getIMEI()+"' AND CodUser = '"+horasConsumidor.getCodUser()+"' "+
//						" AND CodActvidad = '"+ horasConsumidor.getCodActvidad() +"' ";

				String query = "select * from Productividad where Fecha = '"
						+horasConsumidor.getFecha()+"' AND FechaRegistroMovil='"+horasConsumidor.getFechaRegistroMovil()+"'  AND IMEI = '"+horasConsumidor.getIMEI()+"' AND CodUser = '"+horasConsumidor.getCodUser()+"' "+
						" AND CodActvidad = '"+ horasConsumidor.getCodActvidad() +"' AND CodModulo= '"+horasConsumidor.getCodModulo()+"' AND CodFundo='"+horasConsumidor.getCodFundo()+"'";

				return db.rawQuery(query, null);
			}
		});
		Log.d("INFo...FILTER", ""+result.size());
		if(result.size()==0){
			Log.d("INSERT OR UPDATE", "INSERT");
			delegate.insert(horasConsumidor);
		}else{
			Log.d("INSERT OR UPDATE", "UPDATE");
			horasConsumidor.setCodigo(result.get(0).getCodigo());
			delegate.update(horasConsumidor);
		}
		delegate.closeDB();
	}
	
	public void addProductividad(String User,
			Productividad horasConsumidor) throws Exception {
		RSProductividad delegate = getRSProductividad();
		delegate.insert(horasConsumidor);
		delegate.closeDB();
	}

	public void setProductividad(String User,
			Productividad horasConsumidor) throws Exception {
		RSProductividad delegate = getRSProductividad();
		delegate.update(horasConsumidor);
		delegate.closeDB();
	}

	public void delProductividad(String User,
			Productividad horasConsumidor) throws Exception {
		RSProductividad delegate = getRSProductividad();
		delegate.delete(horasConsumidor);
		delegate.closeDB();
	}

	public List<Productividad> getProductividad(String User)
			throws Exception {
		RSProductividad delegate = getRSProductividad();
		List<Productividad> aux = delegate.list();
		delegate.closeDB();
		return aux;
	}

	public List<Productividad> getProductividad(String User,IFilterRS filter) throws Exception {
		RSProductividad delegate = getRSProductividad();
		List<Productividad> aux = delegate.filter(filter);
		delegate.closeDB();
		return aux;
	}

	public void clearProductividad(String User) throws Exception {
		RSProductividad delegate = getRSProductividad();
		delegate.cleanData();
		delegate.closeDB();
	}

	public void crearTablaServicioATerceros() throws Exception {
		RSServicioATerceros delegate = getRSServicioATerceros();
		delegate.cleanData();
		delegate.closeDB();
	}
	
	public void clearServicioATerceros(String User) throws Exception {
		RSServicioATerceros delegate = getRSServicioATerceros();
		delegate.cleanData();
		delegate.closeDB();
	}
	
}

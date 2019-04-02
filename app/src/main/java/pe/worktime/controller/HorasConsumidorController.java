package pe.worktime.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;

import pe.worktime.ContextTest;
import pe.worktime.ManualAsistenciaActivity;
import pe.worktime.app.Application;
import pe.worktime.controller.delegate.HorasConsumidorDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Grupo;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.entity.Modulo;
import pe.worktime.model.entity.ServicioATerceros;
import pe.worktime.model.entity.Turno;
import pe.worktime.model.entity.util.KeyTransac;


public class HorasConsumidorController extends AbstractController {
	private HorasConsumidor selected = null;
	private HorasConsumidorDelegate delegate = null;
	//private List<HorasConsumidor> lista;

	public void InitHorasConsumidor() {

		selected.setCodSupervisor(Application.context.getUser());
		selected.setImei(Application.context.getUser());
		selected.setCodSupervisor(Application.context.getIMEI());
		selected.setCodPlantilla("");
		selected.setFecha(Application.context.getHorasConsumidorController()
				.getSelected().getFecha());
		selected.setFechaRegistroMovil(Application.context
				.getHorasConsumidorController().getSelected()
				.getFechaRegistroMovil());
		selected.setCodEmpresa(Application.context.getEmpresaController().getSelected().getCodEmpresa());
		selected.setCodFundo(Application.context.getFundoController().getSelected().getCodFundo());
		//selected.setCodModulo(Application.context.getFundoController().getModuloSelected().getCodModulo());
		selected.setCodModulo(Application.context.getFundoController().getTurnoSelected().getCod_turno());
		selected.setMigrado(Application.context.getHorasConsumidorController().getSelected().getMigrado());
		selected.setTipolabor(0);
	}

	public HorasConsumidorController() {
		delegate = new HorasConsumidorDelegate();
	}

	public HorasConsumidor getSelected() {
		return selected;
	}

	public void setSelected(HorasConsumidor selected) {
		this.selected = selected;
		try {
			//throw new Exception("Inicializando");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HorasConsumidorDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(HorasConsumidorDelegate delegate) {
		this.delegate = delegate;
	}

	
	@Override
	public void loadMaster() throws Exception {
		List<HorasConsumidor> lst = null;
		boolean loadedWS = false;
		try {
			//delegate.crearTablaServicioATerceros();
			KeyTransac key = Application.context.getKeyUser();
			lst = delegate.getHorasConsumidors(AbstractDelegate.ONLY_RS, key);
			if(lst == null){
				loadedWS = true;
				lst = new ArrayList<HorasConsumidor>();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (loadedWS) {
			delegate.setHorasConsumidors(Application.context.getUser(), lst);
		}
	}
	
	public void cleanData() throws Exception{
		delegate.clearHorasConsumidors(Application.context.getUser(), new ArrayList<HorasConsumidor>());
	}
	
	//@Override
	public void _loadMaster() throws Exception {
		List<HorasConsumidor> lst = null;
		boolean loadedWS = false;
		try {
			KeyTransac key = Application.context.getKeyUser();
			lst = delegate.getHorasConsumidors(AbstractDelegate.ONLY_WS, key);
			loadedWS = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (loadedWS) {
			
			//lista = lst;
			//if(lst.size() != 0){
				delegate.setHorasConsumidors(Application.context.getUser(), lst);
			//}
		}
		/*
		if (lista == null) {
			lista = new ArrayList<HorasConsumidor>();
		}
		*/
	}

	public boolean isValidEntrada(){
		return true;
	}
	
	public boolean isValidSalida() throws  Exception{
		boolean ret = false;
		if(selected != null){
			for (HorasConsumidorDetalle deta : selected.getDetalle()) {
				if(deta.isWorking()){
					ret = true;
				}
			}
		}
		if(!ret){
			throw new Exception("Planilla Cerrada");
		}
		
		return ret;
	}
	
	public boolean isValidTransferencia() throws Exception {
		boolean ret = false;
		if(selected != null){
			for (HorasConsumidorDetalle deta : selected.getDetalle()) {
				if(deta.isWorking()){
					ret = true;
				}
			}
		}
		if(!ret){
			throw new Exception("Planilla Cerrada");
		}
		
		return ret;
	}
	
	public void eliminarTareoTrabajador(HorasConsumidorDetalle hcd)throws  Exception{
		delegate.eliminarTareoTrabajador(hcd);
	}

	public void seleccionarPlanillaEliminar(int index) {
		try {
			List<HorasConsumidor> items = new ArrayList<HorasConsumidor>();
			items = listar();
			//Filtramos solo las horasconsumidor que no hayan sido migrados
			List<HorasConsumidor> itemsfiltered =  new ArrayList<HorasConsumidor>();
			for (HorasConsumidor item : items) {
				if (item.getMigrado() == 0){
					itemsfiltered.add(item);
				}
			}

			selected = itemsfiltered.get(index);
			Application.context.getHorasConsumidorController().eliminarTareo(selected);

		} catch (Exception e) {
			e.printStackTrace();
			selected = null;
		}
	}

	public void eliminarTareo(HorasConsumidor hc)throws  Exception{
		delegate.eliminarTareo(hc);
	}
	
	@Override
	public void seleccionar(int index) {
		try {
			List<HorasConsumidor> items = new ArrayList<HorasConsumidor>();
			items = listar();
			//Filtramos solo las horasconsumidor que no hayan sido migrados
			List<HorasConsumidor> itemsfiltered =  new ArrayList<HorasConsumidor>();
			for (HorasConsumidor item : items) {
				if (item.getMigrado() == 0){
					itemsfiltered.add(item);
				}
			}

			selected = itemsfiltered.get(index);
			if(selected.getTipoActividad().equals("D")){
				ContextTest.idxDirecORIn = 0;
			}else if(selected.getTipoActividad().equals("I")){
				ContextTest.idxDirecORIn = 1;
			}


//				String cTurno = selected.getDetalle().get(0).getCodTurno();
//				/* Sel Turno Modulo */
//				List<Fundo> fundos = Application.context.getFundoController().listar();
//				for (Fundo fundo : fundos) {
//					for (Modulo modulo : fundo.getModulos()) {
//						//for (Turno turno : modulo.getTurnos()) {
//							if(modulo.getCodModulo().equalsIgnoreCase(cTurno)){
//								Application.context.getFundoController().setSelected(fundo);
//								Application.context.getFundoController().setModuloSelected(modulo);
//								//Application.context.getFundoController().setTurnoSelected(turno);
//							}
//						//}
//					}
//				}


				//String cCodconsumidor = selected.getDetalle().get(0).getCodConsumidor();
//				String cCodconsumidor = selected.getDetalle().get(0).getCodTurno();
//				if(!cCodconsumidor.equalsIgnoreCase("")) {
//					List<ConsumidorIndirecto> consumidorIndirecto = Application.context.getConsumidorIndirectoController().listar();
//					for (ConsumidorIndirecto CI : consumidorIndirecto) {
//						if (CI.getCodConsumidor().equalsIgnoreCase(cCodconsumidor)) {
//							Application.context.getConsumidorIndirectoController().setSelected(CI);
//						}
//					}
//				}

//				String cGrupo = selected.getCodGrupo();
//				List<Grupo> grupos = Application.context.getGrupoController().listar();
//				for (Grupo grupo : grupos) {
//					if(grupo.getCodGrupo().equalsIgnoreCase(cGrupo)){
//						Application.context.getGrupoController().setSelected(grupo);
//					}
//				}

//				String cActividad = selected.getCodActividad();
//				List<Actividad> actividades = Application.context.getActividadController().listar();
//				for (Actividad actividad : actividades) {
//					if(actividad.getCodActividad().equalsIgnoreCase(cActividad)){
//						Application.context.getActividadController().setSelected(actividad);
//						if(actividad.getTipo().equals("D")){
//							ContextTest.idxDirecORIn = 0;
//						}else if(actividad.getTipo().equals("I")){
//							ContextTest.idxDirecORIn = 1;
//						}
//					}
//				}

			} catch (Exception e) {
				e.printStackTrace();
				selected = null;
			}		
	}
	
	public void seleccionarMigrados(int index) {
		try {
			    List<HorasConsumidor> items = new ArrayList<HorasConsumidor>();
			    items = listar();
			   //Filtramos solo las horasconsumidor que no hayan sido migrados
				List<HorasConsumidor> itemsfiltered =  new ArrayList<HorasConsumidor>();
				for (HorasConsumidor item : items) {
					if (item.getMigrado() == 1){
						itemsfiltered.add(item);
					}
				}
				
				selected = itemsfiltered.get(index);
			

			} catch (Exception e) {
				e.printStackTrace();
				selected = null;
			}		
	}

	public List<HorasConsumidor> listar() {
		try {
			return delegate.getHorasConsumidors(Application.context.getUser());
			//return lista;
		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),
					e.getMessage());
		}
		return null;
	}
	
	public List<HorasConsumidor> listar_transferencia() {
		try {
			List<HorasConsumidor> lst = listar();
			List<HorasConsumidor> result = new ArrayList<HorasConsumidor>();
			for (HorasConsumidor item : lst) {
				//Log.e("Lista Valida Transferencia", " "+item.getCodGrupo());
				if(!item.isPlanillaCerrada()){
					//Log.e("Lista Valida Transferencia", "   No Cerrada "+item.getCodGrupo());
					if(!isEquals(selected, item)){
					//	Log.e("Lista Valida Transferencia", "             No Equal "+item.getCodGrupo());
						result.add(item);
					}
				}
			}
			return result;
			//return lista;
		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),
					e.getMessage());
		}
		return null;
	}

	@Override
	public void seleccionar(String key) {

	}

	// Automatico
	public void addDetalleDirecto(String CodModulo, String dni )throws Exception  {
		validateDNIIngreso(dni);
		String hInicio = null;
		if (selected.getDetalle().size() == 0) {
			hInicio = Application.context.getFecha();
		} else {
			hInicio = selected.getDetalle().get(0).getHoraInicio();
		}
		addDetalleDirecto(CodModulo, dni, Application.context.getFecha());
	}

	public String getInicioGeneralAutomatico() {
		if(selected.getDetalle().size()!=0){
			if (selected.getDetalle().get(0).getHoraInicio() == null) {
				return "";
			}
			return selected.getDetalle().get(0).getHoraInicio();
		}
		return "";
	}

	// Manual
	public void addDetalleDirecto(String CodModulo, String dni, String hInicio ) throws Exception {
		if (!checkAsistencia(dni)) {
			validateDNIIngreso(dni);
			String FechaHora = Application.context.getFechaOnly()+" "+hInicio+":00";
			HorasConsumidorDetalle objHorasConsumidorDetalle = new HorasConsumidorDetalle();
			objHorasConsumidorDetalle.setCodTurno(CodModulo);
			objHorasConsumidorDetalle.setCodTrabajador(dni);
			//objHorasConsumidorDetalle.setHoraInicioMovil(Application.context.getFecha());
			objHorasConsumidorDetalle.setHoraInicioMovil(FechaHora);
			objHorasConsumidorDetalle.setHoraInicio(hInicio);
			if (objHorasConsumidorDetalle != null) {
				Application.context.getHorasConsumidorController().getSelected().getDetalle().add(objHorasConsumidorDetalle);
				ManualAsistenciaActivity.reloadProducts();
			}
		}
	}
	public void addDetalleIndirecto(String CodConsumidor, String dni,String hInicio) throws Exception {
		if (!checkAsistencia(dni)) {
			validateDNIIngreso(dni);
			String FechaHora = Application.context.getFechaOnly()+" "+hInicio+":00";
			HorasConsumidorDetalle objHorasConsumidirDetalle = new HorasConsumidorDetalle();
			objHorasConsumidirDetalle.setCodConsumidor(CodConsumidor);
			objHorasConsumidirDetalle.setCodTrabajador(dni);
			//objHorasConsumidirDetalle.setHoraInicioMovil(Application.context.getFecha());
			objHorasConsumidirDetalle.setHoraInicioMovil(FechaHora);
			objHorasConsumidirDetalle.setHoraInicio(hInicio);

			if (objHorasConsumidirDetalle != null) {
				Application.context.getHorasConsumidorController().getSelected().getDetalle().add(objHorasConsumidirDetalle);
				ManualAsistenciaActivity.reloadProducts();
			}
		}
	}

	/* AUTOMATICO */

	public void addDetalleIndirecto(String CodConsumidor, String dni ) throws Exception {
		validateDNIIngreso(dni);
		String hInicio = null;
		if (selected.getDetalle().size() == 0) {
			hInicio = Application.context.getFecha();
		} else {
			hInicio = selected.getDetalle().get(0).getHoraInicio();
		}
		addDetalleIndirecto(CodConsumidor, dni, Application.context.getFecha());
	}
	
	private void validateDNIIngreso(String dni) throws Exception{
		//Log.d("INFO", " validateDNIIngreso(String dni) "+dni );
		List<HorasConsumidor> lst = listar();
		for (HorasConsumidor horasConsumidor : lst) {
			for (HorasConsumidorDetalle deta : horasConsumidor.getDetalle()) {
				if(deta.getCodTrabajador().equalsIgnoreCase(dni)){
					if(deta.isPresente()){
						throw new Exception("Trabajador en Actividad en Planilla existente.");
					}
				}
			}
		}		
	}
	
	public boolean isDNIInPlanillaProductividad(String dni, String codConsumidor, String codActividad, String fecha)throws Exception{
		List<HorasConsumidor> lst = listar();
		for (HorasConsumidor horasConsumidor : lst) {
			if(horasConsumidor.isInFecha(fecha)){
				/* Verificamos Actividad */
				if(horasConsumidor.getCodActividad().equalsIgnoreCase(codActividad)){
					for (HorasConsumidorDetalle deta : horasConsumidor.getDetalle()) {
						/* Verificamos DNI y Consumidor */
						if(deta.getCodTrabajador().equalsIgnoreCase(dni) ){
							Log.d("Equals ", deta.getCodTurno().equalsIgnoreCase(codConsumidor) +" ("+deta.getCodTurno()+"|"+codConsumidor+")");
							if(deta.getCodTurno().equalsIgnoreCase(codConsumidor)){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/* MANUAL */


	public boolean checkAsistencia(String doc) {
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if (deta.getCodTrabajador().equalsIgnoreCase(doc)) {
				deta.setHoraInicio(String.valueOf(new Date()));
				return true;
			}
		}
		return false;
	}
	
	
	public boolean salidaOK(String dni , int horas_descanso) throws Exception{
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if (deta.getCodTrabajador().equalsIgnoreCase(dni) && deta.getHoraFin().equalsIgnoreCase("") ) {
				deta.setHoraFin(Application.context.getFecha());
				deta.setHoraFinMovil(Application.context.getFecha());
				deta.setHoras_descanso(horas_descanso);
				return true;
			}
		}
		return false;
	}
	
	public boolean salidaOK(String dni, String hora , int horas_descanso ) throws Exception{
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if (deta.getCodTrabajador().equalsIgnoreCase(dni) && deta.getHoraFin().equalsIgnoreCase("") ) {
				deta.setHoraFin(Application.context.getFechaOnly()+" "+hora+":00");		
				deta.setHoraFinMovil(Application.context.getFecha());
				deta.setHoras_descanso(horas_descanso);
				return true;
			}
		}
		return false;
	}
	
	public boolean salidaOKTarea(String dni, String hora) throws Exception{
	
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if (deta.getCodTrabajador().equalsIgnoreCase(dni) && deta.getHoraFin().equalsIgnoreCase("") ) {
				deta.setHoraFin(hora);
				deta.setHoraFinMovil(Application.context.getFecha());
				return true;
			}
		}
		return false;
	}
	
	
	public boolean salidaAllOK(String hora , int horas_descanso) throws Exception{
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if (deta.isPresente()) {
				deta.setHoraFinIntent(Application.context.getFechaOnly()+" "+hora+":00");
				deta.setHoras_descanso(horas_descanso);
			}
		}		
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if (deta.isPresente()) {
				deta.setHoraFin(Application.context.getFechaOnly()+" "+hora+":00");
				deta.setHoraFinMovil(Application.context.getFecha());
				deta.setHoras_descanso(horas_descanso);
			}
		}
		return true;
	}
	
	public boolean salidaAllOKTarea(String hora) throws Exception{		
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if (deta.isPresente()) {
				
				deta.setHoraFinIntent(hora);
			}
		}		
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if (deta.isPresente()) {
				deta.setHoraFin(hora);
				deta.setHoraFinMovil(Application.context.getFecha());
			}
		}
		return true;
	}
	
	
	public boolean verificarDNI(String dni){
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if (deta.getCodTrabajador().equalsIgnoreCase(dni)) {
				return true;
			}
		}
		return false;
	}

	public boolean verificarDNITransferencia(String dni){
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if (deta.getCodTrabajador().equalsIgnoreCase(dni) && deta.getHoraFin().equalsIgnoreCase("")) {
				return true;
			}
		}
		return false;
	}
	
	//Transferencia Manual
	public boolean transferir(String dni, int idxDestino, String hFin) throws Exception{
		HorasConsumidorDetalle detas =null;
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if(deta.getCodTrabajador().equalsIgnoreCase(dni)){
				detas = deta;
			}
		}
		if(detas==null){
			return false;
		}

		HorasConsumidor dest = null;

		dest = listar_transferencia().get(idxDestino);
		
		if(isEquals(selected, dest)){
			throw new Exception("La planilla Destino no Puede ser la Planilla Origen.");
		}
		
		if(selected.getTipolabor() != dest.getTipolabor()){
			throw new Exception("La planilla Destino no Puede ser de diferente tipo de labor.");
		}

		String hInicioFin = Application.context.getFechaOnly()+" "+hFin+":00";
		String hInicioFinMovil = Application.context.getFecha();

		HorasConsumidorDetalle objHorasConsumidirDetalle = new HorasConsumidorDetalle();
		objHorasConsumidirDetalle.setCodConsumidor(dest.getDetalle().get(0).getCodConsumidor());
		objHorasConsumidirDetalle.setCodTrabajador(dni);
		objHorasConsumidirDetalle.setHoraInicioMovil(hInicioFinMovil);
		objHorasConsumidirDetalle.setHoraInicio(hInicioFin);
		if (objHorasConsumidirDetalle != null) {
			// SET COD CONSUMIDOR DETA
			objHorasConsumidirDetalle.setCodConsumidor( dest.getDetalle().get(0).getCodConsumidor() );
			objHorasConsumidirDetalle.setCodTurno( dest.getDetalle().get(0).getCodTurno() );
			dest.getDetalle().add(objHorasConsumidirDetalle);
		}

		detas.setHoraFinIntent(hInicioFin);
		detas.setHoraFin(hInicioFin);
		detas.setHoraFinMovil(hInicioFinMovil);
		
		delegate.addOrUpdateHorasConsumidors(Application.context.getUser(),dest);
		delegate.addOrUpdateHorasConsumidors(Application.context.getUser(),selected);

		return true;
	}
	
	public boolean isEquals(HorasConsumidor src, HorasConsumidor tar){
		Log.d("isEquals", "Entro Funcion ");
		if(src == null || tar == null) return false;
		Log.d("isEquals", "Src: "+src.getCodigo()+" Dst: "+tar.getCodigo());
		return src.getCodigo() == tar.getCodigo();
		
	}
	
	//Transferencia AutoMatica
	public boolean transferir(String dni, int idxDestino) throws Exception{
		HorasConsumidorDetalle detas = null;
		for (HorasConsumidorDetalle deta : selected.getDetalle()) {
			if(deta.getCodTrabajador().equalsIgnoreCase(dni)){
				detas = deta;
			}
		}
		if(detas==null){
			return false;
		}
		HorasConsumidor dest = null;
		//dest = listar().get(idxDestino);
		dest = listar_transferencia().get(idxDestino);
		
		if(isEquals(selected, dest)){
			throw new Exception("La planilla Destino no Puede ser la Planilla Origen.");
		}
		
		if(selected.getTipolabor() != dest.getTipolabor()){
			throw new Exception("La planilla Destino no Puede ser de diferente tipo de labor.");
		}
		
		/*
		if(dest.isPlanillaCerrada()){
			throw new Exception("La planilla Destino no Puede ser una Planilla Cerrada.");
		}
		*/

		String hInicioFin = Application.context.getFecha();

						
		//detas.setHoraFin(hInicioFin);
		//detas.setHoraFinMovil(hInicioFin);
		
		HorasConsumidorDetalle objHorasConsumidirDetalle = new HorasConsumidorDetalle();
		objHorasConsumidirDetalle.setCodConsumidor(dest.getDetalle().get(0).getCodConsumidor());
		objHorasConsumidirDetalle.setCodTrabajador(dni);
		objHorasConsumidirDetalle.setHoraInicioMovil(hInicioFin);
		objHorasConsumidirDetalle.setHoraInicio(hInicioFin);
		
		
		
		if (objHorasConsumidirDetalle != null) {
			// SET COD CONSUMIDOR DETA
			objHorasConsumidirDetalle.setCodConsumidor( dest.getDetalle().get(0).getCodConsumidor() );
			objHorasConsumidirDetalle.setCodTurno( dest.getDetalle().get(0).getCodTurno() );
			
			dest.getDetalle().add(objHorasConsumidirDetalle);
		}

		
		detas.setHoraFinIntent(hInicioFin);
		detas.setHoraFin(hInicioFin);
		detas.setHoraFinMovil(hInicioFin);
		
		delegate.addOrUpdateHorasConsumidors(Application.context.getUser(),dest);
		delegate.addOrUpdateHorasConsumidors(Application.context.getUser(),selected);

		return true;
	}
	
	
	
	
	public void updateHorasConsumidor()throws Exception {
		delegate.addOrUpdateHorasConsumidors(Application.context.getUser(),selected);
	}
	
	public void saveHorasConsumidor() throws Exception {
		//delegate.addOrUpdateHorasConsumidors(Application.context.getUser(),selected);
		selected.setCodEmpresa(Application.context.getEmpresaController().getSelected().getCodEmpresa());
		selected.setCodFundo(Application.context.getFundoController().getSelected().getCodFundo());
		//selected.setCodModulo(Application.context.getFundoController().getModuloSelected().getCodModulo());


		selected.setCodModulo(Application.context.getFundoController().getModuloSelected().getCodModulo());


		Log.d("INFO", selected.getCodGrupo() );
		Log.d("INFO", selected.getCodPlantilla() );
		
		Log.d("Fundo:Modulo", selected.getCodFundo()+":"+selected.getCodModulo() );
		
		selected.setImei(Application.context.getIMEI());
		selected.setCodSupervisor(Application.context.getUser());
		selected.setFecha(Application.context.getFecha());
		selected.setFechaRegistroMovil(Application.context.getFecha());
		System.out.println("el valor de tipolabor es: " + ContextTest.idxTipoLabor);
		selected.setTipolabor(ContextTest.idxTipoLabor);
		//lista.add(selected);		
		delegate.addOrUpdateHorasConsumidors(Application.context.getUser(),selected);
		//tester(selected);
		selected = new HorasConsumidor();
	}

	public void sincronizar() throws Exception {
		delegate.Sincronizar(Application.context.getKeyUser(), listar());
		//loadMaster();
	}

	public void sincronizarAsistencia() throws Exception {
		delegate.SincronizarAsistencia(Application.context.getKeyUser(), listar());
	}


	public void limpiarHistorial() throws Exception {
		delegate.limpiarHistorialHorasConsumidor(Application.context.getKeyUser(), listar());
		
	}
	
	
	public void tester(HorasConsumidor obj){
		List<HorasConsumidor> src = new ArrayList<HorasConsumidor>();
		src.add(obj);
		try {
			compareListHorasConsumidor(src, listar());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	protected void compareListHorasConsumidor(List<HorasConsumidor> src, List<HorasConsumidor> tar) throws Exception{
		Log.d("SIZE: ",src.size()+" == "+tar.size() );
		
		if(src.size() !=tar.size()) throw new Exception("No Equals Size");
	    for (int i = 0; i < src.size(); i++) {
	    	compareHorasConsumidor(src.get(i), tar.get(i));
	    }
	}
	
	protected void compareHorasConsumidor(HorasConsumidor src, HorasConsumidor tar) throws Exception{
		if(!src.getCodActividad().equalsIgnoreCase(tar.getCodActividad())) throw new Exception("No Equals CodActividad");
		if(!src.getCodGrupo().equalsIgnoreCase(tar.getCodGrupo())) throw new Exception("No Equals CodGrupo");
		//if(!src.getCodigo().equalsIgnoreCase(tar.getGrupo())) throw new Exception("No Equals Grupo");
		if(!src.getCodPlantilla().equalsIgnoreCase(tar.getCodPlantilla())) throw new Exception("No Equals CodPlantilla");
		if(!src.getCodSupervisor().equalsIgnoreCase(tar.getCodSupervisor())) throw new Exception("No Equals CodSupervisor");		
		if(!src.getCodTurnoDia().equalsIgnoreCase(tar.getCodTurnoDia())) throw new Exception("No Equals CodTurnoDia");
		if(!src.getFecha().equalsIgnoreCase(tar.getFecha())) throw new Exception("No Equals Fecha");
		if(!src.getFechaRegistroMovil().equalsIgnoreCase(tar.getFechaRegistroMovil())) throw new Exception("No Equals FechaRegistroMovil");
		if(!src.getImei().equalsIgnoreCase(tar.getImei())) throw new Exception("No Equals Imei");		
		for (int i = 0; i < src.getDetalle().size(); i++) {
			compareHorasConsumidorDetalle(src.getDetalle().get(i),tar.getDetalle().get(i));
		}
	}
	
	protected void compareHorasConsumidorDetalle(HorasConsumidorDetalle src, HorasConsumidorDetalle tar) throws Exception{
		if(!src.getCodConsumidor().equalsIgnoreCase(tar.getCodConsumidor())) throw new Exception("No Equals CodConsumidor");
		if(!src.getCodTrabajador().equalsIgnoreCase(tar.getCodTrabajador())) throw new Exception("No Equals CodTrabajador");
		if(!src.getCodTurno().equalsIgnoreCase(tar.getCodTurno())) throw new Exception("No Equals CodTurno");
		if(!src.getHoraFin().equalsIgnoreCase(tar.getHoraFin())) throw new Exception("No Equals HoraFin");
		if(!src.getHoraFinMovil().equalsIgnoreCase(tar.getHoraFinMovil())) throw new Exception("No Equals HoraFinMovil");
		if(!src.getHoraInicio().equalsIgnoreCase(tar.getHoraInicio())) throw new Exception("No Equals HoraInicio");
		if(!src.getHoraInicioMovil().equalsIgnoreCase(tar.getHoraInicioMovil())) throw new Exception("No Equals HoraInicioMovil");
		if(src.getHoras()!=tar.getHoras()) throw new Exception("No Equals Horas");	
	}
	
	
	
	public Date sumarRestarHorasFecha(Date fecha, int horas){
	
	
		      Calendar calendar = Calendar.getInstance();
		
		      calendar.setTime(fecha); // Configuramos la fecha que se recibe
	
		      calendar.add(Calendar.HOUR, horas);  // numero de horas a a?adir, o restar en caso de horas<0
			
		 
		
		      return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas a?adidas
	
		 
	
		 }
	
	
	// ServicioATerceros
	public void addServicioATerceros(String codEmpresa, String codFundo, String codModulo, String codTurno, String codActividad) throws Exception {
			
			ServicioATerceros objServicioATerceros = new ServicioATerceros();
			objServicioATerceros.setCodEmpresa(codEmpresa);
			objServicioATerceros.setCodFundo(codFundo);
			objServicioATerceros.setCodModulo(codModulo);
			objServicioATerceros.setCodTurno(codTurno);
			//objServicioATerceros.setCodConsumidor(codConsumidor);
			objServicioATerceros.setCodActividad(codActividad);
			
		
			
			if (objServicioATerceros != null) {
				
				Application.context.getHorasConsumidorController().getSelected().setServicioATerceros(objServicioATerceros);
				
			}
		
	}

	
	
	
	
	
	
}
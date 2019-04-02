package pe.worktime.controller.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import android.util.Log;

import pe.worktime.app.Application;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.entity.util.ResWS;
import pe.worktime.model.store.parser.util.IFilterRS;
import pe.worktime.model.util.ModelHelper;

public class HorasConsumidorDelegate extends AbstractDelegate {

	public List<HorasConsumidor> getHorasConsumidors(int DS, KeyTransac key) throws Exception {
		switch (DS) {
		case ONLY_WS:
			return wsManager.getHorasConsumidor(key);
		case ONLY_RS:
			return rsManager.getHorasConsumidor(key.getUser());
		}
		throw new Exception("(HorasConsumidors) DS no Reconocido.");
	}
	
	public List<HorasConsumidor> getHorasConsumidors(String user) throws Exception {
		return rsManager.getHorasConsumidor(user);
	}
	

	/* ONLY_RS */
	public void setHorasConsumidors(String user, List<HorasConsumidor> HorasConsumidors) throws Exception {
//		rsManager.clearHorasConsumidor(user);
//		rsManager.clearServicioATerceros(user);
		if(HorasConsumidors==null){
			return;
		}		
		for (HorasConsumidor horasConsumidor2 : HorasConsumidors) {
			rsManager.addOrUpdateHorasConsumidor(user, horasConsumidor2);
		}		
	}
	
	public void clearHorasConsumidors(String user, List<HorasConsumidor> HorasConsumidors) throws Exception {
		rsManager.clearHorasConsumidor(user);
		rsManager.clearServicioATerceros(user);
		
	
		if(HorasConsumidors==null){
			return;
		}		
		for (HorasConsumidor horasConsumidor3 : HorasConsumidors) {
			rsManager.addOrUpdateHorasConsumidor(user, horasConsumidor3);
		}		
	}

	public void eliminarTareoTrabajador(HorasConsumidorDetalle hcd)throws Exception {
		rsManager.eliminarTareoTrabajador(hcd);
	}

	
	public void addOrUpdateHorasConsumidors(String user, HorasConsumidor horasConsumidors) throws Exception {
		rsManager.addOrUpdateHorasConsumidor(user, horasConsumidors);		
	}

	public void eliminarTareo(HorasConsumidor hc)throws Exception {
		rsManager.delHorasConsumidor("",hc);
	}
	
	public void Sincronizar(KeyTransac key, List<HorasConsumidor> registros) throws Exception {		
		try {			
			List<HorasConsumidor> cerrados = new ArrayList<HorasConsumidor>();
		//	List<HorasConsumidor> abiertos = new ArrayList<HorasConsumidor>();
			for (HorasConsumidor item : registros) {
				Log.d("Info", "Estado Planilla: "+item.isPlanillaCerrada());
				if(item.isPlanillaCerrada() && item.getMigrado() == 0){ //Solo aquellos que no esten migrados
					cerrados.add(item);
			//	}else if(item.getMigrado() == 0){ ////Solo aquellos que no esten migrados
				//	abiertos.add(item);
				}
			}
			List<ResWS> resp =  wsManager.regHorasConsumidor(key, cerrados);
			Log.d("Info", "Recibido: ");
			if(resp== null) {
				throw new Exception("Respuesta invalida");
			}
			if(resp.size() != cerrados.size()) {
				throw new Exception("Respuesta invalida no equals");
			}
			for (int i = 0; i < cerrados.size(); i++) {
				Log.d("Info", "Estado Planilla Check: ");
				//Poner a todos los cerrados estado migrado = 1
				cerrados.get(i).setMigrado(1);
				
				if(!resp.get(i).isOK()){
					Log.d("Info", "Estado Planilla Check: Bad ");
					//A los que fallaron ponerles estado migrado = 0
					cerrados.get(i).setMigrado(0);
					//abiertos.add(cerrados.get(i));
				}
			}			
			
				
			//this.setHorasConsumidors(key.getUser(), abiertos);
			
			//Enviamos a actualizar los registros migrados (Cerrados)
			this.setHorasConsumidors(key.getUser(), cerrados);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SincronizarAsistencia(KeyTransac key, List<HorasConsumidor> registros) throws Exception {
		try {
			List<HorasConsumidor> lstAsis = new ArrayList<HorasConsumidor>();
			for (HorasConsumidor item : registros) {
				if(!item.isPlanillaCerrada() && item.getAsistencia() == 0){ //Solo planillas abiertas y no sincronizadas por asistencia
					lstAsis.add(item);
				}
			}


			List<ResWS> resp =  wsManager.regAsistencia(key, lstAsis);
			if(resp== null) {
				throw new Exception("Respuesta invalida");
			}
			if(resp.size() != lstAsis.size()) {
				throw new Exception("Respuesta invalida no equals");
			}
			for (int i = 0; i < lstAsis.size(); i++) {
				//lstAsis.get(i).setAsistencia(1);
                lstAsis.get(i).setAsistencia(0);

				if(!resp.get(i).isOK()){
					lstAsis.get(i).setAsistencia(0);
				}
			}


			//this.setHorasConsumidors(key.getUser(), abiertos);

			//Enviamos a actualizar los registros migrados (Cerrados)
			this.setHorasConsumidors(key.getUser(), lstAsis);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//BACKUP de METODO SINCRONIZAR INICIAL
	//---------------------------------------
//	public void Sincronizar(KeyTransac key, List<HorasConsumidor> registros) throws Exception {		
//		try {			
//			List<HorasConsumidor> cerrados = new ArrayList<HorasConsumidor>();
//			List<HorasConsumidor> abiertos = new ArrayList<HorasConsumidor>();
//			for (HorasConsumidor item : registros) {
//				Log.d("Info", "Estado Planilla: "+item.isPlanillaCerrada());
//				if(item.isPlanillaCerrada()){
//					cerrados.add(item);
//				}else{
//					abiertos.add(item);
//				}
//			}
//			List<ResWS> resp =  wsManager.regHorasConsumidor(key, cerrados);
//			Log.d("Info", "Recibido: ");
//			if(resp== null) {
//				throw new Exception("Respuesta invalida");
//			}
//			if(resp.size() != cerrados.size()) {
//				throw new Exception("Respuesta invalida no equals");
//			}
//			for (int i = 0; i < cerrados.size(); i++) {
//				Log.d("Info", "Estado Planilla Check: ");
//				if(!resp.get(i).isOK()){
//					Log.d("Info", "Estado Planilla Check: Bad ");
//					abiertos.add(cerrados.get(i));
//				}
//			}			
//			//Check Error
//			
//			//En cehck
//			
//			this.setHorasConsumidors(key.getUser(), abiertos);
//			
//			/*
//			List<ResWS> resp =  wsManager.regHorasConsumidor(key, registros);
//			List<HorasConsumidor> lst  = rsManager.getHorasConsumidor(key.getUser());
//			List<HorasConsumidor> lst2 = new ArrayList<HorasConsumidor>();
//			for (int i = 0; i < lst.size(); i++) {
//				if(!resp.get(i).isOK()){
//					lst2.add(lst.get(i));
//				}
//			}
//			List<HorasConsumidor> lst3  = wsManager.getHorasConsumidor(key);
//			for (HorasConsumidor horasConsumidor : lst3) {
//				lst2.add(horasConsumidor);
//			}
//			this.setHorasConsumidors(key.getUser(), lst2);
//			*/
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void limpiarHistorialHorasConsumidor(KeyTransac key, List<HorasConsumidor> registros) throws Exception {		
		try {			

			List<HorasConsumidor> abiertos = new ArrayList<HorasConsumidor>();
			String HCFecha;
			String FechaActual;
			FechaActual = Application.context.getFecha().substring(0,10); // Dejamos solo fecha, quitamos las horas
			for (HorasConsumidor item : registros) {
				//Log.d("Info", "Estado Planilla: "+item.isPlanillaCerrada());
				HCFecha = item.getFecha().substring(0,10); // Dejamos solo fecha, quitamos las horas
			
				//if(!item.isPlanillaCerrada() || item.getMigrado() == 0 || HCFecha.equals(FechaActual)){ //Se quedan solo los que no estan cerrados o migrados o que sean de la fecha actual.
				//if(!item.isPlanillaCerrada() || item.getMigrado() == 0 || HCFecha.compareTo(FechaActual)==0){
				if(!item.isPlanillaCerrada() || item.getMigrado() == 0 ){
						
					abiertos.add(item);
				}
			}
		
			//this.setHorasConsumidors(key.getUser(), abiertos);
			
			//Enviamos a limpiar los registros y enviamos solo la lista que se quedara almacenada
			this.clearHorasConsumidors(key.getUser(), abiertos);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void _Sincronizar(KeyTransac key, List<HorasConsumidor> registros) throws Exception {		
		try {
			
			
			List<ResWS> resp =  wsManager.regHorasConsumidor(key, registros);
			List<HorasConsumidor> lst  = rsManager.getHorasConsumidor(key.getUser());
			List<HorasConsumidor> lst2 = new ArrayList<HorasConsumidor>();
			for (int i = 0; i < lst.size(); i++) {
				if(!resp.get(i).isOK()){
					lst2.add(lst.get(i));
				}
			}
			List<HorasConsumidor> lst3  = wsManager.getHorasConsumidor(key);
			for (HorasConsumidor horasConsumidor : lst3) {
				lst2.add(horasConsumidor);
			}
			this.setHorasConsumidors(key.getUser(), lst2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public HorasConsumidor getHorasConsumidor(String user) throws Exception {
		return (HorasConsumidor) rsManager.getHorasConsumidor(user);
	}

	public List<HorasConsumidor> findActividad(String user, IFilterRS filter)
			throws Exception {
		return rsManager.getHorasConsumidor(user, filter);
	}

	public HorasConsumidor findByHorasConsumidor(String user, IFilterRS filter) throws Exception {

		List<HorasConsumidor> lst = rsManager.getHorasConsumidor(user, filter);
		if (!lst.isEmpty()) {
			return (HorasConsumidor) lst.get(0);
		}
		return null;
	}

	public void crearTablaServicioATerceros() throws Exception{
		rsManager.crearTablaServicioATerceros();
	}
	
	public Date convertStringToDate(String fecha) {
		ModelHelper helper = new ModelHelper();
		return helper.convertStringToDate(fecha);
	}
	
	
}
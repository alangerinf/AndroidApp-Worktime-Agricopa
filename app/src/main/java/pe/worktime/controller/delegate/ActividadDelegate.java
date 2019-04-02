package pe.worktime.controller.delegate;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.store.parser.util.IFilterRS;

public class ActividadDelegate extends AbstractDelegate {

	public List<Actividad> getActividades(int DS, KeyTransac key) throws Exception {
		switch (DS) {
		case ONLY_WS:
			return wsManager.getActividades(key);
		case ONLY_RS:
			return rsManager.getActividades(key == null ? Application.context.getUser() : key.getUser());
		}
		throw new Exception("(Actividad) DS no Reconocido.");
	}

	/* ONLY_RS */
	public void setActividades(String user, List<Actividad> actividades)
			throws Exception {
		rsManager.selListActividad(user, actividades);
	}
	
	public Actividad getActividad(String user) throws Exception {
		return (Actividad) rsManager.getActividades(user); 
	}

	public List<Actividad> findActividad(String user, IFilterRS filter) throws Exception {
		return rsManager.getActividades(user,filter);
	}

	public Actividad findByCliente(String user, IFilterRS filter) throws Exception {
		
		List<Actividad> lst = rsManager.getActividades(user, filter);
		if (!lst.isEmpty()) {
			return (Actividad) lst.get(0);
		}
		return null;
	}
}

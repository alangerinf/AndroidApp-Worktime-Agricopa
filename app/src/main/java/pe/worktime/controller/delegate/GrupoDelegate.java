package pe.worktime.controller.delegate;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.model.entity.Grupo;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.store.parser.util.IFilterRS;

public class GrupoDelegate extends AbstractDelegate {

	public List<Grupo> getGrupos(int DS, KeyTransac key) throws Exception {
		switch (DS) {
		case ONLY_WS:
			return wsManager.getGrupos(key);
		case ONLY_RS:
			return rsManager.getGrupos(key == null ? Application.context
					.getUser() : key.getUser());
		}
		throw new Exception("(Grupos) DS no Reconocido.");
	}

	/* ONLY_RS */
	public void setGrupos(String user, List<Grupo> grupos) throws Exception {
		rsManager.selListGrupo(user, grupos);
	}

	public Grupo getGrupo(String user) throws Exception {
		return (Grupo) rsManager.getGrupos(user);
	}

	public List<Grupo> findActividad(String user, IFilterRS filter)
			throws Exception {
		return rsManager.getGrupos(user, filter);
	}

	public Grupo findByGrupo(String user, IFilterRS filter) throws Exception {

		List<Grupo> lst = rsManager.getGrupos(user, filter);
		if (!lst.isEmpty()) {
			return (Grupo) lst.get(0);
		}
		return null;
	}

}
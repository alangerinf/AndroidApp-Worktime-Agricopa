package pe.worktime.controller.delegate;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.model.entity.TurnoDia;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.store.parser.util.IFilterRS;

public class TurnoDiaDelegate extends AbstractDelegate {

	public List<TurnoDia> getTurnoDias(int DS, KeyTransac key) throws Exception {
		switch (DS) {
		case ONLY_WS:
			return wsManager.getTurnodDias(key);
		case ONLY_RS:
			return rsManager.getTurnoDia(key == null ? Application.context
					.getUser() : key.getUser());
		}
		throw new Exception("(Turno Dias) DS no Reconocido.");
	}

	/* ONLY_RS */
	public void setTurnoDia(String user, List<TurnoDia> turnos) throws Exception {
		rsManager.selListTurnoDia(user,turnos);
	}

	public TurnoDia getTurnoDia(String user) throws Exception {
		return (TurnoDia) rsManager.getTurnoDia(user);
	}

	public List<TurnoDia> findTurnoDia(String user, IFilterRS filter)
			throws Exception {
		return rsManager.getTurnoDias(user, filter);
	}

	public TurnoDia findByTurnoDia(String user, IFilterRS filter) throws Exception {

		List<TurnoDia> lst = rsManager.getTurnoDias(user, filter);
		if (!lst.isEmpty()) {
			return (TurnoDia) lst.get(0);
		}
		return null;
	}
	
}
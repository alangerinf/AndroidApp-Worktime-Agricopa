package pe.worktime.controller.delegate;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.store.parser.util.IFilterRS;

public class ConsumidorIndirectoDelegate extends AbstractDelegate {

	public List<ConsumidorIndirecto> getConsumidorIndirecto(int DS, KeyTransac key) throws Exception {
		switch (DS) {
		case ONLY_WS:
			return wsManager.getConsumidoresIndirectos(key);
		case ONLY_RS:
			return rsManager.getConsumidorIndirecto(key == null ? Application.context.getUser() : key.getUser());
		}
		throw new Exception("(Consumidor Indirecto) DS no Reconocido.");
	}

	/* ONLY_RS */
	public void setConsumidorIndirecto(String user, List<ConsumidorIndirecto> consumidores)
			throws Exception {
		rsManager.selListConsumidorIndirecto(user, consumidores);
	}
	
	public ConsumidorIndirecto getConsumidor(String user) throws Exception {
		return (ConsumidorIndirecto) rsManager.getConsumidorIndirecto(user); 
	}

	public List<ConsumidorIndirecto> findConsumidorIndirecto(String user, IFilterRS filter) throws Exception {
		return rsManager.getConsumidorIndirecto(user, filter);
	}

	public ConsumidorIndirecto findByConsumidorIndirecto(String user, IFilterRS filter) throws Exception {
		
		List<ConsumidorIndirecto> lst = rsManager.getConsumidorIndirecto(user, filter);
		if (!lst.isEmpty()) {
			return (ConsumidorIndirecto) lst.get(0);
		}
		return null;
	}
}
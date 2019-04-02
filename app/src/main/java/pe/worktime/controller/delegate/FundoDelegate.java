package pe.worktime.controller.delegate;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.store.parser.util.IFilterRS;

public class FundoDelegate extends AbstractDelegate {

	public List<Fundo> getFundos(int DS, KeyTransac key) throws Exception {
		switch (DS) {		
		case ONLY_RS:
			return rsManager.getFundos(key == null ? Application.context.getUser() : key.getUser());
		}
		throw new Exception("(Fundos) DS no Reconocido.");
	}
	
	public List<Fundo> getFundos(int DS, KeyTransac key, Empresa entity) throws Exception {
		switch (DS) {		
		case ONLY_RS:
			return rsManager.getFundos(key == null ? Application.context.getUser() : key.getUser(), entity);
		}
		throw new Exception("(Fundos) DS no Reconocido.");
	}

	/* ONLY_RS */
	public void setFundos(String user, List<Fundo> Fundos) throws Exception {
		rsManager.selListFundo(user, Fundos);
	}

	public Fundo getFundo(String user) throws Exception {
		return (Fundo) rsManager.getFundos(user);
	}

	public List<Fundo> findActividad(String user, IFilterRS filter)
			throws Exception {
		return rsManager.getFundos(user, filter);
	}

	public Fundo findByFundo(String user, IFilterRS filter) throws Exception {
		List<Fundo> lst = rsManager.getFundos(user, filter);
		if (!lst.isEmpty()) {
			return (Fundo) lst.get(0);
		}
		return null;
	}
	/*
	public void crearTablaServicioATerceros() throws Exception{
		rsManager.crearTablaServicioATerceros();
	}*/

}
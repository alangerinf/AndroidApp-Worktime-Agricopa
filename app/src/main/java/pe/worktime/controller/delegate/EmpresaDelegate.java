package pe.worktime.controller.delegate;

import java.util.List;
import pe.worktime.app.Application;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.store.parser.util.IFilterRS;

public class EmpresaDelegate extends AbstractDelegate {

	public List<Empresa> getEmpresas(int DS, KeyTransac key) throws Exception {
		switch (DS) {
			case ONLY_WS:
				return wsManager.getEmpresa(key);
			case ONLY_RS:
				return rsManager.getEmpresa(key == null ? Application.context
						.getUser() : key.getUser());
		}
		throw new Exception("(Turno Dias) DS no Reconocido.");
	}

	/* ONLY_RS */
	public void setEmpresa(String user, List<Empresa> turnos) throws Exception {
		rsManager.selListEmpresa(user,turnos);
	}
	
	public void setEmpresas(String user, List<Empresa> Fundos) throws Exception {
		rsManager.selListEmpresaWithFundos(user,Fundos);
	}

	public Empresa getEmpresa(String user) throws Exception {
		return (Empresa) rsManager.getEmpresa(user);
	}

	public List<Empresa> findEmpresa(String user, IFilterRS filter)
			throws Exception {
		return rsManager.getEmpresa(user, filter);
	}

	public Empresa findByEmpresa(String user, IFilterRS filter) throws Exception {

		List<Empresa> lst = rsManager.getEmpresa(user, filter);
		if (!lst.isEmpty()) {
			return (Empresa) lst.get(0);
		}
		return null;
	}
	
}
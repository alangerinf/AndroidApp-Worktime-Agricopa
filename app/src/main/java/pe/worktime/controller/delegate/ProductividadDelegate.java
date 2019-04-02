package pe.worktime.controller.delegate;

import java.util.ArrayList;
import java.util.List;


import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.model.entity.Productividad;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.entity.util.ResWS;
import pe.worktime.model.store.parser.util.IFilterRS;

public class ProductividadDelegate extends AbstractDelegate {

	public List<Productividad> getProductividads(int DS, KeyTransac key) throws Exception {
		switch (DS) {
		case ONLY_WS:
			return wsManager.getProductividad(key);
		case ONLY_RS:
			return rsManager.getProductividad(key.getUser());
		}
		throw new Exception("(Productividads) DS no Reconocido.");
	}
	
	public List<Productividad> getProductividads(String user) throws Exception {
		return rsManager.getProductividad(user);
	}
	

	/* ONLY_RS */
	public void setProductividads(String user, List<Productividad> Productividads) throws Exception {
		rsManager.clearProductividad(user);
		//rsManager.selListProductividad(user, Productividads);
		if(Productividads==null){
			return;
		}		
		for (Productividad Productividad2 : Productividads) {
			rsManager.addOrUpdateProductividad(user, Productividad2);
		}		
	}

	public void clearProductividads(String user, List<Productividad> productividad) throws Exception {
		rsManager.clearProductividad(user);

		if(productividad==null){
			return;
		}
		for (Productividad prod : productividad) {
			rsManager.addOrUpdateProductividad(user, prod);
		}
	}
	
	public void addOrUpdateProductividads(String user, Productividad Productividads) throws Exception {
		rsManager.addOrUpdateProductividad(user, Productividads);		
	}
	
	public void Sincronizar(KeyTransac key, List<Productividad> registros) throws Exception {
		try {
//			List<Productividad> cerrados = new ArrayList<Productividad>();
//
//			for (Productividad item : registros) {
//				if(item.getMigrado() == 0){ //Solo aquellos que no esten migrados
//					cerrados.add(item);
//				}
//			}

			List<Productividad> fails = new ArrayList<Productividad>();



			List<ResWS> resp =  wsManager.regProductividad(key, registros);
			for (int i = 0; i < registros.size(); i++) {
				if(resp.get(i).isBad()){
					fails.add(registros.get(i));
				}
			}

//			List<ResWS> resp =  wsManager.regProductividad(key, cerrados);
//			for (int i = 0; i < cerrados.size(); i++) {
//				cerrados.get(i).setMigrado(1);
//				if(!resp.get(i).isOK()){
//					cerrados.get(i).setMigrado(0);
//				}
//			}


			//this.setProductividads(key.getUser(), cerrados);
			this.setProductividads(key.getUser(), fails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void _Sincronizar(KeyTransac key, List<Productividad> registros) throws Exception {
		try {
			List<ResWS> resp =  wsManager.regProductividad(key, registros);
			List<Productividad> lst  = rsManager.getProductividad(key.getUser());
			List<Productividad> lst2 = new ArrayList<Productividad>();
			for (int i = 0; i < lst.size(); i++) {
				if(!resp.get(i).isOK()){
					lst2.add(lst.get(i));
				}
			}
			List<Productividad> lst3  = wsManager.getProductividad(key);
			for (Productividad Productividad : lst3) {
				lst2.add(Productividad);
			}
			this.setProductividads(key.getUser(), lst2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Productividad getProductividad(String user) throws Exception {
		return (Productividad) rsManager.getProductividad(user);
	}

	public List<Productividad> findProductividad(String user, IFilterRS filter)
			throws Exception {
		return rsManager.getProductividad(user, filter);
	}

	public Productividad findByProductividad(String user, IFilterRS filter) throws Exception {

		List<Productividad> lst = rsManager.getProductividad(user, filter);
		if (!lst.isEmpty()) {
			return (Productividad) lst.get(0);
		}
		return null;
	}

}
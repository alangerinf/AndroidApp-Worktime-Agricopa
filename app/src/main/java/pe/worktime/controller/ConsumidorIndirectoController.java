package pe.worktime.controller;

import java.util.List;

import android.util.Log;

import pe.worktime.app.Application;
import pe.worktime.controller.delegate.ConsumidorIndirectoDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.ConsumidorIndirecto;
//import Grupo;
import pe.worktime.model.entity.util.KeyTransac;

public class ConsumidorIndirectoController extends AbstractController {

	private ConsumidorIndirecto selected = null;
	private ConsumidorIndirectoDelegate delegate = null;

	public ConsumidorIndirectoController() {
		delegate = new ConsumidorIndirectoDelegate();
	}

	public ConsumidorIndirecto getSelected() {
		return selected;
	}

	public void setSelected(ConsumidorIndirecto selected) {
		this.selected = selected;
	}

	@Override
	public void loadMaster() throws Exception {
		List<ConsumidorIndirecto> lst = null;
		boolean loadedWS = false;
		try {
			KeyTransac key = Application.context.getKeyUser();
			lst = delegate
					.getConsumidorIndirecto(AbstractDelegate.ONLY_WS, key);
			loadedWS = true;
		} catch (Exception e) {
			e.printStackTrace();
			lst = delegate.getConsumidorIndirecto(AbstractDelegate.ONLY_RS,
					null);
		}
		if (lst == null) {
			LogController.addError(Application.context.getUser(),
					"Lista ConsumidorIndirecto Vacia.");
			throw new Exception("Lista Cliente Vacia.");
		} else if (lst.isEmpty()) {
			LogController.addError(Application.context.getUser(),
					"Lista ConsumidorIndirecto Vacia.");
			throw new Exception("Lista Cliente Vacia.");
		} else if (loadedWS) {
			delegate.setConsumidorIndirecto(Application.context.getUser(), lst);
		}
	}

	@Override
	public void seleccionar(int index) {
		try {
			try {
				selected = delegate.getConsumidorIndirecto(
						AbstractDelegate.ONLY_RS, null).get(index);
				// selected = ContextTest.ctx.getLst3().get(index);
			} catch (Exception e) {
				LogController.addError(Application.context.getUser(),
						e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),
					e.getMessage());
		}
	}

	public List<ConsumidorIndirecto> listar() {
		try {
			return delegate.getConsumidorIndirecto(AbstractDelegate.ONLY_RS,
					null);
			// return ContextTest.ctx.getLst3();
		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),
					e.getMessage());
		}
		return null;
	}

	@Override
	public void seleccionar(String key) {
		try {
			List<ConsumidorIndirecto> lst = listar();
			for (ConsumidorIndirecto item : lst) {
				if (item.getCodConsumidor().equalsIgnoreCase(key)) {
					selected = item;
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		selected = null;
	}
	
	public String getNameOrDefault(String key) {
		try {
			Log.e("INFO**********", "Key:"+key);
			List<ConsumidorIndirecto> lst = listar();
			for (ConsumidorIndirecto item : lst) {
				if (item.getCodConsumidor().compareToIgnoreCase(key)==0) {
					return item.getDescripcion();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}
	
}

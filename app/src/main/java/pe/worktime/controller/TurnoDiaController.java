package pe.worktime.controller;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.delegate.TurnoDiaDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.TurnoDia;
import pe.worktime.model.entity.util.KeyTransac;

public class TurnoDiaController extends AbstractController {

	private TurnoDia selected = null;
	private TurnoDiaDelegate delegate = null;

	public TurnoDiaController() {
		delegate = new TurnoDiaDelegate();
	}

	public TurnoDia getSelected() {
		return selected;
	}

	public void setSelected(TurnoDia selected) {
		this.selected = selected;
	}

	@Override
	public void loadMaster() throws Exception {
		List<TurnoDia> lst = null;
		boolean loadedWS = false;
		try {
			KeyTransac key = Application.context.getKeyUser();
			lst = delegate.getTurnoDias(AbstractDelegate.ONLY_WS, key);
			loadedWS = true;
		} catch (Exception e) {
			lst = delegate.getTurnoDias(AbstractDelegate.ONLY_RS, null);
		}
		if (lst == null) {
			LogController.addError(Application.context.getUser(),
					"Lista TurnoDia Vacia.");
			throw new Exception("Lista Cliente Vacia.");
		} else if (lst.isEmpty()) {
			LogController.addError(Application.context.getUser(),
					"Lista TurnoDia Vacia.");
			throw new Exception("Lista Cliente Vacia.");
		} else if (loadedWS) {
			delegate.setTurnoDia(Application.context.getUser(), lst);
		}
	}

	@Override
	public void seleccionar(int index) {
		try {
			try {
				selected = delegate
						.getTurnoDias(AbstractDelegate.ONLY_RS, null)
						.get(index);
				// selected = ContextTest.ctx.getLst5().get(index);
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

	public List<TurnoDia> listar() {
		try {
			return delegate.getTurnoDias(AbstractDelegate.ONLY_RS, null);
			// return ContextTest.ctx.getLst5();
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
			List<TurnoDia> lst = listar();
			for (TurnoDia item : lst) {
				if (item.getCodigo().equalsIgnoreCase(key)) {
					selected = item;
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		selected = null;
	}
}
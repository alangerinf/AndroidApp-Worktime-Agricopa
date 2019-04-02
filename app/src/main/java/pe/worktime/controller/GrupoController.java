package pe.worktime.controller;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.delegate.GrupoDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.Grupo;
import pe.worktime.model.entity.util.KeyTransac;

public class GrupoController extends AbstractController {

	private Grupo selected = null;
	private GrupoDelegate delegate = null;

	public GrupoController() {
		delegate = new GrupoDelegate();
	}

	public Grupo getSelected() {
		return selected;
	}

	public void setSelected(Grupo selected) {
		this.selected = selected;
	}

	@Override
	public void loadMaster() throws Exception {
		List<Grupo> lst = null;
		boolean loadedWS = false;
		try {
			KeyTransac key = Application.context.getKeyUser();
			lst = delegate.getGrupos(AbstractDelegate.ONLY_WS, key);
			loadedWS = true;
		} catch (Exception e) {
			lst = delegate.getGrupos(AbstractDelegate.ONLY_RS, null);
		}
		if (lst == null) {
			LogController.addError(Application.context.getUser(),
					"Lista Grupo Vacia.");
			throw new Exception("Lista Grupo Vacia.");
		} else if (lst.isEmpty()) {
			LogController.addError(Application.context.getUser(),
					"Lista Grupo Vacia.");
			throw new Exception("Lista Grupo Vacia.");
		} else if (loadedWS) {
			delegate.setGrupos(Application.context.getUser(), lst);
		}
	}

	@Override
	public void seleccionar(int index) {
		try {
			try {
				selected = (Grupo) delegate.getGrupos(AbstractDelegate.ONLY_RS,null).get(index);
				//selected = ContextTest.ctx.getLst4().get(index);
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

	public List<Grupo> listar() {
		try {
			return delegate.getGrupos(AbstractDelegate.ONLY_RS, null);
			//return ContextTest.ctx.getLst4();
		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),e.getMessage());
		}
		return null;
	}

	@Override
	public void seleccionar(String key) {
		try {
			List<Grupo> lst = listar();
			for (Grupo item : lst) {
				if (item.getCodGrupo().equalsIgnoreCase(key)) {
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
			List<Grupo> lst = listar();
			for (Grupo item : lst) {
				if (item.getCodGrupo().equalsIgnoreCase(key)) {
					return item.getGrupo();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}
	
	public String getConsumidorNameOrDefault(String key) {
		try {
			return Application.context.getConsumidorIndirectoController().getNameOrDefault(key);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}
	
	
}
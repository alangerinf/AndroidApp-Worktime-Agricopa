package pe.worktime.controller;

import java.util.ArrayList;
import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.delegate.ActividadDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.util.KeyTransac;

public class ActividadController extends AbstractController {

	private Actividad selected = null;
	private Actividad actividadServida = null;
	private ActividadDelegate delegate = null;

	public ActividadController() {
		delegate = new ActividadDelegate();
	}

	public Actividad getSelected() {
		return selected;
	}

	public void setSelected(Actividad selected) {
		this.selected = selected;
	}

	@Override
	public void loadMaster() throws Exception {
		List<Actividad> lst = null;
		boolean loadedWS = false;
		try {
			KeyTransac key = Application.context.getKeyUser();
			lst = delegate.getActividades(AbstractDelegate.ONLY_WS, key);
			loadedWS = true;
		} catch (Exception e) {
			e.printStackTrace();
			lst = delegate.getActividades(AbstractDelegate.ONLY_RS, null);
		}
		if (lst == null) {
			LogController.addError(Application.context.getUser(),
					"Lista Actividad Vacia.");
			throw new Exception("Lista Actividad Vacia.");
		} else if (lst.isEmpty()) {
			LogController.addError(Application.context.getUser(),
					"Lista Actividad Vacia.");
			throw new Exception("Lista Actividad Vacia.");
		} else if (loadedWS) {
			delegate.setActividades(Application.context.getUser(), lst);
		}
	}

	@Override
	public void seleccionar(int index) {
		try {
			try {
				selected = delegate.getActividades(AbstractDelegate.ONLY_RS,
						null).get(index);
				// selected = ContextTest.ctx.getLst1().get(index);
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
	

	public void seleccionarActividadServida(int index) {
		try {
			try {
				actividadServida = listaDirecta().get(index);
				// selected = ContextTest.ctx.getLst1().get(index);
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
	
	public void seleccionarDirecta(int index) {
		try {
			try {
				selected = listaDirecta().get(index);
				// selected = ContextTest.ctx.getLst1().get(index);
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
	
	public void seleccionarIndirecta(int index) {
		try {
			try {
				selected = listaIndirecta().get(index);
				// selected = ContextTest.ctx.getLst1().get(index);
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

	public List<Actividad> listar() {
		try {
			return delegate.getActividades(AbstractDelegate.ONLY_RS, null);
			// return ContextTest.ctx.getLst1();
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
			List<Actividad> lst = listar();
			for (Actividad item : lst) {
				if (item.getCodActividad().equalsIgnoreCase(key)) {
					selected = item;
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		selected = null;
	}


	public String buscarTipo(String key) {
		try {
			List<Actividad> lst = listar();
			for (Actividad item : lst) {
				if (item.getCodActividad().equalsIgnoreCase(key)) {
					selected = item;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selected.getTipo().toString();
	}
	
	
	public List<Actividad> listaDirecta() {
		
		try {
			List<Actividad> lst1 = listar();
			List<Actividad> lstDirecta = new ArrayList<Actividad>();
			for (Actividad itm : lst1) {
				if (itm.isDirecta()) {
					lstDirecta.add(itm);
				}
			}
			return lstDirecta;

		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),e.getMessage());
		}
		return null;
	}
	
	public List<Actividad> listaIndirecta() {
		try {
			List<Actividad>  lst1 = listar();
			List<Actividad> lstIndirecta = new ArrayList<Actividad>();
			for (Actividad itm : lst1) {
				if (itm.isIndirecta()) {
					lstIndirecta.add(itm);
				}
			}
			return lstIndirecta;

		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),e.getMessage());
		}
		return null;
	}
	
	
	public String getNameOrDefault(String key) {
		try {
			Actividad prev = selected;			
			seleccionar(key);
			Actividad result = selected;
			selected = prev;
			return result.getActividad();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public Actividad getActividadServida() {
		return actividadServida;
	}

	public void setActividadServida(Actividad actividadServida) {
		this.actividadServida = actividadServida;
	}



	
	
}
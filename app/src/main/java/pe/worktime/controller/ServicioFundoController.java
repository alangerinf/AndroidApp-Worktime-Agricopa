package pe.worktime.controller;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.delegate.FundoDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Modulo;
import pe.worktime.model.entity.Turno;

public class ServicioFundoController extends AbstractController {

	private Fundo selected = null;
	private Modulo moduloSelected = null;
	private Turno turnoSelected = null;
	private FundoDelegate delegate = null;

	public ServicioFundoController() {
		delegate = new FundoDelegate();
	}

	public Fundo getSelected() {
		return selected;
	}

	public void setSelected(Fundo selected) {
		this.selected = selected;
	}
		
	public Modulo getModuloSelected() {
		return moduloSelected;
	}

	public void setModuloSelected(Modulo moduloSelected) {
		this.moduloSelected = moduloSelected;
	}
		
	public Turno getTurnoSelected() {
		return turnoSelected;
	}

	public void setTurnoSelected(Turno turnoSelected) {
		this.turnoSelected = turnoSelected;
	}

	@Override
	public void loadMaster() throws Exception {
	}
	
	public void loadMaster(List<Fundo> fundos) throws Exception {
		delegate.setFundos(Application.context.getUser(), fundos);
	}

	@Override
	public void seleccionar(int index) {
		try {
			try {
				selected = delegate.getFundos(AbstractDelegate.ONLY_RS,
						null).get(index);				
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
	
	public void seleccionarModulo(int index) {
		try {
			try {
				moduloSelected =  selected.getModulos().get(index);				
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
	
	public void seleccionarTurno(int index) {
		try {
			try {
				turnoSelected =  moduloSelected.getTurnos().get(index);				
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

	public List<Fundo> listar() {
		try {
			return delegate.getFundos(AbstractDelegate.ONLY_RS, null);
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
			List<Fundo> lst = listar();
			for (Fundo item : lst) {
				if (item.getCodFundo().equalsIgnoreCase(key)) {
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
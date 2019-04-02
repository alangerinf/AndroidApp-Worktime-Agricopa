package pe.worktime.controller;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.delegate.FundoDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Modulo;
import pe.worktime.model.entity.Turno;

public class FundoController extends AbstractController {

	private Fundo selected = null;
	private Fundo fundoServido = null;
	private Modulo moduloSelected = null;
	private Modulo moduloServido = null;
	private Turno turnoSelected = null;
	private Turno turnoServido = null;
	private FundoDelegate delegate = null;

	public FundoController() {
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
		//delegate.crearTablaServicioATerceros();
	}

	@Override
	public void seleccionar(int index) {
		try {
			try {
				selected = delegate.getFundos(AbstractDelegate.ONLY_RS,
						null,Application.context.getEmpresaController().getSelected()).get(index);				
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
	
	
	public void seleccionarFundoServido(int index, Empresa entity) {
		try {
			try {
			//	fundoServido = delegate.getFundos(AbstractDelegate.ONLY_RS,	null).get(index);
				
				fundoServido = delegate.getFundos(AbstractDelegate.ONLY_RS, null, entity).get(index);
			} catch (Exception e) {
				LogController.addError(Application.context.getUser(),e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),e.getMessage());
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
	
	public void seleccionarModuloServido(int index) {
		try {
			try {
				moduloServido =  fundoServido.getModulos().get(index);				
			} catch (Exception e) {
				LogController.addError(Application.context.getUser(),e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),e.getMessage());
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
	
	public void seleccionarTurnoServido(int index) {
		try {
			try {
				turnoServido =  moduloServido.getTurnos().get(index);				
			} catch (Exception e) {
				LogController.addError(Application.context.getUser(),e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),e.getMessage());
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
	
	public List<Fundo> listarDeEmpresa(Empresa entity) {
		try {
			return delegate.getFundos(AbstractDelegate.ONLY_RS, null, entity);
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
			//List<Fundo> lst = listar();
			List<Fundo> lst  = listarDeEmpresa(Application.context.getEmpresaController().getSelected());
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

	public void seleccionarModulo(String key) {
		try {
			List<Modulo> lst = selected.getModulos();
			for (Modulo item : lst) {
				if (item.getCodModulo().equalsIgnoreCase(key)) {
					moduloSelected = item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		selected = null;
	}

	public String getNameOrDefault(String key) {
		try {
			Fundo prev = selected;
			seleccionar(key);
			Fundo result = selected;
			selected = prev;
			return result.getFundo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getNameOrDefaultModulo(String key) {
		try {
			//Modulo prev = moduloSelected;
			seleccionarModulo(key);
			Modulo result = moduloSelected;
			//moduloSelected = prev;
			return result.getModulo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public Fundo getFundoServido() {
		return fundoServido;
	}

	public void setFundoServido(Fundo fundoServido) {
		this.fundoServido = fundoServido;
	}

	public Modulo getModuloServido() {
		return moduloServido;
	}

	public void setModuloServido(Modulo moduloServido) {
		this.moduloServido = moduloServido;
	}

	public Turno getTurnoServido() {
		return turnoServido;
	}

	public void setTurnoServido(Turno turnoServido) {
		this.turnoServido = turnoServido;
	}
	
	
}
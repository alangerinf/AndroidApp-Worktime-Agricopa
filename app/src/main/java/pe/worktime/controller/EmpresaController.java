package pe.worktime.controller;

import java.util.List;
import pe.worktime.app.Application;
import pe.worktime.controller.delegate.EmpresaDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.Empresa;
//import Fundo;
import pe.worktime.model.entity.util.KeyTransac;

public class EmpresaController extends AbstractController {

	private Empresa selected = null;
	private Empresa empresaservida = null;
	private EmpresaDelegate delegate = null;

	public EmpresaController() {
		delegate = new EmpresaDelegate();
	}

	public Empresa getSelected() {
		return selected;
	}

	public void setSelected(Empresa selected) {
		this.selected = selected;
	}

	@Override
	public void loadMaster() throws Exception {
		List<Empresa> lst = null;
		boolean loadedWS = false;
		try {
			//KeyTransac key = Application.context.getKeyUser();
			KeyTransac key = new KeyTransac("", "", "");
			lst = delegate.getEmpresas(AbstractDelegate.ONLY_WS, key);
			loadedWS = true;
		} catch (Exception e) {
			LogController.addError(Application.context.getUser(), e.getMessage());
			System.out.println(e.getMessage());
			lst = delegate.getEmpresas(AbstractDelegate.ONLY_RS, null);
		}
		if (lst == null) {
			LogController.addError(Application.context.getUser(),
					"Lista Empresa Vacia.");
			throw new Exception("Lista Empresa Vacia.");
		} else if (lst.isEmpty()) {
			LogController.addError(Application.context.getUser(),
					"Lista Empresa Vacia.");
			throw new Exception("Lista Empresa Vacia.");
		} else if (loadedWS) {
			delegate.setEmpresa(Application.context.getUser(), lst);
		}
	}
	
	public void loadMasterSnd(List<Empresa> empresas) throws Exception{
		delegate.setEmpresas(Application.context.getUser(), empresas);
	}

	@Override
	public void seleccionar(int index) {
		try {
			try {
				selected = delegate.getEmpresas(AbstractDelegate.ONLY_RS, null).get(index);
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
	
	public void seleccionarEmpresaServida(int index) {
		try {
			try {
				empresaservida =  delegate.getEmpresas(AbstractDelegate.ONLY_RS, null).get(index);				
			} catch (Exception e) {
				LogController.addError(Application.context.getUser(),e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),e.getMessage());
		}
	}

	public List<Empresa> listar() {
		try {
			return delegate.getEmpresas(AbstractDelegate.ONLY_RS, null);
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
			List<Empresa> lst = listar();
			for (Empresa item : lst) {
				if (item.getCodEmpresa().equalsIgnoreCase(key)) {
					selected = item;
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		selected = null;
	}

	public Empresa getEmpresaServida() {
		return empresaservida;
	}

	public void setEmpresaServida(Empresa empresaservida) {
		this.empresaservida = empresaservida;
	}
	
	
}
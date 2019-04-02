package pe.worktime.controller;

//import android.util.Log;

import pe.worktime.app.Application;
import pe.worktime.controller.delegate.UsuarioDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.Usuario;
import pe.worktime.model.entity.util.UserContext;

public class UsuarioController extends AbstractController {

	private Usuario selected = null;
	private UsuarioDelegate delegado = null;
	private String Msg = "";

	public UsuarioController() {
		delegado = new UsuarioDelegate();
	}

	@Override
	public void loadMaster() throws Exception {
		throw new Exception("No Implementado.");
	}

	public void autenticar(String User, String Password) throws Exception {
		UserContext userObj = null;
		boolean loadedWS = false;
		try {
			userObj = delegado.login(AbstractDelegate.ONLY_WS, User, Password);
			loadedWS = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				userObj = delegado.login(AbstractDelegate.ONLY_RS,  User,
						Password);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (userObj == null) {
			LogController.addError(Application.context.getUser(),
					"Login Usuario Vacia.");
			throw new Exception(
					"Autenticación Errónea, Verifique su Usuario y Contraseña.");
		} else {
			Application.context.initContext();
		}

		selected = userObj.getUser();
		if (loadedWS) {
			selected.setClave(Password);
			selected.setUser(User);
			selected.setFechaCelular(Application.context.getFechaOnly());
			Usuario lastUser = null;
			try {
				lastUser = delegado.getLastUser();			
			} catch (Exception e) {
				lastUser = null;
			}
			if(isNeedCreateDataBase(selected, lastUser)){
				try {
					Application.context.getHorasConsumidorController().cleanData();
					//Application.context.getProductividadController().cleanData();
					Application.context.getHorasConsumidorController().getDelegate().crearTablaServicioATerceros();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (isNeedLoadMaster(selected, lastUser)) {
				Application.context.getManagerView().print(20, ".Lotes.");
				//Application.context.getFundoController().loadMaster(userObj.getFundos());
				Application.context.getManagerView().print(30, ".Fundos.");
				Application.context.getEmpresaController().loadMasterSnd(userObj.getEmpresas());
				/* Cargamos Maestros dependiendo de las Condiciones */
				Application.context.getManagerView().print(50,"..Actividades..");
				Application.context.getActividadController().loadMaster();
				Application.context.getManagerView().print(70,"..Cultivos..");
				Application.context.getCultivoController().loadMaster();
				//Application.context.getManagerView().print(70, "...Grupos...");
				//Application.context.getGrupoController().loadMaster();
				Application.context.getManagerView().print(80,"..Consumidor Indirecto..");
				Application.context.getConsumidorIndirectoController().loadMaster();
				//Application.context.getManagerView().print(90, ".Turno Dia.");
				//Application.context.getTurnoDiaController().loadMaster();
				//Application.context.getManagerView().print(50,"Turno Dia");
				//Application.context.getHorasConsumidorController().loadMaster();
				Application.context.getManagerView().print(90, "...Personal...");
				Application.context.getPersonalController().loadMaster();
				Application.context.getManagerView().print(100, "Completo");
			}
			delegado.setUser(selected);
			if (Msg.equalsIgnoreCase("")) {
				Msg = "Sincronizacion Completa";
			}
		}

	}
	

	@Override
	public void seleccionar(int index) {
	}

	@Override
	public void seleccionar(String key) {
	}

	public Usuario getSelected() {
		return selected;
	}

	public void setSelected(Usuario selected) {
		this.selected = selected;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	@SuppressWarnings("unused")
	protected boolean isNeedLoadMaster(Usuario select, Usuario last) {
		if(true){
			return true;
		}
		
		if(last==null){
			return true;
		}
		
		if(select.getFechaCelular().equalsIgnoreCase(last.getFechaCelular())){
			return false;
		}		
		return true;
	}
	
	
	protected boolean isNeedCreateDataBase(Usuario select, Usuario last){		
		if(last==null){
			return true;
		}		
		return false;
	}
	

}
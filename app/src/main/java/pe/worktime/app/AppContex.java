package pe.worktime.app;

import android.content.Context;
import android.telephony.TelephonyManager;

import pe.worktime.controller.ActividadController;
import pe.worktime.controller.ConsumidorIndirectoController;
import pe.worktime.controller.CultivoController;
import pe.worktime.controller.EmpresaController;
import pe.worktime.controller.FundoController;
import pe.worktime.controller.GrupoController;
import pe.worktime.controller.HorasConsumidorController;
import pe.worktime.controller.PersonalController;
import pe.worktime.controller.ProductividadController;
import pe.worktime.controller.TurnoDiaController;
import pe.worktime.controller.UsuarioController;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.util.ModelHelper;
import pe.worktime.view.ManagerView;

public class AppContex {
	
	public Context context = null;
	private UsuarioController usuarioController;
	private FundoController fundoController;
	private ActividadController actividadController;
	private CultivoController cultivoController;
	private GrupoController grupoController;
	private PersonalController personalController;
	private ConsumidorIndirectoController consumidorIndirectoController;
	private TurnoDiaController turnoDiaController;	
	private HorasConsumidorController horasConsumidorController;
	private EmpresaController empresaController;
	private ProductividadController productividadController;
	private ManagerView managerView;

	public AppContex() {
		managerView = new ManagerView();
		usuarioController = new UsuarioController();
		empresaController = new EmpresaController();
	}

	public void initContext() {
		fundoController = new FundoController();
		actividadController = new ActividadController();
		cultivoController = new  CultivoController();
		grupoController = new GrupoController();
		personalController = new  PersonalController();
		consumidorIndirectoController = new ConsumidorIndirectoController();
		turnoDiaController = new TurnoDiaController();
		horasConsumidorController = new HorasConsumidorController();
		productividadController = new ProductividadController();
	//	empresaController = new EmpresaController();
	}

	public String getUser() {
		if (usuarioController.getSelected() == null) {
			return "";
		}
		return usuarioController.getSelected().getCodigo();
	}

	public String getFecha() {
		ModelHelper helper = new ModelHelper();
		return helper.getFecha();
	}
	
	public String getFechaOnly() {
		ModelHelper helper = new ModelHelper();
		return helper.getFechaOnly();
	}

	public String getHora() {
		ModelHelper helper = new ModelHelper();
		return helper.getHora();
	}
	

	public ManagerView getManagerView() {
		return managerView;
	}

	/* UTIL GET KEY TRANSAC */
	public KeyTransac getKeyUser() {
		if (usuarioController.getSelected() != null) {
			/*
			return new KeyTransac(usuarioController.getSelected().getUser(),
					usuarioController.getSelected().getCfs(), getIMEI());
			*/
			return new KeyTransac(usuarioController.getSelected().getCodigo(),
					usuarioController.getSelected().getCfs(), getIMEI());
		}
		return null;
	}
	
	public String getIMEI() {
		try {			
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			tm.getDeviceId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	

	public UsuarioController getUsuarioController() {
		return usuarioController;
	}

	public ActividadController getActividadController() {
		return actividadController;
	}

	public CultivoController getCultivoController() {
		return cultivoController;
	}

	public GrupoController getGrupoController() {
		return grupoController;
	}

	public ConsumidorIndirectoController getConsumidorIndirectoController() {
		return consumidorIndirectoController;
	}

	public TurnoDiaController getTurnoDiaController() {
		return turnoDiaController;
	}

	public HorasConsumidorController getHorasConsumidorController() {
		return horasConsumidorController;
	}
	public PersonalController getPersonalController() {
		return personalController;
	}

	public FundoController getFundoController() {
		return fundoController;
	}
	
	public EmpresaController getEmpresaController() {
		return empresaController;
	}

	public ProductividadController getProductividadController() {
		return productividadController;
	}
	
	public String IMEI = ""; 
	
	
}

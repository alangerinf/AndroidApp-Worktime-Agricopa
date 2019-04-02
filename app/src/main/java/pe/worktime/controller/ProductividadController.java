package pe.worktime.controller;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import pe.worktime.ContextTest;
import pe.worktime.app.Application;
import pe.worktime.controller.delegate.ProductividadDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.entity.Productividad;
import pe.worktime.model.entity.ProductividadDetalle;
import pe.worktime.model.entity.util.KeyTransac;

public class ProductividadController extends AbstractController {

	private Productividad selected = null;
	private ProductividadDelegate delegate = null;
	public String codConsumidor = null;
	public int type = -1;
	public final static int TYPE_DIRECTA = 0;
	public final static int TYPE_INDIRECTA = 1;
	
	
	public void InitProductividad() {
		selected = new Productividad();
		selected.setCodUser(Application.context.getUser());
		selected.setIMEI(Application.context.getUser());

		//selected.setFecha(Application.context.getFechaOnly());
		//selected.setFechaRegistroMovil(Application.context.getFecha());

		selected.setFecha(Application.context.getHorasConsumidorController().getSelected().getFecha());
		selected.setFechaRegistroMovil(Application.context.getHorasConsumidorController().getSelected().getFechaRegistroMovil());

		//selected.setCodEmpresa(Application.context.getEmpresaController().getSelected().getCodEmpresa());
		selected.setCodEmpresa(Application.context.getHorasConsumidorController().getSelected().getCodEmpresa());
		//selected.setCodFundo(Application.context.getFundoController().getSelected().getCodFundo());
		selected.setCodFundo(Application.context.getHorasConsumidorController().getSelected().getCodFundo());
		//selected.setCodModulo(Application.context.getFundoController().getModuloSelected().getCodModulo());
		selected.setCodModulo(Application.context.getHorasConsumidorController().getSelected().getCodModulo());
		selected.setIdCultivo(Application.context.getHorasConsumidorController().getSelected().getIdCultivo());
		/* Cod Actividad */
		selected.setCodActvidad(Application.context.getHorasConsumidorController().getSelected().getCodActividad());
		selected.setMigrado(0);
		//Turno Si es Directo
		// CC Si es Indirecto
		Log.d("Fundo:", selected.getCodFundo()+"Nombre"+Application.context.getFundoController().getNameOrDefault(selected.getCodFundo())+" Modulo:"+selected.getCodModulo() );
	}

	public ProductividadController() {
		delegate = new ProductividadDelegate();
		List<Productividad> l = null;
		try {
			l = delegate.getProductividads("");			
		} catch (Exception e) {
			if(l==null){
				try {
					delegate.setProductividads("", new ArrayList<Productividad>());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		}
	}

	public Productividad getSelected() {
		return selected;
	}

	public void setSelected(){
		selected = new Productividad();
	}

	public void setSelected(Productividad selected) {
		this.selected = selected;
		/*
		try {
			throw new Exception("Inicializando");
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}

	public ProductividadDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(ProductividadDelegate delegate) {
		this.delegate = delegate;
	}

	public void addDetalleManual(String dni, double cantidad) throws Exception {
		//this.delegate = delegate;
		if(cantidad < 0){
			throw new Exception("Cantidad No Valida.");
		}
		if(cantidad >= 10000){
			throw new Exception("Cantidad No Valida.");
		}
		String codConsumidor = "";

		if(!Application.context.getHorasConsumidorController().isDNIInPlanillaProductividad(dni, codConsumidor ,selected.getCodActvidad(), Application.context.getFechaOnly() )){
			throw new Exception("DNI no existe en ningun Registro de Horas asociado para esta Fecha.");
		}

		ProductividadDetalle deta = new ProductividadDetalle();
//		deta.setCantidad(""+cantidad);
//		deta.setCodConsumidor("");
//		deta.setCodTrabajador(dni);
//		deta.setHoraRegMovil(Application.context.getFecha());



		selected.getDetalle().clear();
		selected.getDetalle().add(deta);
		Application.context.getProductividadController().saveHorasProductividad();

		Log.e("DET. PRODUCTIVIDAD","INSERTADO CORRECTAMENTE");
	}

	public void addDetalle(String dni, double cantidad,String codConsumidor) throws Exception {
		//this.delegate = delegate;
		if(cantidad < 0){
			throw new Exception("Cantidad No Valida.");
		}
		if(cantidad >= 10000){
			throw new Exception("Cantidad No Valida.");
		}
//		String codConsumidor = "";
//		switch (type) {
//		case TYPE_DIRECTA:
//			codConsumidor = (Application.context.getFundoController().getTurnoSelected().getCod_turno());
//			break;
//		case TYPE_INDIRECTA:
//			codConsumidor = (Application.context.getConsumidorIndirectoController().getSelected().getCodConsumidor());
//			break;
//		default:
//			break;
//		}


		if(!Application.context.getHorasConsumidorController().isDNIInPlanillaProductividad(dni, codConsumidor ,selected.getCodActvidad(), Application.context.getFechaOnly() )){
			throw new Exception("DNI no existe en ningun Registro de Horas asociado para esta Fecha.");
		}
		
		ProductividadDetalle deta = new ProductividadDetalle();
		deta.setCantidad(""+cantidad);
		deta.setCodConsumidor(codConsumidor);
		deta.setCodTrabajador(dni);
		deta.setHoraRegMovil(Application.context.getFecha());
//		switch (type) {
//		case TYPE_DIRECTA:
//			deta.setCodConsumidor(Application.context.getFundoController().getTurnoSelected().getCod_turno());
//			break;
//		case TYPE_INDIRECTA:
//			deta.setCodConsumidor(Application.context.getConsumidorIndirectoController().getSelected().getCodConsumidor());
//			break;
//		default:
//			break;
//		}

		selected.getDetalle().add(deta);

		Log.e("DET. PRODUCTIVIDAD","INSERTADO CORRECTAMENTE");
	}
	
	@Override
	public void loadMaster() throws Exception {
		List<Productividad> lst = null;
		boolean loadedWS = false;
		try {
			KeyTransac key = Application.context.getKeyUser();
			lst = delegate.getProductividads(AbstractDelegate.ONLY_RS, key);
			if(lst == null){
				loadedWS = true;
				lst = new ArrayList<Productividad>();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (loadedWS) {
			delegate.setProductividads(Application.context.getUser(), lst);
		}
	}
	
	public void cleanData() throws Exception{
		delegate.setProductividads(Application.context.getUser(), new ArrayList<Productividad>());
		//delegate.clearProductividads(Application.context.getUser(), new ArrayList<Productividad>());
	}



	
	//@Override
	public void _loadMaster() throws Exception {
		List<Productividad> lst = null;
		boolean loadedWS = false;
		try {
			KeyTransac key = Application.context.getKeyUser();
			lst = delegate.getProductividads(AbstractDelegate.ONLY_WS, key);
			loadedWS = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (loadedWS) {
			delegate.setProductividads(Application.context.getUser(), lst);
		}
	}
	
	
	@Override
	public void seleccionar(int index) {
		try {
				selected = listar().get(index);				
			} catch (Exception e) {
				e.printStackTrace();
				selected = null;
			}		
	}

	public List<Productividad> listar() {
		try {
			return delegate.getProductividads(Application.context.getUser());
		} catch (Exception e) {
			e.printStackTrace();
			LogController.addError(Application.context.getUser(),
					e.getMessage());
		}
		return null;
	}

	@Override
	public void seleccionar(String key) {

	}

	public boolean checkAsistencia(String doc) {
		for (ProductividadDetalle deta : selected.getDetalle()) {
			if (deta.getCodTrabajador().equalsIgnoreCase(doc)) {
				return true;
			}
		}
		return false;
	}
				
	public boolean verificarDNI(String dni){
		for (ProductividadDetalle deta : selected.getDetalle()) {
			if (deta.getCodTrabajador().equalsIgnoreCase(dni)) {
				return true;
			}
		}
		return false;
	}

	public boolean modificarProductividad(String dni,String cant){
		if(ContextTest.idxTipoEntradaProductividad == 0)   //PRODUCTIVIDAD MANUAL
		{
			for (ProductividadDetalle deta : selected.getDetalle()) {
				if (deta.getCodTrabajador().equalsIgnoreCase(dni)) {
					deta.setCantidad(cant);
				}
			}
		}
		if(ContextTest.idxTipoEntradaProductividad == 1)   //PRODUCTIVIDAD AUTOMATICO
		{
			for (ProductividadDetalle deta : selected.getDetalle()) {
					deta.setCantidad(cant);
			}
		}
		return false;
	}
	
	public void updateHorasConsumidor()throws Exception {
		delegate.addOrUpdateProductividads(Application.context.getUser(),selected);
	}

	
	public void saveHorasProductividad() throws Exception {
		//delegate.addOrUpdateHorasConsumidors(Application.context.getUser(),selected);
//		selected.setCodEmpresa(Application.context.getEmpresaController().getSelected().getCodEmpresa());
//		selected.setCodFundo(Application.context.getFundoController().getSelected().getCodFundo());
//		selected.setCodModulo(Application.context.getFundoController().getModuloSelected().getCodModulo());

		//selected.setCodEmpresa(Application.context.getHorasConsumidorController().getSelected().getCodEmpresa());
		//selected.setCodFundo(Application.context.getHorasConsumidorController().getSelected().getCodFundo());
		//selected.setCodModulo(Application.context.getHorasConsumidorController().getSelected().getCodModulo());
		//selected.setIMEI(Application.context.getIMEI());
		//selected.setCodUser(Application.context.getUser());
		//selected.setFecha(Application.context.getFecha());
		//selected.setFechaRegistroMovil(Application.context.getFecha());
			//lista.add(selected);


		delegate.addOrUpdateProductividads(Application.context.getUser(),selected);
		Log.d("Btn Registrar:","Registrado");
			//tester(selected);
		//selected = new Productividad();
	}

	
	public void sincronizar() throws Exception {
		delegate.Sincronizar(Application.context.getKeyUser(), listar());
	}
	
	/* Oper Deta */
	
}

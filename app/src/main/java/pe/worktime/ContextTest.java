package pe.worktime;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import pe.worktime.app.Application;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.entity.Grupo;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.entity.Trabajador;
import pe.worktime.model.entity.Turno;
import pe.worktime.model.entity.TurnoDia;
import pe.worktime.model.entity.util.Credencial;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.entity.util.UserContext;
import pe.worktime.model.service.WSManager;

public class ContextTest {

	public static ContextTest ctx = new ContextTest();
	public UserContext context = null;
	public List<Actividad> lst1 = null;
	public List<ConsumidorIndirecto> lst3 = null;
	public List<Turno> lstTurno = null;
	public List<TurnoDia> lst5 = null;
	public List<Trabajador> lstTrabajador = null;
	public HorasConsumidor horas = null;
	public List<HorasConsumidor> registros = new ArrayList<HorasConsumidor>();
	public List<HorasConsumidorDetalle> detalle = new ArrayList<HorasConsumidorDetalle>();
	public HorasConsumidorDetalle detaHorasConsumidor = null ;
	public List<Grupo> lst4 = null;
	private List<Actividad> lstIndirecta = null;
	private List<Actividad> lstDirecta = null;
	public static int idxTyActividad = 0;
	public static int idxEntrada = 0;
	public static int idxTipoEntrada = 0; // 0 Manual    1 Automatica
	public static int idxTipoLabor = 0;
	public static int idxDirecORIn = 0;
	public static int idxTipoEntradaIngreso = 0;
	public static int idxTipoEntradaSalida = 0;
	public static int idxTipoEntradaTransferencia = 0;
	public static int idxTipoEntradaProductividad= -1;
	
	public static int idxOpcionHistorial = 0;

	//Variables en memoria para Transferencia
	public static String dniTransferencia = "";
	public static int idxTransferencia = -1;
	public static String hInicioFin = "";
	
	
	public void init() {

		WSManager ws = new WSManager();
		try {
			Log.d("Info", "Login Test");
			context = ws.getUser(new Credencial("02","0001", "001"));
			Log.d("Info", context.getUser().getNombre());
			KeyTransac key = new KeyTransac(context.getUser().getCodigo(),
					"000", "1234567890");
			Log.d("Info", "WSActividad Test");

			lst1 = ws.getActividades(key);
			Log.d("Info", "Cnt" + lst1.size());

			Log.d("Info", "WSConsumidorIndirecto Test");
			lst3 = ws.getConsumidoresIndirectos(key);
			Log.d("Info", "Cnt" + lst3.size());

			Log.d("Info", "WSGrupo Test");
			lst4 = ws.getGrupos(key);
			Log.d("Info", "Cnt" + lst4.size());

			Log.d("Info", "WSTurnoDia Test");
			lst5 = ws.getTurnodDias(key);
			Log.d("Info", "Cnt" + lst5.size());
			Log.d("Info", "WSHorasConsumidor Test");

			List<HorasConsumidor> lst6 = ws.getHorasConsumidor(key);
			Log.d("Info", "Cnt" + lst6.size());
			Log.d("Info", "END ...");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ContextTest() {

	}

	public List<Actividad> getLst1() {
		return lst1;
	}

	public void setLst1(List<Actividad> lst1) {
		this.lst1 = lst1;
	}

	public List<ConsumidorIndirecto> getLst3() {
		return lst3;
	}

	public void setLst3(List<ConsumidorIndirecto> lst3) {
		this.lst3 = lst3;
	}

	public List<Turno> getLstTurno() {
		return lstTurno;
	}

	public void setLstTurno(List<Turno> lstTurno) {
		this.lstTurno = lstTurno;
	}

	public List<Grupo> getLst4() {
		return lst4;
	}

	public void setLst4(List<Grupo> lst4) {
		this.lst4 = lst4;
	}

	public HorasConsumidor getHoras() {
		return horas;
	}

	public void setHoras(HorasConsumidor horas) {
		this.horas = horas;
	}

	public List<HorasConsumidor> getRegistros() {
		return registros;
	}

	public void setRegistros(List<HorasConsumidor> registros) {
		this.registros = registros;
	}

	public List<TurnoDia> getLst5() {
		return lst5;
	}

	public void setLst5(List<TurnoDia> lst5) {
		this.lst5 = lst5;
	}

	public List<Actividad> _listaDirecta() {
		
		try {
			lstDirecta = new ArrayList<Actividad>();
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
	
	public List<Actividad> _listaIndirecta() {
		try {
			lstIndirecta = new ArrayList<Actividad>();
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
	
	public List<Actividad> getLstIndirecta() {
		return lstIndirecta;
	}

	public void setLstIndirecta(List<Actividad> lstIndirecta) {
		this.lstIndirecta = lstIndirecta;
	}

	public List<Actividad> getLstDirecta() {
		return lstDirecta;
	}

	public void setLstDirecta(List<Actividad> lstDirecta) {
		this.lstDirecta = lstDirecta;
	}

	public List<HorasConsumidorDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<HorasConsumidorDetalle> detalle) {
		this.detalle = detalle;
	}

	public HorasConsumidorDetalle getDetaHorasConsumidor() {
		return detaHorasConsumidor;
	}

	public void setDetaHorasConsumidor(HorasConsumidorDetalle detaHorasConsumidor) {
		this.detaHorasConsumidor = detaHorasConsumidor;
	}
}

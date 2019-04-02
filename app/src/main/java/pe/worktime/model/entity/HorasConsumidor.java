package pe.worktime.model.entity;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import pe.worktime.model.entity.util.AbstractEntity;

public class HorasConsumidor extends AbstractEntity {

	private int codigo; // PK solo para Registro Interno
	private int tipolabor; // tipo de labor: 0:Hora Efectiva 1:Tarea
	private int migrado; //migrado: 0: NO /  1: SI
	private int migradoProd; //migrado: 0: NO /  1: SI
	private int asistencia; //migrado: 0: NO /  1: SI
	private String idCultivo; // tipo de cultivo
	private String codTurnoDia;
	private String codActividad;
	private String codSupervisor;
	private String codPlantilla;
	private String codGrupo;
	private String fecha;
	private String imei;
	private String fechaRegistroMovil;
	private List<HorasConsumidorDetalle> detalle;
	private ServicioATerceros servicioATerceros;

	private String codEmpresa = "";
	private String codFundo  = "";
	private String codModulo  = "";

	private String tipoActividad ="";
	private String codigoCecoOModulo = "";
	private String nombreCecoOModulo = "";

	public int getTipolabor() {
		return tipolabor;
	}

	public void setTipolabor(int tipolabor) {
		this.tipolabor = tipolabor;
	}

	
	public int getMigrado() {
		return migrado;
	}

	public void setMigrado(int migrado) {
		this.migrado = migrado;
	}

	public HorasConsumidor() {
		super();
		this.codTurnoDia = "0";
		this.codActividad = "";
		this.codSupervisor = "";
		this.codPlantilla = "";
		this.codGrupo = "";
		this.fecha = "";
		this.imei = "";
		this.fechaRegistroMovil = "";
		this.tipolabor = 0;
		this.detalle = new ArrayList<HorasConsumidorDetalle>();
		this.servicioATerceros = new ServicioATerceros();
		this.migrado = 0;
		this.migradoProd = 0 ;
		this.asistencia = 0;
		this.idCultivo = "";
	}

	public HorasConsumidor(String codTurnoDia, String codActividad,
			String codSupervisor, String codPlantilla, String codGrupo,
			String fecha, String imei, String fechaRegistroMovil,
			List<HorasConsumidorDetalle> detalle ,String Cultivo) {
		super();
		this.codTurnoDia = codTurnoDia;
		this.codActividad = codActividad;
		this.codSupervisor = codSupervisor;
		this.codPlantilla = codPlantilla;
		this.codGrupo = codGrupo;
		this.fecha = fecha;
		this.imei = imei;
		this.fechaRegistroMovil = fechaRegistroMovil;
		this.detalle = detalle;
		this.migrado = 0;
		this.migradoProd = 0;
		this.asistencia = 0;
		this.idCultivo=Cultivo;
	}
	
	public HorasConsumidor(String codTurnoDia, String codActividad,
			String codSupervisor, String codPlantilla, String codGrupo,
			String fecha, String imei, String fechaRegistroMovil,
			List<HorasConsumidorDetalle> detalle, ServicioATerceros servicioATerceros) {
		super();
		this.codTurnoDia = codTurnoDia;
		this.codActividad = codActividad;
		this.codSupervisor = codSupervisor;
		this.codPlantilla = codPlantilla;
		this.codGrupo = codGrupo;
		this.fecha = fecha;
		this.imei = imei;
		this.fechaRegistroMovil = fechaRegistroMovil;
		this.detalle = detalle;
		this.servicioATerceros = servicioATerceros;
		this.migrado = 0;
		this.migradoProd = 0;
		
	}

	public String getIdCultivo() {	return idCultivo;}

	public void setIdCultivo(String idCultivo) {this.idCultivo = idCultivo;}

	public String getCodTurnoDia() {
		return codTurnoDia;
	}

	public void setCodTurnoDia(String codTurnoDia) {
		this.codTurnoDia = codTurnoDia;
	}

	public String getCodActividad() {
		return codActividad;
	}

	public void setCodActividad(String codActividad) {
		this.codActividad = codActividad;
	}

	public String getCodSupervisor() {
		return codSupervisor;
	}

	public void setCodSupervisor(String codSupervisor) {
		this.codSupervisor = codSupervisor;
	}

	public String getCodPlantilla() {
		return codPlantilla;
	}

	public void setCodPlantilla(String codPlantilla) {
		this.codPlantilla = codPlantilla;
	}

	public String getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getFechaRegistroMovil() {
		return fechaRegistroMovil;
	}

	public void setFechaRegistroMovil(String fechaRegistroMovil) {
		this.fechaRegistroMovil = fechaRegistroMovil;
	}

	public List<HorasConsumidorDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<HorasConsumidorDetalle> detalle) {
		this.detalle = detalle;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int nroPresentes(){
		int i = 0;
		for (HorasConsumidorDetalle deta : this.detalle) {
			if(deta.isPresente())
			i++;	
		}
		return i;
	}
	
	public int nroAusentes(){
		int i = 0;
		for (HorasConsumidorDetalle deta : this.detalle) {
			if(deta.isAusente())
			i++;	
		}
		return i;
	}

	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getCodFundo() {
		return codFundo;
	}

	public void setCodFundo(String codFundo) {
		this.codFundo = codFundo;
	}

	public String getCodModulo() {
		return codModulo;
	}

	public void setCodModulo(String codModulo) {
		this.codModulo = codModulo;
	}
	
	public boolean isPlanillaCerrada(){
		for (HorasConsumidorDetalle item : detalle) {
			if(item.isWorking()){
				return false;
			}
		}
		return true;
	}
	
	public String getCodConsumidor(){
		for (HorasConsumidorDetalle item : detalle) {
			if("".compareToIgnoreCase(item.getCodTurno())== 0)
				return item.getCodConsumidor();
			else
				return item.getCodTurno();
		}
		return "";
	}
	
	public boolean isInFecha(String _fecha){
		Log.d("Start Witch: ", this.fecha.startsWith(_fecha) + "  ( "+this.fecha+" | "+_fecha+" ) ");
		return this.fecha.startsWith(_fecha);
	}
	
	public HorasConsumidorDetalle buscarDetalle(String dni){
		
		for (HorasConsumidorDetalle item : detalle) {
			if(dni.compareToIgnoreCase(item.getCodTrabajador())== 0){
			//if (item.getCodTrabajador().equalsIgnoreCase(dni)){
				return item;
	
			}
	
		}
		
		HorasConsumidorDetalle hcd = new HorasConsumidorDetalle();
		return hcd;
	
	}
	
	public HorasConsumidorDetalle buscarPrimerDetalle(){
		/*
		for (HorasConsumidorDetalle item : detalle) {
			if(dni.compareToIgnoreCase(item.getCodTrabajador())== 0){
			//if (item.getCodTrabajador().equalsIgnoreCase(dni)){
				return item;
	
			}
	
		}
		
		HorasConsumidorDetalle hcd = new HorasConsumidorDetalle();*/
		return (HorasConsumidorDetalle) detalle.get(0);
	
	}

	public ServicioATerceros getServicioATerceros() {
		return servicioATerceros;
	}

	public void setServicioATerceros(ServicioATerceros servicioATerceros) {
		this.servicioATerceros = servicioATerceros;
	}

	public int getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(int asistencia) {
		this.asistencia = asistencia;
	}

	public int getMigradoProd() {
		return migradoProd;
	}

	public void setMigradoProd(int migradoProd) {
		this.migradoProd = migradoProd;
	}

	public String getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public String getCodigoCecoOModulo() {
		return codigoCecoOModulo;
	}

	public void setCodigoCecoOModulo(String codigoCecoOModulo) {
		this.codigoCecoOModulo = codigoCecoOModulo;
	}

	public String getNombreCecoOModulo() {
		return nombreCecoOModulo;
	}

	public void setNombreCecoOModulo(String nombreCecoOModulo) {
		this.nombreCecoOModulo = nombreCecoOModulo;
	}
}
package pe.worktime.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Productividad {

	private int codigo; // PK solo para Registro Interno
	private String CodUser;
	private String IMEI;
	private String CodEmpresa;
	private String CodFundo;
	private String CodModulo;
	private String CodActvidad;
	private String FechaRegistroMovil;
	private String Fecha;
	private String idCultivo;
	private int migrado;


	private List<ProductividadDetalle> detalle = new ArrayList<ProductividadDetalle>();

	public String getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(String idCultivo) {
		this.idCultivo = idCultivo;
	}

	public int getCodigo() {return codigo;}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCodUser() {
		return CodUser;
	}

	public void setCodUser(String codUser) {
		CodUser = codUser;
	}

	public String getIMEI() {
		return IMEI;
	}

	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}

	public String getCodEmpresa() {
		return CodEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		CodEmpresa = codEmpresa;
	}

	public String getCodFundo() {
		return CodFundo;
	}

	public void setCodFundo(String codFundo) {
		CodFundo = codFundo;
	}

	public String getCodModulo() {
		return CodModulo;
	}

	public void setCodModulo(String codModulo) {
		CodModulo = codModulo;
	}

	public String getCodActvidad() {
		return CodActvidad;
	}

	public void setCodActvidad(String codActvidad) {
		CodActvidad = codActvidad;
	}

	public String getFechaRegistroMovil() {
		return FechaRegistroMovil;
	}

	public void setFechaRegistroMovil(String fechaRegistroMovil) {
		FechaRegistroMovil = fechaRegistroMovil;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public List<ProductividadDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<ProductividadDetalle> detalle) {
		this.detalle = detalle;
	}
	
	/* A D I C I O N A L  */
	public int NroRegistros(){
		try {
			return detalle.size();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public double subTotal(){
		double sub = 0, resultado,parteEntera;
		try {
			for (ProductividadDetalle deta : detalle) {
				sub += deta.getCantidadNumeric();
			}



			resultado = sub;
			parteEntera = Math.floor(resultado);
			resultado=(resultado-parteEntera)*Math.pow(10, 2);
			resultado=Math.round(resultado);
			resultado=(resultado/Math.pow(10, 2))+parteEntera;
			return resultado;

			//return sub;
			
		} catch (Exception e) {
			// TODO: handle exception
			return sub;
		}
	}

	public int getMigrado() {
		return migrado;
	}

	public void setMigrado(int migrado) {
		this.migrado = migrado;
	}
}

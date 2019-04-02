/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.worktime.model.entity;

import pe.worktime.model.entity.util.AbstractEntity;


/**
 *
 * @author omega
 */
public class Usuario extends AbstractEntity {

	private String empresa= "";
    private String codigo = "";
    private String nombre;
    private String user;
    private String descripcion;
    private String clave;
    private String cfs = "";
    private String fechaServer = "";
    private String fechaCelular = "";
    private String flagautoloadmaster = "";
    private String flagautocleandata = "";
    private String flagautocleanlastdata = "";
    
    public Usuario() {
    }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCfs() {
		return cfs;
	}

	public void setCfs(String cfs) {
		this.cfs = cfs;
	}

	public String getFechaServer() {
		return fechaServer;
	}

	public void setFechaServer(String fechaServer) {
		this.fechaServer = fechaServer;
	}

	public String getFechaCelular() {
		return fechaCelular;
	}

	public void setFechaCelular(String fechaCelular) {
		this.fechaCelular = fechaCelular;
	}

	public String getFlagautoloadmaster() {
		return flagautoloadmaster;
	}

	public void setFlagautoloadmaster(String flagautoloadmaster) {
		this.flagautoloadmaster = flagautoloadmaster;
	}

	public String getFlagautocleandata() {
		return flagautocleandata;
	}

	public void setFlagautocleandata(String flagautocleandata) {
		this.flagautocleandata = flagautocleandata;
	}

	public String getFlagautocleanlastdata() {
		return flagautocleanlastdata;
	}

	public void setFlagautocleanlastdata(String flagautocleanlastdata) {
		this.flagautocleanlastdata = flagautocleanlastdata;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
    
	
	
}

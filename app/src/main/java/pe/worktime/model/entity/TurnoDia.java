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
public class TurnoDia extends AbstractEntity {

	private String codigo;
	private String nombre; 
    private String inicio;
    private String fin;

    public TurnoDia() {
    }

	public TurnoDia(String codigo, String nombre, String inicio, String fin) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.inicio = inicio;
		this.fin = fin;
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

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

}

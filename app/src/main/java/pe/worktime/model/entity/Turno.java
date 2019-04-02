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
public class Turno extends AbstractEntity {

    private String cod_turno;
    private String nombre;
        
    public Turno() {
    }
    
    public Turno(String cod_turno, String nombre) {
		super();
		this.cod_turno = cod_turno;
		this.nombre = nombre;
	}

	public Turno(String cod_turno) {
        this.cod_turno = cod_turno;
    }

    public String getCod_turno() {
        return cod_turno;
    }

    public void setCod_turno(String cod_turno) {
        this.cod_turno = cod_turno;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
}

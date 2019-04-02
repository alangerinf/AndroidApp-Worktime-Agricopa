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
public class Grupo extends AbstractEntity {

	private String codGrupo;
	private String grupo;

	public Grupo() {
	}

	public String getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
}

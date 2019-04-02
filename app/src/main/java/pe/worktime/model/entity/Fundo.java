/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.worktime.model.entity;

import java.util.ArrayList;
import java.util.List;

import pe.worktime.model.entity.util.AbstractEntity;

/**
 * 
 * @author omega
 */

public class Fundo extends AbstractEntity {

	private String codFundo;
	private String fundo;
	private List<Modulo> modulos = new ArrayList<Modulo>();

	public Fundo() {
	}

	public Fundo(String codFundo, String fundo) {
		this.codFundo = codFundo;
		this.fundo = fundo;
	}

	public String getCodFundo() {
		return codFundo;
	}

	public void setCodFundo(String codFundo) {
		this.codFundo = codFundo;
	}

	public String getFundo() {
		return fundo;
	}

	public void setFundo(String fundo) {
		this.fundo = fundo;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
}
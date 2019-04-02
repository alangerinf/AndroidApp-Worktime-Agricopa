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
public class Supervisor extends AbstractEntity {

    private String codSupervisor;
    private String supervisor;
    private String usuario;
    private String clave;
    private List<Grupo> grupos = new ArrayList<Grupo>();

    public Supervisor() {
    }

    public Supervisor(String codSupervisor, String supervisor, String usuario, String clave) {
        this.codSupervisor = codSupervisor;
        this.supervisor = supervisor;
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCodSupervisor() {
        return codSupervisor;
    }

    public void setCodSupervisor(String codSupervisor) {
        this.codSupervisor = codSupervisor;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
    
    
}

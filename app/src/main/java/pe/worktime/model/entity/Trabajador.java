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
public class Trabajador extends AbstractEntity {

    private String codTrabajador;
    private String trabajador;
    
 
    public Trabajador() {
    }

    public Trabajador(String codTrabajador, String trabajador) {
        this.codTrabajador = codTrabajador;
        this.trabajador = trabajador;
    }

    public String getCodTrabajador() {
        return codTrabajador;
    }

    public void setCodTrabajador(String codTrabajador) {
        this.codTrabajador = codTrabajador;
    }

    public String getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(String trabajador) {
        this.trabajador = trabajador;
    }
    
    
     
    
}

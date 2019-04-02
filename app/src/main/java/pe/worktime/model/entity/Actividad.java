/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.worktime.model.entity;

import pe.worktime.app.Application;
import pe.worktime.model.entity.util.AbstractEntity;


/**
 *
 * @author omega
 */
public class Actividad extends AbstractEntity {

	private String codActividad;
    private String actividad;
    //private int horas;
    private String tipo;
    private double horas;
    private int codCultivo;
    private int asitencia_b;
    

    public Actividad() {
    }

    public Actividad(String codActividad, String actividad, int horas, String tipo, int codCultivo,int asitencia_b) {
        this.codActividad = codActividad;
        this.actividad = actividad;
        this.horas = horas;
        this.tipo = tipo;
        this.codCultivo = codCultivo;
        this.asitencia_b = asitencia_b;
    }
    

	public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(String codActividad) {
        this.codActividad = codActividad;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }
    
    public void setHoras(String horas) {
    	try {
    		this.horas = Integer.parseInt(horas);
		} catch (Exception e) {
			this.horas = -1;
		}
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public boolean isDirecta() {
        if(tipo.compareToIgnoreCase("D")==0 && codCultivo == Integer.parseInt(Application.context.getCultivoController().getSelected().getCodCultivo()))
		return true;
        else return false;
        //return tipo.compareToIgnoreCase("D")==0;
	}
    
    public boolean isIndirecta() {
        if(tipo.compareToIgnoreCase("I")==0 && codCultivo == Integer.parseInt(Application.context.getCultivoController().getSelected().getCodCultivo()))
            return true;
        else return false;
		//return tipo.compareToIgnoreCase("I")==0;
	}

    public int getCodCultivo() {return codCultivo;}

    public void setCodCultivo(int codCultivo) {this.codCultivo = codCultivo;}

    public int getAsitencia_b() { return asitencia_b;   }

    public void setAsitencia_b(int asitencia_b) {   this.asitencia_b = asitencia_b;  }
}

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
public class Planilla extends AbstractEntity {

    private String cod_planilla;
    private String planilla;

    public Planilla() {
    }

    public Planilla(String cod_planilla, String planilla) {
        this.cod_planilla = cod_planilla;
        this.planilla = planilla;
    }

    public String getCod_planilla() {
        return cod_planilla;
    }

    public void setCod_planilla(String cod_planilla) {
        this.cod_planilla = cod_planilla;
    }

    public String getPlanilla() {
        return planilla;
    }

    public void setPlanilla(String planilla) {
        this.planilla = planilla;
    }
}

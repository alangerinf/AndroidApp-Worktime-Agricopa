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
public class ConsumidorIndirecto extends AbstractEntity {

    private String codConsumidor;
    private String descripcion;

    public ConsumidorIndirecto() {
    }

    public ConsumidorIndirecto(String codConsumidor, String descripcion) {
        this.codConsumidor = codConsumidor;
        this.descripcion = descripcion;
    }

    public String getCodConsumidor() {
        return codConsumidor;
    }

    public void setCodConsumidor(String codConsumidor) {
        this.codConsumidor = codConsumidor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}

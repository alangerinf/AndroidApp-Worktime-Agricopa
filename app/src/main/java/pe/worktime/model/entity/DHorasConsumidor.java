/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.worktime.model.entity;

import java.util.Date;

import pe.worktime.model.entity.util.AbstractEntity;

/**
 *
 * @author omega
 */
public class DHorasConsumidor extends AbstractEntity {

    private Double IddHorasConsumidor;
    private Double IdHorasConsumidor;
    private String codTrabajador;
    private String cod_consumidor;
    private Date horaInicio;
    private Date horaFin;
    private int horas;

    public DHorasConsumidor() {
    }

    public DHorasConsumidor(Double IddHorasConsumidor, Double IdHorasConsumidor, String codTrabajador, String cod_consumidor, Date horaInicio, Date horaFin, int horas) {
        this.IddHorasConsumidor = IddHorasConsumidor;
        this.IdHorasConsumidor = IdHorasConsumidor;
        this.codTrabajador = codTrabajador;
        this.cod_consumidor = cod_consumidor;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.horas = horas;
    }

    public Double getIdHorasConsumidor() {
        return IdHorasConsumidor;
    }

    public void setIdHorasConsumidor(Double IdHorasConsumidor) {
        this.IdHorasConsumidor = IdHorasConsumidor;
    }

    public Double getIddHorasConsumidor() {
        return IddHorasConsumidor;
    }

    public void setIddHorasConsumidor(Double IddHorasConsumidor) {
        this.IddHorasConsumidor = IddHorasConsumidor;
    }

    public String getCodTrabajador() {
        return codTrabajador;
    }

    public void setCodTrabajador(String codTrabajador) {
        this.codTrabajador = codTrabajador;
    }

    public String getCod_consumidor() {
        return cod_consumidor;
    }

    public void setCod_consumidor(String cod_consumidor) {
        this.cod_consumidor = cod_consumidor;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}

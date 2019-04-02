
package pe.worktime.model.entity;

import java.util.ArrayList;
import java.util.List;

import pe.worktime.model.entity.util.AbstractEntity;

public class Modulo extends AbstractEntity {

    private String codModulo;
    private String modulo;
    private List<Turno> turnos = new ArrayList<Turno>();

    public Modulo() {
    }

    public Modulo(String codModulo, String modulo) {
        this.codModulo = codModulo;
        this.modulo = modulo;
    }

    public String getCodModulo() {
        return codModulo;
    }

    public void setCodModulo(String codModulo) {
        this.codModulo = codModulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}

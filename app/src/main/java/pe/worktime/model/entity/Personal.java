package pe.worktime.model.entity;

import pe.worktime.model.entity.util.AbstractEntity;

public class Personal extends AbstractEntity {

    private String dni;
    private String nombreApellido;

    public Personal() {
    }

    public Personal(String dni, String nombreApellido) {
        this.dni = dni;
        this.nombreApellido = nombreApellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }
}

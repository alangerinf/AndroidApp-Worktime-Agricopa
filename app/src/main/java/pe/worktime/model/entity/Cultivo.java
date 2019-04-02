package pe.worktime.model.entity;

import pe.worktime.model.entity.util.AbstractEntity;

public class Cultivo extends AbstractEntity {

    private String codCultivo;
    private String cultivo;
    private String fcodCultivo;

    public Cultivo() {
    }

    public Cultivo(String codCultivo, String cultivo , String fcodCultivo) {
        this.codCultivo = codCultivo;
        this.cultivo = cultivo;
        this.fcodCultivo = fcodCultivo;
    }

    public String getCodCultivo() {
        return codCultivo;
    }

    public void setCodCultivo(String codCultivo) {
        this.codCultivo = codCultivo;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    public String getFcodCultivo() {
        return fcodCultivo;
    }

    public void setFcodCultivo(String fcodCultivo) {
        this.fcodCultivo = fcodCultivo;
    }
}

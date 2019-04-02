package pe.worktime.model.entity;

public class GrupoDetalle {

    private String CodDetaGrupo;
    private String CodDetaTrabajador;

    public GrupoDetalle() {
    }

    public GrupoDetalle(String CodDetaGrupo, String CodDetaTrabajador) {
        this.CodDetaGrupo = CodDetaGrupo;
        this.CodDetaTrabajador = CodDetaTrabajador;
    }

    public String getCodDetaGrupo() {
        return CodDetaGrupo;
    }

    public void setCodDetaGrupo(String CodDetaGrupo) {
        this.CodDetaGrupo = CodDetaGrupo;
    }

    public String getCodDetaTrabajador() {
        return CodDetaTrabajador;
    }

    public void setCodDetaTrabajador(String CodDetaTrabajador) {
        this.CodDetaTrabajador = CodDetaTrabajador;
    }
}

package pe.worktime.model.entity;

public class Asistencia {
    private int codigo;
    private String CodUser;
    private String IMEI;
    private String CodEmpresa;
    private String CodFundo;
    private String CodModulo;
    private String CodActvidad;
    private String FechaRegistroMovil;
    private String Fecha;
    private String DNI_trabajador;
    private int migrado; //migrado: 0: NO /  1: SI

    public Asistencia() {
    }

    public Asistencia(String codUser, String IMEI, String codEmpresa, String codFundo, String codModulo, String codActvidad, String fechaRegistroMovil, String fecha, String DNI_trabajador , int migrado) {
        CodUser = codUser;
        this.IMEI = IMEI;
        CodEmpresa = codEmpresa;
        CodFundo = codFundo;
        CodModulo = codModulo;
        CodActvidad = codActvidad;
        FechaRegistroMovil = fechaRegistroMovil;
        Fecha = fecha;
        this.DNI_trabajador = DNI_trabajador;
        this.migrado = migrado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodUser() {
        return CodUser;
    }

    public void setCodUser(String codUser) {
        CodUser = codUser;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getCodEmpresa() {
        return CodEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        CodEmpresa = codEmpresa;
    }

    public String getCodFundo() {
        return CodFundo;
    }

    public void setCodFundo(String codFundo) {
        CodFundo = codFundo;
    }

    public String getCodModulo() {
        return CodModulo;
    }

    public void setCodModulo(String codModulo) {
        CodModulo = codModulo;
    }

    public String getCodActvidad() {
        return CodActvidad;
    }

    public void setCodActvidad(String codActvidad) {
        CodActvidad = codActvidad;
    }

    public String getFechaRegistroMovil() {
        return FechaRegistroMovil;
    }

    public void setFechaRegistroMovil(String fechaRegistroMovil) {
        FechaRegistroMovil = fechaRegistroMovil;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getDNI_trabajador() {
        return DNI_trabajador;
    }

    public void setDNI_trabajador(String DNI_trabajador) {
        this.DNI_trabajador = DNI_trabajador;
    }

    public int getMigrado() {
        return migrado;
    }

    public void setMigrado(int migrado) {
        this.migrado = migrado;
    }
}

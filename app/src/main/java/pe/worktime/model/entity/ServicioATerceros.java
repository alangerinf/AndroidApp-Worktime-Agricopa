package pe.worktime.model.entity;


public class ServicioATerceros {
	
	private String codEmpresa;
	private String codFundo;
	private String codModulo;
	private String codTurno;
	//private String codConsumidor;
	private String codActividad;

	public ServicioATerceros() {
		super();
		this.codEmpresa = "";
		this.codFundo = "";
		this.codModulo = "";
		this.codTurno = "";
		//this.codConsumidor = "";
		this.codActividad = "";
	
	}

	public ServicioATerceros(String codEmpresa,String codFundo,String codModulo, String codTurno, String codActividad){
			
		super();
		this.codEmpresa = codEmpresa;
		this.codFundo = codFundo;
		this.codModulo = codModulo;
		this.codTurno = codTurno;
		//this.codConsumidor = codConsumidor;
		this.codActividad = codActividad;
		
	}


	public String getCodTurno() {
		return codTurno;
	}

	public void setCodTurno(String codTurno) {
		this.codTurno = codTurno;
	}
/*
	public String getCodConsumidor() {
		return codConsumidor;
	}

	public void setCodConsumidor(String codConsumidor) {
		this.codConsumidor = codConsumidor;
	}
*/
	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getCodFundo() {
		return codFundo;
	}

	public void setCodFundo(String codFundo) {
		this.codFundo = codFundo;
	}

	public String getCodModulo() {
		return codModulo;
	}

	public void setCodModulo(String codModulo) {
		this.codModulo = codModulo;
	}

	public String getCodActividad() {
		return codActividad;
	}

	public void setCodActividad(String codActividad) {
		this.codActividad = codActividad;
	}
	
	
	
}
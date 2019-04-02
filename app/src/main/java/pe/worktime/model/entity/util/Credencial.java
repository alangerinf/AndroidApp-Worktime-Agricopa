package pe.worktime.model.entity.util;

public class Credencial extends AbstractEntity {

	private String usuario;
	private String password;
	private String imei;

	public Credencial() {
	}

	public Credencial( String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}

	public Credencial(String usuario, String password, String imei) {
		//super();
		this.usuario = usuario;
		this.password = password;
		this.imei = imei;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

}

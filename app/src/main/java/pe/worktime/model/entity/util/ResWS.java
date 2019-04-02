package pe.worktime.model.entity.util;

public class ResWS extends AbstractEntity {

	private String cod;
	private String msg;

	public ResWS(String cod, String msg) {
		this.cod = cod;
		this.msg = msg;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isOK() {
		return this.cod.equalsIgnoreCase("1");
	}
	
	public boolean isBad() {
		return !this.cod.equalsIgnoreCase("1");
	}

}
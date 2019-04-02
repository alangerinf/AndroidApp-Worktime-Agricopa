package pe.worktime.model.entity.util;

public class KeyTransac extends AbstractEntity {

	private String user;
	private String key;
	private String imei;
	
	public KeyTransac(String user, String key, String imei) {
		this.user = user;
		this.key = key;
		this.imei = imei;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
}
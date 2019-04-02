package pe.worktime.model.entity;

public class ProductividadDetalle {

	private int codProductividad;
	private String codTrabajador;
	private String codConsumidor;
	private String cantidad;
	private String horaRegMovil;

	public int getCodProductividad() {
		return codProductividad;
	}

	public void setCodProductividad(int codProductividad) {
		this.codProductividad = codProductividad;
	}

	public String getCodTrabajador() {
		return codTrabajador;
	}

	public void setCodTrabajador(String codTrabajador) {
		this.codTrabajador = codTrabajador;
	}

	public String getCodConsumidor() {
		return codConsumidor;
	}

	public void setCodConsumidor(String codConsumidor) {
		this.codConsumidor = codConsumidor;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getHoraRegMovil() {
		return horaRegMovil;
	}

	public void setHoraRegMovil(String horaRegMovil) {
		this.horaRegMovil = horaRegMovil;
	}
	
	/* U T I L */
	public double getCantidadNumeric() {
		try {
			return Double.parseDouble(cantidad);
		} catch (Exception e) {
			return 0;
		}
	}

}

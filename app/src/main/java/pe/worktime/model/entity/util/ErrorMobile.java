/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.worktime.model.entity.util;

import java.util.Date;

public class ErrorMobile extends AbstractEntity {

	private Date fecha;
	private String user;
	private String msg;

	public ErrorMobile(String user, String msg) {
		this.user = user;
		this.msg = msg;
		this.fecha = new Date();
	}

	public ErrorMobile(long fecha, String user, String msg) {
		this.fecha = new Date(fecha);
		this.user = user;
		this.msg = msg;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getMsg() {
		return msg;
	}

	public String getUser() {
		return user;
	}

}

package pe.worktime.model.entity;

import java.util.Date;

import android.util.Log;

import pe.worktime.app.Application;
import pe.worktime.model.util.ModelHelper;

public class HorasConsumidorDetalle {

	private String codTrabajador;
	private String codTurno;
	private String codConsumidor;
	private String horaInicio;
	private String horaFin;
	private int horas;
	private String horaInicioMovil;
	private String horaFinMovil;
	private int horas_descanso;


	public HorasConsumidorDetalle() {
		super();
		this.codTrabajador = "";
		this.codTurno = "";
		this.codConsumidor = "";
		this.horaInicio = "";
		this.horaFin = "";
		this.horas = -1;
		this.horaInicioMovil = "";
		this.horaFinMovil = "";
		this.horas_descanso = 0;

	}

	public HorasConsumidorDetalle(String codTrabajador, String codTurno,
			String codConsumidor, String horaInicio, String horaFin, int horas,
			String horaInicioMovil, String horaFinMovil , int horas_descanso ) {
		super();
		this.codTrabajador = codTrabajador;
		this.codTurno = codTurno;
		this.codConsumidor = codConsumidor;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.horas = horas;
		this.horaInicioMovil = horaInicioMovil;
		this.horaFinMovil = horaFinMovil;
		this.horas_descanso = horas_descanso;

	}

	public String getCodTrabajador() {
		return codTrabajador;
	}

	public void setCodTrabajador(String codTrabajador) {
		this.codTrabajador = codTrabajador;
	}

	public String getCodTurno() {
		return codTurno;
	}

	public void setCodTurno(String codTurno) {
		this.codTurno = codTurno;
	}

	public String getCodConsumidor() {
		return codConsumidor;
	}

	public void setCodConsumidor(String codConsumidor) {
		this.codConsumidor = codConsumidor;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public int getHoras_descanso() {return horas_descanso;	}

	public void setHoras_descanso(int horas_descanso) { this.horas_descanso = horas_descanso;}

	public void setHoraFinOk(String horaFin) {
		this.horaFin = horaFin;
	}
	
	public void setHoraInicio(String horaInicio) {
		if(horaInicio.length() == 0 ){
			 return;
		}
		if(horaInicio.length() > 20){
			return;
		}
		if(horaInicio.length() == 5){
			horaInicio = horaInicio+":00";
		}
		if(horaInicio.length() < 10){
			horaInicio =  Application.context.getFechaOnly() +
					" "+horaInicio;  
		}		
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) throws Exception {
		if(horaFin.length() == 0 ){
			 return;
		}
		if(horaFin.length() > 20){
			return;
		}
		if(horaFin.length() < 10){
			horaFin =  Application.context.getFechaOnly() + " "+horaFin;
		}
		this.horaFin = horaFin;
		validarIngresoSalida();
	}
	
	public void setHoraFinIntent(String horaFin) throws Exception{
		
		ModelHelper helper = new ModelHelper();
		Date inicio = helper.convertStringToDate(horaInicio);
		Date fin = helper.convertStringToDate(horaFin);
		
		Log.d("validarIngresoSalida", "this.horaInicio: "+horaInicio + " horaInicioMovil: "+horaInicioMovil );
		
		Log.d("validarIngresoSalida", "Inicio: "+inicio + " Fin: "+fin );
		Log.d("validarIngresoSalida", "fin.getTime() < inicio.getTime() "+ (fin.getTime() < inicio.getTime()) );
		Log.d("validarIngresoSalida", "Fin: "+ fin.getTime() +" Inicio: "+inicio.getTime() );
		
		if(fin.getTime() < inicio.getTime()){
			throw new Exception("Salida Incorrecta");
		}
	}
	
	
	private void validarIngresoSalida() throws Exception{
		ModelHelper helper = new ModelHelper(); 
		//Log.d("validarIngresoSalida", "Inicio: "+horaInicio + " INicio Movi: "+horaInicioMovil );
		
		//if(horaInicio.equalsIgnoreCase("")){
		//	horaInicio  = horaInicioMovil;
		//}
		
		Date inicio = helper.convertStringToDate(horaInicio);
		Date fin = helper.convertStringToDate(horaFin);
		Log.d("validarIngresoSalida", "Inicio: "+inicio + " Fin: "+fin );
		Log.d("validarIngresoSalida", "fin.getTime() < inicio.getTime() "+ (fin.getTime() < inicio.getTime()) );
		Log.d("validarIngresoSalida", "Fin: "+ fin.getTime() +" Inicio: "+inicio.getTime() );
		
		if(fin.getTime() < inicio.getTime()){
			horaFin = "";
			throw new Exception("Salida Incorrecta");
		}
		calcularSalida();
	}
	
	private void calcularSalida(){
		try{
			ModelHelper helper = new ModelHelper(); 
			Date inicio = helper.convertStringToDate(horaInicio);
			Date fin = helper.convertStringToDate(horaFin);
			//Segundos
			int dif = (int)((fin.getTime() - inicio.getTime())/1000);
			//Minutos
			dif = (int) dif/60;
			Log.d("Inicio: ", ""+inicio);
			Log.d("Fin: ", ""+fin);
			Log.d("Info Dif: ", ""+dif);
			horas = dif;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public String getHoraInicioMovil() {
		return horaInicioMovil;
	}

	public void setHoraInicioMovil(String horaInicioMovil) {
		if(horaInicioMovil.length() == 0 ){
			 return;
		}
		if(horaInicioMovil.length() > 20){
			return;
		}
		if(horaInicioMovil.length() < 10){
			horaInicioMovil =  Application.context.getFechaOnly() +
					" "+horaInicioMovil;  
		}
		this.horaInicioMovil = horaInicioMovil;
	}

	public String getHoraFinMovil() {
		return horaFinMovil;
	}

	public void setHoraFinMovil(String horaFinMovil) {
		if(horaFinMovil.length() == 0 ){
			 return;
		}
		if(horaFinMovil.length() > 20){
			return;
		}
		if(horaFinMovil.length() < 10 ){
			horaFinMovil =  Application.context.getFechaOnly() +
					" "+horaFinMovil;  
		}
		this.horaFinMovil = horaFinMovil;
	}

	public boolean isPresente() {	
		//return this.horaFin.equalsIgnoreCase(""); 
		return this.horaFinMovil.equalsIgnoreCase(""); 
	}

	public boolean isAusente() {
		//return !this.horaFin.equalsIgnoreCase("");
		return !this.horaFinMovil.equalsIgnoreCase("");
	}
	
	
	public boolean isWorking() {
		return this.horaFin.equalsIgnoreCase("");
		//return !this.horaFinMovil.equalsIgnoreCase("");
	}

}
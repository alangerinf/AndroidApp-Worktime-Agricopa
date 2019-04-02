package pe.worktime.model.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

public class ModelHelper {

    private String getFormatField(int val){
        if(val<10){
            return "0"+val;
        }
        return String.valueOf(val);
    }
    
    /*
     * 2012-03-26 03:51:19
     */
    public String _getFecha() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) + "-" + getFormatField(c.get(Calendar.MONTH)) + "-" + getFormatField(c.get(Calendar.DAY_OF_MONTH)) + " "
                + getFormatField(c.get(Calendar.HOUR_OF_DAY)) + ":" + getFormatField(c.get(Calendar.MINUTE)) + ":" + getFormatField(c.get(Calendar.SECOND));
    }
    
    @SuppressWarnings("static-access")
	public String getFecha(){
    	DateFormat df = new DateFormat();
    	return df.format("yyyy-MM-dd kk:mm:ss", new Date()).toString();
    }
    
    public String _getFechaOnly() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) + "-" + getFormatField(c.get(Calendar.MONTH)) + "-" + getFormatField(c.get(Calendar.DAY_OF_MONTH)) ;
    }
    
    @SuppressWarnings("static-access")
	public String getFechaOnly(){
    	DateFormat df = new DateFormat();
    	return df.format("yyyy-MM-dd", new Date()).toString();
    }
    
    @SuppressLint("SimpleDateFormat") 
    public Date convertStringToDate(String value){
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
    	try {
    		return formatter.parse(value);
		} catch (Exception e) {
			e.printStackTrace();
			formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm");
	    	try {
	    		return formatter.parse(value);
	    	}catch (Exception e2) {
	    		e.printStackTrace();
	    	}
			return null;
		}    	
    }
    

    /*
     * 03:51:19
     */
    public String _getHora() {
        Calendar c = Calendar.getInstance();
        return getFormatField(c.get(Calendar.HOUR_OF_DAY)) + ":" + getFormatField(c.get(Calendar.MINUTE)) + ":" + getFormatField(c.get(Calendar.SECOND));
    }
    
    @SuppressWarnings("static-access")
	public String getHora(){
    	DateFormat df = new DateFormat();
    	return df.format("kk:mm:ss", new Date()).toString();
    }
    
    //Es numero o String
    
    public int isText(String texto) {
		String numeros = "0123456789";
		for (int i = 0; i < texto.length(); i++) {
			if (numeros.indexOf(texto.charAt(i), 0) != -1) {
				return 1;
			}
		}
		return 0;
	}
    
    @SuppressLint("SimpleDateFormat")
    public String cambiarFormatoFecha(Date fecha)
    {	String fec;
    	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");		
    	fec = formater.format(fecha);			
    	return fec;
    }
    
    
}

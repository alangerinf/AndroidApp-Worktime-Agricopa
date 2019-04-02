package pe.worktime.controller.util;

import java.util.Vector;

import pe.worktime.controller.delegate.util.ErrorDelegate;

public class LogController {
	
	public static void addError(String User, String Msg) {
        ErrorDelegate delegado  = new ErrorDelegate();
        delegado.addError(User, Msg);
    }

    /* Get Logs de Errores */
    @SuppressWarnings("rawtypes")
	public static Vector getErrores() throws Exception {
        ErrorDelegate delegado  = new ErrorDelegate();
        return delegado.getErrores();
    }

}

package pe.worktime.model.entity.test;

import java.util.List;

import android.util.Log;

import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.test.util.AbstractTest;
import pe.worktime.model.entity.util.KeyTransac;

public class ActividadTest  extends AbstractTest {

	@Override
	public void run() {
		try {			
			KeyTransac key = new KeyTransac("0001", "00", "123456789");			
			List<Actividad> lst =  ws.getActividades(key);			
			rs.selListActividad(User, lst);
			List<Actividad> lst2 = rs.getActividades(User);									
			compareListActividad(lst,lst2);
			Log.d("INFO TEST ", "TEST OK "+this.getClass().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
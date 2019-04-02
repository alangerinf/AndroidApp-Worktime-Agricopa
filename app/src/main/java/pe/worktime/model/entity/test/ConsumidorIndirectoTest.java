package pe.worktime.model.entity.test;

import java.util.List;

import android.util.Log;

import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.entity.test.util.AbstractTest;
import pe.worktime.model.entity.util.KeyTransac;

public class ConsumidorIndirectoTest  extends AbstractTest {

	@Override
	public void run() {
		try {			
			KeyTransac key = new KeyTransac("0001", "00", "123456789");			
			List<ConsumidorIndirecto> lst =  ws.getConsumidoresIndirectos(key);			
			rs.selListConsumidorIndirecto(User, lst);
			List<ConsumidorIndirecto> lst2 = rs.getConsumidorIndirecto(User);									
			compareListConsumidorIndirecto(lst,lst2);
			Log.d("INFO TEST ", "TEST OK "+this.getClass().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
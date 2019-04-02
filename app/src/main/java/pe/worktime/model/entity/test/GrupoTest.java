package pe.worktime.model.entity.test;

import java.util.List;

import android.util.Log;

import pe.worktime.model.entity.Grupo;
import pe.worktime.model.entity.test.util.AbstractTest;
import pe.worktime.model.entity.util.KeyTransac;

public class GrupoTest extends AbstractTest {

	@Override
	public void run() {
		try {			
			KeyTransac key = new KeyTransac("0001", "00", "123456789");			
			List<Grupo> lst =  ws.getGrupos(key);			
			rs.selListGrupo(User, lst);
			List<Grupo> lst2 = rs.getGrupos(User);									
			compareListGrupo(lst,lst2);
			Log.d("INFO TEST ", "TEST OK "+this.getClass().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
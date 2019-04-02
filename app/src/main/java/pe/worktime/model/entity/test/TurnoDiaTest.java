package pe.worktime.model.entity.test;

import java.util.List;

import android.util.Log;

import pe.worktime.model.entity.TurnoDia;
import pe.worktime.model.entity.test.util.AbstractTest;
import pe.worktime.model.entity.util.KeyTransac;

public class TurnoDiaTest extends AbstractTest {

	@Override
	public void run() {
		try {			
			KeyTransac key = new KeyTransac("0001", "00", "123456789");
			List<TurnoDia> lst =  ws.getTurnodDias(key);
			rs.selListTurnoDia(User, lst);
			List<TurnoDia> lst2 = rs.getTurnoDia(User);									
			compareListTurnoDia(lst,lst2);
			Log.d("INFO TEST ", "TEST OK "+this.getClass().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void compareListTurnoDia(List<TurnoDia> src, List<TurnoDia> tar) throws Exception{
		if(src.size() !=tar.size()) throw new Exception("No Equals Size");
	    for (int i = 0; i < src.size(); i++) {
	    	compareTurnoDia(src.get(i), tar.get(i));
	    }
	}
	
	protected void compareTurnoDia(TurnoDia src, TurnoDia tar) throws Exception{
		if(!src.getCodigo().equalsIgnoreCase(tar.getCodigo())) throw new Exception("No Equals Codigo");
		if(!src.getNombre().equalsIgnoreCase(tar.getNombre())) throw new Exception("No Equals Nombre");		
		if(!src.getInicio().equalsIgnoreCase(tar.getInicio())) throw new Exception("No Equals Inicio");
		if(!src.getFin().equalsIgnoreCase(tar.getFin())) throw new Exception("No Equals Fin");
	}
	
}
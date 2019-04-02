package pe.worktime.model.entity.test;

import java.util.List;

import android.util.Log;

import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Usuario;
import pe.worktime.model.entity.test.util.AbstractTest;
import pe.worktime.model.entity.util.Credencial;
import pe.worktime.model.entity.util.UserContext;

public class UserTest extends AbstractTest {

	@Override
	public void run() {
		try {
			UserContext context = ws.getUser(new Credencial("002","0001", "001"));
			context.getUser().setClave("001");
			context.getUser().setUser("0001");			
			rs.setUsuario(context.getUser());
			Usuario user = rs.getUsuario();
			compareUser(context.getUser(), user );
			rs.selListFundo(context.getUser().getCodigo(),context.getFundos());
			List<Fundo> fundos = rs.getFundos(context.getUser().getCodigo());
			compareListFundo(context.getFundos(),fundos);					
			Log.d("INFO TEST ", "TEST OK "+this.getClass().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
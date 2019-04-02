package pe.worktime.controller.delegate;

import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.model.entity.Usuario;
import pe.worktime.model.entity.util.Credencial;
import pe.worktime.model.entity.util.UserContext;

public class UsuarioDelegate extends AbstractDelegate {

    private UserContext usr = null;
    private Usuario user = null;
    
   /* ONLY_RS - ONLY_WS */
    public UserContext login(int DS, String User, String Password) throws Exception {
        switch (DS) {
            case ONLY_WS:
            	Credencial credencial =  new Credencial(User,Password);
                usr = wsManager.getUser(credencial);
                return usr;
            case ONLY_RS:
            	Usuario obj = loginRS( User,Password);
            	if(obj!=null){
            		return new UserContext(null,obj);
            	}
            	return null;
        }
        throw  new Exception("(Login) DS no Reconocido.");
    }
    
    public Usuario loginRS( String User, String Password) throws Exception {
    	Credencial credencial =  new Credencial(User,Password);
    	return rsManager.loginUsuario(credencial);
    }
    
    /* ONLY_RS */
    public void setUser(Usuario personal) throws Exception {
        rsManager.setUsuario(personal);
    }

    public UserContext getUsr() {
        return usr;
    }

	public Usuario getUser() {
		return user;
	}
	
	public Usuario getLastUser() {
		try {
			return rsManager.getUsuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
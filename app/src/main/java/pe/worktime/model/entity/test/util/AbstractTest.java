package pe.worktime.model.entity.test.util;

import java.util.List;

import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Grupo;
import pe.worktime.model.entity.Modulo;
import pe.worktime.model.entity.Turno;
import pe.worktime.model.entity.Usuario;
import pe.worktime.model.service.WSManager;
import pe.worktime.model.store.RSManager;

public abstract class AbstractTest {

	protected WSManager ws = new WSManager();
	protected RSManager rs = new RSManager();
	
	protected String User = "0001";
	
	public abstract void run();
	
	protected void compareUser(Usuario src, Usuario tar) throws Exception{
        if(!src.getCfs().equalsIgnoreCase(tar.getCfs())) throw new Exception("No Equals Cfs");
        if(!src.getClave().equalsIgnoreCase(tar.getClave())) throw new Exception("No Equals Clave");
        if(!src.getDescripcion().equalsIgnoreCase(tar.getDescripcion())) throw new Exception("No Equals Descripcion");
        if(!src.getFechaCelular().equalsIgnoreCase(tar.getFechaCelular())) throw new Exception("No Equals Fecha Celular");
        if(!src.getFechaServer().equalsIgnoreCase(tar.getFechaServer())) throw new Exception("No Equals Fecha Server");
        if(!src.getNombre().equalsIgnoreCase(tar.getNombre())) throw new Exception("No Equals Nombre");
        if(!src.getUser().equalsIgnoreCase(tar.getUser())) throw new Exception("No Equals User");
        if(!src.getCodigo().equalsIgnoreCase(tar.getCodigo())) throw new Exception("No Equals Codigo");        
        if(!src.getFlagautocleandata().equalsIgnoreCase(tar.getFlagautocleandata())) throw new Exception("No Equals Flagautocleandata");
        if(!src.getFlagautocleanlastdata().equalsIgnoreCase(tar.getFlagautocleanlastdata())) throw new Exception("No Equals Flagautocleanlastdata");
        if(!src.getFlagautoloadmaster().equalsIgnoreCase(tar.getFlagautoloadmaster())) throw new Exception("No Equals Flagautoloadmaster");
    }
		
	protected void compareListFundo(List<Fundo> src, List<Fundo> tar) throws Exception{
		if(src.size() !=tar.size()) throw new Exception("No Equals Size");
	    for (int i = 0; i < src.size(); i++) {
	    	compareFundo(src.get(i), tar.get(i));
	    }
	}
		
	protected void compareFundo(Fundo src, Fundo tar) throws Exception{
		if(!src.getCodFundo().equalsIgnoreCase(tar.getCodFundo())) throw new Exception("No Equals CodFundo");
		if(!src.getFundo().equalsIgnoreCase(tar.getFundo())) throw new Exception("No Equals Fundo");
		compareListModulo(src.getModulos(), tar.getModulos());
	}
		
	protected void compareListModulo(List<Modulo> src, List<Modulo> tar) throws Exception{
		if(src.size() !=tar.size()) throw new Exception("No Equals Modulos");
		for (int i = 0; i < src.size(); i++) {
		    compareModulo(src.get(i), tar.get(i));
		}
	}
		
	protected void compareModulo(Modulo src, Modulo tar) throws Exception{
		if(!src.getCodModulo().equalsIgnoreCase(tar.getCodModulo())) throw new Exception("No Equals CodModulo");
		if(!src.getModulo().equalsIgnoreCase(tar.getModulo())) throw new Exception("No Equals Modulo");
		compareListTurno(src.getTurnos(), tar.getTurnos());
	}
		
	protected void compareListTurno(List<Turno> src, List<Turno> tar) throws Exception{
		if(src.size() !=tar.size()) throw new Exception("No Equals Turnos");
		for (int i = 0; i < src.size(); i++) {
		    compareTurno(src.get(i), tar.get(i));
		}
	}
		
	protected void compareTurno(Turno src, Turno tar) throws Exception{
		if(!src.getCod_turno().equalsIgnoreCase(tar.getCod_turno())) throw new Exception("No Equals Cod_turno");
		if(!src.getNombre().equalsIgnoreCase(tar.getNombre())) throw new Exception("No Equals Nombre");
	}
	
	
	protected void compareListActividad(List<Actividad> src, List<Actividad> tar) throws Exception{
		if(src.size() !=tar.size()) throw new Exception("No Equals Size");
	    for (int i = 0; i < src.size(); i++) {
	    	compareActividad(src.get(i), tar.get(i));
	    }
	}
		
	protected void compareActividad(Actividad src, Actividad tar) throws Exception{
		if(!src.getCodActividad().equalsIgnoreCase(tar.getCodActividad())) throw new Exception("No Equals CodAcividad");
		if(!src.getActividad().equalsIgnoreCase(tar.getActividad())) throw new Exception("No Equals Actividad");
		if(!src.getTipo().equalsIgnoreCase(tar.getTipo())) throw new Exception("No Equals Tipo");
		if(src.getHoras() !=tar.getHoras()) throw new Exception("No Equals Horas");
	}
	
	protected void compareListConsumidorIndirecto(List<ConsumidorIndirecto> src, List<ConsumidorIndirecto> tar) throws Exception{
		if(src.size() !=tar.size()) throw new Exception("No Equals Size");
	    for (int i = 0; i < src.size(); i++) {
	    	compareConsumidorIndirecto(src.get(i), tar.get(i));
	    }
	}
	
	protected void compareConsumidorIndirecto(ConsumidorIndirecto src, ConsumidorIndirecto tar) throws Exception{
		if(!src.getCodConsumidor().equalsIgnoreCase(tar.getCodConsumidor())) throw new Exception("No Equals CodConsumidor");
		if(!src.getDescripcion().equalsIgnoreCase(tar.getDescripcion())) throw new Exception("No Equals Descripcion");		
	}
		
	
	protected void compareListGrupo(List<Grupo> src, List<Grupo> tar) throws Exception{
		if(src.size() !=tar.size()) throw new Exception("No Equals Size");
	    for (int i = 0; i < src.size(); i++) {
	    	compareGrupo(src.get(i), tar.get(i));
	    }
	}
	
	protected void compareGrupo(Grupo src, Grupo tar) throws Exception{
		if(!src.getCodGrupo().equalsIgnoreCase(tar.getCodGrupo())) throw new Exception("No Equals CodGrupo");
		if(!src.getGrupo().equalsIgnoreCase(tar.getGrupo())) throw new Exception("No Equals Grupo");
	}
	
}

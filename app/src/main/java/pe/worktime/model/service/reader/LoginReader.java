package pe.worktime.model.service.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.util.Log;

import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Modulo;
import pe.worktime.model.entity.Turno;
import pe.worktime.model.entity.Usuario;
import pe.worktime.model.entity.util.UserContext;

public class LoginReader extends AbstractReaderXML {

	private final String RESPONSE = "AutenticarResult";
	private final String EMPRESAWS = "EmpresaWS";
	private final String EMPRESAS = "Empresas";
//	private final String FUNDOS = "Fundos";
	private final String FUNDOWS = "FundoWS";
	private final String MODULOS = "Modulos";
	private final String MODULOWS = "ModuloWS";
	private final String TURNOS = "Turnos";
	private final String TURNOSWS = "TurnoWS";
	private final String CODIGO = "Codigo";
	private final String SUPERVISOR = "Supervisor";
	private final String FULLNAME = "Fullname";
	private final String FLAGAUTOLOADMASTER = "FlagAutoLoadMaster";
	private final String FLAGAUTOCLEANDATA = "FlagAutoCleanData";
	private final String FLAGCLEANLASTDATA = "FlagCleanLastData";
	private final String CODIGO_FUNDO = "Codigo";
	private final String NOMBRE_FUNDO = "Nombre";
	private final String CODIGO_MODULO = "Codigo";
	private final String NOMBRE_MODULO = "Nombre";
	private final String CODIGO_TURNO = "Codigo";
	private final String NOMBRE_TURNO = "Nombre";
	private final String EMPRESA = "CodEmpresa";
	private final String CODIGO_EMPRESA = "Codigo";
	private final String NOMBRE_EMPRESA = "Nombre";

	private List<Empresa> empresas;
//	private List<Fundo> fundos;
	private Fundo Obj;
	private Empresa ObjEmpresa;
	private Modulo ObjModulo;
	private Turno ObjTurno;

	private Usuario user;

	public LoginReader() {
		empresas = null;
	//	fundos = null;
		Obj = null;
		ObjModulo = null;
		ObjTurno = null;
	}

	@SuppressWarnings("rawtypes")
	public void startElement(String qName, Hashtable attributes){
		if (qName.equalsIgnoreCase(EMPRESAWS)) {
			ObjEmpresa = new Empresa();
			ObjEmpresa.setCodEmpresa(attributes.get(CODIGO_EMPRESA).toString());
			ObjEmpresa.setEmpresa(attributes.get(NOMBRE_EMPRESA).toString());
			if (empresas != null) {
				empresas.add(ObjEmpresa);
			}
		} else if (qName.equalsIgnoreCase(FUNDOWS)) {
			Obj = new Fundo();
			Obj.setCodFundo(attributes.get(CODIGO_FUNDO).toString());
			Obj.setFundo(attributes.get(NOMBRE_FUNDO).toString());
			if (ObjEmpresa != null) {
				ObjEmpresa.getFundos().add(Obj);
			}
		} else if (qName.equalsIgnoreCase(MODULOWS)) {
			ObjModulo = new Modulo();
			ObjModulo.setCodModulo(attributes.get(CODIGO_MODULO).toString());
			ObjModulo.setModulo(attributes.get(NOMBRE_MODULO).toString());
			if (Obj != null) {
				Obj.getModulos().add(ObjModulo);
			}
		} else if (qName.equalsIgnoreCase(TURNOSWS)) {
			ObjTurno = new Turno();
			ObjTurno.setCod_turno(attributes.get(CODIGO_TURNO).toString());
			ObjTurno.setNombre(attributes.get(NOMBRE_TURNO).toString());
			if (ObjModulo != null) {
				ObjModulo.getTurnos().add(ObjTurno);
			}
		} else if (qName.equalsIgnoreCase(TURNOS)) {
			if (ObjModulo != null) {
				ObjModulo.setTurnos(new ArrayList<Turno>());
			}
		} else if (qName.equalsIgnoreCase(MODULOS)) {
			if (Obj != null) {
				Obj.setModulos(new ArrayList<Modulo>());
			}
		} else if (qName.equalsIgnoreCase(EMPRESAS)) {
				empresas = new ArrayList<Empresa>();
		/*	
		} else if (qName.equalsIgnoreCase(FUNDOS)) {
				fundos = new ArrayList<Fundo>();*/
		} else if (qName.equalsIgnoreCase(RESPONSE)) {
			user = new Usuario();
			user.setCodigo(attributes.get(CODIGO).toString());
			user.setNombre(attributes.get(SUPERVISOR).toString());
			user.setDescripcion(attributes.get(FULLNAME).toString());
			user.setFlagautoloadmaster(attributes.get(FLAGAUTOLOADMASTER)
					.toString());
			user.setFlagautocleandata(attributes.get(FLAGAUTOCLEANDATA)
					.toString());
			user.setFlagautocleanlastdata(attributes.get(FLAGCLEANLASTDATA)
					.toString());
			user.setEmpresa(attributes.get(EMPRESA)
					.toString());
		}
	}

	public UserContext getContext() {
		
		Log.d("INFOOO", user.getNombre());
		
		return new UserContext(empresas, user);
	}
}

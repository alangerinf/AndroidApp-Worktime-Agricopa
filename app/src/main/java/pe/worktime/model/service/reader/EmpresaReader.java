package pe.worktime.model.service.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import pe.worktime.model.entity.Empresa;

public class EmpresaReader extends AbstractReaderXML {

	private final String LISTA = "Master_EmpresaResponse";
	private final String ENTITY = "EmpresaWS";
	private final String CODIGO = "Codigo";
	private final String NOMBRE = "Nombre";
	private List<Empresa> empresas;
	private Empresa Obj;

	public EmpresaReader() {
		empresas = null;
		Obj = null;
	}

	@SuppressWarnings("rawtypes")
	public void startElement(String qName, Hashtable attributes) {
		System.out.println("ingreso");
		if (qName.equalsIgnoreCase(ENTITY)) {
			Obj = new Empresa();
			Obj.setCodEmpresa(attributes.get(CODIGO).toString());
			Obj.setEmpresa(attributes.get(NOMBRE).toString());
			if (empresas != null) {
				empresas.add(Obj);
			}
		} else if (qName.equalsIgnoreCase(LISTA)) {
			empresas = new ArrayList<Empresa>();
		}
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}
}

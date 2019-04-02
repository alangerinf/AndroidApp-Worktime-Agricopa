package pe.worktime.model.service.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pe.worktime.model.entity.Grupo;

public class GrupoReader extends AbstractReaderXML {

	private final String LISTA = "Master_GrupoResponse";
	private final String ENTITY = "GrupoWS";
	private final String CODIGO = "Codigo";
	private final String NOMBRE = "Nombre";

	private List<Grupo> grupos;
	private Grupo Obj;

	public GrupoReader() {
		grupos = null;
		Obj = null;
	}

	@SuppressWarnings("rawtypes")
	public void startElement(String qName, Hashtable attributes) {
		if (qName.equalsIgnoreCase(ENTITY)) {
			Obj = new Grupo();

			Obj.setCodGrupo(attributes.get(CODIGO).toString());
			Obj.setGrupo(attributes.get(NOMBRE).toString());
						
			if (grupos != null) {
				grupos.add(Obj);
			}

		} else if (qName.equalsIgnoreCase(LISTA)) {
			grupos = new ArrayList<Grupo>();
		}
	}

	public List<Grupo> getgrupos() {
		return grupos;
	}
}
	
	
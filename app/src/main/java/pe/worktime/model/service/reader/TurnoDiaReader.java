package pe.worktime.model.service.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pe.worktime.model.entity.TurnoDia;

public class TurnoDiaReader extends AbstractReaderXML {

	private final String LISTA = "Master_Turno_DiaResponse";
	private final String ENTITY = "TurnoDiaWS";
	private final String CODIGO = "Codigo";
	private final String NOMBRE = "Nombre";
	private final String INICIO = "Inicio";
	private final String FIN = "Fin";

	private List<TurnoDia> turnosDias;
	private TurnoDia Obj;

	public TurnoDiaReader() {
		turnosDias = null;
		Obj = null;
	}

	@SuppressWarnings("rawtypes")
	public void startElement(String qName, Hashtable attributes) {
		if (qName.equalsIgnoreCase(ENTITY)) {
			Obj = new TurnoDia();	
			Obj.setCodigo(attributes.get(CODIGO).toString());
			Obj.setNombre(attributes.get(NOMBRE).toString());
			Obj.setInicio(attributes.get(INICIO).toString());
			Obj.setFin(attributes.get(FIN).toString());
			if (turnosDias != null) {
				turnosDias.add(Obj);
			}
		} else if (qName.equalsIgnoreCase(LISTA)) {
			turnosDias = new ArrayList<TurnoDia>();
		}
	}

	public List<TurnoDia> getTurnoDias() {
		return turnosDias;
	}
}

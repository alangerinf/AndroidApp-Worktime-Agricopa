package pe.worktime.model.service.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pe.worktime.model.entity.ConsumidorIndirecto;

public class ConsumidorIndirectoReader extends AbstractReaderXML {

	private final String LISTA = "Master_Consumidor_IndirectoResponse";
	private final String ENTITY = "ConsumidorIndirectoWS";
	private final String CODIGO = "Codigo";
	private final String NOMBRE = "Nombre";

	private List<ConsumidorIndirecto> consumidoresIndirectos;
	private ConsumidorIndirecto Obj;

	public ConsumidorIndirectoReader() {
		consumidoresIndirectos = null;
		Obj = null;
	}

	@SuppressWarnings("rawtypes")
	public void startElement(String qName, Hashtable attributes) {
		if (qName.equalsIgnoreCase(ENTITY)) {
			Obj = new ConsumidorIndirecto();

			Obj.setCodConsumidor(attributes.get(CODIGO).toString());
			Obj.setDescripcion(attributes.get(NOMBRE).toString());
						
			if (consumidoresIndirectos != null) {
				consumidoresIndirectos.add(Obj);
			}

		} else if (qName.equalsIgnoreCase(LISTA)) {
			consumidoresIndirectos = new ArrayList<ConsumidorIndirecto>();
		}
	}

	public List<ConsumidorIndirecto> getConsumidoresIndirectos() {
		return consumidoresIndirectos;
	}
}
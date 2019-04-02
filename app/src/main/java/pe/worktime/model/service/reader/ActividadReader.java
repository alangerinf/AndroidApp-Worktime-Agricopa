package pe.worktime.model.service.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pe.worktime.model.entity.Actividad;

public class ActividadReader extends AbstractReaderXML {

	private final String LISTA = "Master_ActividadResponse";
	private final String ENTITY = "ActividadWS";
	private final String CODIGO = "Codigo";
	private final String NOMBRE = "Nombre";
	private final String HORAS = "Horas";
	private final String TIPO = "Tipo";
	private final String CULTIVO_ID = "Cultivo_Id";
	private final String ASISTENCIA_B = "Asistencia_b";

	private List<Actividad> actividades;
	private Actividad Obj;

	public ActividadReader() {
		actividades = null;
		Obj = null;
	}

	@SuppressWarnings("rawtypes")
	public void startElement(String qName, Hashtable attributes) {
		if (qName.equalsIgnoreCase(ENTITY)) {
			Obj = new Actividad();

			Obj.setCodActividad(attributes.get(CODIGO).toString());
			Obj.setActividad(attributes.get(NOMBRE).toString());
			Obj.setTipo(attributes.get(TIPO).toString());
			Obj.setHoras(Double.parseDouble(attributes.get(HORAS).toString()));
			Obj.setCodCultivo(Integer.parseInt(attributes.get(CULTIVO_ID).toString()));
			Obj.setAsitencia_b(Integer.parseInt(attributes.get(ASISTENCIA_B).toString()));
				
			try {
				Obj.setHoras(Double.parseDouble(attributes.get(HORAS)
						.toString()));
			} catch (Exception e) {
				Obj.setHoras(-1);
			}
			
			if (actividades != null) {
				actividades.add(Obj);
			}

		} else if (qName.equalsIgnoreCase(LISTA)) {
			actividades = new ArrayList<Actividad>();
		}
	}

	public List<Actividad> getActividades() {
		return actividades;
	}
}

package pe.worktime.model.service.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pe.worktime.model.entity.Personal;

public class PersonalReader extends AbstractReaderXML {

    private final String LISTA = "Master_TrabajadorResponse";
    private final String ENTITY = "TrabajadorWS";
    private final String CODIGO = "DNI";
    private final String NOMBRE = "NombreApellido";

    private List<Personal> personal;
    private Personal Obj;

    public PersonalReader() {
        personal = null;
        Obj = null;
    }

    @Override
    public void startElement(String qName, Hashtable attributes) {
        if (qName.equalsIgnoreCase(ENTITY)) {
            Obj = new Personal();

            Obj.setDni(attributes.get(CODIGO).toString());
            Obj.setNombreApellido(attributes.get(NOMBRE).toString());



            if (personal != null) {
                personal.add(Obj);
            }

        } else if (qName.equalsIgnoreCase(LISTA)) {
            personal = new ArrayList<Personal>();
        }
    }

    public List<Personal> getPersonal() {
        return personal;
    }
}

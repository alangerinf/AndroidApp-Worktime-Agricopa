package pe.worktime.model.service.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pe.worktime.model.entity.Cultivo;

public class CultivoReader extends AbstractReaderXML {

    private final String LISTA = "Master_CultivoResponse";
    private final String ENTITY = "CultivoWS";
    private final String CODIGO = "Codigo";
    private final String NOMBRE = "Nombre";
    private final String FCODIGO = "FCodigo";

    private List<Cultivo> cultivo;
    private Cultivo Obj;

    public CultivoReader() {
        cultivo = null;
        Obj = null;
    }

    @Override
    public void startElement(String qName, Hashtable attributes) {
        if (qName.equalsIgnoreCase(ENTITY)) {
            Obj = new Cultivo();

            Obj.setCodCultivo(attributes.get(CODIGO).toString());
            Obj.setCultivo(attributes.get(NOMBRE).toString());
            Obj.setFcodCultivo(attributes.get(FCODIGO).toString());

            if (cultivo != null) {
                cultivo.add(Obj);
            }

        } else if (qName.equalsIgnoreCase(LISTA)) {
            cultivo = new ArrayList<Cultivo>();
        }
    }

    public List<Cultivo> getCultivo() {
        return cultivo;
    }
}

package pe.worktime.model.service.delegate;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pe.worktime.model.entity.Cultivo;
import pe.worktime.model.entity.Personal;
import pe.worktime.model.service.WSAbstractSync;
import pe.worktime.model.service.reader.CultivoReader;
import pe.worktime.model.service.reader.PersonalReader;
import pe.worktime.model.service.util.ConexionManager;
import pe.worktime.model.service.writer.AbstractWriterXML;

public class WSPersonal extends WSAbstractSync<Personal> {


    private String RelativeURL = "";
    private String RelativeSoapAction = "Master_Trabajador";

    public WSPersonal() {
    }

    public WSPersonal(String RelativeURL, String RelativeSoapAction) {
        this.RelativeURL = RelativeURL;
        this.RelativeSoapAction = RelativeSoapAction;
    }

    @Override
    public List<Personal> CargaDatos(AbstractWriterXML writer) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        PersonalReader handler = new PersonalReader();
        saxParser.parse(ConexionManager.getHttpResponseSoapAccionPOST( BasicPath+RelativeURL, writer.toXml(),BasicSoapAction+RelativeSoapAction),  handler);
        return handler.getPersonal();
    }
}

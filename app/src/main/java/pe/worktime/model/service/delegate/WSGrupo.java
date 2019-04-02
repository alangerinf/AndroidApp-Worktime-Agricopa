package pe.worktime.model.service.delegate;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pe.worktime.model.entity.Grupo;
import pe.worktime.model.service.WSAbstractSync;
import pe.worktime.model.service.reader.GrupoReader;
import pe.worktime.model.service.util.ConexionManager;
import pe.worktime.model.service.writer.AbstractWriterXML;

public class WSGrupo extends WSAbstractSync<Grupo> {

    private String RelativeURL = "";
    private String RelativeSoapAction = "Master_Grupo";

    public WSGrupo() {
    }
    
    public WSGrupo(String RelativeURL, String RelativeSoapAction) {
        this.RelativeURL = RelativeURL;
        this.RelativeSoapAction = RelativeSoapAction;
    } 

    public List<Grupo> CargaDatos(AbstractWriterXML writer) throws  Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        GrupoReader handler = new GrupoReader();
        saxParser.parse(ConexionManager.getHttpResponseSoapAccionPOST( BasicPath+RelativeURL, writer.toXml(),BasicSoapAction+RelativeSoapAction),  handler);
        return handler.getgrupos();

    }

}

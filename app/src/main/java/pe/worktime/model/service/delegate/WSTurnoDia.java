package pe.worktime.model.service.delegate;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pe.worktime.model.entity.TurnoDia;
import pe.worktime.model.service.WSAbstractSync;
import pe.worktime.model.service.reader.TurnoDiaReader;
import pe.worktime.model.service.util.ConexionManager;
import pe.worktime.model.service.writer.AbstractWriterXML;

public class WSTurnoDia extends WSAbstractSync<TurnoDia> {

    private String RelativeURL = "";
    private String RelativeSoapAction = "Master_Turno_Dia";

    public WSTurnoDia() {
    }
    
    public WSTurnoDia(String RelativeURL, String RelativeSoapAction) {
        this.RelativeURL = RelativeURL;
        this.RelativeSoapAction = RelativeSoapAction;
    } 

    public List<TurnoDia> CargaDatos(AbstractWriterXML writer) throws  Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        TurnoDiaReader handler = new TurnoDiaReader();
        saxParser.parse(ConexionManager.getHttpResponseSoapAccionPOST( BasicPath+RelativeURL, writer.toXml(),BasicSoapAction+RelativeSoapAction),  handler);
        return handler.getTurnoDias();

    }
}

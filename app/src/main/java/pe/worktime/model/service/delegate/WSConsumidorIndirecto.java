package pe.worktime.model.service.delegate;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.service.WSAbstractSync;
import pe.worktime.model.service.reader.ConsumidorIndirectoReader;
import pe.worktime.model.service.util.ConexionManager;
import pe.worktime.model.service.writer.AbstractWriterXML;

public class WSConsumidorIndirecto extends WSAbstractSync<ConsumidorIndirecto> {

    private String RelativeURL = "";
    private String RelativeSoapAction = "Master_Consumidor_Indirecto";

    public WSConsumidorIndirecto() {
    }
    
    public WSConsumidorIndirecto(String RelativeURL, String RelativeSoapAction) {
        this.RelativeURL = RelativeURL;
        this.RelativeSoapAction = RelativeSoapAction;
    } 

    public List<ConsumidorIndirecto> CargaDatos(AbstractWriterXML writer) throws  Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        ConsumidorIndirectoReader handler = new ConsumidorIndirectoReader();
        saxParser.parse(ConexionManager.getHttpResponseSoapAccionPOST( BasicPath+RelativeURL, writer.toXml(),BasicSoapAction+RelativeSoapAction),  handler);
        return handler.getConsumidoresIndirectos();

    }
}

package pe.worktime.model.service.delegate;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.util.ResWS;
import pe.worktime.model.service.WSAbstractSync;
import pe.worktime.model.service.reader.HorasConsumidorReader;
import pe.worktime.model.service.reader.ResWSReader;
import pe.worktime.model.service.util.ConexionManager;
import pe.worktime.model.service.writer.AbstractWriterXML;

public class WSHorasConsumidor extends WSAbstractSync<HorasConsumidor> {

    private String RelativeURL = "";
    private String RelativeSoapAction = "Master_Horas_Consumidor";
    private String RelativeSoapAction2 = "Reg_Horas_Consumidor";

    public WSHorasConsumidor() {
    }
    
    public WSHorasConsumidor(String RelativeURL, String RelativeSoapAction) {
        this.RelativeURL = RelativeURL;
        this.RelativeSoapAction = RelativeSoapAction;
    } 

    public List<HorasConsumidor> CargaDatos(AbstractWriterXML writer) throws  Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        HorasConsumidorReader handler = new HorasConsumidorReader();
        saxParser.parse(ConexionManager.getHttpResponseSoapAccionPOST( BasicPath+RelativeURL, writer.toXml(),BasicSoapAction+RelativeSoapAction),  handler);
        return handler.getHorasConsumidor();
    }

    public List<ResWS> RegDatos(AbstractWriterXML writer) throws  Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();        
        ResWSReader handler = new ResWSReader();
        saxParser.parse(ConexionManager.getHttpResponseSoapAccionPOST( BasicPath+RelativeURL, writer.toXml(),BasicSoapAction+RelativeSoapAction2),  handler);
        return handler.getResults();
    }
    
}

package pe.worktime.model.service.delegate;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pe.worktime.model.entity.Productividad;
import pe.worktime.model.entity.util.ResWS;
import pe.worktime.model.service.WSAbstractSync;
import pe.worktime.model.service.reader.ProductividadReader;
import pe.worktime.model.service.reader.ResWSReader;
import pe.worktime.model.service.util.ConexionManager;
import pe.worktime.model.service.writer.AbstractWriterXML;

public class WSProductividad extends WSAbstractSync<Productividad> {

    private String RelativeURL = "";
    @SuppressWarnings("unused")
	private String RelativeSoapAction = "Master_Productividad";
    private String RelativeSoapAction2 = "Reg_Productividad";

    public WSProductividad() {
    }
    
    public WSProductividad(String RelativeURL, String RelativeSoapAction) {
        this.RelativeURL = RelativeURL;
        this.RelativeSoapAction = RelativeSoapAction;
    } 

    public List<Productividad> CargaDatos(AbstractWriterXML writer) throws  Exception {
    	return null;
    }

    /*
    public List<Productividad> CargaDatos(AbstractWriterXML writer) throws  Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        ProductividadReader handler = new ProductividadReader();
        saxParser.parse(ConexionManager.getHttpResponseSoapAccionPOST( BasicPath+RelativeURL, writer.toXml(),BasicSoapAction+RelativeSoapAction),  handler);
        return handler.getProductividad();
    }
    */

    public List<ResWS> RegDatos(AbstractWriterXML writer) throws  Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();        
        ResWSReader handler = new ResWSReader();
        saxParser.parse(ConexionManager.getHttpResponseSoapAccionPOST( BasicPath+RelativeURL, writer.toXml(),BasicSoapAction+RelativeSoapAction2),  handler);
        return handler.getResults();
    }
    
}

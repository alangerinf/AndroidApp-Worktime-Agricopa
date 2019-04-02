package pe.worktime.model.service.delegate;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pe.worktime.model.entity.util.UserContext;
import pe.worktime.model.service.WSAbstractSync;
import pe.worktime.model.service.reader.LoginReader;
import pe.worktime.model.service.util.ConexionManager;
import pe.worktime.model.service.writer.AbstractWriterXML;

public class WSLogin extends WSAbstractSync<UserContext> {

    private String RelativeURL = "";
    private String RelativeSoapAction = "Autenticar";

    public WSLogin() {
    }
    
    public WSLogin(String RelativeURL, String RelativeSoapAction) {
        this.RelativeURL = RelativeURL;
        this.RelativeSoapAction = RelativeSoapAction;
    } 

    public List<UserContext> CargaDatos(AbstractWriterXML writer) throws  Exception {
        return null;
    }

    public UserContext login(AbstractWriterXML writer) throws  Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        LoginReader handler = new LoginReader();
        saxParser.parse(ConexionManager.getHttpResponseSoapAccionPOST( BasicPath+RelativeURL, writer.toXml(),BasicSoapAction+RelativeSoapAction),  handler);
        return handler.getContext();
    }

}

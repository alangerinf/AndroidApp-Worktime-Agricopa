package pe.worktime.model.service.delegate;

import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import pe.worktime.model.entity.Empresa;
import pe.worktime.model.service.WSAbstractSync;
import pe.worktime.model.service.reader.EmpresaReader;
import pe.worktime.model.service.util.ConexionManager;
import pe.worktime.model.service.writer.AbstractWriterXML;

public class WSEmpresa extends WSAbstractSync<Empresa> {

    private String RelativeURL = "";
    private String RelativeSoapAction = "Master_Empresa";

    public WSEmpresa() {
    }
    
    public WSEmpresa(String RelativeURL, String RelativeSoapAction) {
        this.RelativeURL = RelativeURL;
        this.RelativeSoapAction = RelativeSoapAction;
    } 

    public List<Empresa> CargaDatos(AbstractWriterXML writer) throws  Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        EmpresaReader handler = new EmpresaReader();
        saxParser.parse(ConexionManager.getHttpResponseSoapAccionPOST( BasicPath+RelativeURL, writer.toXml(),BasicSoapAction+RelativeSoapAction),  handler);
        //System.out.println("Paso!!!!!");
        return handler.getEmpresas();

    }
}
package pe.worktime.model.service.delegate;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pe.worktime.model.entity.Cultivo;
import pe.worktime.model.service.WSAbstractSync;
import pe.worktime.model.service.reader.CultivoReader;
import pe.worktime.model.service.util.ConexionManager;
import pe.worktime.model.service.writer.AbstractWriterXML;

public class WSCultivo extends WSAbstractSync<Cultivo> {


    private String RelativeURL = "";
    private String RelativeSoapAction = "Master_Cultivo";

    public WSCultivo() {
    }

    public WSCultivo(String RelativeURL, String RelativeSoapAction) {
        this.RelativeURL = RelativeURL;
        this.RelativeSoapAction = RelativeSoapAction;
    }

    @Override
    public List<Cultivo> CargaDatos(AbstractWriterXML writer) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        CultivoReader handler = new CultivoReader();
        saxParser.parse(ConexionManager.getHttpResponseSoapAccionPOST( BasicPath+RelativeURL, writer.toXml(),BasicSoapAction+RelativeSoapAction),  handler);
        return handler.getCultivo();
    }
}
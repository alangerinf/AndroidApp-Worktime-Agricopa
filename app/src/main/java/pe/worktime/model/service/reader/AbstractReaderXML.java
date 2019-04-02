
package pe.worktime.model.service.reader;

import java.util.Hashtable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class AbstractReaderXML extends DefaultHandler  {

    @SuppressWarnings("unchecked")
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        @SuppressWarnings("rawtypes")
		Hashtable hash = new Hashtable();
        for(int i=0;i<attributes.getLength();i++){
            hash.put(attributes.getQName(i), attributes.getValue(i));
        }
        startElement(qName, hash);    
    }

    @SuppressWarnings("rawtypes")
	public abstract void startElement(String qName, Hashtable attributes);

}

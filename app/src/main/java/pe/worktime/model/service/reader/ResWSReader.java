package pe.worktime.model.service.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pe.worktime.model.entity.util.ResWS;

public class ResWSReader extends AbstractReaderXML {

	private final String RES_WS = "ResWS";
	private final String COD = "Cod";
	private final String MSG = "Msg";
	private List<ResWS> results;
	private ResWS Obj;

	public ResWSReader() {
		results = new ArrayList<ResWS>();
		Obj = null;
	}

	@SuppressWarnings("rawtypes")
	public void startElement(String qName, Hashtable attributes) {
		if (qName.equalsIgnoreCase(RES_WS)) {
			Obj = new ResWS(attributes.get(COD).toString(), attributes.get(MSG)
					.toString());
			if (results != null) {
				results.add(Obj);
			}
		}
	}

	public List<ResWS> getResults() {
		return results;
	}
}

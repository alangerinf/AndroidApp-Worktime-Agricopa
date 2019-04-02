package pe.worktime.model.service.writer;

import pe.worktime.app.Application;
import pe.worktime.model.entity.util.KeyTransac;

public class ActividadWriter extends AbstractWriterXML {

	private KeyTransac key;

	public ActividadWriter(KeyTransac key) {
		this.key = key;
	}
	
	@Override
	protected String writeBodyXML() {

		StringBuffer buffer = new StringBuffer();
		buffer.append("<Master_Actividad xmlns=\"http://tempuri.org/\">");
		{
			buffer.append("<key");
			{
				buffer.append(" Empresa=\"");
				{
					buffer.append(Application.context.getEmpresaController().getSelected().getCodEmpresa());
					buffer.append("\"");
				}
				buffer.append(" Username=\"");
				{
					buffer.append(key.getUser());
					buffer.append("\"");
				}			
				buffer.append(" IMEI=\"");
				{
					buffer.append(key.getImei());
					buffer.append("\"");
				}
			}
			buffer.append("/>");
		}
		buffer.append("</Master_Actividad>");
		return buffer.toString();
	}
}
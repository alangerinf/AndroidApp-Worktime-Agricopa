package pe.worktime.model.service.writer;

import pe.worktime.model.entity.util.KeyTransac;

public class EmpresaWriter extends AbstractWriterXML {

	private KeyTransac key;
	
	public EmpresaWriter(KeyTransac key) {
		this.key = key;
	}
	
	@Override
	protected String writeBodyXML() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<Master_Empresa xmlns=\"http://tempuri.org/\">");
		{
			buffer.append("<key");
			{
				buffer.append(" Empresa=\"");
				{
					buffer.append("0");
					buffer.append("\"");
				}
				buffer.append(" Username=\"");
				{
					buffer.append("0");
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
		buffer.append("</Master_Empresa>");
		return buffer.toString();
	}
}
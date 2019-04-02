package pe.worktime.model.service.writer;

import pe.worktime.app.Application;
import pe.worktime.model.entity.util.Credencial;

public class LoginWriter extends AbstractWriterXML {

	private Credencial credencial;

	public LoginWriter(Credencial credencial) {
		this.credencial = credencial;
	}
	
	@Override
	protected String writeBodyXML() {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<Autenticar xmlns=\"http://tempuri.org/\">");
		{
			buffer.append("<credencial");
			{
				buffer.append(" Empresa=\"");
				{
					buffer.append(Application.context.getEmpresaController().getSelected().getCodEmpresa());
					buffer.append("\"");
				}
				buffer.append(" Username=\"");
				{
					buffer.append(credencial.getUsuario());
					buffer.append("\"");
				}
				buffer.append(" Password=\"");
				{
					buffer.append(credencial.getPassword());
					buffer.append("\"");
				}
				buffer.append(" IMEI=\"");
				{
					buffer.append(credencial.getImei());
					buffer.append("\"");
				}
			}
			buffer.append(" />");
		}
		buffer.append("</Autenticar>");
		return buffer.toString();
	}
}
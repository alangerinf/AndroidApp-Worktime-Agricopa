package pe.worktime.model.service.writer;

import java.util.List;

import pe.worktime.app.Application;
//import HorasConsumidor;
//import HorasConsumidorDetalle;
import pe.worktime.model.entity.Productividad;
import pe.worktime.model.entity.ProductividadDetalle;
import pe.worktime.model.entity.util.KeyTransac;

public class ProductividadWriter extends AbstractWriterXML {

	private KeyTransac key = null;
	private List<Productividad> horas = null;
	private int type;


	public ProductividadWriter(KeyTransac key) {
		this.key = key;
		this.type = 1;
	}

	
	public ProductividadWriter(KeyTransac key, List<Productividad> horas) {
		this.key = key;
		this.horas = horas;
		this.type = 2;
	}

	@Override
	protected String writeBodyXML() {
		switch (type) {
		case 1:
			return LoadMaster();
		case 2:
			return Registrar();
		}
		return "";
	}

	protected String LoadMaster() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<Master_Productividad xmlns=\"http://tempuri.org/\">");
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
		buffer.append("</Master_Productividad>");		
		return buffer.toString();
	}

	protected String Registrar() {		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<Reg_Productividad xmlns=\"http://tempuri.org/\">");
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
			buffer.append("<transaction>");
			{				
				for (Productividad item : horas) {					
					buffer.append("<ProductividadWS ");
					{
						
						buffer.append(getAtribute("CodUser", item.getCodUser()));
						buffer.append(getAtribute("IMEI", item.getIMEI()));
						buffer.append(getAtribute("CodEmpresa", item.getCodEmpresa()));
						buffer.append(getAtribute("CodFundo", item.getCodFundo()));
						buffer.append(getAtribute("CodModulo", item.getCodModulo()));
						buffer.append(getAtribute("CodActvidad", item.getCodActvidad()));
						buffer.append(getAtribute("Fecha", item.getFecha()));
						buffer.append(getAtribute("FechaRegistroMovil", item.getFechaRegistroMovil()));
						buffer.append(getAtribute("CodCultivo", item.getIdCultivo()));
				        
					}
					buffer.append(" >");
					{
						buffer.append("<Detalle>");
						for (ProductividadDetalle subItem : item.getDetalle()) {
							buffer.append("<ProductividadDetalleWS ");
							{	
								buffer.append(getAtribute("DniTrabajador" , subItem.getCodTrabajador() ));
								buffer.append(getAtribute("CodConsumidor", subItem.getCodConsumidor() )); 
								buffer.append(getAtribute("Horas" , subItem.getCantidad() ));
								buffer.append(getAtribute("HoraRegMovil", subItem.getHoraRegMovil() ));								
							}
							buffer.append(" />");
						}
						buffer.append("</Detalle>");
					}
					buffer.append("</ProductividadWS>");
				}
			}			
			buffer.append("</transaction>");
		}
		buffer.append("</Reg_Productividad>");
		return buffer.toString();
	}

}
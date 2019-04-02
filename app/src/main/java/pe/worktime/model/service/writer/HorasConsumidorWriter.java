package pe.worktime.model.service.writer;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.entity.util.KeyTransac;

public class HorasConsumidorWriter extends AbstractWriterXML {

	private KeyTransac key = null;
	private List<HorasConsumidor> horas = null;
	private int type;

	public HorasConsumidorWriter(KeyTransac key) {
		this.key = key;
		this.type = 1;
	}
	
	public HorasConsumidorWriter(KeyTransac key, List<HorasConsumidor> horas) {
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
		buffer.append("<Master_Horas_Consumidor xmlns=\"http://tempuri.org/\">");
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
		buffer.append("</Master_Horas_Consumidor>");
		return buffer.toString();
	}

	protected String Registrar() {		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<Reg_Horas_Consumidor xmlns=\"http://tempuri.org/\">");
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
				for (HorasConsumidor item : horas) {
					buffer.append("<HorasConsumidorWS ");
					{
						buffer.append(getAtribute("CodEmpresa", item.getCodEmpresa()));
						buffer.append(getAtribute("CodFundo", item.getCodFundo()));
						buffer.append(getAtribute("CodModulo", item.getCodModulo()));
						
						buffer.append(getAtribute("CodUser", item.getCodSupervisor()));
						buffer.append(getAtribute("IMEI", item.getImei()));
						buffer.append(getAtribute("CodTurnoDia", item.getCodTurnoDia()));
						buffer.append(getAtribute("CodActvidad", item.getCodActividad()));
						buffer.append(getAtribute("CodPlanilla", item.getCodPlantilla()));
						buffer.append(getAtribute("CodGrupo", item.getCodGrupo()));						
						buffer.append(getAtribute("Fecha", item.getFecha()));
						buffer.append(getAtribute("FechaRegistroMovil", item.getFechaRegistroMovil()));
						buffer.append(getAtribute("tipo_labor", item.getTipolabor()));
						buffer.append(getAtribute("CodCultivo", item.getIdCultivo()));
					}
					buffer.append(" >");
					{
						buffer.append("<Detalle>");
						for (HorasConsumidorDetalle subItem : item.getDetalle()) {
							buffer.append("<HorasConsumidorDetalleWS ");
							{
								buffer.append(getAtribute("DniTrabajador", subItem.getCodTrabajador()));
								buffer.append(getAtribute("CodTurno", subItem.getCodTurno()));
								buffer.append(getAtribute("CodConsumidorIndirecto", subItem.getCodConsumidor()));								
								buffer.append(getAtribute("HoraInicio", subItem.getHoraInicio()));
								buffer.append(getAtribute("HoraFin", subItem.getHoraFin()));
								buffer.append(getAtribute("Horas", subItem.getHoras()));
								buffer.append(getAtribute("HoraInicioMovil", subItem.getHoraInicioMovil()));						
								buffer.append(getAtribute("HoraFinMovil", subItem.getHoraFinMovil()));

							}
							buffer.append(" />");
						}
						buffer.append("</Detalle>");
						
						buffer.append("<Servicio ");
					//	for (HorasConsumidorDetalle subItem : item.getDetalle()) {
						//	buffer.append("<HorasConsumidorServicioWS ");
							{
								buffer.append(getAtribute("CodEmpresa", item.getServicioATerceros().getCodEmpresa()));
								System.out.println("CodEmpresaServicio: " + item.getServicioATerceros().getCodEmpresa());
								buffer.append(getAtribute("CodFundo", item.getServicioATerceros().getCodFundo()));
								System.out.println("CodFundoServicio: " + item.getServicioATerceros().getCodFundo());
								buffer.append(getAtribute("CodModulo", item.getServicioATerceros().getCodModulo()));
								System.out.println("CodModuloServicio: " + item.getServicioATerceros().getCodModulo());
								buffer.append(getAtribute("CodTurno", item.getServicioATerceros().getCodTurno()));
								System.out.println("CodTurnoServicio: " + item.getServicioATerceros().getCodTurno());
								buffer.append(getAtribute("CodActividad", item.getServicioATerceros().getCodActividad()));
								System.out.println("CodActividadServicio: " + item.getServicioATerceros().getCodActividad());
							}
							buffer.append(" />");
						//}
						//buffer.append("</Servicio>");
					}
					buffer.append("</HorasConsumidorWS>");
				}				
			}			
			buffer.append("</transaction>");
		}
		buffer.append("</Reg_Horas_Consumidor>");
		return buffer.toString();
	}

}
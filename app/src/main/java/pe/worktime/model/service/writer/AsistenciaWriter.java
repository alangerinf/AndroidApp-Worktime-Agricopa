package pe.worktime.model.service.writer;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.entity.util.KeyTransac;

public class AsistenciaWriter extends AbstractWriterXML {

	private KeyTransac key = null;
	private List<HorasConsumidor> horas = null;
	private int type;

	public AsistenciaWriter(KeyTransac key) {
		this.key = key;
		this.type = 1;
	}

	public AsistenciaWriter(KeyTransac key, List<HorasConsumidor> horas) {
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
		buffer.append("<Master_Asistencia xmlns=\"http://tempuri.org/\">");
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
		buffer.append("</Master_Asistencia>");
		return buffer.toString();
	}

	protected String Registrar() {		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<Reg_Asistencia xmlns=\"http://tempuri.org/\">");
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
					buffer.append("<AsistenciaWS ");
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
							buffer.append("<AsistenciaDetalleWS ");
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
						

					}
					buffer.append("</AsistenciaWS>");
				}				
			}			
			buffer.append("</transaction>");
		}
		buffer.append("</Reg_Asistencia>");
		return buffer.toString();
	}

}
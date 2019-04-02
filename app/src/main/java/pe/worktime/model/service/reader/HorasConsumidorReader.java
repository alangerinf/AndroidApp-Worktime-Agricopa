package pe.worktime.model.service.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;

public class HorasConsumidorReader extends AbstractReaderXML {

	private final String LISTA = "Master_Horas_ConsumidorResult";
	
	private final String ENTITY = "HorasConsumidorWS";
	private final String ENTITY_COD_USER = "CosUser";
	private final String ENTITY_IMEI = "IMEI";
	private final String ENTITY_COD_TURNO_DIA = "CodTurnoDia";
	private final String ENTITY_COD_ACTIVIDAD = "CodActvidad";
	private final String ENTITY_COD_PLANILLA = "CodPlanilla";
	private final String ENTITY_COD_GRUOI = "CodGrupo";
	private final String ENTITY_FECHA = "Fecha";
	private final String ENTITY_RECHA_REG_MOVIL = "FechaRegistroMovil";
	
	private final String ENTITY_COD_EMPRESA = "CodEmpresa";
	private final String ENTITY_COD_FUNDO = "CodFundo";
	private final String ENTITY_COD_MODULO = "CodModulo";
	private final String ENTITY_COD_CULTIVO = "CodCultivo";
	
	private final String SUB_ENTITY = "HorasConsumidorDetalleWS";
	private final String SUB_ENTITY_DNI_TRABAJADOR = "DniTrabajador";
	private final String SUB_ENTITY_COD_TURNO = "CodTurno";	
	private final String SUB_ENTITY_HORA_INICIO = "HoraInicio";
	private final String SUB_ENTITY_HORA_FIN = "HoraFin";
	private final String SUB_ENTITY_HORAS = "Horas";
	private final String SUB_ENTITY_HORA_INICIO_MOVIL = "HoraInicioMovil";
	private final String SUB_ENTITY_HORA_FIN_MOVIL = "HoraFinMovil";
	private final String SUB_ENTITY_HORA_DESCANSO = "horasDescanso";


	private List<HorasConsumidor> lst;
	private HorasConsumidor Obj;
	private HorasConsumidorDetalle SubObj;
	
	public HorasConsumidorReader() {
		lst = null;
		Obj = null;
		SubObj = null;
	}

	@SuppressWarnings("rawtypes")
	public void startElement(String qName, Hashtable attributes) {
		if (qName.equalsIgnoreCase(ENTITY)) {
			
			Obj = new HorasConsumidor();

			Obj.setCodSupervisor(attributes.get(ENTITY_COD_USER).toString());			
			Obj.setImei(attributes.get(ENTITY_IMEI).toString());
			Obj.setCodTurnoDia(attributes.get(ENTITY_COD_TURNO_DIA).toString());
			Obj.setCodActividad(attributes.get(ENTITY_COD_ACTIVIDAD).toString());
			try { //No Posee Planilla
				Obj.setCodPlantilla(attributes.get(ENTITY_COD_PLANILLA).toString());
			} catch (Exception e) {
				Obj.setCodPlantilla("");
			}			
			
			Obj.setCodGrupo(attributes.get(ENTITY_COD_GRUOI).toString());
			Obj.setFecha(attributes.get(ENTITY_FECHA).toString());
			Obj.setFechaRegistroMovil(attributes.get(ENTITY_RECHA_REG_MOVIL).toString());		
			
			Obj.setCodEmpresa(attributes.get(ENTITY_COD_EMPRESA).toString());
			Obj.setCodFundo(attributes.get(ENTITY_COD_FUNDO).toString());
			Obj.setCodModulo(attributes.get(ENTITY_COD_MODULO).toString());
			Obj.setIdCultivo(attributes.get(ENTITY_COD_CULTIVO).toString());
			
			
			Obj.setDetalle(new ArrayList<HorasConsumidorDetalle>());
			
			if (lst != null) {
				lst.add(Obj);
			}

		}
		else if(qName.equalsIgnoreCase(SUB_ENTITY)){
			SubObj = new HorasConsumidorDetalle();
			
			SubObj.setCodTrabajador(attributes.get(SUB_ENTITY_DNI_TRABAJADOR).toString());
			SubObj.setCodTurno(attributes.get(SUB_ENTITY_COD_TURNO).toString());
			SubObj.setHoraInicio(attributes.get(SUB_ENTITY_HORA_INICIO).toString());
			SubObj.setHoraFinOk(attributes.get(SUB_ENTITY_HORA_FIN).toString());
			try {
				SubObj.setHoras(Integer.parseInt(attributes.get(SUB_ENTITY_HORAS).toString()));
			} catch (Exception e) {
				SubObj.setHoras(-1);
			}
			SubObj.setHoraInicioMovil(attributes.get(SUB_ENTITY_HORA_INICIO_MOVIL).toString());
			SubObj.setHoraFinMovil(attributes.get(SUB_ENTITY_HORA_FIN_MOVIL).toString());
			SubObj.setHoras_descanso(Integer.parseInt(attributes.get(SUB_ENTITY_HORA_DESCANSO).toString()));
			
			if (Obj != null) {
				Obj.getDetalle().add(SubObj);
			}
		}else if (qName.equalsIgnoreCase(LISTA)) {
			lst = new ArrayList<HorasConsumidor>();
		}
	}

	public List<HorasConsumidor> getHorasConsumidor() {
		return lst;
	}
}
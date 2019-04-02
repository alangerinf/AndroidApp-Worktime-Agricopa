package pe.worktime.model.entity.test;

import java.util.List;

import android.util.Log;

import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.entity.test.util.AbstractTest;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.entity.util.ResWS;

public class HorasConsumidorTest extends AbstractTest {

	@Override
	public void run() {
		try {			
			KeyTransac key = new KeyTransac("0001", "00", "123456789");
			List<HorasConsumidor> lst =  ws.getHorasConsumidor(key);
			Log.d("INFO", "CNT: "+lst.size());
			Log.d("INFO", "DETA: "+lst.get(0).getDetalle().size());	
			rs.clearHorasConsumidor(User);			
			for (HorasConsumidor item : lst) {
				rs.addOrUpdateHorasConsumidor(User, item);
			}
			for (HorasConsumidor item : lst) {
				rs.addOrUpdateHorasConsumidor(User, item);
			}			
			//Log.d("IMEI", lst.get(0).getImei());			
			List<HorasConsumidor> lst2 = rs.getHorasConsumidor(User);			
			compareListHorasConsumidor(lst,lst2);
			{
				//Log.d("IMEI..", lst2.get(0).getImei());				
				//lst2.get(0).setCodPlantilla("");
				//lst2.get(0).getDetalle().get(0).setHoraFinMovil("2012-05-31 00:00:00.000");
				lst2.get(0).getDetalle().get(0).setHoras(8);
				HorasConsumidorDetalle deta = new HorasConsumidorDetalle();
				deta.setCodConsumidor("c001");
				deta.setCodTrabajador("85632145");
				deta.setCodTurno("t001");
				deta.setHoraFin("");
				deta.setHoraFinMovil("");
				deta.setHoraInicio("31/05/2012 5:05:40");
				deta.setHoraInicioMovil("31/05/2012 5:05:40");
				deta.setHoras(-1);
				
				lst2.get(0).getDetalle().add(deta);
				List<ResWS> rsult = ws.regHorasConsumidor(key, lst2);
				Log.d("INFO", "CNT: "+rsult.size());
				for (ResWS resWS : rsult) {
					Log.d("ResWS", "COD: "+resWS.getCod()+" MSG: "+resWS.getMsg());
				}
			}
			Log.d("INFO TEST ", "TEST OK "+this.getClass().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void compareListHorasConsumidor(List<HorasConsumidor> src, List<HorasConsumidor> tar) throws Exception{
		Log.d("SIZE: ",src.size()+" == "+tar.size() );
		
		if(src.size() !=tar.size()) throw new Exception("No Equals Size");
	    for (int i = 0; i < src.size(); i++) {
	    	compareHorasConsumidor(src.get(i), tar.get(i));
	    }
	}
	
	protected void compareHorasConsumidor(HorasConsumidor src, HorasConsumidor tar) throws Exception{
		if(!src.getCodActividad().equalsIgnoreCase(tar.getCodActividad())) throw new Exception("No Equals CodActividad");
		if(!src.getCodGrupo().equalsIgnoreCase(tar.getCodGrupo())) throw new Exception("No Equals CodGrupo");
		//if(!src.getCodigo().equalsIgnoreCase(tar.getGrupo())) throw new Exception("No Equals Grupo");
		if(!src.getCodPlantilla().equalsIgnoreCase(tar.getCodPlantilla())) throw new Exception("No Equals CodPlantilla");
		if(!src.getCodSupervisor().equalsIgnoreCase(tar.getCodSupervisor())) throw new Exception("No Equals CodSupervisor");		
		if(!src.getCodTurnoDia().equalsIgnoreCase(tar.getCodTurnoDia())) throw new Exception("No Equals CodTurnoDia");
		if(!src.getFecha().equalsIgnoreCase(tar.getFecha())) throw new Exception("No Equals Fecha");
		if(!src.getFechaRegistroMovil().equalsIgnoreCase(tar.getFechaRegistroMovil())) throw new Exception("No Equals FechaRegistroMovil");
		if(!src.getImei().equalsIgnoreCase(tar.getImei())) throw new Exception("No Equals Imei");		
		for (int i = 0; i < src.getDetalle().size(); i++) {
			compareHorasConsumidorDetalle(src.getDetalle().get(i),tar.getDetalle().get(i));
		}
	}
	
	protected void compareHorasConsumidorDetalle(HorasConsumidorDetalle src, HorasConsumidorDetalle tar) throws Exception{
		if(!src.getCodConsumidor().equalsIgnoreCase(tar.getCodConsumidor())) throw new Exception("No Equals CodConsumidor");
		if(!src.getCodTrabajador().equalsIgnoreCase(tar.getCodTrabajador())) throw new Exception("No Equals CodTrabajador");
		if(!src.getCodTurno().equalsIgnoreCase(tar.getCodTurno())) throw new Exception("No Equals CodTurno");
		if(!src.getHoraFin().equalsIgnoreCase(tar.getHoraFin())) throw new Exception("No Equals HoraFin");
		if(!src.getHoraFinMovil().equalsIgnoreCase(tar.getHoraFinMovil())) throw new Exception("No Equals HoraFinMovil");
		if(!src.getHoraInicio().equalsIgnoreCase(tar.getHoraInicio())) throw new Exception("No Equals HoraInicio");
		if(!src.getHoraInicioMovil().equalsIgnoreCase(tar.getHoraInicioMovil())) throw new Exception("No Equals HoraInicioMovil");
		if(src.getHoras()!=tar.getHoras()) throw new Exception("No Equals Horas");	
	}
}
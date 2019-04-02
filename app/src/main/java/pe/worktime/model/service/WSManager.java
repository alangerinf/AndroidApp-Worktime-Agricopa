package pe.worktime.model.service;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.model.PersistenceModel;
import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.entity.Cultivo;
import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.Grupo;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.Personal;
import pe.worktime.model.entity.Productividad;
import pe.worktime.model.entity.TurnoDia;
import pe.worktime.model.entity.util.Credencial;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.entity.util.ResWS;
import pe.worktime.model.entity.util.UserContext;
import pe.worktime.model.service.delegate.WSActividad;
import pe.worktime.model.service.delegate.WSAsistencia;
import pe.worktime.model.service.delegate.WSConsumidorIndirecto;
import pe.worktime.model.service.delegate.WSCultivo;
import pe.worktime.model.service.delegate.WSEmpresa;
import pe.worktime.model.service.delegate.WSGrupo;
import pe.worktime.model.service.delegate.WSHorasConsumidor;
import pe.worktime.model.service.delegate.WSLogin;
import pe.worktime.model.service.delegate.WSPersonal;
import pe.worktime.model.service.delegate.WSProductividad;
import pe.worktime.model.service.delegate.WSTurnoDia;
import pe.worktime.model.service.writer.AbstractWriterXML;
import pe.worktime.model.service.writer.ActividadWriter;
import pe.worktime.model.service.writer.AsistenciaWriter;
import pe.worktime.model.service.writer.ConsumidorIndirectoWriter;
import pe.worktime.model.service.writer.CultivoWriter;
import pe.worktime.model.service.writer.EmpresaWriter;
import pe.worktime.model.service.writer.GrupoWriter;
import pe.worktime.model.service.writer.HorasConsumidorWriter;
import pe.worktime.model.service.writer.LoginWriter;
import pe.worktime.model.service.writer.PersonalWriter;
import pe.worktime.model.service.writer.ProductividadWriter;
import pe.worktime.model.service.writer.TurnoDiaWriter;

public class WSManager extends PersistenceModel {
   
    /* SECURITY */
   
	public UserContext getUser(Credencial credencial) throws Exception {
		credencial.setImei(Application.context.IMEI);
        AbstractWriterXML writer = new LoginWriter(credencial);
        WSLogin service = new WSLogin();
        return service.login(writer);
    }
	
    /* LOAD MASTER*/ 
   
    public List<Grupo> getGrupos(KeyTransac key) throws Exception {
    	key.setImei(Application.context.IMEI);
        AbstractWriterXML writer = new GrupoWriter(key);
        WSGrupo service = new WSGrupo();
        return service.CargaDatos(writer);
    }
    
    public List<Actividad> getActividades(KeyTransac key) throws Exception {
    	key.setImei(Application.context.IMEI);
        AbstractWriterXML writer = new ActividadWriter(key);
        WSActividad service = new WSActividad();
        return service.CargaDatos(writer);
    }

    public List<Cultivo> getCultivo(KeyTransac key) throws Exception {
        key.setImei(Application.context.IMEI);
        AbstractWriterXML writer = new CultivoWriter(key);
        WSCultivo service = new WSCultivo();
        return service.CargaDatos(writer);
    }


    public List<Personal> getPersonal(KeyTransac key) throws Exception {
        key.setImei(Application.context.IMEI);
        AbstractWriterXML writer = new PersonalWriter(key);
        WSPersonal service = new WSPersonal();
        return service.CargaDatos(writer);
    }

    
    public List<TurnoDia> getTurnodDias(KeyTransac key) throws Exception {
    	key.setImei(Application.context.IMEI);
        AbstractWriterXML writer = new TurnoDiaWriter(key);
        WSTurnoDia service = new WSTurnoDia();
        return service.CargaDatos(writer);
    }
    
    public List<ConsumidorIndirecto> getConsumidoresIndirectos(KeyTransac key) throws Exception {
    	key.setImei(Application.context.IMEI);
        AbstractWriterXML writer = new ConsumidorIndirectoWriter(key);
        WSConsumidorIndirecto service = new WSConsumidorIndirecto();
        return service.CargaDatos(writer);
    }
        
    public List<HorasConsumidor> getHorasConsumidor(KeyTransac key) throws Exception {
    	key.setImei(Application.context.IMEI);
        AbstractWriterXML writer = new HorasConsumidorWriter(key);
        WSHorasConsumidor service = new WSHorasConsumidor();
        return service.CargaDatos(writer);
    }
    
    public List<Empresa> getEmpresa(KeyTransac key) throws Exception {
        key.setImei(Application.context.IMEI);
        AbstractWriterXML writer = new EmpresaWriter(key);
        WSEmpresa service = new WSEmpresa();
        return service.CargaDatos(writer);
    }
    
    
    /* R E G   O P E R A T I O N S */
    
    public List<ResWS> regHorasConsumidor(KeyTransac key, List<HorasConsumidor> registros) throws Exception {
    	key.setImei(Application.context.IMEI);
    	AbstractWriterXML writer = new HorasConsumidorWriter(key, registros);
        WSHorasConsumidor service = new WSHorasConsumidor();
        return service.RegDatos(writer);
    }

    public List<ResWS> regAsistencia(KeyTransac key, List<HorasConsumidor> registros) throws Exception {
        key.setImei(Application.context.IMEI);
        AbstractWriterXML writer = new AsistenciaWriter(key, registros);
        WSAsistencia service = new WSAsistencia();
        return service.RegDatos(writer);
    }


    
    public List<Productividad> getProductividad(KeyTransac key) throws Exception {
        key.setImei(Application.context.IMEI);
    	AbstractWriterXML writer = new ProductividadWriter(key);
        WSProductividad service = new WSProductividad();
        return service.CargaDatos(writer);

        //return null;
    }
    
    public List<ResWS> regProductividad(KeyTransac key, List<Productividad> registros) throws Exception {
    	key.setImei(Application.context.IMEI);
    	AbstractWriterXML writer = new ProductividadWriter(key, registros);
        WSProductividad service = new WSProductividad();
        return service.RegDatos(writer);
    }
    
    
    /*   
    public List<MotivoNoPedido> getMotivosNoPedido(KeyTransac key) throws Exception {
        AbstractWriterXML writer = new MotivoNoPedidoWriter(key);
        WSMotivoNoPedido service = new WSMotivoNoPedido();
        return service.CargaDatos(writer);
    }
    
    public List<Producto> getProductos(KeyTransac key) throws Exception {
        AbstractWriterXML writer = new ProductoWriter(key);
        WSProducto service = new WSProducto();
        return service.CargaDatos(writer);
    }
    
    
    
    public List<ResWS> regNoPedidos(KeyTransac key, List<NoPedido> noPedidos) throws Exception {
        AbstractWriterXML writer = new NoPedidoWriter(key, noPedidos);
        WSNoPedido service = new WSNoPedido();
        return service.RegDatos(writer);
    }
    
    public List<ResWS> regNoCobros(KeyTransac key, List<NoCobro> noCobros) throws Exception {
        AbstractWriterXML writer = new NoCobroWriter(key, noCobros);
        WSNoCobro service = new WSNoCobro();
        return service.RegDatos(writer);
    }
    
    public List<ResWS> regCobros(KeyTransac key, List<Cobro> cobros) throws Exception {
        AbstractWriterXML writer = new CobroWriter(key, cobros);
        WSCobro service = new WSCobro();
        return service.RegDatos(writer);
    }*/
}

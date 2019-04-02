package pe.worktime.model.service;

import java.util.List;

import pe.worktime.model.service.writer.AbstractWriterXML;

public abstract class WSAbstractSync<E> {

    protected static final String BasicPath = "http://wsworktimeagricopa.ibao.pe/Services/MntUsuario.asmx";

    protected static final String BasicSoapAction = "http://tempuri.org/";
    protected static final String ContentLanguage = "es-ES";
    protected static final String ContentType = "text/xml; charset=utf-8";
    //protected static final String DefaultMethod = HttpConnection.POST;
    public abstract List<E> CargaDatos(AbstractWriterXML writer) throws Exception ;    
    
}
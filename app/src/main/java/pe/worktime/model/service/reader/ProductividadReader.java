package pe.worktime.model.service.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pe.worktime.model.entity.Productividad;
import pe.worktime.model.entity.ProductividadDetalle;

/**
 * Created by Administrador on 20/06/2018.
 */

public class ProductividadReader extends AbstractReaderXML {

    private final String LISTA = "Reg_ProductividadResult";

    private final String ENTITY = "ProductividadWS";
    private final String ENTITY_COD_USER = "CodUser";
    private final String ENTITY_IMEI = "IMEI";
    private final String ENTITY_FECHA = "Fecha";
    private final String ENTITY_FECHA_REG_MOVIL = "FechaRegistroMovil";

    private final String ENTITY_COD_EMPRESA = "CodEmpresa";
    private final String ENTITY_COD_FUNDO = "CodFundo";
    private final String ENTITY_COD_MODULO = "CodModulo";
    private final String ENTITY_COD_ACTIVIDAD = "CodActvidad";


    private final String SUB_ENTITY = "ProductividadDetalleWS";
    private final String SUB_ENTITY_COD_TRABAJADOR = "CodTrabajador";
    private final String SUB_ENTITY_COD_CONSUMIDOR = "CodConsumidor";
    private final String SUB_ENTITY_CANTIDAD = "Cantidad";
    private final String SUB_ENTITY_HORA_REG_MOVIL = "HoraRegistroMovil";

    private List<Productividad> lst;
    private Productividad Obj;
    private ProductividadDetalle SubObj;

    public ProductividadReader() {
        lst = null;
        Obj = null;
        SubObj = null;
    }

    public void startElement(String qName, Hashtable attributes) {
        if (qName.equalsIgnoreCase(ENTITY)) {

            Obj = new Productividad();

            Obj.setCodUser(attributes.get(ENTITY_COD_USER).toString());
            Obj.setIMEI(attributes.get(ENTITY_IMEI).toString());
            Obj.setFecha(attributes.get(ENTITY_FECHA).toString());
            Obj.setFechaRegistroMovil(attributes.get(ENTITY_FECHA_REG_MOVIL).toString());

            Obj.setCodEmpresa(attributes.get(ENTITY_COD_EMPRESA).toString());
            Obj.setCodFundo(attributes.get(ENTITY_COD_FUNDO).toString());
            Obj.setCodModulo(attributes.get(ENTITY_COD_MODULO).toString());
            Obj.setCodActvidad(attributes.get(ENTITY_COD_ACTIVIDAD).toString());


            Obj.setDetalle(new ArrayList<ProductividadDetalle>());

            if (lst != null) {
                lst.add(Obj);
            }

        }
        else if(qName.equalsIgnoreCase(SUB_ENTITY)){
            SubObj = new ProductividadDetalle();

            SubObj.setCodTrabajador(attributes.get(SUB_ENTITY_COD_TRABAJADOR).toString());
            SubObj.setCodConsumidor(attributes.get(SUB_ENTITY_COD_CONSUMIDOR).toString());
            SubObj.setCantidad(attributes.get(SUB_ENTITY_CANTIDAD).toString());
            SubObj.setHoraRegMovil(attributes.get(SUB_ENTITY_HORA_REG_MOVIL).toString());

            if (Obj != null) {
                Obj.getDetalle().add(SubObj);
            }
        }else if (qName.equalsIgnoreCase(LISTA)) {
            lst = new ArrayList<Productividad>();
        }
    }

    public List<Productividad> getProductividad() {
        return lst;
    }
}

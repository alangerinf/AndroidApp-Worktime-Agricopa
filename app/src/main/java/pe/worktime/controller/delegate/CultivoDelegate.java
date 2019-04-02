package pe.worktime.controller.delegate;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.model.entity.Cultivo;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.store.parser.util.IFilterRS;

public class CultivoDelegate extends AbstractDelegate {
    public List<Cultivo> getCultivo(int DS, KeyTransac key) throws Exception {
        switch (DS) {
            case ONLY_WS:
                return wsManager.getCultivo(key);
            case ONLY_RS:
                return rsManager.getCultivo(key == null ? Application.context.getUser() : key.getUser());
        }
        throw new Exception("(Cultivo) DS no Reconocido.");
    }

    /* ONLY_RS */
    public void setCultivo(String user, List<Cultivo> cultivo)
            throws Exception {
        rsManager.selListCultivo(user, cultivo);
    }

    public Cultivo getCultivo(String user) throws Exception {
        return (Cultivo) rsManager.getCultivo(user);
    }

    public List<Cultivo> findCultivo(String user, IFilterRS filter) throws Exception {
        return rsManager.getCultivo(user,filter);
    }

    public Cultivo findByCliente(String user, IFilterRS filter) throws Exception {

        List<Cultivo> lst = rsManager.getCultivo(user, filter);
        if (!lst.isEmpty()) {
            return (Cultivo) lst.get(0);
        }
        return null;
    }
}

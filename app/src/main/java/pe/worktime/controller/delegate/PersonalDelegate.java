package pe.worktime.controller.delegate;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.model.entity.Personal;
import pe.worktime.model.entity.util.KeyTransac;
import pe.worktime.model.store.parser.util.IFilterRS;

public class PersonalDelegate extends AbstractDelegate {
    public List<Personal> getPersonal(int DS, KeyTransac key) throws Exception {
        switch (DS) {
            case ONLY_WS:
                return wsManager.getPersonal(key);
            case ONLY_RS:
                return rsManager.getPersonal(key == null ? Application.context.getUser() : key.getUser());
        }
        throw new Exception("(Personal) DS no Reconocido.");
    }

    /* ONLY_RS */
    public void setPersonal(String user, List<Personal> personal)
            throws Exception {
        rsManager.selListPersonal(user, personal);
    }

    public Personal getPersonal(String user) throws Exception {
        return (Personal) rsManager.getPersonal(user);
    }

    public List<Personal> findPersonal(String user, IFilterRS filter) throws Exception {
        return rsManager.getPersonal(user,filter);
    }

    public Personal findByCliente(String user, IFilterRS filter) throws Exception {

        List<Personal> lst = rsManager.getPersonal(user, filter);
        if (!lst.isEmpty()) {
            return (Personal) lst.get(0);
        }
        return null;
    }

    public Personal buscarPersonal(String codTrabajador) throws Exception {
        return rsManager.buscarPersonal(codTrabajador);
    }
}

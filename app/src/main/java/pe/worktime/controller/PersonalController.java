package pe.worktime.controller;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.delegate.PersonalDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.Personal;
import pe.worktime.model.entity.util.KeyTransac;

public class PersonalController extends AbstractController {
    private Personal selected = null;
    private Personal cultivoServida = null;
    private PersonalDelegate delegate = null;

    public PersonalController() {
        delegate = new PersonalDelegate();
    }

    public Personal getSelected() {
        return selected;
    }

    public void setSelected(Personal selected) {
        this.selected = selected;
    }



    @Override
    public void loadMaster() throws Exception {
        List<Personal> lst = null;
        boolean loadedWS = false;
        try {
            KeyTransac key = Application.context.getKeyUser();
            lst = delegate.getPersonal(AbstractDelegate.ONLY_WS, key);
            loadedWS = true;
        } catch (Exception e) {
            e.printStackTrace();
            lst = delegate.getPersonal(AbstractDelegate.ONLY_RS, null);
        }
        if (lst == null) {
            LogController.addError(Application.context.getUser(),"Lista Personal Vacia.");
            throw new Exception("Lista Personal Vacia.");
        } else if (lst.isEmpty()) {
            LogController.addError(Application.context.getUser(),
                    "Lista Personal Vacia.");
            throw new Exception("Lista Personal Vacia.");
        } else if (loadedWS) {
            delegate.setPersonal(Application.context.getUser(), lst);
        }
    }

    @Override
    public void seleccionar(int index) {
        try {
            try {
                selected = delegate.getPersonal(AbstractDelegate.ONLY_RS,null).get(index);
                // selected = ContextTest.ctx.getLst1().get(index);
            } catch (Exception e) {
                LogController.addError(Application.context.getUser(),e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogController.addError(Application.context.getUser(),e.getMessage());
        }
    }

    public List<Personal> listar() {
        try {
            return delegate.getPersonal(AbstractDelegate.ONLY_RS, null);

        } catch (Exception e) {
            e.printStackTrace();
            LogController.addError(Application.context.getUser(),e.getMessage());
        }
        return null;
    }

    @Override
    public void seleccionar(String key) {
        try {
            List<Personal> lst = listar();
            for (Personal item : lst) {
                if (item.getDni().equalsIgnoreCase(key)) {
                    selected = item;
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        selected = null;
    }

    public String getNameOrDefault(String key) {
        try {
            Personal prev = selected;
            seleccionar(key);
            Personal result = selected;
            selected = prev;
            return result.getNombreApellido();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public Personal buscarPersonal(String codTrabajador) {
        try {
            return delegate.buscarPersonal(codTrabajador);
        } catch (Exception e) {
            e.printStackTrace();
            LogController.addError(Application.context.getUser(),
                    e.getMessage());
        }
        return null;
    }
}

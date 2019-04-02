package pe.worktime.controller;

import java.util.ArrayList;
import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.delegate.CultivoDelegate;
import pe.worktime.controller.util.AbstractController;
import pe.worktime.controller.util.AbstractDelegate;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.Cultivo;
import pe.worktime.model.entity.util.KeyTransac;

public class CultivoController extends AbstractController {
    private Cultivo selected = null;
    private Cultivo cultivoServida = null;
    private CultivoDelegate delegate = null;

    public CultivoController() {
        delegate = new CultivoDelegate();
    }

    public Cultivo getSelected() {
        return selected;
    }

    public void setSelected(Cultivo selected) {
        this.selected = selected;
    }



    @Override
    public void loadMaster() throws Exception {
        List<Cultivo> lst = null;
        boolean loadedWS = false;
        try {
            KeyTransac key = Application.context.getKeyUser();
            lst = delegate.getCultivo(AbstractDelegate.ONLY_WS, key);
            loadedWS = true;
        } catch (Exception e) {
            e.printStackTrace();
            lst = delegate.getCultivo(AbstractDelegate.ONLY_RS, null);
        }
        if (lst == null) {
            LogController.addError(Application.context.getUser(),"Lista Cultivo Vacia.");
            throw new Exception("Lista Cultivo Vacia.");
        } else if (lst.isEmpty()) {
            LogController.addError(Application.context.getUser(),
                    "Lista Cultivo Vacia.");
            throw new Exception("Lista Actividad Vacia.");
        } else if (loadedWS) {
            delegate.setCultivo(Application.context.getUser(), lst);
        }
    }

    @Override
    public void seleccionar(int index) {
        try {
            try {
                selected = delegate.getCultivo(AbstractDelegate.ONLY_RS,null).get(index);
                // selected = ContextTest.ctx.getLst1().get(index);
            } catch (Exception e) {
                LogController.addError(Application.context.getUser(),e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogController.addError(Application.context.getUser(),e.getMessage());
        }
    }

    public void seleccionarPorFundo(int index) {
        try {
            selected = listarPorFundo().get(index);

        } catch (Exception e) {
            e.printStackTrace();
            LogController.addError(Application.context.getUser(),e.getMessage());
        }
    }
    public List<Cultivo> listar() {
        try {

            return delegate.getCultivo(AbstractDelegate.ONLY_RS, null);
            // return ContextTest.ctx.getLst1();
        } catch (Exception e) {
            e.printStackTrace();
            LogController.addError(Application.context.getUser(),e.getMessage());
        }
        return null;
    }

    public List<Cultivo> listarPorFundo() {
        try {
            List<Cultivo> lst1 = listar();
            List<Cultivo> lstCultivo = new ArrayList<Cultivo>();
            for (Cultivo itm : lst1) {
                if (Integer.parseInt( itm.getFcodCultivo()) == Integer.parseInt(Application.context.getFundoController().getSelected().getCodFundo())) {
                    lstCultivo.add(itm);
                }
            }
            return lstCultivo;

        } catch (Exception e) {
            e.printStackTrace();
            LogController.addError(Application.context.getUser(),e.getMessage());
        }
        return null;

    }

    @Override
    public void seleccionar(String key) {
        try {
            List<Cultivo> lst = listar();
            for (Cultivo item : lst) {
                if (item.getCodCultivo().equalsIgnoreCase(key)) {
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
            Cultivo prev = selected;
            seleccionar(key);
            Cultivo result = selected;
            selected = prev;
            return result.getCultivo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

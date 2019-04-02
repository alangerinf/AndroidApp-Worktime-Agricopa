/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.worktime.view;

import java.util.Vector;

import pe.worktime.MainActivity.LoadMaestros;

import android.app.Activity;


/**
 *
 * @author Alfa
 */
public class ManagerView {

    public static final String LOGIN = "LOGIN";
    public static final String MENU_PRINCIPAL = "MENU_PRINCIPAL";
    public static final String MENU_CLIENTE = "MENU_CLIENTE";
    public static final String CLIENTE_INFO = "CLIENTE_INFO";
    public static final String CLIENTE_FIND = "CLIENTE_FIND";
    public static final String PRODUCTO_LISTA = "PRODUCTO_LISTA";
    public static final String CLIENTE_NO_ATENCION = "CLIENTE_NO_ATENCION";
    public static final String CLIENTE_COBRO = "CLIENTE_COBRO";
    public static final String CLIENTE_COBRO_MULTIPLE = "CLIENTE_COBRO_MULTIPLE";
    public static final String PEDIDO_ADD_ITEM = "PEDIDO_ADD_ITEM";
    public static final String PEDIDO_EDIT_ITEM = "PEDIDO_EDIT_ITEM";
    public static final String PEDIDO_RESUMEN = "PEDIDO_RESUMEN";
    public static final String SINCRONIZACION_MENU = "SINCRONIZACION_MENU";
    @SuppressWarnings("unused")
	private AbstractUI currentFRM;

    @SuppressWarnings("rawtypes")
	private Vector navegate;

    public Activity actual;
    


    public LoadMaestros buff = null;
    
    public void print(int porc, String msg){
    	if(buff!=null){
    		buff.traceMsg(porc, msg);
    	}
    }

    
    
    @SuppressWarnings("rawtypes")
	public ManagerView() {
        navegate = new Vector();
    }

    public Runnable getInit() {
        return new Runnable() {

            public void run() {
                loadFrm(LOGIN);
            }
        };
    }

    public void loadFrm(String showFrm) {
        /* try {
            if (showFrm.equalsIgnoreCase(LOGIN)) {
                currentFRM = new LoginView();
            } else if (showFrm.equalsIgnoreCase(MENU_PRINCIPAL)) {
//                currentFRM = new ListClienteView(Application.context.getClienteController().listar());
//                currentFRM = new TableClienteView(Application.context.getClienteController().listar());
//                currentFRM = new PrincipalMenuView(Application.context.getClienteController().listar());
                //currentFRM = new FilterClienteView(Application.context.getClienteController().listar());
                //currentFRM = new FilterClienteView(null);
            } else if (showFrm.equalsIgnoreCase(MENU_CLIENTE)) {
                currentFRM = new ClienteMenuView(Application.context.getClienteController().getSelected(), Application.context.getCondicionVentaController().listar(), Application.context.getDocXCobrarController().hasDocsXCobrarByCliente(Application.context.getClienteController().getSelected().getCodigo()));
            } else if (showFrm.equalsIgnoreCase(CLIENTE_INFO)) {
                currentFRM = new ClienteInfoView(Application.context.getClienteController().getSelected());
            } else if (showFrm.equalsIgnoreCase(CLIENTE_NO_ATENCION)) {
                currentFRM = new NoAtencionView(Application.context.getClienteController().getSelected(), Application.context.getMotivoNoPedidoController().listar(), Application.context.getMotivoNoCobroController().listar());
            } else if (showFrm.equalsIgnoreCase(CLIENTE_COBRO)) {
                currentFRM = new CobroClienteView(Application.context.getClienteController().getSelected(), Application.context.getDocXCobrarController().filtrarByCliente(Application.context.getClienteController().getSelected().getCodigo()));
            } else if (showFrm.equalsIgnoreCase(CLIENTE_FIND)) {
                currentFRM = new ClienteFilterView(Application.context.getClienteController().listar());
            } else if (showFrm.equalsIgnoreCase(PRODUCTO_LISTA)) {
                currentFRM = new ProductoFilterView(Application.context.getProductoController().listar());
            } else if (showFrm.equalsIgnoreCase(PEDIDO_ADD_ITEM)) {
                currentFRM = new PedidoAddItemView(Application.context.getProductoController().listar());
            } else if (showFrm.equalsIgnoreCase(PEDIDO_EDIT_ITEM)) {
                currentFRM = new PedidoEditItemView(Application.context.getProductoController().listar(), Application.context.getPedidoController().getSelectedDeta());
            } else if (showFrm.equalsIgnoreCase(PEDIDO_RESUMEN)) {
                currentFRM = new PedidoDetalleView(Application.context.getPedidoController().getSelected());
            } else if (showFrm.equalsIgnoreCase(SINCRONIZACION_MENU)) {
                currentFRM = new SincronizacionMenuView(1, 1, 1, 1);
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            while (true) {
                try {
                    showBack();
                    return;
                } catch (Exception ex) {
                    e.printStackTrace();
                }
            }
        }
        pushNavegate(showFrm);
        try {
            currentFRM.run();
        } catch (Exception x) {
            x.printStackTrace();
            MsgError.ShowDialog("ERROR", x.getMessage());
        }*/
    }

    public void showBack() {
        int i = navegate.size() - 1;
        if (i >= 1) {
            loadFrm((String) navegate.elementAt(i - 1));
            if (navegate.size() > i) {
                navegate.removeElementAt(i);
            }
            navegate.removeElementAt(i - 1);
        } else {
            loadFrm(LOGIN);
            return;
        }
    }

    @SuppressWarnings({ "unused", "unchecked" })
	private void pushNavegate(String showFrm) {
        for (int i = 0; i < navegate.size(); i++) {
            String items = (String) navegate.elementAt(i);
            if (items.equalsIgnoreCase(showFrm)) {
                @SuppressWarnings("rawtypes")
				Vector aux = new Vector();
                for (int j = 0; j <= i; j++) {
                    aux.addElement(navegate.elementAt(j));
                }
                navegate.removeAllElements();
                for (int j = 0; j < aux.size(); j++) {
                    navegate.addElement(aux.elementAt(j));
                }
                return;
            }
        }
        navegate.addElement(showFrm);
    }
}

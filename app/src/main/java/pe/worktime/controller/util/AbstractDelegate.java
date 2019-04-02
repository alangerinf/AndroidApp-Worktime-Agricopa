package pe.worktime.controller.util;

import pe.worktime.model.service.WSManager;
import pe.worktime.model.store.RSManager;

public class AbstractDelegate {

	public static final int ONLY_WS = 1;
    public static final int ONLY_RS = 2;
    protected static final WSManager wsManager = new WSManager();
    protected static final RSManager rsManager = new RSManager();

}

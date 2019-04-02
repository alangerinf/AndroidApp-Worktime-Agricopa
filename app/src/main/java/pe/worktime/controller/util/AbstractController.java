package pe.worktime.controller.util;

//import java.util.Vector;

public abstract class AbstractController {

	protected static final String TIP_SEL = "SEL";
	protected static final String TIP_ADD = "ADD";
	protected static final String TIP_EDIT = "EDIT";
	protected static final String TIP_DEL = "DEL";

	public abstract void loadMaster() throws Exception;

	//public abstract Vector listar();

	public abstract void seleccionar(int index);

	public abstract void seleccionar(String key);

	// protected abstract Object buscar(String key);

	/*protected String getFecha() {
		return Application.context.getFecha();
	}*/

}

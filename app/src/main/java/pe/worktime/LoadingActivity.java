package pe.worktime;

import com.worktime.R;

import pe.worktime.util.AbstractActivity;

public class LoadingActivity extends AbstractActivity {
	
	public static int viewLoad = R.layout.loading;
			
	@Override
	protected void postLoadView() {				
	}
	
	@Override
	protected int getViewId() {
		return viewLoad;
	}
	@Override
	protected String getTextTitle() {
		return "Cargando";
	}
}

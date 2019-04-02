package pe.worktime;

import android.os.Bundle;
import android.util.Log;
//import android.view.View;
import android.widget.EditText;

import com.worktime.R;
//import android.widget.TabHost;

public class AsistenciaTabActivity extends AbstractTabActivity {
	/** Called when the activity is first created. */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_abstract);
		setTabs();
		getTabHost().setCurrentTab(0);
				
	}
	

	@Override
	protected void onResume() {
		super.onResume();
		EditText txtdni = (EditText) getTabHost().getCurrentView().findViewById(R.id.txtDniAutomatico);
		if(txtdni==null)
		{
			txtdni = (EditText) getTabHost().getCurrentView().findViewById(R.id.txtDniManual);
		}
		if(txtdni!=null){
			txtdni.requestFocus();
		}
		Log.d("ERROR","DNI: "+txtdni );		
	}
	
	private void setTabs() {
		if (ContextTest.idxTipoEntrada == 0) {
			addTab("Leer", 0, EntradaManualActivity.class);
			addTab("Detalle", 0,
					ManualAsistenciaActivity.class);
			addTab("Resumen", 0,
					ResumenAsistenciaActivity.class);
		} else if (ContextTest.idxTipoEntrada == 1){
			addTab("Leer", 0, EntradaAutomaticaActivity.class);
			addTab("Detalle", 0,
					ManualAsistenciaActivity.class);
			addTab("Resumen", 0,
					ResumenAsistenciaActivity.class);
		}		
	}
}
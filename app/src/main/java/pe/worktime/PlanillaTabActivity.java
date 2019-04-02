package pe.worktime;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.worktime.R;

public class PlanillaTabActivity extends AbstractTabActivity {
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_abstract);
		setTabs() ;		
	}
	
	private void setTabs()
	{
		if(ContextTest.idxTipoEntradaIngreso ==0){
			addTab("Leer", 0,PlanillaEntradaManualActivity.class);
			addTab("Detalle", 0, IngresoAsistenciaActivity.class);
			addTab("Resumen", 0,ResumenIngresoActivity.class);
		}
		
		if(ContextTest.idxTipoEntradaIngreso ==1){
			addTab("Leer", 0, PlanillaEntradaAutomaticaActivity.class);
			addTab("Detalle", 0, IngresoAsistenciaActivity.class);
			addTab("Resumen", 0,ResumenIngresoActivity.class);
		}
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
	
}
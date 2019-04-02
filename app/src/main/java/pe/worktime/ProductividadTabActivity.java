package pe.worktime;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.worktime.R;

public class ProductividadTabActivity extends AbstractTabActivity {
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


		if(ContextTest.idxTipoEntradaProductividad == 0)   //PRODUCTIVIDAD MANUAL
		{
			addTab("Leer", 0, ProductividadManualActivity.class);
			addTab("Detalle", 0, ProductividadDetalleActivity.class);
			//addTab("Detalle", 0, SalidaAsistenciaActivity.class); // CAMBIAR POR PRODUCTIVIDAD LISTA ACTIVITY
			addTab("Resumen", 0, ProductividadResumenActivity.class);
		}
		if(ContextTest.idxTipoEntradaProductividad == 1)   //PRODUCTIVIDAD AUTOMATICA
		{
			addTab("Leer", 0, ProductividadEntradaActivity.class);
			addTab("Detalle", 0, ProductividadDetalleActivity.class);
			//addTab("Detalle", 0, SalidaAsistenciaActivity.class); // CAMBIAR POR PRODUCTIVIDAD LISTA ACTIVITY
			addTab("Resumen", 0, ProductividadResumenActivity.class);
		}
	}
}
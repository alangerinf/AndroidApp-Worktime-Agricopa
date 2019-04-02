package pe.worktime;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.worktime.R;

public class SalidaTabActivity extends AbstractTabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_abstract);
		setTabs();
	}

	private void setTabs() {

			if (ContextTest.idxTipoEntradaSalida == 0 && ContextTest.idxTipoLabor == 0) { //SALIDA MANUAL HORA EFECTIVA
				addTab("Leer", 0, SalidaManualActivity.class);
				addTab("Detalle", 0, SalidaAsistenciaActivity.class);
				addTab("Resumen", 0, ResumenSalidaActivity.class);
			}
			if (ContextTest.idxTipoEntradaSalida == 1 && ContextTest.idxTipoLabor == 0) { //SALIDA AUTOMATICA HORA EFECTIVA
				addTab("Leer", 0, SalidaAutomaticoActivity.class);
				addTab("Detalle", 0, SalidaAsistenciaActivity.class);
				addTab("Resumen", 0, ResumenSalidaActivity.class);
			}

			if (ContextTest.idxTipoEntradaSalida == 0 && ContextTest.idxTipoLabor == 1) { //SALIDA MANUAL TAREA
				addTab("Leer", 0, SalidaManualTareaActivity.class);
				addTab("Detalle", 0, SalidaAsistenciaActivity.class);
				addTab("Resumen", 0, ResumenSalidaActivity.class);
			}

			if (ContextTest.idxTipoEntradaSalida == 1 && ContextTest.idxTipoLabor == 1) { //SALIDA AUTOMATICA TAREA
				addTab("Leer", 0, SalidaManualTareaActivity.class);
				addTab("Detalle", 0, SalidaAsistenciaActivity.class);
				addTab("Resumen", 0, ResumenSalidaActivity.class);
			}

	
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		EditText txtdni = (EditText) getTabHost().getCurrentView()
				.findViewById(R.id.txtDniAutomatico);
		if (txtdni == null) {
			txtdni = (EditText) getTabHost().getCurrentView().findViewById(R.id.txtDniManual);
		}
		if (txtdni != null) {
			txtdni.requestFocus();
		}
		Log.d("ERROR", "DNI: " + txtdni);
	}
}
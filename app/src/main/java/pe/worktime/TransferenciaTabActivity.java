package pe.worktime;

import android.os.Bundle;

import com.worktime.R;

public class TransferenciaTabActivity extends AbstractTabActivity {
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_abstract);
		setTabs() ;		
	}
	
	private void setTabs()
	{
		ContextTest.hInicioFin = "";
		ContextTest.dniTransferencia = "";
		ContextTest.idxTransferencia = -1;

		if(ContextTest.idxTipoEntradaTransferencia ==0){
			addTab("Leer", 0,TransferenciaManualActivity.class);
			addTab("Detalle", 0,TransferenciaAsistenciaActivity.class);
			addTab("Resumen", 0,ResumenTransferenciaActivity.class);
		}
		
		if(ContextTest.idxTipoEntradaTransferencia ==1){
			addTab("Leer", 0,TransferenciaAutomaticaActivity.class);
			addTab("Detalle", 0,TransferenciaAsistenciaActivity.class);
			addTab("Resumen", 0,ResumenTransferenciaActivity.class);
		}
	}
}

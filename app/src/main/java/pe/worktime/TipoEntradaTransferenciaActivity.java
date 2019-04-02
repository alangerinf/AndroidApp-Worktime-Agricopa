package pe.worktime;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.util.AbstractActivity;

public class TipoEntradaTransferenciaActivity extends AbstractActivity {

	public static int viewLoad = R.layout.tipo_entrada_transferencia;
	protected Button btnManual = null;
	protected Button btnAutomatica = null;
	protected Button btnMasivo = null;
	private String permiso;

	@Override
	protected void postLoadView() {
		permiso = Application.context.getUsuarioController().getSelected().getFlagautoloadmaster();

		btnManual = (Button) findViewById(R.id.btn_tipo_entrada_manual);
		btnManual.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ContextTest.idxTipoEntrada = -1;
				ContextTest.idxTipoEntradaIngreso = -1;
				ContextTest.idxTipoEntradaSalida = -1;
				ContextTest.idxTipoEntradaTransferencia = 0;
				Intent i = new Intent(getApplicationContext(),
						PlanillaAsistenciaActivity.class);
				startActivity(i);
			};
		});

		btnAutomatica = (Button) findViewById(R.id.btn_tipoEntrada_automatica);
		btnAutomatica.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ContextTest.idxTipoEntrada = -1;
				ContextTest.idxTipoEntradaIngreso = -1;
				ContextTest.idxTipoEntradaSalida = -1;
				ContextTest.idxTipoEntradaTransferencia = 1;
				Intent i = new Intent(getApplicationContext(),
						PlanillaAsistenciaActivity.class);
				startActivity(i);
			};
		});

		btnMasivo = (Button) findViewById(R.id.btn_tipoMasivo);
		btnMasivo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ContextTest.idxTipoEntrada = -1;
				ContextTest.idxTipoEntradaIngreso = -1;
				ContextTest.idxTipoEntradaSalida = -1;
				ContextTest.idxTipoEntradaTransferencia = 3;
				Intent i = new Intent(getApplicationContext(),
						PlanillaAsistenciaActivity.class);
				startActivity(i);
			};
		});

		if(permiso.equalsIgnoreCase("False")){ btnManual.setVisibility(View.GONE); }
	}
	
	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Tipo Transferencia";
	}

	@Override
	public void onBackPressed (){
			Intent itet = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
			startActivity(itet);
	}
}
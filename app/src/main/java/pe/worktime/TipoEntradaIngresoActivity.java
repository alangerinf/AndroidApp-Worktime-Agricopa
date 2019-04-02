package pe.worktime;

import pe.worktime.util.AbstractActivity;

import pe.worktime.app.Application;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.worktime.R;

public class TipoEntradaIngresoActivity extends AbstractActivity {

	public static int viewLoad = R.layout.tipo_entrada_ingreso;
	protected Button btnManual = null;
	protected Button btnAutomatica = null;
	private String permiso;

	@Override
	protected void postLoadView() {
		permiso = Application.context.getUsuarioController().getSelected().getFlagautoloadmaster();

		btnManual = (Button) findViewById(R.id.btn_tipo_entrada_manual);
		btnManual.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ContextTest.idxTipoEntrada = -1;
				ContextTest.idxTipoEntradaIngreso = 0;
				ContextTest.idxTipoEntradaSalida = -1;
				ContextTest.idxTipoEntradaTransferencia = -1;
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
				ContextTest.idxTipoEntradaIngreso = 1;
				ContextTest.idxTipoEntradaSalida = -1;
				ContextTest.idxTipoEntradaTransferencia = -1;
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
		return "Tipo Entrada";
	}
}
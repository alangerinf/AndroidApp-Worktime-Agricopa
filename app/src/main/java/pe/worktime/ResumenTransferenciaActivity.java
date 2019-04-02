package pe.worktime;

import pe.worktime.app.Application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.worktime.R;

public class ResumenTransferenciaActivity extends Activity {
	
	private TextView txtPresentes;
	private TextView txtAusentes;
	private TextView txtGrupo;
	private TextView txtActividad;
	private TextView textTitleF;
	private String p = null;
	private String au = null;
	private String p1 = null;
	private String au1 = null;
	
	private static ResumenTransferenciaActivity actual;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Put your code here
		setContentView(R.layout.resumen_salida);
		actual = this;
		txtPresentes = (TextView) findViewById(R.id.txt_nro_Asistentes);
		txtAusentes = (TextView) findViewById(R.id.txt_faltas);
		txtGrupo = (TextView) findViewById(R.id.txt_grupo);
		txtActividad = (TextView) findViewById(R.id.txt_actividad);
		textTitleF = (TextView)findViewById(R.id.textTitleF);
		textTitleF.setText("Resumen Salida");
		
		if (txtPresentes != null) {
			p = String.valueOf(Application.context.getHorasConsumidorController().getSelected().nroPresentes());
			txtPresentes.setText(p);
		}
	
		if (txtAusentes != null) {
			au = String.valueOf(Application.context.getHorasConsumidorController().getSelected().nroAusentes());
			txtAusentes.setText(au);			
		}
		if (txtGrupo != null) {
		    //txtGrupo.setText(Application.context.getGrupoController().getSelected().getGrupo());
			txtGrupo.setText(Application.context.getHorasConsumidorController().getSelected().getFecha());
		}
		
		if (txtActividad != null) {
			//txtActividad.setText(Application.context.getActividadController().getSelected().getActividad());
			txtActividad.setText(Application.context.getActividadController().getNameOrDefault(Application.context.getHorasConsumidorController().getSelected().getCodActividad()));
		}
				
		Button btn_continue = (Button) findViewById(R.id.btn_continue);
		btn_continue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				try {
					if(ContextTest.idxTipoEntradaTransferencia ==0){  //Manual
						Application.context.getHorasConsumidorController().transferir(ContextTest.dniTransferencia, ContextTest.idxTransferencia,ContextTest.hInicioFin);
					}

					if(ContextTest.idxTipoEntradaTransferencia ==1){ //Automatica
						Application.context.getHorasConsumidorController().transferir(ContextTest.dniTransferencia, ContextTest.idxTransferencia);
					}


					//Application.context.getHorasConsumidorController().updateHorasConsumidor();

					Intent i = new Intent(getApplicationContext(),
							MenuPrincipalActivity.class);
					
					startActivity(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//reloadProducts();
			if (txtPresentes != null) {
				p1 = String.valueOf(Application.context.getHorasConsumidorController().getSelected().nroPresentes());
				txtPresentes.setText(p1);
			}
			if (txtAusentes != null) {
				au1 =String.valueOf(Application.context.getHorasConsumidorController().getSelected().nroAusentes());
				txtAusentes.setText(au1);		
			}
		}
	};

	public static void reloadProducts() {
		if (actual != null) {
			actual.handler.sendEmptyMessage(0);
		}
	}
	
}

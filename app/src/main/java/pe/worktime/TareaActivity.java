package pe.worktime;

import java.util.List;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.model.entity.Actividad;
import pe.worktime.util.AbstractActivity;

public class TareaActivity extends AbstractActivity {

	public static int viewLoad = R.layout.tarea;
	protected Spinner cboActividad = null;
	private List<Actividad> lst;
	protected TextView lblNombreFundo = null;
	protected TextView lblNombreModulo = null;
	protected TextView lblNombreTurno = null;
	protected Button btnSeleccionar = null;
    protected Button btnAsistencia = null;
	protected Button btnGrupo = null;
							

	@Override
	protected void postLoadView() {		

		lblNombreFundo = (TextView) findViewById(R.id.lblFundo);
		//lblNombreFundo.setText("Fundo: "+ ContextTest.context.getSelectedFundo().getFundo());
		lblNombreModulo = (TextView) findViewById(R.id.lbl_modulo1);
		//lblNombreModulo.setText("Modulo: "+ ContextTest.context.getSelectedModulo().getModulo());
		lblNombreTurno = (TextView) findViewById(R.id.lblTurno);
		//lblNombreTurno.setText("Turno: "+ ContextTest.context.getSelectedTurno().getCod_turno());

		String[] datos;
		//lst = ContextTest.context.getLstTareas();
		int size = 0;
		size = lst.size();
		System.out.println("Actividad Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lst.get(i).getActividad();
			Log.e("ACTIVIDAD", datos[i] = lst.get(i).getActividad());
		}
		// cboModulo = new Spinner(this);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);

		cboActividad = (Spinner) findViewById(R.id.cboTarea);

		adaptador
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboActividad.setAdapter(adaptador);

		cboActividad
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {
						Log.e("ITEM SELECCIONADO", lst.get(position)
								.getCodActividad());
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
		loadPrincipal();
	}
	
	protected void loadPrincipal(){
		
		btnSeleccionar = (Button) findViewById(R.id.btn_Seleccionar);
		btnSeleccionar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//ContextTest.context.seleccionarTarea(cboActividad.getSelectedItemPosition());
				Intent i = new Intent(getApplicationContext(),
						TareaSeleccionActivity.class);
				startActivity(i);
			}
		});
	}
	
	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Tarea";
	}

}

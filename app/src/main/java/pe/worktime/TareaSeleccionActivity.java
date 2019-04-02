package pe.worktime;

import java.util.List;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.model.entity.Grupo;
import pe.worktime.util.AbstractActivity;

public class TareaSeleccionActivity extends AbstractActivity {

	public static int viewLoad = R.layout.tareaseleccion;
	protected Spinner cboGrupo = null;
	private List<Grupo> lst;
	protected TextView lblTareaSeleccionada = null;
	private int indiceSupervisor = MainActivity.indiceLoginSupervisor;
	
	@Override
	protected void postLoadView() {
		
		lblTareaSeleccionada = (TextView) findViewById(R.id.lblTarea);
		//lblTareaSeleccionada.setText("Tarea : "+ ContextTest.context.getSelectedActividad().getActividad());
		System.out.println("INDICE SUPERVISOR" + indiceSupervisor);
		
		String[] datos;
		//lst = ContextTest.context.getSelectedSupervisor().getGrupos();
		int size = 0;
		size = lst.size();
		System.out.println("Size:" + size );
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lst.get(i).getGrupo();
		}
		//cboModulo = new Spinner(this);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);

		cboGrupo = (Spinner) findViewById(R.id.cboGrupo);
		
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboGrupo.setAdapter(adaptador);

		cboGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							android.view.View v, int position, long id) {
						Log.e("ITEM SELECCIONADO", lst.get(position)
								.getGrupo());
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
			//loadPrincipal();
		
	}
	
	/*protected void loadPrincipal(){
		
		Button btnSeleccionar = (Button) findViewById(R.id.btn_Asistencia);
		btnSeleccionar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ContextTest.context.seleccionarGrupo(cboGrupo.getSelectedItemPosition());
				Intent i = new Intent(getApplicationContext(),
						AsistenciaTabActivity.class);
				startActivity(i);
			}
		});
	}*/
	
	
	@Override
	protected int getViewId() {
		// TODO Auto-generated method stub
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		// TODO Auto-generated method stub
		return "Tarea Seleccionada";
	}
}
